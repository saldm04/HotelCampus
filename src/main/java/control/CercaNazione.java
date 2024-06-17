package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class CercaNazione
 */
@WebServlet("/CercaNazione")
public class CercaNazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> nazioniEuropee = Arrays.asList(
		    "Albania", "Andorra", "Armenia", "Austria", "Azerbaigian", "Bielorussia", "Belgio",
		    "Bosnia ed Erzegovina", "Bulgaria", "Croazia", "Cipro", "Repubblica Ceca", "Danimarca",
		    "Estonia", "Finlandia", "Francia", "Georgia", "Germania", "Grecia", "Ungheria", "Islanda",
		    "Irlanda", "Italia", "Kazakistan", "Kosovo", "Lettonia", "Liechtenstein", "Lituania",
		    "Lussemburgo", "Malta", "Moldavia", "Monaco", "Montenegro", "Paesi Bassi", "Macedonia del Nord",
		    "Norvegia", "Polonia", "Portogallo", "Romania", "Russia", "San Marino", "Serbia", "Slovacchia",
		    "Slovenia", "Spagna", "Svezia", "Svizzera", "Turchia", "Ucraina", "Regno Unito", "Città del Vaticano", "Altro"
		);
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String naz = request.getParameter("nazionalita");
        List<String> suggerimenti = new ArrayList<>();
        
        if (naz != null && !naz.isEmpty()) {
            suggerimenti = nazioniEuropee.stream()
                .filter(n -> n.toLowerCase().startsWith(naz.toLowerCase()))
                .collect(Collectors.toList());
        }

        Gson gson = new Gson();
        String json = gson.toJson(suggerimenti);
        out.print(json);
        out.flush();
    }
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
