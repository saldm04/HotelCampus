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
		
		List<String> errors = new ArrayList<>();
		
		if(request.getParameter("action") != null) {
			if(request.getParameter("action").equals("addCamera")) {
				
		    
	
			}
			
			if(request.getParameter("action").equals("delete")) {
				
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
