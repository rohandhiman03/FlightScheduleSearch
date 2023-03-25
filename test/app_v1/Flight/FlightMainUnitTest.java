/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package app_v1.Flight;

import app_v1.User.UserOptions;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rohan
 */
public class FlightMainUnitTest {
    
    public FlightMainUnitTest() {
    }

/**
 * This function will test API Connectivity and whether flight details are retrieved
 */
    @Test
    public void testFlightSearch() {
        int actual = 0;
        int expected = 1;
//        

ArrayList<FlightInfo> flightlist = new ArrayList<>();
        System.out.println("app_v1.Flight.FlightSearchTest");
        
        FlightSearch fs = new FlightSearch();
        
        UserOptions userOptions = new UserOptions("IN",
                    "INR",
                    "2022-11-16",
                    "2022-11-16",
                    3,
                    "PNQ",
                    "BOM");
        
        flightlist = fs.GetFlights(userOptions);
        if(!flightlist.isEmpty()){
            actual = 1;
        }
                assertEquals(expected, actual);
    }
    
    /**
     * This function tests saving of favourite flight data into database
     */
    @Test
    public void testSaveFlight() {
        ArrayList<FlightInfo> flightlist = new ArrayList<>();
        FlightSearch fs = new FlightSearch();
        
        UserOptions userOptions = new UserOptions("IN",
                    "INR",
                    "2022-11-17",
                    "2022-11-18",
                    3,
                    "BOM",
                    "PNQ");
        
        flightlist = fs.GetFlights(userOptions);
        
        
        boolean check = fs.SaveFlights(3, 0);
        
        System.out.println("Value Returned: "+check);
    }
    
    /**
     * This function tests deletion of selected favourite flight data from database for particular user
     */
     @Test
    public void testDeleteFlight() {
         int actual = 0;
         int expected = 1;
        FavFlightsGateway fs = new FavFlightsGateway();
        boolean check = fs.DeleteFlight(3, 76);
         if(check == true){
             actual = 1;
         }
         
         assertEquals(expected, actual);
    }
    
}
