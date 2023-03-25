/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.Flight;

/**
 *
 * @author smita
 */
public class FlightInfo implements Comparable<FlightInfo>{
    
    /**
    *
    *  m_flightID local id assigned to individual flight details
    */
    

    protected int    m_flightID = -1;
    /**
    *  m_ID USER ID
    */
    protected int    m_ID = -1;    
    /**
    *  m_originAirport Source/Origin Airport
    */
    protected String m_originAirport = "";
    /**
    * m_destinationAirport Destination Airport
    */
    protected String m_destinationAirport = "";
    /**
    * m_Carrier_in Inbound Carrier
    */
    protected String m_Carrier_in = "";
    /**
    * m_Carrier_out Outbound Carrier
    */
    protected String m_Carrier_out = "";
    /**
    * m_departureDate Departure Date
    */
    protected String m_departureDate = "";
    /**
    * m_returnDate Return Date
    */
    protected String m_returnDate = "";  
    /**
    * m_price Flight Price
    */
    protected double m_price;
    
    

    @Override
    public int compareTo(FlightInfo f)
    {       
        return Double.compare(m_price,f.m_price);        
    }
    
    /**
     * 
     * @return returns the origin airport
     */
    public String getOriginAirport()
    {
        return this.m_originAirport;        
    }
     /**
     * 
     * @return returns the destination airport
     */
    public String getDestinationAirport()
    {
        return this.m_destinationAirport;        
    }
     /**
     * 
     * @return returns the Inbound carrier
     */
    public String getInCarrier()
    {
        return this.m_Carrier_in;        
    }
     /**
     * 
     * @return returns the Outbound Carrier
     */
    public String getOutCarrier()
    {
        return this.m_Carrier_out;        
    }
    /**
     * 
     * @return returns the Departure Date
     */
    public String getDepartureDate()
    {
        return this.m_departureDate;        
    }
    /**
     * 
     * @return returns the Return Date
     */
    public String getReturnDate()
    {
        return this.m_returnDate;        
    }
    /**
     * 
     * @return returns the Price of the flight
     */
    public double getPrice()
    {
        return this.m_price;        
    }
    /**
     * 
     * @return returns the Flight ID
     */
    public int getFlightID()
    {
        return this.m_flightID;        
    }
    /**
     * 
     * @return returns the User ID
     */
    public int getUserID()
    {
        return this.m_ID;        
    }
}
