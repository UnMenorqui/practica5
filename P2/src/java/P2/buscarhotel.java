package P2;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/buscarhotel"})

public class buscarhotel extends HttpServlet { 
    
    
    private HttpSession sesion;
    
   /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
                       
        Connection connection = null;
        try                        
        {            
          // load the sqlite-JDBC driver using the current class loader
          Class.forName("org.sqlite.JDBC");   
              
          
          // create a database connection
          //connection = DriverManager.getConnection("jdbc:sqlite:F:\\AD\\Pràctica 2\\BD\\exemple.db");
          //connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db"); //Mac Aleix
          connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
          
          
          //String id_hotel = request.getParameter("id_hotel");
          String nombrehotel = request.getParameter("nombrehotel");
          System.out.println("Nombrehotel: " +nombrehotel);
          String cadenahotelera = request.getParameter("cadenahotelera");
          //String calle = request.getParameter("calle");
          //String numero = request.getParameter("numero");
          //String codigopostal = request.getParameter("codigopostal");
          String ciudad = request.getParameter("ciudad");
          //String provincia = request.getParameter("provincia");
          String pais = request.getParameter("pais");
          //String num_habitaciones = request.getParameter("numerohabitaciones");
          
          ResultSet rs; 
          
          
          if (nombrehotel.equals("")) {
              if (cadenahotelera.equals("")) {
                  if (ciudad.equals("")) {
                      rs = statement.executeQuery("Select * from hoteles"); //Buscar todo (parametros vacios)
                  }
                  else {
                      rs = statement.executeQuery("Select * from hoteles where ciudad='"+ciudad+"'");
                  }
              }
              else {
                  if (ciudad.equals("")) {
                      rs = statement.executeQuery("Select * from hoteles where cadena='"+cadenahotelera+"'");
                  }
                  else {
                      rs = statement.executeQuery("Select * from hoteles where cadena='"+cadenahotelera+"' and ciudad='"+ciudad+"'");
                  }
              }
          }
          else {
              if (cadenahotelera.equals("")) {
                  if (ciudad.equals("")) {
                      rs = statement.executeQuery("Select * from hoteles where nom_hotel='"+nombrehotel+"'");
                  }
                  else {
                      rs = statement.executeQuery("Select * from hoteles where nom_hotel='"+nombrehotel+"' and ciudad='"+ciudad+"'");
                  }
              }
              else {
                  if (ciudad.equals("")) {
                      rs = statement.executeQuery("Select * from hoteles where nom_hotel='"+nombrehotel+"' and cadena='"+cadenahotelera+"'");
                  }
                  else {
                      rs = statement.executeQuery("Select * from hoteles where nom_hotel='"+nombrehotel+"' and cadena='"+cadenahotelera+"' and ciudad='"+ciudad+"'");
                  }
              }
          }
          
          
          if (rs.next()) {
              hotel hotel1;
              ArrayList<hotel> hotel = new ArrayList<>();
              hotel1 = new hotel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9), rs.getInt(10));
              
              hotel.add(hotel1);
              while (rs.next()) {
                  hotel1 = new hotel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9), rs.getInt(10));
                  hotel.add(hotel1);
              }
              sesion = request.getSession(false);
              sesion.setAttribute("hoteles", hotel);
              response.sendRedirect("buscarhotel.jsp");
          } else {
              sesion = request.getSession(false);
              sesion.setAttribute("error", "6");
              response.sendRedirect("error.jsp");
          }
        } 
        catch(SQLException | ClassNotFoundException e)
        {
          System.err.println(e.getMessage());
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }       
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestPOST(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestPOST(request, response);
    }

}
