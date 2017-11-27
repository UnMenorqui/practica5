package org.servletVuelo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import com.vuelowsoap.VueloWS;

@WebServlet(name = "servletVueloConsulta", urlPatterns = {"/servletVueloConsulta"})
public class servletVueloConsulta extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/VueloWSapplication/VueloWS.wsdl")
    private VueloWS service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>  "
                    + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" 
                    + "  <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"> "
                    + "  <link rel=\"stylesheet\" href=\"css/menu.css\" type=\"text/css\"/> ");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body> "
                    + "<ul>"
                    + " <li><a href=\"menu.jsp\">Home</a></li>"
                    + " <li class=\"dropdown\">"
                    + "     <a href=\"#\">Flights</a>"
                    + "     <div class=\"dropdown-content\">"
                    + "         <a href=\"altavuelo.jsp\">Register Flight</a>"
                    + "         <a href=\"buscarvuelo.jsp\">Search Flight</a>"
                    + "         <a href=\"consultaplazasvuelo.jsp\">Check free flight seats</a>"
                    + "         <a href=\"reservaplazavuelo.jsp\">Book seat in flight</a>"
                    + "     </div> \n </li>"
                    + " <li class=\"dropdown\">" +
"                           <a href=\"#\">Hotels</a>" +
"                           <div class=\"dropdown-content\">" +
"                               <a href=\"altahotel.jsp\">Register Hotel</a>" +
"                               <a href=\"buscarhotel.jsp\">Search Hotel</a>" +
"                               <a href=\"consultahabitaciones.jsp\">Check free rooms</a>" +
"                               <a href=\"reservahabitacion.jsp\">Reserve Room</a>" +
"                           </div> \n" +
"                       </li>\n" +
"                       <li><a href=\"logout\">Log Out</a></li>\n" +
"                      </ul>"
                    + "<div class='message'> <div class='form'>");
           try {
                    int resultLibres = consultaLibres(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("fecha")));
                    String resultado = "";
                    if(resultLibres < 0) resultado = "ERROR!";
                    else if(resultLibres == 0) resultado = "There are no free seats!";
                    else resultado = "The number of free places is: " + resultLibres;
                    out.println(resultado);
                } catch (Exception ex) {
                    out.println("Exception: " + ex);
                }finally {
                out.close();
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int consultaLibres(int idVuelo, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.vuelowsoap.VueloWSoap port = service.getVueloWSoapPort();
        return port.consultaLibres(idVuelo, fecha);
    }

}

