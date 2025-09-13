/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Session;
import jakarta.mail.Authenticator;
import java.util.Properties;

public class sessions {
    public static int userId = -1;
    public static String username = "";
    
    // Start the session by setting the userId and username
    public static void startSession(int id, String uname) {
        userId = id;
        username = uname;
    }
    
    // End the session by resetting the userId and username
    public static void endSession() {
        userId = -1;
        username = "";
    }

    static Session getInstance(Properties props, Authenticator authenticator) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
