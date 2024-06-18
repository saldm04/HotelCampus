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

import model.Camera;
import model.CameraDAO;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
