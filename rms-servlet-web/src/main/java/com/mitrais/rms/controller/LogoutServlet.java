package com.mitrais.rms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.rms.model.User;

@WebServlet("/logout")
public class LogoutServlet extends AbstractController {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	session.invalidate();
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
	}
}
