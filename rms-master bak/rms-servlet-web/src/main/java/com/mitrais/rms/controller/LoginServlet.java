package com.mitrais.rms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("userpass");
        User user = new User(null, username, password, null);
        req.setAttribute("user", user);
        
        Boolean isError = false;
        if(username == null || username.trim().equals("")) {
        	req.setAttribute("validation", "Username cannot be empty");
        	isError = true;
        }
        else if(password == null || password.trim().equals("")) {
        	req.setAttribute("validation", "Password cannot be empty");
        	isError = true;
        }
        
        if(!isError) {
	        UserDao userDao = UserDaoImpl.getInstance();
	        Optional<User> userData = userDao.findByUserNameAndPassword(username, password);
	        
	        if(!userData.isPresent()) {
	        	req.setAttribute("validation","Invalid username or password");
	        	isError = true;
	        }
	        else {
	        	HttpSession session = req.getSession();
	        	session.setAttribute("loggedUser", user);
	        }
        }
        
        if(isError) {
        	// if error, display validation
        	String path = getTemplatePath(req.getServletPath());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
	        requestDispatcher.forward(req, resp);
        }
        else {
        	resp.sendRedirect("users/list");
        }
    }
}
