package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import model.CameraPrenotata;
import model.CameraPrenotataDAO;
import model.Prenotazione;
import model.PrenotazioneDAO;
import model.Servizio;
import model.ServizioDAO;
import model.ServizioPrenotato;
import model.ServizioPrenotatoDAO;

/**
 * Servlet implementation class AdminPrenotazioni
 */
@WebServlet("/admin/Prenotazioni")
public class AdminPrenotazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
	    PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO(dataSource);
	    CameraPrenotataDAO cameraPrenotataDAO = new CameraPrenotataDAO(dataSource);
	    ServizioPrenotatoDAO servizioPrenotatoDAO = new ServizioPrenotatoDAO(dataSource);

		RequestDispatcher rd = request.getRequestDispatcher("/admin/areaRiservata.jsp?edit=prenotazioni");
		
		List<String> errors = (List<String>) request.getAttribute("problemDetectd");
		
		
		
		if(errors != null && !errors.isEmpty()) {
			request.removeAttribute("problemDetectd");
			request.getSession().setAttribute("problemDetectd", errors);
        	rd.forward(request, response);
        	return;
		}
		
		Date dataInizio = null;
		Date dataFine = null;
		
		
	    String email = request.getParameter("email");
	    String dataInizioStr = request.getParameter("checkindate");
	    String dataFineStr = request.getParameter("checkoutdate");
	    
	    if(dataInizioStr == null || dataInizioStr.trim().equals("")) {
	    	dataInizio = Date.valueOf("1970-01-01"); // Data minima valida
	    }else {
	    	dataInizio = Date.valueOf(dataInizioStr);
	    }
	    
	    if(dataFineStr == null || dataFineStr.trim().equals("")) {
	    	dataFine = Date.valueOf("9999-12-31"); // Data massima valida
	    }else {
	    	dataFine = Date.valueOf(dataFineStr);
	    }
	 

	    try {
	           Collection<Prenotazione> prenotazioni = prenotazioneDAO.doRetrieveByFilter(email, dataInizio, dataFine);
	           Map<Integer, Collection<CameraPrenotata>> camereMap = new HashMap<Integer, Collection<CameraPrenotata>>();
	           Map<Integer, Collection<ServizioPrenotato>> serviziMap = new HashMap<Integer, Collection<ServizioPrenotato>>();

	            for (Prenotazione prenotazione : prenotazioni) {
	                int idPrenotazione = prenotazione.getId();
	                Collection<CameraPrenotata> camere = cameraPrenotataDAO.doRetrieveByPrenotazione(idPrenotazione);
	                Collection<ServizioPrenotato> servizi = servizioPrenotatoDAO.doRetrieveByPrenotazione(idPrenotazione);
	               
	                
	                camereMap.put(idPrenotazione, camere);
	                serviziMap.put(idPrenotazione, servizi);
	            }
	            
	       
                

	            request.setAttribute("prenotazioni", prenotazioni);
	            request.setAttribute("camereMap", camereMap);
	            request.setAttribute("serviziMap", serviziMap);
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
	    
		
		
		
		
		rd.forward(request, response);
		
	}

}
