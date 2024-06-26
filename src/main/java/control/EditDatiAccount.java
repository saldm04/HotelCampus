package control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

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
 * Servlet implementation class EditDatiAccount
 */
@WebServlet("/common/editDatiAccount")
public class EditDatiAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteDAO utenteDAO = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		Utente user = (Utente) request.getSession().getAttribute("utente");
		Utente newUser = new Utente();
		RequestDispatcher rd = request.getRequestDispatcher("/common/areaRiservata.jsp?edit=dati");
		
		if(user.getPassword().equals(toHash(oldPass))) {
			newUser.setEmail(user.getEmail());
			newUser.setNome(user.getNome());
			newUser.setCognome(user.getCognome());
			newUser.setDataNascita(user.getDataNascita());
			newUser.setAdmin(user.isAdmin());
			newUser.setNazionalità(user.getNazionalità());
			String pass = toHash(newPass);
			newUser.setPassword(pass);
			user.setPassword(pass);
		}
		int value=0;
		try {
			value=utenteDAO.updateUtente(newUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(value==1){
			request.setAttribute("esitoPositivo",
					"La password è stata modificata con successo");
		}else{
			request.setAttribute("esitoNegativo",
					"La password non è stata modificata");
		}
		
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/homepage.jsp");
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
