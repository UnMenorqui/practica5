package com.vueloWSoap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "VueloWS")
public class VueloWSoap {

    /**
     * Web service operation
     * @param id_vuelo
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "consulta_libres")
    public int consulta_libres(@WebParam(name = "id_vuelo") int id_vuelo, @WebParam(name = "fecha") int fecha) {
        int plazas_libres = 0;
        Connection connection = null;
        try {            
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");        

            // create a database connection
            //connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db"); //Mac Aleix
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/p5.db"); //Mac Toni
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            System.out.println("id_vuelo: "+id_vuelo+" fecha:"+ fecha);
            
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
                                                + "where id_vuelo = " + id_vuelo + " and fecha = " + fecha);

            plazas_libres = Integer.parseInt(rs.getString("num_plazas_max")) - Integer.parseInt(rs.getString("num_plazas_ocupadas"));

        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return plazas_libres;
    }

    /**
     * Web service operation
     * @param id_vuelo
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "reserva_plaza")
    public int reserva_plaza(@WebParam(name = "id_vuelo") int id_vuelo, @WebParam(name = "fecha") int fecha) {
        int plazas_ocupadas = -1;
        Connection connection = null;
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");        

            // create a database connection
            //connection = DriverManager.getConnection("jdbc:sqlite:/Users/aleixabrieprat/Documents/FIB/7e quadrimestre/ad/lab/p5.db"); //Mac Aleix
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/Toni/Desktop/BD/p5.db"); //Mac Toni
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
            System.out.println("id_vuelo: "+id_vuelo+" fecha:"+ fecha);
            
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
                                                + "where id_vuelo = " + id_vuelo + " and fecha = " + fecha);
            rs.next();
            if (Integer.parseInt(rs.getString("num_plazas_max")) - Integer.parseInt(rs.getString("num_plazas_ocupadas")) != 0) {
                statement.executeUpdate("update vuelo_fecha "
                                      + "set num_plazas_ocupadas = (num_plazas_ocupadas + 1) "
                                      + "where id_vuelo = " + id_vuelo + " and fecha = " + fecha);
                
                rs = statement.executeQuery("select num_plazas_ocupadas "
                                          + "from vuelo_fecha "
                                          + "where id_vuelo = " + id_vuelo + " and fecha = " + fecha);
                if (rs.next()) plazas_ocupadas = Integer.parseInt(rs.getString("num_plazas_ocupadas"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return plazas_ocupadas;
    }
}
