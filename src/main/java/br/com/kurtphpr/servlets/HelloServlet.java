package br.com.kurtphpr.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6370170913284932914L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String nome  = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		resp.getWriter().println("Olá mundo servlet!" + nome + " " + sobrenome);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String nome  = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		resp.getWriter().println("Olá mundo servlet!" + nome + " " + sobrenome);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.getWriter().println("Olá mundo do PUT!");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.getWriter().println("Olá mundo do DELETE" );
	}	
}
