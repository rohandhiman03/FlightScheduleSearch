package DataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author smita
 */
import org.sql2o.*;

public class DB {
    
    private static Sql2o m_sql2o = null;    
    private DB()
    {
    } 
     
    public static Sql2o getInstance()
    {
        if(m_sql2o == null)
        {
             m_sql2o = new Sql2o("jdbc:postgresql://localhost:5432/Flight", "postgres", "password");
            return m_sql2o;
        }  
        return m_sql2o;
    }   
}