package filters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet Filter implementation class AccessControlFilter
 */
@WebFilter(filterName = "/SecurityFilter", urlPatterns = "/*")
public class SecurityFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
        String servletPath = httpRequest.getServletPath();
        
        List<String> errors = new ArrayList<>();
        
        /*==========================Validazione Edit Servizi==========================*/
        if(servletPath.equals("/admin/EditServizi")) {
        	
        	if(request.getParameter("action") != null) {
        		
    			if(request.getParameter("action").equals("addServizio")) {
    				
    				checkCSRF(httpRequest, httpResponse);
    				
    				String nome = request.getParameter("nome");
    				String descrizione = request.getParameter("descrizione");
    				try {
    					Integer costo = Integer.parseInt(request.getParameter("costo"));
    					if(costo < 1) {
    						errors.add("Il costo inserito non è valido");
        		        }
    				}catch (NumberFormatException e){
    					errors.add("Il costo inserito non è valido");
    				}
    				
    				int countFile = 0;
    				
    				for (Part part : ((HttpServletRequest) request).getParts()) {
    					String fileName = part.getSubmittedFileName();
    					if (fileName != null && !fileName.equals("")) {
    						countFile++;
    						if(!isImageFile(part.getInputStream().readAllBytes())) {
    							errors.add("Il file inserito non è valido");
    						}
    							
    					}
    				}
    				
    				if(countFile == 0) {
    					errors.add("L'immagine è obbligatoria");
    				}
    				
    				if(nome == null || nome.trim().isEmpty()) {
    					errors.add("Il campo nome non può essere vuoto!");
    				}
    		        if(descrizione == null || descrizione.trim().isEmpty()) {
    		        	errors.add("Il campo descrizione non può essere vuoto!");
    				}
    		        
    		        if(isXSS(nome)) {
    		        	errors.add("il nome inserito non è valido");
    		        }
    		        
    		        if(isXSS(descrizione)) {
    		        	errors.add("la descrizione inserita non è valida");
    		        }
    		        
    			}
    			if(request.getParameter("action").equals("delete")) {
            		checkCSRF(httpRequest, httpResponse);
    			}
    			
        	}
        }
        
        /*==========================Validazione Edit Camere==========================*/
        if(servletPath.equals("/admin/EditCamere")) {
        	
        	
        	if(request.getParameter("action") != null) {
        		
        			
        		if(request.getParameter("action").equals("addCamera")) {
        		
        			checkCSRF(httpRequest, httpResponse);
        		
        			try {
        				Integer numeroCamera = Integer.parseInt(request.getParameter("numeroCamera"));
        				Integer numeroOspiti = Integer.parseInt(request.getParameter("numeroMaxOspiti"));
        				Integer quadratura = Integer.parseInt(request.getParameter("quadratura"));
        				Integer costo = Integer.parseInt(request.getParameter("costo"));
        			
        				if(numeroCamera < 1)
        					errors.add("il numero della camera deve essere positivo");
        				if(numeroOspiti < 1)
        					errors.add("il numero degli ospiti deve essere positivo");
        				if(quadratura < 1)
        					errors.add("la quadratura della camera deve essere positivo");
        				if(costo < 1)
        					errors.add("il costo della camera deve essere positivo");
        				
        			}catch(NumberFormatException e){
        				errors.add("Bisogna iserire valori numerici");
        			}
        		
					String tipologia = request.getParameter("tipologia");
				
					if(tipologia == null || tipologia.trim().equals(""))
						errors.add("il campo tipologia non può essere vuoto");
					else {
						tipologia = tipologia.toLowerCase();
		        
						if(!tipologia.equals("deluxe") && !tipologia.equals("exclusive") && !tipologia.equals("standard") && !tipologia.equals("luxury"))
							errors.add("la tipologia inserita non è valida");
		        
						if(isXSS(tipologia))
							errors.add("la tipologia inserita non è valida");
					}
				
					int countFile = 0;
				
					for (Part part : ((HttpServletRequest) request).getParts()) {
						String fileName = part.getSubmittedFileName();
						if (fileName != null && !fileName.equals("")) {
							countFile++;
						if(!isImageFile(part.getInputStream().readAllBytes())) {
								errors.add("Il file inserito non è valido");
							}
						
						}
					}
				
					if(countFile == 0)
						errors.add("almeno una immagine è obbligatoria");
        		
        		}
        	
        		if(request.getParameter("action").equals("delete")) {
        			checkCSRF(httpRequest, httpResponse);
				}	
        	}
        }
        
        /*==========================Verifica CSRF per visualizza utenti==========================*/
        if(servletPath.equals("/admin/Utenti")) {
        	if(request.getParameter("action") != null) {
        		checkCSRF(httpRequest, httpResponse);
        	}
        	
        }
        
        /*==========================Verfica Visualizza Prenotazioni ADMIN==========================*/
        if(servletPath.equals("/admin/Prenotazioni")) {
        	String email = request.getParameter("email");
     	    String dataInizioStr = request.getParameter("checkindate");
     	    String dataFineStr = request.getParameter("checkoutdate");
     	    
     	    if((email == null  || email.trim().equals("")) && (dataInizioStr == null  || dataInizioStr.trim().equals("")) && (dataFineStr == null  || dataFineStr.trim().equals("")))
     	    	errors.add("Almeno un filtro deve essere attivo");
     	    	
     	    if(email != null  && !email.trim().equals(""))
     	    	if(isSQLInjection(email) || isXSS(email))
     	    		errors.add("Qualcosa non va con l'email");
     	    
     	   if(dataInizioStr != null  && !dataInizioStr.trim().equals(""))
    	    	if(isSQLInjection(dataInizioStr) || isXSS(dataInizioStr))
    	    		errors.add("Qualcosa non va con la data di checkIn");
     	   
     	  if(dataFineStr != null  && !dataFineStr.trim().equals(""))
  	    	if(isSQLInjection(dataFineStr) || isXSS(dataFineStr))
  	    		errors.add("Qualcosa non va con la data di checkOut");
     	    
        }
        
        if(servletPath.equals("/common/Prenotazioni")) {
     	    String dataInizioStr = request.getParameter("checkindate");
     	    String dataFineStr = request.getParameter("checkoutdate");
     	   
     	   if(dataInizioStr != null  && !dataInizioStr.trim().equals(""))
    	    	if(isSQLInjection(dataInizioStr) || isXSS(dataInizioStr))
    	    		errors.add("Qualcosa non va con la data di checkIn");
     	   
     	  if(dataFineStr != null  && !dataFineStr.trim().equals(""))
  	    	if(isSQLInjection(dataFineStr) || isXSS(dataFineStr))
  	    		errors.add("Qualcosa non va con la data di checkOut");
     	    
        }
        
        if(servletPath.equals("/common/editDatiAccount")){
        	String oldPass = request.getParameter("oldPass");
        	String newPass = request.getParameter("newPass");
        	
        	if(oldPass == null || oldPass.trim().isEmpty()) {
            	errors.add("Il campo password non può essere vuoto!");
    		}
        	
        	if(newPass == null || newPass.trim().isEmpty()) {
            	errors.add("Il campo password non può essere vuoto!");
    		}
        }
        
        if(servletPath.equals("/SignIn")) {

        	String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		String nome = request.getParameter("nome");
    		String cognome = request.getParameter("cognome");
    		String dataNascita = request.getParameter("dataNascita");
    		String nazionalita = request.getParameter("nazionalita");
    		
        	if(email == null || email.trim().isEmpty()) {
    			errors.add("Il campo email non può essere vuoto!");
    		}else {
    			if(isXSS(email))
    				errors.add("la mail inserita non rispecchia i parametri richiesti");
    		}
        	
            if(password == null || password.trim().isEmpty()) {
            	errors.add("Il campo password non può essere vuoto!");
    		}else {
    			if(isXSS(password))
    				errors.add("la password inserita non rispecchia i parametri richiesti");
    		}
            if(nome == null || nome.trim().isEmpty()) {
            	errors.add("Il campo nome non può essere vuoto!");
    		}else {
    			if(isXSS(nome))
    				errors.add("il nome inserito non rispecchia i parametri richiesti");
    		}
            if(cognome == null || cognome.trim().isEmpty()) {
            	errors.add("Il campo cognome non può essere vuoto!");
    		}else {
    			if(isXSS(cognome))
    				errors.add("il cognome inserito non rispecchia i parametri richiesti");
    		}
            if(dataNascita == null || dataNascita.trim().isEmpty() ) {
            	errors.add("Il campo dataNascita non può essere vuoto!");
    		}else {
    			if(isXSS(dataNascita))
    				errors.add("la data inserita non rispecchia i parametri richiesti");
    		}
            
        	List<String> nazioniEuropee = Arrays.asList(
        		    "Albania", "Andorra", "Armenia", "Austria", "Azerbaigian", "Bielorussia", "Belgio",
        		    "Bosnia ed Erzegovina", "Bulgaria", "Croazia", "Cipro", "Repubblica Ceca", "Danimarca",
        		    "Estonia", "Finlandia", "Francia", "Georgia", "Germania", "Grecia", "Ungheria", "Islanda",
        		    "Irlanda", "Italia", "Kazakistan", "Kosovo", "Lettonia", "Liechtenstein", "Lituania",
        		    "Lussemburgo", "Malta", "Moldavia", "Monaco", "Montenegro", "Paesi Bassi", "Macedonia del Nord",
        		    "Norvegia", "Polonia", "Portogallo", "Romania", "Russia", "San Marino", "Serbia", "Slovacchia",
        		    "Slovenia", "Spagna", "Svezia", "Svizzera", "Turchia", "Ucraina", "Regno Unito", "Città del Vaticano", "Altro"
        		);
        	
            if(nazionalita == null || nazionalita.trim().isEmpty()) {
            	errors.add("Il campo nazionalità non può essere vuoto!");
    		}else {
    			 if(!nazioniEuropee.contains(nazionalita.trim())){
    	                errors.add("La nazionalità specificata non è valida!");
    	         }
    		}
        }
        
        if(!errors.isEmpty()) {
        	request.setAttribute("problemDetectd", errors);
        }
        
		chain.doFilter(request, response);
	}
	
	/*==========================METODI PER LA SICUREZZA==========================*/
	
	private  void checkCSRF(HttpServletRequest request , HttpServletResponse response) {
		String requestToken = request.getParameter("csrfToken");
        String sessionToken = (String) request.getSession().getAttribute("csrfToken");
        
        if (requestToken == null || !requestToken.equals(sessionToken)) {
			//System.out.println(sessionToken);
            try {
				response.sendRedirect(request.getContextPath() + "/homepage.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return; 
        }
	}
	
	private static boolean isXSS(String input) {
	        if (input == null || input.isEmpty()) {
	            return false;
	        }

	        String lowerCaseInput = input.toLowerCase();

	        String[] xssPatterns = {
	            "<script", 
	            "</script>", 
	            "<img", 
	            "onerror=", 
	            "onload=", 
	            "<iframe", 
	            "javascript:", 
	            "vbscript:", 
	            "<object", 
	            "<embed", 
	            "<link", 
	            "<style", 
	            "<base", 
	            "<form", 
	            "<svg", 
	            "<math"
	        };

	        for (String pattern : xssPatterns) {
	            if (lowerCaseInput.contains(pattern)) {
	                return true;
	            }
	        }

	        return false;
	    }
	 
	 private static boolean isSQLInjection(String input) {
	        if (input == null || input.isEmpty()) {
	            return false;
	        }

	        String lowerCaseInput = input.toLowerCase();

	        String[] sqlInjectionPatterns = {
	            "'", 
	            "\"", 
	            "--", 
	            ";", 
	            "/*", 
	            "*/", 
	            "select ", 
	            "insert ", 
	            "update ", 
	            "delete ", 
	            "drop ", 
	            "exec ", 
	            "union ", 
	            "create ", 
	            "alter ", 
	            "shutdown "
	        };

	        for (String pattern : sqlInjectionPatterns) {
	            if (lowerCaseInput.contains(pattern)) {
	                return true;
	            }
	        }

	        return false;
	  }
	 

	   private static final Map<String, String> IMAGE_MAGIC_NUMBERS = new HashMap<>();

	   static {
	        // JPEG
	        IMAGE_MAGIC_NUMBERS.put("FFD8", "JPEG");
	        // PNG
	        IMAGE_MAGIC_NUMBERS.put("89504E47", "PNG");
	        // GIF
	        IMAGE_MAGIC_NUMBERS.put("47494638", "GIF");
	        // BMP
	        IMAGE_MAGIC_NUMBERS.put("424D", "BMP");
	       
	        IMAGE_MAGIC_NUMBERS.put("4D4D002A", "TIFF");
	   
	        IMAGE_MAGIC_NUMBERS.put("49492A00", "TIFF");
	    }
	   
	   private static boolean isImageFile(byte[] fileBytes) {
	        if (fileBytes == null || fileBytes.length < 4) { 
	            return false;
	        }

	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < Math.min(fileBytes.length, 8); i++) {
	            sb.append(String.format("%02X", fileBytes[i]));
	        }

	        String magicNumberHex = sb.toString();
	        for (String magicNumber : IMAGE_MAGIC_NUMBERS.keySet()) {
	            if (magicNumberHex.startsWith(magicNumber)) {
	                return true;
	            }
	        }

	        return false;
	    }
}
