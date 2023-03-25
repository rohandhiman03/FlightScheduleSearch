/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.User;

import DataBase.DB;
import app_v1.Flight.FlightInfo;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

/**
 *
 * @author smita
 */
public class UserLoginGateway {
   
    private static UserLoginGateway m_loginGateway = null;
    
    protected UserLoginGateway()
    {
    } 
    
    protected static UserLoginGateway getInstance()
    {
        if(m_loginGateway == null)
        {
             m_loginGateway = new UserLoginGateway();
            return m_loginGateway;
        }  
        return m_loginGateway;
    }
    
    public static void createTable()
    {
        String createSql = "create table if not exists userInfo(m_id serial Primary Key ,m_username varchar,m_password varchar)";
             
        
        try(Connection con = DB.getInstance().open()){
            con.createQuery(createSql).executeUpdate();
        }
    }
    
    protected  boolean SaveUser(UserInfo a_userInfo)
    {  
        try(Connection con = DB.getInstance().open()){

        
        String sql = "INSERT INTO userInfo(m_username, m_password) VALUES(:user, :pwd)";
        a_userInfo.m_id = (int) con.createQuery(sql,true)
                .addParameter("user", a_userInfo.m_username).addParameter("pwd", a_userInfo.m_password)
                .executeUpdate().getKey();
        }
        catch(Sql2oException e)
        {
            System.out.println(e);
            return false;
        }
        return true;
      
    }
    
    protected boolean CheckIfUserNameExists(String a_userName)
    {
        Sql2o db = DB.getInstance();
        
        try(Connection con = DB.getInstance().open())
        {
            String sql = "(SELECT * FROM userinfo WHERE m_username=:user)";
            UserInfo u = con.createQuery(sql).addParameter("user",a_userName).executeAndFetchFirst(UserInfo.class); 
            return u != null;
        }
        catch(Sql2oException e)
        {
            return false;
        }
    }
    
    protected UserInfo GetUser(String a_userName, String a_password)
    {
        Sql2o db = DB.getInstance();
        
        try(Connection con = DB.getInstance().open())
        {
            String sql = "SELECT * FROM userInfo WHERE m_username=:userName and m_password=:pwd";
            UserInfo u = null;
            
            u = con.createQuery(sql).addParameter("userName",a_userName)
                    .addParameter("pwd",a_password).executeAndFetchFirst(UserInfo.class);
            return u;
                    }
        catch(Sql2oException e)
        {
            return null;
        }
           
    }
    
    protected boolean deleteUser(FlightInfo a_flight)
    { 
        //delete all entries related to the user
        return true;
    }
    
    
       
    
    
    
    

    
    
}
