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

import model.Camera;
import model.CameraDAO;
import model.Servizio;
import model.ServizioDAO;

/**
 * Servlet implementation class EditCamere
 */
@WebServlet("/admin/EditCamere")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditCamere extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CameraDAO camereDAO = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/areaRiservata.jsp?edit=camere");
		
		List<String> errors = (List<String>) request.getAttribute("problemDetectd");
		
		
		
		if(errors != null && !errors.isEmpty()) {
			request.removeAttribute("problemDetectd");
			request.getSession().setAttribute("problemDetectd", errors);
        	rd.forward(request, response);
        	return;
		}
		
		if(request.getParameter("action") != null) {
			if(request.getParameter("action").equals("addCamera")) {
				Camera cameraSave = new Camera();
				Integer numeroCamera = Integer.parseInt(request.getParameter("numeroCamera"));
				Integer numeroOspiti = Integer.parseInt(request.getParameter("numeroMaxOspiti"));
				Integer quadratura = Integer.parseInt(request.getParameter("quadratura"));
				Integer costo = Integer.parseInt(request.getParameter("costo"));
				String tipologia = request.getParameter("tipologia");
				
				
				int i = 1;
				
				for (Part part : request.getParts()) {
					String fileName = part.getSubmittedFileName();
					if (fileName != null && !fileName.equals("")) {
							if(i == 1) {
								cameraSave.setImg1(part.getInputStream().readAllBytes());
								i++;
							}else {
								cameraSave.setImg2(part.getInputStream().readAllBytes());
							}
					}
				}    
		        
		        cameraSave.setCosto(costo);
		        cameraSave.setDisponibile(true);
		        cameraSave.setNumero(numeroCamera);
		        cameraSave.setNumeroMaxOspiti(numeroOspiti);
		        cameraSave.setQuadratura(quadratura);
		        cameraSave.setTipo(tipologia);
		        
		        try {
					camereDAO.doSave(cameraSave);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(request.getParameter("action").equals("delete")) {
				try {
					camereDAO.setDisponibile(Integer.parseInt(request.getParameter("numero")), false);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		try {
			Collection<Camera> camere = camereDAO.doRetrieveAll("");
			camere.removeIf(s -> s.isDisponibile()==false);
			request.setAttribute("editCamere", camere);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
		
	}
}
