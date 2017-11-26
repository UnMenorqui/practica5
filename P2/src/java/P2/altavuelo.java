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


@WebServlet(urlPatterns = {"/altavuelo"})

public class altavuelo extends HttpServlet { 
    
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
          //connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db");
          //connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/practica 2/p2.db"); //Mac Aleix
          connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
          
          
          String numerovuelo = request.getParameter("numero");
          String compania = request.getParameter("compañia");
          String origen = request.getParameter("origen");
          String horasalida = request.getParameter("horasalida");
          String destino = request.getParameter("destino");
          String horallegada = request.getParameter("horallegada");
          
          
          /** Comparar Horas llegada/salida
          
          LocalTime llegada = LocalTime.parse(horallegada);
          LocalTime salida = LocalTime.parse(horasalida);
          
          if (salida.compareTo(llegada) > 0) {
              response.sendRedirect("error.jsp");
          }
          **/
          
          ResultSet rs = statement.executeQuery("select * from vuelos where id_vuelo ='"+numerovuelo+"'");
          
          if (rs.next()) {
              sesion = request.getSession(false);
              sesion.setAttribute("error", "5");
              response.sendRedirect("error.jsp");
          } else {
              statement.executeUpdate("insert into vuelos values('"+numerovuelo+"','"+compania+"','"+origen+"','"+horasalida+"','"+destino+"','"+horallegada+"')");
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
