/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.Flight;

import DataBase.DB;
import java.util.ArrayList;
import org.sql2o.*;
import java.util.List;


/**
 *
 * @author smita
 */
public class FavFlightsGateway {   
/**
 *   
* Creation of flights table (if the table doesnt exist) for inserting the favourite flight data selected by user 
 */
    public static void CreateTable()    
    {

        String createSql = "create table if not exists flights(m_flightid serial, m_id int not null references userInfo(m_id),"
                + "m_originairport varchar, m_destinationairport varchar,"
                + "m_carrier_in varchar, m_carrier_out varchar, m_departuredate varchar, m_returndate varchar,m_price float,"
                + "Primary Key (m_id, m_originairport, m_destinationairport,m_carrier_in,m_carrier_out,m_departuredate,m_returndate,m_price )) ";     
        try(Connection con = DB.getInstance().open()){
            con.createQuery(createSql).executeUpdate();
        }
        
        catch(Sql2oException e)
        {
            System.out.println(e);
        }

    }
            /**
         * Inserting the favourite flights selected by the user in table flights
         */
    public boolean SaveFlight(FlightInfo a_flight)    
    {  

        try(Connection con = DB.getInstance().open()){

        String sql = "INSERT INTO flights (m_id, m_originairport, m_destinationairport, m_carrier_in, m_carrier_out,"
                + "m_departuredate,m_returndate, m_price) VALUES (:id, :originairport, :destinationairport, :carrier_in, :carrier_out,"
                + " :departuredate,:returndate,:price )";
        con.setRollbackOnException(false);
        a_flight.m_flightID = (int) con.createQuery(sql,true)                
                .addParameter("id", a_flight.m_ID)
                .addParameter("originairport", a_flight.m_originAirport)
                .addParameter("destinationairport", a_flight.m_destinationAirport)
                .addParameter("carrier_in", a_flight.m_Carrier_in)
                .addParameter("carrier_out", a_flight.m_Carrier_out)                
                .addParameter("departuredate", a_flight.m_departureDate)
                .addParameter("returndate", a_flight.m_returnDate) 
                .addParameter("price", a_flight.m_price)
                .executeUpdate().getKey();
        }
        catch(Sql2oException e)
        {
            return false;
        }
        return true;
      
    }
             /**
             * function to fetch the Favourite flights of an user
             */   
    public ArrayList<FlightInfo> GetFavFlights(int a_userID)   

    {   
        List<FlightInfo> flights;
        
        try(Connection con = DB.getInstance().open())
        {
            String sql = "SELECT * FROM flights WHERE m_id=:id";
            flights = con.createQuery(sql).addParameter("id",a_userID).executeAndFetch(FlightInfo.class);
        }  
        catch(Sql2oException e)
        {
            return null;
        }
        ArrayList<FlightInfo> arrayListFlights = new ArrayList(flights);
        return arrayListFlights;
    }
        /**
     * function to delete the flights selected by user from the users Favourites
     */
    public boolean DeleteFlight(int a_userID, int a_flightID)  

    { 
         try(Connection con = DB.getInstance().open())
        {
            String sql = "DELETE FROM flights WHERE m_flightid =:id";
            con.createQuery(sql).addParameter("id",a_flightID).executeUpdate();
        }  
        catch(Sql2oException e)
        {
            return false;
        }
        
        return true;
    }
}
