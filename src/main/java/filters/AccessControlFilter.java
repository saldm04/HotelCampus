package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;

/**
 * Servlet Filter implementation class AccessControlFilter
 */
@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		Utente utente = (Utente) httpServletRequest.getSession().getAttribute("utente");
		String path = httpServletRequest.getServletPath();
		if (path.contains("/common/") && utente==null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/loginProva.jsp");
			return;
		} else if (path.contains("/admin/") && (utente==null || !utente.isAdmin())) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/loginProva.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
