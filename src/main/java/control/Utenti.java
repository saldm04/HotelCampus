package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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
 * Servlet implementation class Utenti
 */
@WebServlet("/admin/Utenti")
public class Utenti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtenteDAO utente = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
		
		if(request.getParameter("action") != null) {
			if(request.getParameter("action").equals("updateAdminStatus")) {
			
				Utente admin = (Utente)request.getSession().getAttribute("utente");
			
				if(admin.isAdmin()) {
					String email = request.getParameter("email");
					boolean newAdminStatus = Boolean.parseBoolean(request.getParameter("isAdmin"));
				
		            try {
		                Utente utenteToUpdate = utente.doRetrieveByKey(email);
		                if (utenteToUpdate != null) {
		                    utenteToUpdate.setAdmin(newAdminStatus);
		                    utente.updateUtente(utenteToUpdate);
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
				}
			
			}
			
			if(request.getParameter("action").equals("delete")) {
				
				Utente admin = (Utente)request.getSession().getAttribute("utente");
			
				if(admin.isAdmin()) {
					String email = request.getParameter("email");
				
		            try {
		              
		                 utente.doDelete(email);
		                 if(admin.getEmail().equals(email)){
		                	 response.sendRedirect(getServletContext().getContextPath()+"/common/Logout");
		                	 return;
		                 }
		                
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
				}
			
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/areaRiservata.jsp?edit=clienti");
		
		try {
			Collection<Utente> utenti = utente.doRetrieveAll("");
			request.setAttribute("utenti", utenti);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}
}
