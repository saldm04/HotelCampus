package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.print.attribute.standard.DateTimeAtCompleted;
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
import model.CameraPrenotata;
import model.CameraPrenotataDAO;
import model.Prenotazione;
import model.PrenotazioneDAO;
import model.Servizio;
import model.ServizioDAO;
import model.ServizioPrenotato;
import model.ServizioPrenotatoDAO;
import model.Utente;

/**
 * Servlet implementation class Acquista
 */
@WebServlet("/common/Acquista")
public class Acquista extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/homepage.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		ArrayList<Camera> camereInSessione = (ArrayList<Camera>) sessione.getAttribute("CarrelloCamere");
		ServizioDAO servizioDAO = new ServizioDAO((DataSource) getServletContext().getAttribute("DataSource"));
		CameraDAO cameraDAO = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ServizioPrenotatoDAO servizioPrenotatoDAO = new ServizioPrenotatoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		CameraPrenotataDAO cameraPrenotataDAO = new CameraPrenotataDAO((DataSource) getServletContext().getAttribute("DataSource"));
		PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ArrayList<Servizio> servizi = new ArrayList<Servizio>();
		
		try {
			Integer totaleCarrello = Integer.parseInt(request.getParameter("totaleCarrello"));
			Prenotazione prenotazione = new Prenotazione();
			String strInizioPre = (String) sessione.getAttribute("dataInizioPrenotazione");
			String strFinePre = (String) sessione.getAttribute("dataFinePrenotazione");
			if(strFinePre!=null && strInizioPre!=null){
				prenotazione.setDataInizio(Date.valueOf(strInizioPre));
				prenotazione.setDataFine(Date.valueOf(strFinePre));
			}
			prenotazione.setDataPrenotazione(new Timestamp(System.currentTimeMillis()));
			if(totaleCarrello!=null) {
				prenotazione.setImporto(totaleCarrello);
			}
			Utente user = (Utente) sessione.getAttribute("utente");
			prenotazione.setUtente(user.getEmail());
			int idPrenotazione = prenotazioneDAO.doSaveReturnKey(prenotazione);
			
			
			servizi = (ArrayList<Servizio>) servizioDAO.doRetrieveAll("");
			for(Servizio servizio : servizi){
				String qtServiziStr = request.getParameter(servizio.getNome().replaceAll("\\s+", ""));
				Integer qtServizi = 0;
				if(qtServiziStr!=null){
					qtServizi = Integer.parseInt(qtServiziStr);
				}
				if(qtServizi>0 && servizio.isDisponibile()==false){
					prenotazioneDAO.doDelete(idPrenotazione);
					for(Servizio s : servizi){
						sessione.removeAttribute("att"+s.getNome().replaceAll("\\s+", ""));
					}
					sessione.removeAttribute("numeroMassimoTotaleOspiti");
					sessione.removeAttribute("dataFinePrenotazione");
					sessione.removeAttribute("dataInizioPrenotazione");
					sessione.removeAttribute("Servizi");
					sessione.removeAttribute("CarrelloCamere");
					RequestDispatcher rd = request.getRequestDispatcher("../carrello.jsp");
					request.setAttribute("erroreAcquisto", "Ci dispiace! Il servizio \""+servizio.getNome()+"\" che "
							+ "hai provato ad acquistare non è più disponibile.");
					rd.forward(request, response);
					return;
				}else if(qtServizi>0 && servizio.isDisponibile()==true){
					ServizioPrenotato servizioPrenotato = new ServizioPrenotato();
					servizioPrenotato.setPrenotazione(idPrenotazione);
					servizioPrenotato.setServizio(servizio.getNome());
					servizioPrenotato.setCosto(servizio.getCosto()*qtServizi);
					servizioPrenotatoDAO.doSave(servizioPrenotato);
				}
			}
			
			
			for(Camera cameraCarello : camereInSessione){
				Camera cameraDatabase = cameraDAO.doRetrieveByKey(cameraCarello.getNumero());
				if(cameraDatabase.isDisponibile()==false){
					prenotazioneDAO.doDelete(idPrenotazione);
					for(Servizio s : servizi){
						sessione.removeAttribute("att"+s.getNome().replaceAll("\\s+", ""));
					}
					sessione.removeAttribute("numeroMassimoTotaleOspiti");
					sessione.removeAttribute("dataFinePrenotazione");
					sessione.removeAttribute("dataInizioPrenotazione");
					sessione.removeAttribute("Servizi");
					sessione.removeAttribute("CarrelloCamere");
					RequestDispatcher rd = request.getRequestDispatcher("../carrello.jsp");
					request.setAttribute("erroreAcquisto", "Ci dispiace! La camera numero "+cameraCarello.getNumero()+" che "
							+ "hai provato ad acquistare non è più disponibile.");
					rd.forward(request, response);
					return;
				}else{
					CameraPrenotata cameraPrenotata = new CameraPrenotata();
					cameraPrenotata.setCamera(cameraDatabase.getNumero());
					cameraPrenotata.setPrenotazione(idPrenotazione);
					cameraPrenotata.setCosto(cameraDatabase.getCosto());
					cameraPrenotataDAO.doSave(cameraPrenotata);
				}
			}
			
			
			for(Servizio s : servizi){
				sessione.removeAttribute("att"+s.getNome().replaceAll("\\s+", ""));
			}
			sessione.removeAttribute("numeroMassimoTotaleOspiti");
			sessione.removeAttribute("dataFinePrenotazione");
			sessione.removeAttribute("dataInizioPrenotazione");
			sessione.removeAttribute("Servizi");
			sessione.removeAttribute("CarrelloCamere");
			
			response.sendRedirect(request.getContextPath()+"/homepage.jsp");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
