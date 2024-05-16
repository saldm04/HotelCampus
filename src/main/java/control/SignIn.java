package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
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
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");
		String nazionalita = request.getParameter("nazionalita");
		
		List<String> errors = new ArrayList<>();
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("signIn.jsp");
		
    	
    	
		if(email == null || email.trim().isEmpty()) {
			errors.add("Il campo email non può essere vuoto!");
		}
        if(password == null || password.trim().isEmpty()) {
        	errors.add("Il campo password non può essere vuoto!");
		}
        if(nome == null || nome.trim().isEmpty()) {
        	errors.add("Il campo nome non può essere vuoto!");
		}
        if(cognome == null || cognome.trim().isEmpty()) {
        	errors.add("Il campo cognome non può essere vuoto!");
		}
        if(dataNascita == null || dataNascita.trim().isEmpty() ) {
        	errors.add("Il campo dataNascita non può essere vuoto!");
		}
        if(nazionalita == null || nazionalita.trim().isEmpty()) {
        	errors.add("Il campo nazionalità non può essere vuoto!");
		}
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return;
        }
        
        email = email.trim();
        password = toHash(password.trim());
        nome = nome.trim();
        cognome = cognome.trim();
        dataNascita = dataNascita.trim();
        nazionalita = nazionalita.trim();
        
        UtenteDAO account = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        Utente user = new Utente();
        
		try {
			user.setNome(nome);
			user.setCognome(cognome);
			user.setEmail(email);
			user.setPassword(password);
			user.setDataNascita(Date.valueOf(dataNascita));
			user.setNazionalità(nazionalita);
			
			account.doSave(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		response.sendRedirect("homepage.jsp");
       
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
