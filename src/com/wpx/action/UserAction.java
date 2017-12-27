package com.wpx.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wpx.dao.UserDao;
import com.wpx.model.User;
import com.wpx.util.DbUtil;
import com.wpx.util.NavUtil;
import com.wpx.util.ResponseUtil;
import com.wpx.util.StringUtil;

public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private  User user;
	private String error;
	private String username;
	
	private int userId;
	private String password;
	
	private String mainPage; 
	private String navCode;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	


	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	public String login(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(user.getUserName())||StringUtil.isEmpty(user.getPassword())){
			error="用户名或密码为空！";
			return ERROR;
		}
	
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				error="用户名或密码错误！";
				return ERROR;
			}else{
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String preSave(){
		navCode=NavUtil.getNavgation("系统管理", "修改密码");
		mainPage="user/modifyPassword.jsp";
		return SUCCESS;
	}
	
	public String save(){
		User user=new User();
		user.setUserId(userId);
		user.setPassword(password);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int updateNums=userDao.modifyPassword(con, user);
			JSONObject resultJson=new JSONObject();
			if(updateNums>0){
				resultJson.put("success", true);
			}else{
				resultJson.put("errorMsg", "修改密码失败");
			}
			ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String logOut(){
		HttpSession session=request.getSession();
		session.removeAttribute("currentUser");
		return "logOut";
	}
	
	public String vali(){
		Connection con=null;
		
		try {
			con=dbUtil.getCon();
			System.out.println(username);
			 User user = userDao.getByName(con,username);
			HttpServletResponse response=ServletActionContext.getResponse();
			if(user==null){
				ResponseUtil.write("<font color='red'>该用户名不存在</font>", response);
			}else{
				ResponseUtil.write("<font color='green'>用户名验证成功</font>", response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}  
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}	
	
}
