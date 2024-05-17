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

import model.Servizio;
import model.ServizioDAO;

/**
 * Servlet implementation class Servizi
 */
@WebServlet("/Servizi")
public class Servizi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServizioDAO servizio = new ServizioDAO((DataSource) getServletContext().getAttribute("DataSource"));
		RequestDispatcher rd = request.getRequestDispatcher("servizi.jsp");
		
		try {
			Collection<Servizio> servizi = servizio.doRetrieveAll("");
			servizi.removeIf(s -> s.isDisponibile()==false);
			request.setAttribute("servizi", servizi);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
