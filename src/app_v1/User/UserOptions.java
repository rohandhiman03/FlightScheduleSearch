/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.User;
import java.time.LocalDate;
/**
 *
 * @author smita
 */

public class UserOptions {
   
     public enum user_type {
    ARMED_FORCES,
    STUDENT,
    SENIOR_CITIZEN,
    DOCTOR_NURSES,
    REGULAR_FARE
  }
      String m_market = "IN";
      String m_currency = "INR";
      public String m_locale = "en-GB";

      String m_outboundPartialDate = LocalDate.now().toString();
      String m_inboundPartialDate = "2023-12"; //
      int m_userID = -1;

      String m_originCity = "lond";
      String m_destCity = "pari";

       
    public UserOptions(String a_market, String a_currency, 
            String a_outboundPartialDate,String a_inboundPartialDate, int a_userID, 
            String a_originCity, String a_destCity)
    {
        this.m_market = a_market;
        this.m_currency = a_currency;

        this.m_outboundPartialDate = a_outboundPartialDate;
        this.m_inboundPartialDate = a_inboundPartialDate;
        this.m_userID = a_userID;  

        this.m_originCity = a_originCity;
        this.m_destCity = a_destCity;

    }
    
    public String getOriginCity()
    {
        return this.m_originCity;        
    }
    public String getDestinationCity()
    {
        return this.m_destCity;        
    }
    public String getMarket()
    {
        return this.m_market;        
    }
    public String getCurrency()
    {
        return this.m_currency;        
    }
    public String getDepartureDate()
    {
        return this.m_outboundPartialDate;        
    }
    public String getReturnDate()
    {
        return this.m_inboundPartialDate;        
    }
    public int getUserID()
    {
        return this.m_userID;        
    }
    

};