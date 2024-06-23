package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/homepage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<String> errors = new ArrayList<>();
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");

		
		if(username == null || username.trim().isEmpty()) {
			errors.add("Il campo username non può essere vuoto!");
		}
        if(password == null || password.trim().isEmpty()) {
        	errors.add("Il campo password non può essere vuoto!");
		}
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return;
        }
        
        username = username.trim();
        password = toHash(password.trim());
       
		
        UtenteDAO account = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        Utente user = null;
		try {
			user = account.doRetrieveByKey(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		
		if(password.equals(user.getPassword()) && user.isAdmin() && user.getEmail()!=""){
        	request.getSession().setAttribute("utente", user);
			response.sendRedirect("homepage.jsp");
		} else if (password.equals(user.getPassword()) && !user.isAdmin() && user.getEmail()!=""){
			request.getSession().setAttribute("utente", user);
			response.sendRedirect("homepage.jsp");
		}else{
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
		}
	}
	
	
	private static String toHash(String password) {
		String hashString = null;
		
		try {
			
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			
			hashString = "";
			
			for (int i = 0; i < hash.length; i++) {
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3);
			}
			
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return hashString;
	}
	
}
