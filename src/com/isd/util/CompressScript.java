package com.isd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class CompressScript {

	//压缩指定的脚本文件或css文件进行压缩
	public String compressFile(String filename) {
		// TODO Auto-generated method stub
		//执行时，将文件备份到 /WEB-INF/backup的相应目录下，然后对文件进行处理后，覆盖当前位置的文件
		filename = filename.startsWith("/")?filename:"/"+filename;
		String charset = "UTF-8";
		String src  = filename;
		String dest = "/WEB-INF/backup/"+C.MD5(filename);
		
		String srcFullname  = ServletActionContext.getServletContext().getRealPath(src);
		String destFullname = ServletActionContext.getServletContext().getRealPath(dest);

		File srcFile  = new File(srcFullname); 
		File destFile = new File(destFullname);
		File destPath = new File(destFullname.substring(0, destFullname.lastIndexOf(File.separator)));
		
		//如果需要，创建目标目录
		if(!destPath.exists()){
			destPath.mkdirs();
		}

		Writer out = null;
		Reader in  = null;
		
		//实施压缩，压缩后的文件直接覆盖当前文件
		try{
			//是否需要压缩
			boolean todo = false;
			
			//如果源文件不存在，则不做任何处理
			if(!srcFile.exists()){
				todo = false;
			}
			//如果备份文件不存在，需要备份；如果备份文件比当前文件时间早，说明当前文件做过修改，需要重新备份
			else if(!destFile.exists() || destFile.lastModified() < srcFile.lastModified()){
				C.writeFile(C.readfile(srcFullname), destFullname);
				todo = true;
			} 
			
			if(todo == true){
				if(src.toLowerCase().endsWith(".js")){
					in = new InputStreamReader(new FileInputStream(srcFile), charset);  
					JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
	
	                    public void warning(String message, String sourceName,
	                            int line, String lineSource, int lineOffset) {
	                        if (line < 0) {
	                            System.err.println("\n[WARNING] " + message);
	                        } else {
	                            System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
	                        }
	                    }
	
	                    public void error(String message, String sourceName,
	                            int line, String lineSource, int lineOffset) {
	                        if (line < 0) {
	                            System.err.println("\n[ERROR] " + message);
	                        } else {
	                            System.err.println("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
	                        }
	                    }
	
	                    public EvaluatorException runtimeError(String message, String sourceName,
	                            int line, String lineSource, int lineOffset) {
	                        error(message, sourceName, line, lineSource, lineOffset);
	                        return new EvaluatorException(message);
	                    }
	                });
	
	                // Close the input stream first, and then open the output stream,
	                // in case the output file should override the input file.
	                in.close(); 
	                in = null;
	
	                out = new OutputStreamWriter(new FileOutputStream(srcFile), charset);
	
	                compressor.compress(out, -1, true, false, true, false);
	                
	                out.close();
	                out = null;
				}else if(src.toLowerCase().endsWith(".css")){
					in = new InputStreamReader(new FileInputStream(srcFile), charset);  
	                CssCompressor compressor = new CssCompressor(in);

	                // Close the input stream first, and then open the output stream,
	                // in case the output file should override the input file.
	                in.close(); 
	                in = null;

                    out = new OutputStreamWriter(new FileOutputStream(srcFile), charset);

	                compressor.compress(out, -1);
	                out.close();
	                out = null;
				}
				//修改备份文件的时间戳，避免重复的压缩
				destFile.setLastModified(srcFile.lastModified()+1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		HttpServletRequest request = ServletActionContext.getRequest();
		String fullUrl=request.getRequestURL().toString();
		String url = fullUrl.substring(0, fullUrl.lastIndexOf("/", fullUrl.lastIndexOf("/")-1)) + src;
		return url;
	}
	
	//将目录下的所有脚本文件或css文件进行压缩
	public boolean compressPath(String path){
		path = path.startsWith("/")?path:"/"+path;
		String pathFullname = ServletActionContext.getServletContext().getRealPath(path);
		File pathFile = new File(pathFullname);
		if(pathFile.isDirectory()){
			File [] files = pathFile.listFiles();
			for( int i = 0; i < files.length; i ++){
				File file = files[i];
				String filename = file.getName();
				if(file.isFile() && filename.indexOf("min")<0 && (filename.toLowerCase().endsWith(".js")||filename.toLowerCase().endsWith(".css"))){
					this.compressFile(path+File.separator+filename);
				}else if(file.isDirectory()){
					compressPath(path+File.separator+filename);
				}
			}
		}
		return true;
	}
}
