package com.isd.action.mining;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.manage.Message;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.User;
import com.isd.service.mining.MessageService;
import com.isd.service.mining.NetworkService;
import com.isd.service.mining.UserService;
import com.isd.util.C;
import com.isd.util.SendMail;


public class UserAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8376350115422424187L;
	
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private NetworkService networkService;
	
	private User user;
	private String ensure;
	private Integer id;
	private String verifyCode;

	private String rememberme;

	//登录
	public String login(){
		String result = userService.login(user.getName(), user.getPassword(), rememberme);
		ctx.put("text", result);
		return TEXT;
	}
	
	//登出
	public String logout(){
		session.removeAttribute(C.SESSION_USER);
		session.removeAttribute(C.SESSION_NETWORK);
		C.removeCookie(C.COOKIE_TOKEN);
		return HOME;
	}
	
	//从Session中获得user
	public String info(){ 
		return SUCCESS;
	}

	//注册用户页面
	public String index(){
		return SUCCESS;
	}
	
	//添加用户
	public String add(){
		String checkCode = (String)session.getAttribute("verifyCode");
		if(!verifyCode.equals(checkCode)){
			ctx.put("text", "验证码不正确");			
		} else {
			user.setStatus(0);
			//设置为demo账户的访客
			user.setType(0);
			user.setNetworkid(C.DEFAULT_NETWORK);
			user.setCtime(new Date());
			user.setUtime(new Date());
			userService.addUser(user);
			ctx.put("text", "SUCCESS");
		}
		return TEXT;		
	}
	
	//检测用户名是否已注册
	public String checkname(){
		Integer result = userService.checkName(user.getName());
		ctx.put("text", result);
		return TEXT;
	}
	
	//验证用户名跟邮箱
	public String checkuser(){
		String checkCode = (String)session.getAttribute("verifyCode");
		if(!verifyCode.equals(checkCode)){
			ctx.put("text", "验证码不正确");			
		}else {			
			int result = userService.checkUser(user.getName(), user.getEmail());			
			
			if(result==0){
				ctx.put("text", "用户名或邮箱不正确");				
			}else{
				List<User> list = userService.findByProperty("name", user.getName());
				if(list==null||list.size()<1){
					ctx.put("text", "用户名或邮箱不正确");			
				} else {
					User suser = list.get(0);
					session.setAttribute(C.SESSION_TMPUSER, suser);
					ctx.put("text", "SUCCESS");			
				}
			}
		}
		return TEXT;
	}
	
	//验证用户名邮箱页面
	public String getpwd(){
		return SUCCESS;
	}
	
	//创建验证码
	public String getcode() throws Exception {  
		
        response.setContentType("image/jpeg");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
      
        // 在内存中创建图象   
        int width = 70, height = 22;  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        // 获取图形上下文   
        Graphics g = image.getGraphics();  
        // 生成随机类   
        Random random = new Random();  
        // 设定背景色   
        g.setColor(getRandColor(200, 250));  
        g.fillRect(0, 0, width, height);  
        // 设定字体   
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));  
        // 画边框   
        g.setColor(getRandColor(160, 200));  
        g.drawRect(0, 0, width - 1, height - 1);  
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到   
        g.setColor(getRandColor(160, 200));  
        for (int i = 0; i < 155; i++) {  
            int x = random.nextInt(width);  
            int y = random.nextInt(height);  
            int xl = random.nextInt(12);  
            int yl = random.nextInt(12);  
            g.drawLine(x, y, x + xl, y + yl);  
        }  
        // 取随机产生的认证码(4位数字)   
        String sRand = "";  
        for (int i = 0; i < 4; i++) {  
            String rand = String.valueOf(random.nextInt(10));  
            sRand += rand;  
            // 将认证码显示到图象中   
            g.setColor(new Color(20 + random.nextInt(110), 20 + random  
                    .nextInt(110), 20 + random.nextInt(110)));  
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成   
            g.drawString(rand, 13 * i + 14, 20);  
        }  
  
        // 将认证码存入Session
        session.setAttribute("verifyCode", sRand);  
  
        // 图象生效   
        g.dispose();  
        // 输出图象到页面   
        ImageIO.write(image, "JPEG", response.getOutputStream());  
        return null;  
    }  
	
	//重置密码页面
	public String resetpwd(){
		return SUCCESS;	
	}
	

	public String waiting(){
		return SUCCESS;
	}
	
	//发送修改密码邮件
	public String updatepwd(){
		User suser = (User)session.getAttribute(C.SESSION_TMPUSER);
		if(suser.getPassword().equals(user.getPassword())){
			ctx.put("text", "新密码与原密码一致，请更换");		
		}else{
			suser.setPassword(user.getPassword());
			userService.updateUser(suser);
			
			byte[] b = Base64.encodeBase64(suser.getId().toString().getBytes(), true);

			String content= "请在浏览下打开以下链接来确认修改密码 ：<a href='"+
			//"http://localhost:8080/mining/user/ensure.shtml?ensure="+ 
			"http://dig88.cn/user/ensure.shtml?ensure="+ 
			new String(b) + "'>"
			//"http://localhost:8080/mining/user/ensure.shtml?ensure="+ 
			+"http://dig88.cn/user/ensure.shtml?ensure="+ 
			new String(b) +"</a> ， 确认后您可以用新密码进行登录。";
			
			SendMail sm = new SendMail("smtp.dig24.cn");
			
			sm.sendMail(C.MAIL_FROM,C.MAIL_FROMNAME, suser.getEmail(), null, null, "修改密码确认", content, C.MAIL_ACCOUNT, C.MAIL_PASSOWRD, "");
			
			 
			ctx.put("text", "SUCCESS");			
		}
		return TEXT;
	}
	
	//接收确认邮件
	/*public String ensure(){
		byte[] b = Base64.decodeBase64(ensure.getBytes());
		int id = Integer.parseInt(new String(b));
		User tuser = userService.findById(id);
		if(tuser != null){
			String pwd = tuser.getTemp();
			tuser.setPassword(pwd);
			tuser.setTemp("");
			userService.updateUser(tuser);
		}
		return  REDIRECT ;
	}*/
	
	//修改
	public String update(){
		User suser = (User)session.getAttribute(C.SESSION_USER);
		suser.setPassword(user.getPassword());
		suser.setMobile(user.getMobile());
		suser.setEmail(user.getEmail());
		userService.updateUser(suser);
		session.setAttribute(C.SESSION_USER, suser);
		ctx.put("redirect", "user/info.shtml");
		return REDIRECT;
	}
	
	public String message(){
		List<Message> list = messageService.findById(mynetwork.getId() ,pageindex*5, 5);
		Integer total = messageService.countById(mynetwork.getId());
		ctx.put("list", list);
		ctx.put("count", total.toString());	
		return  SUCCESS;
	}
	
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色   
        Random random = new Random();  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }

	//用户管理首页
	public String manage_index(){
		if(user!=null&&user.getName()!=null){
			user.setName(C.getURLChinese(user.getName()));
		}
		List<User> list = userService.list(user, pageindex*pagesize, pagesize);
		HashMap<Integer, Network> hash = networkService.hash();
		Integer total = userService.count(user);
		ctx.put("list", list);
		ctx.put("hash", hash);
		ctx.put("total", total.toString());
		return SUCCESS;
	}

	//获得用户信息
	public String manage_userinfo(){
		Integer id = user.getId();
		user = userService.findById(id);
		if(user.getExpire()==null){
			//缺省30天的试用期
			user.setExpire(C.stringToDate(C.getDate(-30)));
		}
		//账户列表
		List<Network> list = networkService.list(0, 999999);
		ctx.put("list", list);
		return SUCCESS;
	}

	//审核用户
	public String manage_review(){
		Integer id = user.getId();
		if(id!=null&&id!=0){
			User u = userService.findById(id);
			u.setStatus(user.getStatus());
			u.setExpire(user.getExpire());
			u.setType(user.getType());
			u.setNetworkid(user.getNetworkid());
			userService.updateUser(u);

		}
		ctx.put("text", "success");
		return TEXT;
	}

	public String manage_delete(){
		userService.delete(user.getId());
		ctx.put("text", "success");
		return TEXT;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEnsure() {
		return ensure;
	}

	public void setEnsure(String ensure) {
		this.ensure = ensure;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getRememberme() {
		return rememberme;
	}

	public void setRememberme(String rememberme) {
		this.rememberme = rememberme;
	}
}
