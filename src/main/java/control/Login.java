package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<String> errors = new ArrayList<>();
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("loginProva.jsp");

		
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
        password = password.trim();
		
        UtenteDAO account = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        Utente user = null;
		try {
			user = account.doRetrieveByKey(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*
         * AGGIUNGERE CRITTOGRAFIA SHA-512 PASSWORD
         */
		
		if(password.equals(user.getPassword()) && user.isAdmin() && user.getEmail()!=""){
        	request.getSession().setAttribute("utente", user);
			response.sendRedirect("admin/protected.jsp");
		} else if (password.equals(user.getPassword()) && !user.isAdmin() && user.getEmail()!=""){
			request.getSession().setAttribute("utente", user);
			response.sendRedirect("common/protected.jsp");
		}else{
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
		}
	}
}
