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


@WebServlet(urlPatterns = {"/buscarvuelo"})

public class buscarvuelo extends HttpServlet { 
    
    
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
          
          
          String numerovuelo = request.getParameter("numero");
          String compania = request.getParameter("compania");
          String origen = request.getParameter("origen");
          String destino = request.getParameter("destino");
          
          ResultSet rs; 
          if (numerovuelo.equals("")) {
              if (compania.equals("")) {
                  if (origen.equals("")) {
                      if (destino.equals("")) {
                         rs = statement.executeQuery("Select * from vuelos"); //Buscar todo (parametros todos vacíos)
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where destino='"+destino+"'"); //Buscar solo por destino
                      } 
                  }
                  else {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where origen='"+origen+"'"); //Buscar solo por origen
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where destino='"+destino+"' and origen='"+origen+"'"); //Buscar por origen y destino
                      }
                  }
              }
              else {
                  if (origen.equals("")) {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where companyia='"+compania+"'"); //Buscar solo por compañia
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where companyia='"+compania+"' and destino='"+destino+"'"); //Buscar por compañia y destino
                      }
                  }
                  else {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where companyia='"+compania+"' and origen='"+origen+"'"); //Buscar por compañia y origen
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where companyia='"+compania+"' and destino='"+destino+"' and origen='"+origen+"'"); //Buscar por compañia, origen y destino
                      }
                  }
              }
          }
          else {
              if (compania.equals("")) {
                  if (origen.equals("")) {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"'"); //Buscar por numero de vuelo
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and destino='"+destino+"'"); //Buscar por numero de vuelo y destino

                      } 
                  }
                  else {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and origen='"+origen+"'"); //Buscar por numero de vuelo y origen
                          
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and destino='"+destino+"' and origen='"+origen+"'"); //Buscar por numero de vuelo, destino y origen
                          
                      }
                  }
              }
              else {
                  if (origen.equals("")) {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and companyia='"+compania+"'"); //Buscar por numero de vuelo y compañia
                          
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and companyia='"+compania+"' and destino='"+destino+"'"); //Buscar por numero de vuelo, compañia y destino
                          
                      }
                  }
                  else {
                      if (destino.equals("")) {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and companyia='"+compania+"' and origen='"+origen+"'"); //Buscar por numero de vuelo, compañia y origen
                          
                      }
                      else {
                          rs = statement.executeQuery("Select * from vuelos where id_vuelo='"+numerovuelo+"' and companyia='"+compania+"' and destino='"+destino+"' and origen='"+origen+"'"); //Buscar todo
                          
                      }
                  }
              } 
          }
          if (rs.next()) {
              vuelo vuelo1;
              ArrayList<vuelo> vuelo = new ArrayList<>();
              vuelo1 = new vuelo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
              vuelo.add(vuelo1);
              while (rs.next()) {
                  vuelo1 = new vuelo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                  vuelo.add(vuelo1);
              }
              sesion = request.getSession(false);
              sesion.setAttribute("vuelos", vuelo);
              response.sendRedirect("buscarvuelo.jsp");
          } else {
              sesion = request.getSession(false);
              sesion.setAttribute("error", "4");
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
