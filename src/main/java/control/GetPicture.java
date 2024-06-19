package control;

import model.CameraDAO;
import model.ServizioDAO;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class GetPicture
 */
@WebServlet("/GetPicture")
public class GetPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String beanType = (String) request.getParameter("beanType");
		String id = (String) request.getParameter("id");
		Integer numberImg = Integer.parseInt((String) request.getParameter("numberImg"));
				byte[] img = null;
		try {
			if(id!=null && beanType!=null && numberImg!=null){
				if(beanType.equals("servizio")){
					ServizioDAO servizio = new ServizioDAO((DataSource) getServletContext().getAttribute("DataSource"));
					if(numberImg==1){
						img = servizio.doRetrieveByKey(id).getImg1();
					}else if(numberImg==2){
						img = servizio.doRetrieveByKey(id).getImg2();
					}
				}else if(beanType.equals("camera")){
					CameraDAO camera = new CameraDAO((DataSource) getServletContext().getAttribute("DataSource"));
					if(numberImg==1){
						img = camera.doRetrieveByKey(Integer.parseInt(id)).getImg1();
					}else if(numberImg==2){
						img = camera.doRetrieveByKey(Integer.parseInt(id)).getImg2();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ServletOutputStream out = response.getOutputStream();
		if(img!=null){
			out.write(img);
			response.setContentType("image/jpeg");
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
