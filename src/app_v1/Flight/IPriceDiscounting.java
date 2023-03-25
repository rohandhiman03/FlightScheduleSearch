/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app_v1.Flight;

import app_v1.Flight.FlightInfo;
import java.util.ArrayList;

/**
 *
 * @author smita
 */
public interface IPriceDiscounting {
   /**
    * 
    * @param a_flight all flight list passed on which discount has to be processed according to the Fare Type selected
    */ 
    public void UpdateFlightPrices( ArrayList<FlightInfo> a_flight);
}
