package com.mitrais.rms.controller;

import com.mitrais.rms.dao.MasterDataDao;
import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.MasterDataDaoImpl;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.helper.Message;
import com.mitrais.rms.model.MasterData;
import com.mitrais.rms.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jpath.adapter.Convert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;

@WebServlet("/users/*")
public class UserServlet extends AbstractController {
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getTemplatePath(req.getServletPath() + req.getPathInfo());

		if ("/list".equalsIgnoreCase(req.getPathInfo())) {
			HttpSession session = req.getSession();
			session.setAttribute("user", null);
			
			UserDao userDao = UserDaoImpl.getInstance();
			List<User> users = userDao.findAll();
			req.setAttribute("users", users);
		} else if (req.getPathInfo().toLowerCase().contains("/form")) {
			displayEmployeeData(req, resp);
		}
		else if (req.getPathInfo().toLowerCase().contains("/delete")) {
			deleteEmployeeData(req, resp);
			path = "list";
		}

		RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().toLowerCase().contains("/form")) {
			if (req.getParameter("id") == "")
				createEmployee(req, resp);
			else
				employeeEditSave(req, resp);
		}
	}
	
	private void deleteEmployeeData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("id") == "" || req.getParameter("id") == null) {
			Message msg = new Message(Message.MESSAGE_TYPE.error, "User does not exist");
			resp.addCookie(msg.setToCookie(req.getCookies()));
		}
		else 
		{
			long id = Long.parseLong(req.getParameter("id"));
			
			UserDao userDao = UserDaoImpl.getInstance();
			userDao.delete(id);
			
			Message msg = new Message(Message.MESSAGE_TYPE.information, "User has been deleted");
			resp.addCookie(msg.setToCookie(req.getCookies()));
		}
	}
	
	private void displayEmployeeData(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getParameter("id") != "" && req.getParameter("id") != null) {
			long id = Long.parseLong(req.getParameter("id"));
			
			UserDao userDao = UserDaoImpl.getInstance();
			Optional<User> user = userDao.find(id);

			if (user.isPresent()) {
				HttpSession session = req.getSession();
	        	session.setAttribute("user", user.get());
			} else {
				Message msg = new Message(Message.MESSAGE_TYPE.error, "User does not exist");
				resp.addCookie(msg.setToCookie(req.getCookies()));
			}
		}
		
		MasterDataDao scDao = MasterDataDaoImpl.getInstance();
		List<MasterData> MasterDataList = scDao.findByCodeType(MasterData.CODE_TYPE.user_type.toString());
		req.setAttribute("MasterDataList", MasterDataList);
	}

	private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String usertype = req.getParameter("usertype");

		User user = new User(null, username, password, usertype);
		HttpSession session = req.getSession();
    	session.setAttribute("user", user);
    	
		if (mandatoryInputValidation(user, req, resp)) {
			UserDao userDao = UserDaoImpl.getInstance();
			userDao.save(user);

			Message msg = new Message(Message.MESSAGE_TYPE.information, "User has been successfully created");
			resp.addCookie(msg.setToCookie(req.getCookies()));
			resp.sendRedirect("form?id=" + user.getId());
		} else {
			resp.sendRedirect("form");
		}
	}

	private void employeeEditSave(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long id = req.getParameter("id") == "" || req.getParameter("id") == null ? 0
				: Long.parseLong(req.getParameter("id"));

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String usertype = req.getParameter("usertype");

		User user = new User(id, username, password, usertype);

		if (mandatoryInputValidation(user, req, resp)) {
			UserDao userDao = UserDaoImpl.getInstance();
			userDao.update(user);

			Message msg = new Message(Message.MESSAGE_TYPE.information, "User has been successfully edited");
			resp.addCookie(msg.setToCookie(req.getCookies()));
		}

		resp.sendRedirect("form?id=" + user.getId());
	}

	private boolean mandatoryInputValidation(User user, HttpServletRequest req, HttpServletResponse resp) {
		if (user.getUserName().trim().equals("")) {
			Message msg = new Message(Message.MESSAGE_TYPE.error, "User name cannot be empty");
			resp.addCookie(msg.setToCookie(req.getCookies()));
			return false;
		} else if (user.getPassword().trim().equals("")) {
			Message msg = new Message(Message.MESSAGE_TYPE.error, "Password cannot be empty");
			resp.addCookie(msg.setToCookie(req.getCookies()));
			return false;
		}

		return true;
	}
}
