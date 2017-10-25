package com.neuedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
/**
 * ���ڵ�¼
 * @author neuedu
 *
 */
@WebServlet(urlPatterns="/login.do",loadOnStartup=1)
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 8606608159237396153L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserService userservice = new UserServiceImpl();
		User user = userservice.selectUserByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			//�ض���emplist.do�����
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			resp.addCookie(cookie);
			resp.sendRedirect("emplist.do");
		}else{
			//��¼ʧ���ض��򵽵�¼ҳ��
			resp.sendRedirect("loginview.do");
		}
	}
	
}
