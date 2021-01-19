package com.isd.entity.report;

import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.isd.util.C;

public class HtmlToPdf {  
    /** 
     * html转pdf 
     * @param srcPath html路径，可以是硬盘上的路径，也可以是网络路径 
     * @param destPath pdf保存路径 
     * @return 转换成功返回true 
     */  
    public String convert(String src, String dest){  
		String path = "/report/"+C.dateToString(new Date(), "yyyy") + "/" + C.dateToString(new Date(), "MM") + "/";

		// 得到目录的物理路径并创建目录
		String absolute = ServletActionContext.getServletContext().getRealPath(path);
		File absolutePath = new File(absolute);
		if (!absolutePath.isDirectory()) {
			try {
					absolutePath.mkdirs();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
		}
		
		dest = new Date().getTime()+"_"+dest;
    	
   
        String cmd = getCommand(src, absolute+"/"+dest);  

          
        try{  
            Process proc = Runtime.getRuntime().exec(cmd.toString());  
            //HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());  
            //HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());  
            //error.start();  
            //output.start();  
            proc.waitFor();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
        return path+dest;  
    } 
    
    private String getCommand(String htmlName, String pdfName){  
    	String option = " --javascript-delay 60000 --quiet ";
        String system = System.getProperty("os.name");  
        if(system.indexOf("indow")>-1)   //Window系统  
            return "\"C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf.exe\" " + option + "\""+htmlName + "\" " + pdfName;  
        else  if("Linux".equalsIgnoreCase(system))  //Linux 系统  
            return "/usr/local/bin/wkhtmltopdf " + option + "\""+htmlName + "\" " + pdfName;  
        return ""   ;  
    }      
}  
