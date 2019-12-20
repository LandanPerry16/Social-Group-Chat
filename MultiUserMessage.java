//Class: Multi User Message
//Coder:Asa Brown
package Message;
import java.io.PrintWriter;
import java.util.*;
/**
 *
 * @author Asa
 */
public class MultiUserMessage extends Message{
     String Users;

    public MultiUserMessage(String Mess, String timeSent, String senderName, String Users) {
        super(Mess, timeSent, senderName);
        this.Users=Users;
    }
    public void sendMessage(String Mess, PrintWriter out){
        setTimeSent(); //sets time sent
        String userList = senderName;
        userList += getUsers();
        String Log = senderName+"("+timeSent+")\n"+Mess;
        out.println("sMsg:\n"+userList+"\n"+Log);//Sends server userIDs and the message to save
        out.println("GM|"+Users+"|"+Mess);

    }
    public void addUsers(String User){ //Need to see GUI before actually coding
        this.Users += User;
    }
    public void addAllUsers(String Users){
        this.Users+=Users;
    }
    public String getUsers(){
        return Users;
    }
    
    
}
