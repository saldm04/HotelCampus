package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Camera;
import model.CameraDAO;
import model.Servizio;
import model.ServizioDAO;

/**
 * Servlet implementation class AggiungiAlCarrello
 */
@WebServlet("/GestisciCarrello")
public class GestisciCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String servizio = request.getParameter("servizio");
		String camera = request.getParameter("camera");
		HttpSession sessione = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("carrello.jsp");
		Integer numeroMassimoTotaleOspiti = (Integer) sessione.getAttribute("numeroMassimoTotaleOspiti");
		
		if(servizio != null && op != null && camera==null){
			Integer value = Integer.parseInt((String) sessione.getAttribute(servizio));
			if(value!=null) {
				if(op.equals("aggiungi") && value<numeroMassimoTotaleOspiti){
					value++;
				}else if(op.equals("rimuovi") && value>0){
					value--;
				}
				sessione.setAttribute(servizio, value.toString());
			}
		}else if(camera!=null && servizio == null && op == null){
			ArrayList<Camera> camere = (ArrayList<Camera>) sessione.getAttribute("CarrelloCamere");
			if(camere!=null){
				camere.removeIf(c -> c.getNumero() == Integer.parseInt(camera));
				sessione.setAttribute("CarrelloCamere", camere);
			}
		}
		
		rd.forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("prenotazione.jsp");
		ServizioDAO servizioDAO = new ServizioDAO((DataSource) getServletContext().getAttribute("DataSource"));
		HttpSession sessione = request.getSession();
		
		if(sessione.getAttribute("Servizi")==null){
			try {
				ArrayList<Servizio> s = (ArrayList<Servizio>) servizioDAO.doRetrieveAll("");
				s.removeIf(ser -> ser.isDisponibile()==false);
				sessione.setAttribute("Servizi", s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sessione.getAttribute("CarrelloCamere")==null){
			sessione.setAttribute("CarrelloCamere", new ArrayList<Camera>());
		}
		
		if(request.getParameter("numeroCamera")!=null){
			CameraDAO cameraDAO = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
			ArrayList<Camera> camere = (ArrayList<Camera>) sessione.getAttribute("CarrelloCamere");
			try {
				Camera c = cameraDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("numeroCamera")));
				if(c.isDisponibile())
					camere.add(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		rd.forward(request, response);
	}
}
