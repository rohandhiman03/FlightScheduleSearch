package app_v1.Flight;

import app_v1.User.UserOptions;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author smita
 */
public interface IFlighSearch {
    public  ArrayList<FlightInfo> GetFlights(UserOptions a_userOptions);
    public boolean SaveFlights(int a_userID, int a_flightID);
    public ArrayList<FlightInfo> GetFavFlights(int a_userID);
    public void DelFromFav(int a_userID, int a_flightID);
    public  ArrayList<FlightInfo> GetDiscountedPrices(UserOptions.user_type a_usertype);
} 
