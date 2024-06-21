package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Camera;
import model.Prenotazione;
import model.Servizio;
import model.ServizioDAO;

/**
 * Servlet implementation class Acquista
 */
@WebServlet("/common/Acquista")
public class Acquista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		ArrayList<Camera> camere = (ArrayList<Camera>) sessione.getAttribute("CarrelloCamere");
		ServizioDAO servizioDAO = new ServizioDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ArrayList<Servizio> servizi = new ArrayList<Servizio>();
		try {
			servizi = (ArrayList<Servizio>) servizioDAO.doRetrieveAll("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servizi.removeIf(s -> s.isDisponibile()==false);
		Integer totaleCarrello = Integer.parseInt(request.getParameter("totaleCarrello"));
		
		Prenotazione prenotazione = new Prenotazione();
		
		String strInizioPre = (String) sessione.getAttribute("dataInizioPrenotazione");
		String strFinePre = (String) sessione.getAttribute("dataFinePrenotazione");
		if(strFinePre!=null && strInizioPre!=null){
			prenotazione.setDataInizio(Date.valueOf(strInizioPre));
			prenotazione.setDataInizio(Date.valueOf(strFinePre));
		}
		
		
		for(Servizio servizio : servizi){
			
		}
		
	}

}
