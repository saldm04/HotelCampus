package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Camera;
import model.CameraDAO;
import model.CameraPrenotata;
import model.CameraPrenotataDAO;
import model.PrenotazioneDAO;

/**
 * Servlet implementation class camereDisponibiliPrenotazione
 */
@WebServlet("/CamereDisponibiliPrenotazione")
public class CamereDisponibiliPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CameraDAO cameraDAO = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
		RequestDispatcher rd = request.getRequestDispatcher("prenotazione.jsp");
		
		try {
			Collection<Camera> camere = cameraDAO.doRetrieveAll("");
			camere.removeIf(c -> c.isDisponibile()==false);
			request.setAttribute("camereDisponibili", camere);
			request.setAttribute("ricercaEffettuata", false);
			String maxOspiti = cameraDAO.getMaxNumeroOspiti();
			request.setAttribute("maxAttributeValueOspiti", maxOspiti);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO((DataSource) getServletContext().getAttribute("DataSource"));
		CameraPrenotataDAO cameraPrenotataDAO = new CameraPrenotataDAO((DataSource) getServletContext().getAttribute("DataSource"));
		CameraDAO cameraDAO = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
		
		RequestDispatcher rd = request.getRequestDispatcher("prenotazione.jsp");
		
		Collection<Camera> camere = null;
		Collection<CameraPrenotata> camerePrenotate = null;
		
		try {
			camerePrenotate = cameraPrenotataDAO.doRetrieveAll("");
			camere = cameraDAO.doRetrieveAll("");
			camere.removeIf(c -> c.isDisponibile()==false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dataCheckIn = null;
		Date dataCheckOut = null;
		Integer numeroOspiti = null;
		
		String dataCheckInString = request.getParameter("checkindate");
		String dataCheckOutString = request.getParameter("checkoutdate");
		String numeroOspitiString = request.getParameter("numOspiti");
		
		if(dataCheckInString==null || dataCheckOutString==null ||
			numeroOspitiString==null || dataCheckInString.equals("")
			|| dataCheckOutString.equals("") || numeroOspitiString.equals(""))
		{
			response.sendRedirect("CamereDisponibiliPrenotazione");
			return;
		}
		
        try {
			dataCheckIn = formatter.parse(dataCheckInString);
			dataCheckOut = formatter.parse(dataCheckOutString);
			numeroOspiti = Integer.parseInt(numeroOspitiString);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(dataCheckIn.compareTo(dataCheckOut)>=0 || numeroOspiti<=0)
        	response.sendRedirect("CamereDisponibiliPrenotazione");
        
        
        ArrayList<Camera> camereCarrello = (ArrayList<Camera>) request.getSession().getAttribute("CarrelloCamere");
        if(camereCarrello==null || camereCarrello.isEmpty()){
        	request.getSession().setAttribute("dataInizioPrenotazione", dataCheckInString);
        	request.getSession().setAttribute("dataFinePrenotazione", dataCheckOutString);
        }
        
        Date dataInizio = null;
		Date dataFine = null;
        for(CameraPrenotata cam : camerePrenotate){
        	try {
				dataInizio = prenotazioneDAO.doRetrieveByKey(cam.getPrenotazione()).getDataInizio();
				dataFine = prenotazioneDAO.doRetrieveByKey(cam.getPrenotazione()).getDataFine();
				
				if(isOccupata(dataCheckIn, dataCheckOut, dataInizio, dataFine)){
	        		camere.removeIf(c -> c.getNumero() == cam.getCamera());
	        	}
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
        }
        
        final int guests = numeroOspiti;
        camere.removeIf(c -> guests > c.getNumeroMaxOspiti());
        
        if(camereCarrello!=null && !camereCarrello.isEmpty()){
        	for(Camera cameraNelCarrello : camereCarrello){
        		camere.removeIf(c -> c.getNumero() == cameraNelCarrello.getNumero());
        	}
        }
		
        request.setAttribute("camereDisponibili", camere);
        request.setAttribute("ricercaEffettuata", true);
        try {
			request.setAttribute("maxAttributeValueOspiti", cameraDAO.getMaxNumeroOspiti());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		rd.forward(request, response);
	}
	
	private boolean isOccupata(Date dataIn, Date dataOut, Date dataMin, Date dataMax){
		if(dataIn.compareTo(dataMin)>=0 && dataIn.compareTo(dataMax)<0)
			return true;
		if(dataOut.compareTo(dataMin)>0 && dataOut.compareTo(dataMax)<=0)
			return true;
		if(dataIn.compareTo(dataMin)<=0 && dataOut.compareTo(dataMax)>=0)
			return true;
		
		return false;
	}
}
