package P2;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/altahotel"})

public class altahotel extends HttpServlet { 
    
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
        
        HttpSession session=request.getSession(false);  
        if(session!=null){  
        
            try                        
            {            
              // load the sqlite-JDBC driver using the current class loader
              Class.forName("org.sqlite.JDBC");   


              // create a database connection
              //connection = DriverManager.getConnection("jdbc:sqlite:F:\\AD\\Pràctica 2\\BD\\exemple.db");
              connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db"); //Mac Aleix
              //connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Documents/Universitat/AD/LAB/P2/p2.db");
              //connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
              Statement statement = connection.createStatement();
              statement.setQueryTimeout(30);  // set timeout to 30 sec.

              String id_hotel = request.getParameter("id_hotel");
              System.out.println("Id_hotel: " + id_hotel);

              String nombrehotel = request.getParameter("nombrehotel");
              System.out.println("nombrehotel:: " + nombrehotel);

              String cadenahotelera = request.getParameter("cadenahotelera");
              System.out.println("cadenahotelera: " + cadenahotelera);

              String num_habitaciones = request.getParameter("num_habitaciones");
              System.out.println("num_habitaciones: " + num_habitaciones);

              String calle = request.getParameter("calle");
              System.out.println("calle: " + calle);

              String numero = request.getParameter("numero");
              System.out.println("numero: " + numero);

              String codigopostal = request.getParameter("codigopostal");
              System.out.println("CP:: " + codigopostal);

              String ciudad = request.getParameter("ciudad");
              System.out.println("ciudad: " + ciudad);

              String provincia = request.getParameter("provincia");
              System.out.println("provincia: " + provincia);

              String pais = request.getParameter("pais");
              System.out.println("pais: " + pais);



              ResultSet rs = statement.executeQuery("select * from hoteles where id_hotel ='"+id_hotel+"'");

              if (rs.next()) {
                  sesion = request.getSession(false);
                  sesion.setAttribute("error", "7");
                  response.sendRedirect("error.jsp");
              } else {
                  statement.executeUpdate("insert into hoteles values('"+id_hotel+"','"+nombrehotel+"','"+cadenahotelera+"','"+num_habitaciones+"','"+calle+"','"+numero+"','"+codigopostal+"','"+ciudad+"','"+provincia+"','"+pais+"')");
                  response.sendRedirect("menu.jsp");
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
        else {
              sesion = request.getSession(false);
              sesion.setAttribute("error", "8");
              response.sendRedirect("error.jsp");
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
