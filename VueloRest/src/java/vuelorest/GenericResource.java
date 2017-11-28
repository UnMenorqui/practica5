package vuelorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of restad.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        return "<html><head/><body><h1>Hello world!</h1></body></html>";
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    
    /**
     * Método GET para comprobar los asientos libres dados un id y una fecha
     *
     * @param id
     * @param fecha
     * @return
     */
    @Path("consultaLibres")
    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String consultalibres(@QueryParam("id") String id, @QueryParam("fecha") String fecha) {
        // Completar codigo
        int plazas_libres = 0;
        Connection connection = null;
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db"); //Mac Aleix
            //connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            System.out.println("id_vuelo: "+id+" fecha:"+ fecha);
            
            statement.executeUpdate("create table if not exists vuelo_fecha(id_vuelo integer primary key, fecha integer, num_plazas_ocupadas integer, num_plazas_max integer)");
            ResultSet rs = statement.executeQuery("select * from vuelo_fecha");
            boolean esta = false;
            while (rs.next()) {
                esta = true;
            }
            if (!esta) {
                statement.executeUpdate("insert into vuelo_fecha values(1, 20171122, 0, 100)");
                statement.executeUpdate("insert into vuelo_fecha values(2, 20171122, 0, 50)");
                statement.executeUpdate("insert into vuelo_fecha values(3, 20171122, 0, 10)");
            }

            rs = statement.executeQuery("select * "
                    + "from vuelo_fecha "
                    + "where id_vuelo = " + Integer.parseInt(id) + " and fecha = " + Integer.parseInt(fecha));

            plazas_libres = Integer.parseInt(rs.getString("num_plazas_max")) - Integer.parseInt(rs.getString("num_plazas_ocupadas"));
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        
        String resultado = "";
        if(plazas_libres < 0) resultado = "ERROR!";
        else if(plazas_libres == 0) resultado = "No hay habitaciones libres!";
        else resultado = "El numero de plazas libres es: " + plazas_libres;
        return " <!DOCTYPE html>" + 
               " <html>" +
               "    <head>" +
               "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
               " <style>.message {\n" +
                    "  max-width: 800px;\n" +
                    "  padding: 15px;\n" +
                    "  margin: 0 auto;\n" +
                    "  margin-top:100px;\n" +
                    "  margin-bottom: 100px;\n" +
                    "  background: #CDCECD;\n" +
                    "  border-radius: 28px 28px 28px 28px;\n" +
                    "  -moz-border-radius: 28px 28px 28px 28px;\n" +
                    "  -webkit-border-radius: 28px 28px 28px 28px;\n" +
                    "  border: 1px solid #000000;\n" +
                    "} #login_box {\n" +
                    "    position: fixed;\n" +
                    "    top: 0;\n" +
                    "    right: 0;\n" +
                    "    bottom: 0;\n" +
                    "    left: 0;\n" +
                    "    background-size: cover;\n" +
                    "    background-position: 50% 50%;\n" +
                    "    background-repeat: repeat;\n" +
                    "}</style>" +
               "    </head>" +
               "    <body> " +
               "        <div class='login_box'> <div class='message'>" +
                            resultado +
               "    </body>" +
               "</html>";
    }
    
     /**
     * Método POST para comprobar los asientos libres dados un id y una fecha
     *
     * @param id
     * @param fecha
     * @return
     */
    @Path("reservarPlaza")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String reserva(@FormParam("id") String id, @FormParam("fecha") String fecha) {
        // Completar codigo
        int plazas_ocupadas = -1;
        Connection connection = null;
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db");
            //connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            System.out.println("id_vuelo: "+id+" fecha:"+ fecha);

            statement.executeUpdate("create table if not exists vuelo_fecha(id_vuelo integer primary key, fecha integer, num_plazas_ocupadas integer, num_plazas_max integer)");
            ResultSet rs = statement.executeQuery("select * from vuelo_fecha");
            boolean esta = false;
            while (rs.next()) {
                esta = true;
            }
            if (!esta) {
                statement.executeUpdate("insert into vuelo_fecha values(1, 20171122, 0, 100)");
                statement.executeUpdate("insert into vuelo_fecha values(2, 20171122, 0, 50)");
                statement.executeUpdate("insert into vuelo_fecha values(3, 20171122, 0, 10)");
            }

            rs = statement.executeQuery("select * "
                    + "from vuelo_fecha "
                    + "where id_vuelo = " + Integer.parseInt(id) + " and fecha = " + Integer.parseInt(fecha));
            rs.next();
            if (Integer.parseInt(rs.getString("num_plazas_max")) - Integer.parseInt(rs.getString("num_plazas_ocupadas")) != 0) {
                statement.executeUpdate("update vuelo_fecha "
                        + "set num_plazas_ocupadas = (num_plazas_ocupadas + 1) "
                        + "where id_vuelo = " + Integer.parseInt(id) + " and fecha = " + Integer.parseInt(fecha));

                rs = statement.executeQuery("select num_plazas_ocupadas "
                        + "from vuelo_fecha "
                        + "where id_vuelo = " + Integer.parseInt(id) + " and fecha = " + Integer.parseInt(fecha));
                if (rs.next()) {
                    plazas_ocupadas = Integer.parseInt(rs.getString("num_plazas_ocupadas"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        String resultado ="";
        if(plazas_ocupadas < 0) resultado ="-1";
        else resultado ="El numero de plazas ocupadas es:"+ plazas_ocupadas;
        return " <!DOCTYPE html>" + 
               " <html>" +
               "    <head>" +
               "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
               " <style>.message {\n" +
                    "  max-width: 800px;\n" +
                    "  padding: 15px;\n" +
                    "  margin: 0 auto;\n" +
                    "  margin-top:100px;\n" +
                    "  margin-bottom: 100px;\n" +
                    "  background: #CDCECD;\n" +
                    "  border-radius: 28px 28px 28px 28px;\n" +
                    "  -moz-border-radius: 28px 28px 28px 28px;\n" +
                    "  -webkit-border-radius: 28px 28px 28px 28px;\n" +
                    "  border: 1px solid #000000;\n" +
                    "} #login_box {\n" +
                    "    position: fixed;\n" +
                    "    top: 0;\n" +
                    "    right: 0;\n" +
                    "    bottom: 0;\n" +
                    "    left: 0;\n" +
                    "    background-size: cover;\n" +
                    "    background-position: 50% 50%;\n" +
                    "    background-repeat: repeat;\n" +
                    "}</style>" +
               "    </head>" +
               "    <body> " +
               "        <div class='login_box'> <div class='message'>" +
                            resultado +
               "    </body>" +
               "</html>";
    }
}
