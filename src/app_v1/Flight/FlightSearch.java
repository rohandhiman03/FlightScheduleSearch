/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.Flight;

import app_v1.User.UserOptions;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.*;
import java.net.*;

import java.util.ArrayList;

/**
 *
 * @author smita
 */

public class FlightSearch implements IFlighSearch{
    private static FavFlightsGateway flightGateway;
    /**
     * m_flightList Arraylist for list of all flights
     */
    ArrayList<FlightInfo> m_flightList;  
    public FlightSearch()   
    {
       flightGateway = new FavFlightsGateway();
       m_flightList = new ArrayList<>();
    }
        public static String getData(String urlLink) {   
            /**
             * getting the flight details from Skyscanner API
             */
        String json = "";
        try {    

        URL url = new URL(urlLink);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
          throw new RuntimeException("Failed : HTTP error code : "
              + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
          (conn.getInputStream())));

        String output;

        while ((output = br.readLine()) != null) {
          json += output;
        }

        conn.disconnect();

        } catch (MalformedURLException e) {

        e.printStackTrace();

        } catch (IOException e) {

        e.printStackTrace();

        }
       return json;
  }
        /**
         * 
         * Function to call API and get the data of all the flights as required by the user
         * @param a_userOptions all inputs given by the user on frontend
         * @return returns all flight details for the given input
         */
    @Override
    public  ArrayList<FlightInfo> GetFlights(UserOptions a_userOptions)    
    {

        
        while(m_flightList.size() > 0){             //clearing the old data present in arraylist
            m_flightList.remove(m_flightList.size()-1);
        }
        String url ="https://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/" + a_userOptions.getMarket() +"/" + a_userOptions.getCurrency() + "/"+
                a_userOptions.m_locale + "/" + a_userOptions.getOriginCity() +  "/"+ 
                a_userOptions.getDestinationCity()+"/"+ a_userOptions.getDepartureDate() +"/"+ a_userOptions.getReturnDate() +
                "?apikey=prtl6749387986743898559646983194";
        
        
        String output = getData(url);        
        JSONParser parser = new JSONParser();
        try{            
            Object obj = parser.parse(output);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray carriersAll = (JSONArray) jsonObject.get("Carriers");
            JSONArray quotesAll = (JSONArray) jsonObject.get("Quotes");
            JSONArray PlacesAll = (JSONArray) jsonObject.get("Places");


                for(int j=0;j<quotesAll.size();j++)
                {
                    FlightInfo f = new FlightInfo();

                    JSONObject quoteData = (JSONObject) quotesAll.get(j);
                    
                    JSONObject outBound = (JSONObject)quoteData.get("OutboundLeg");
                    int originStationID = Integer.parseInt(outBound.get("OriginId").toString());
                    int destStationID = Integer.parseInt(outBound.get("DestinationId").toString());
                    String originStation="";
                    String destStation="";
                            
                    for(int k =0; k < PlacesAll.size();k++)
                    {
                        JSONObject placeData = (JSONObject) PlacesAll.get(k);
                        int placeid = Integer.parseInt(placeData.get("PlaceId").toString());
                        if(placeid == originStationID)
                            originStation = placeData.get("Name").toString();
                        if(placeid == destStationID)
                            destStation = placeData.get("Name").toString();
                        if(!destStation.isEmpty() && !originStation.isEmpty())
                        {
                            break;
                        }
                    }
                    
                    String departureDate = outBound.get("DepartureDate").toString();
                    double price = Double.parseDouble(quoteData.get("MinPrice").toString());
                    JSONArray carriers = (JSONArray) outBound.get("CarrierIds");
                    
                    int carrierId = Integer.parseInt(carriers.get(0).toString());
                    String carrierName="";
                    for(int i=0;i<carriersAll.size();i++)
                    {
                        JSONObject CarrierData = (JSONObject) carriersAll.get(i);
                        int cID = Integer.parseInt(CarrierData.get("CarrierId").toString());    
                        if(cID == carrierId)
                        {
                            carrierName = CarrierData.get("Name").toString(); 
                            break;
                        }
                        
                    }

                    String departureDate_R="";
                    String carrierNameR="";
                    if(!a_userOptions.getReturnDate().isEmpty())
                    {
                        JSONObject inBound = (JSONObject)quoteData.get("InboundLeg");
                        departureDate_R = inBound.get("DepartureDate").toString();
                        JSONArray carriers_R = (JSONArray) inBound.get("CarrierIds");

                        int carrierId_R = Integer.parseInt(carriers_R.get(0).toString());
                        for(int i=0;i<carriersAll.size();i++)
                        {
                            JSONObject CarrierData = (JSONObject) carriersAll.get(i);
                            int cID = Integer.parseInt(CarrierData.get("CarrierId").toString());    
                            if(cID == carrierId_R)
                            {
                                carrierNameR = CarrierData.get("Name").toString(); 
                                break;
                            }

                        }
                        
                    }
                    

                    f.m_departureDate = departureDate;               
                    f.m_destinationAirport = destStation;
                    f.m_Carrier_in = carrierNameR;
                    f.m_originAirport = originStation;                   
                    f.m_returnDate = departureDate_R;
                    f.m_Carrier_out = carrierName;
                    f.m_price = price;
                    f.m_ID = a_userOptions.getUserID();
                    f.m_flightID = j;
                
                m_flightList.add(f);
                }
           
        }
        catch(ParseException pe)
        {
            
        }
        return m_flightList;
    }
    
    @Override
        /**
     * Function to get discount on the prices as per the Fare Type selected by the user
     *  ARMED_FORCES 30% Armed Forces get discount on flight price
        * STUDENT Students get 10% discount on flight price
        * SENIOR_CITIZEN SENIOR CITIZEN get 20% discount on flight price
     */
    
    public  ArrayList<FlightInfo> GetDiscountedPrices(UserOptions.user_type a_usertype)  

    {

        IPriceDiscounting priceDiscounting = null;
        
       if(a_usertype == UserOptions.user_type.ARMED_FORCES)
           priceDiscounting = new PricingForArmedForces();
       else if(a_usertype == UserOptions.user_type.STUDENT)
           priceDiscounting = new PricingForStudent();
       else if(a_usertype == UserOptions.user_type.SENIOR_CITIZEN)
           priceDiscounting = new PricingForSeniorCitizen();
       
        priceDiscounting.UpdateFlightPrices(m_flightList);
         return m_flightList;
    }


     public static void CreateTable()
     {
         FavFlightsGateway.CreateTable();
     }
     
     /**
      * Function to save the flight as favourite
      * @param a_userID UserID of the User
      * @param a_flightID Flight ID of the flight selected to Save as favourite
      * @return 
      */  
     public boolean SaveFlights(int a_userID, int a_flightID) // flights displayed in sequence of flightID No need to search through all flights and get the flight with a_flightID
     {      
         FlightInfo f = m_flightList.get(a_flightID);
         return flightGateway.SaveFlight(f);
     }
     
     /**
      * 
      * @param a_userID UserID of the User
      * @return returns the flights selected favourite by the user
      */
     
     public ArrayList<FlightInfo> GetFavFlights(int a_userID)
     {
         return flightGateway.GetFavFlights(a_userID);
     }
     
     /**
      * Function to delete the user selected flight from his/her favourite tab
      * @param a_userID UserID of User
      * @param a_flightID flight id of the flight selected to be deleted
      */
     public void DelFromFav(int a_userID, int a_flightID)
     {  
         flightGateway.DeleteFlight(a_userID, a_flightID);
     }
}
