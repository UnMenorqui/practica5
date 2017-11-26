package P2;




import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns={"/login"})
public class login extends HttpServlet {
    
    private HttpSession sesion;
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

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
        HttpSession sesion = request.getSession();
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
          statement.executeUpdate("create table if not exists usuarios (id_usuario string primary key, password string)");
          statement.executeUpdate("create table if not exists vuelos (id_vuelo integer primary key, companyia string, origen string, hora_salida string, destino string, hora_llegada string)");
          statement.executeUpdate("create table if not exists hoteles (id_hotel integer primary key, nom_hotel string, cadena string,calle string, numero integer,codigo_postal string,ciudad string,provincia string,pais string,numb_hab integer)");
          
          String username = request.getParameter("name"); //works with tag name=""
          //System.out.println("username: " + username);
          String password = request.getParameter("password");
          //System.out.println("password: " + password);

          PreparedStatement preparedStatement = connection.prepareStatement("select * from usuarios where id_usuario = ?");
          preparedStatement.setString(1,username);
          
          //Cerca d'usuari
          ResultSet rs = preparedStatement.executeQuery();       
         
          
          
          if (rs.next() && sesion.getAttribute("usuario") == null ) {
              //Usuari existent
              sesion.setAttribute("usuario", username);
              response.sendRedirect("menu.jsp");

          }
          else {
              sesion = request.getSession(false);
              sesion.setAttribute("error", "1");
              response.sendRedirect("error.jsp");
          }
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
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
    
    protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //me llega la url "proyecto/login/out"
        String action = (request.getPathInfo() != null ? request.getPathInfo():"");
        HttpSession sesion = request.getSession();
        if (action.equals("/out")) {
            sesion.invalidate();
            response.sendRedirect("/login.jsp");
        }else {
            
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestGET(request, response);
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