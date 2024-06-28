package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import model.Servizio;
import model.ServizioDAO;
import model.Utente;

/**
 * Servlet implementation class EditServizi
 */
@WebServlet("/admin/EditServizi")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditServizi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServizioDAO servizio = new ServizioDAO((DataSource) getServletContext().getAttribute("DataSource"));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/areaRiservata.jsp?edit=servizi");
	
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

		
		List<String> errors = (List<String>) request.getAttribute("problemDetectd");
		
		
		
		if(errors != null && !errors.isEmpty()) {
			request.removeAttribute("problemDetectd");
			request.getSession().setAttribute("problemDetectd", errors);
        	rd.forward(request, response);
        	return;
		}
		
		if(request.getParameter("action") != null) {
			if(request.getParameter("action").equals("addServizio")) {
				
				Servizio servizioSave = new Servizio();
				String nome = request.getParameter("nome");
				String descrizione =  request.getParameter("descrizione");
				Integer costo = Integer.parseInt(request.getParameter("costo"));
				
				for (Part part : request.getParts()) {
					String fileName = part.getSubmittedFileName();
					if (fileName != null && !fileName.equals("")) {
							servizioSave.setImg1(part.getInputStream().readAllBytes());
					}
				}
		        
		        descrizione = descrizione.replace("\n", "<br>");
		        
		        servizioSave.setCosto(costo);
		        servizioSave.setDescrizione(descrizione);
		        servizioSave.setNome(nome);
		        
		        try {
					servizio.doSave(servizioSave);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
			
			if(request.getParameter("action").equals("delete")) {
				try {
					servizio.setDisponibile(request.getParameter("nome"),false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		try {
			Collection<Servizio> servizi = servizio.doRetrieveAll("");
			servizi.removeIf(s -> s.isDisponibile()==false);
			request.setAttribute("editServizi", servizi);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
		
	}
 
}
