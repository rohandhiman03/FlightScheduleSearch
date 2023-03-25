/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package app_v1.User;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rohan
 */
public class UserLoginGatewayTest {
    
     IUserSearch m_IUserInfoSearch;
     
    public UserLoginGatewayTest() {
    }

    /**
     * This function will test for Signing Up Of User
     */
    @Test
    public void testSignUp() {
    int actual = 0 ;
    int expected = 1;
    
    UserLoginGateway ulog = new UserLoginGateway();
    
    IUserSearch IUserInfo = new UserSearch();
    String username = "unittest";
    String password = "1234";
    
    boolean check = IUserInfo.SaveUser(username, password);
    
    if(check == true){
        actual=1;
    }
     System.out.println("Signup success: "+check);
     
        assertEquals(expected, actual);
        
       
    }
    
    /**
     * This function will test for Signing in of existing user
     */
    @Test
    public void testSignIn() {
        int actual = 0 ;
    int expected = 1;
    
    UserLoginGateway ulog = new UserLoginGateway();
    
    String username = "unittest";
    String password = "1234";
   
    m_IUserInfoSearch = new UserSearch();
    
    UserInfo u = m_IUserInfoSearch.GetUser(username, password);
    
    if(u!=null){
        actual = 1;
    }
    
        assertEquals(expected, actual);
    }
    
}
