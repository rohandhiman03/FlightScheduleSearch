package app_v1.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author smita
 */
public interface IUserSearch {
    
    public boolean SaveUser(String a_username, String a_password); 
    
    public UserInfo GetUser(String a_userName, String a_password );
   
    public boolean CheckIfUserNameExists(String a_userName);
    
}
