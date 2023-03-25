/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_v1.User;

import app_v1.User.UserLoginGateway;
/**
 *
 * @author smita
 */

public class UserSearch implements IUserSearch{

    static private UserInfo  m_userInfo;

    public static void CreateTable()
     {
         UserLoginGateway.createTable();
     }
    public UserSearch()
    {
        if(m_userInfo == null)
        {
             m_userInfo = new UserInfo();            
        }  
    }
    public UserInfo GetUserInfo()
    {
        return m_userInfo;
    }
    
    @Override
    public boolean SaveUser(String a_username, String a_password)
    {        
        if(GetUser(a_username,a_password)==null)
        {     
            
            m_userInfo.m_password = a_password;
            m_userInfo.m_username = a_username;
            return UserLoginGateway.getInstance().SaveUser(m_userInfo);
        }
        return false;
    }
    
    @Override
    public UserInfo GetUser(String a_userName,String a_password )
    {        
        return UserLoginGateway.getInstance().GetUser(a_userName,a_password);
    }
    
    
    @Override
    public boolean CheckIfUserNameExists(String a_userName)
    {
        return UserLoginGateway.getInstance().CheckIfUserNameExists(a_userName);
    } 
    
}
