package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.CameraDAO;
import model.Camera;

/**
 * Servlet implementation class getCameraByID
 */
@WebServlet("/getCameraByID")
public class getCameraByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCameraByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CameraDAO cameraDAO = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
		RequestDispatcher rd = request.getRequestDispatcher("camera.jsp");
		
		try {
			Integer numero = Integer.parseInt(request.getParameter("cameraId"));
			
			Camera camera = cameraDAO.doRetrieveByKey(numero);
			
			request.setAttribute("camera", camera);
			
			rd.forward(request, response);
			
		}catch(Exception e) {
			response.sendRedirect("homepage.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
