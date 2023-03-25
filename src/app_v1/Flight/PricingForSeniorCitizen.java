/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.Flight;

import app_v1.Flight.FlightInfo;
import java.util.ArrayList;

/**
 *
 * @author smita
 */
public class PricingForSeniorCitizen implements IPriceDiscounting{
 /**
    * This function is for applying discount for Fare Type Senior Citizen
    * @param a_flight all flight list passed on which discount has to be processed according to the Fare Type selected
    */
    @Override
    public void UpdateFlightPrices(ArrayList<FlightInfo> a_flight) {
        for(int i=0; i < a_flight.size(); i++)
        {
            a_flight.get(i).m_price -= (a_flight.get(i).m_price * 20) / 100;
        } 
    }
    
}
