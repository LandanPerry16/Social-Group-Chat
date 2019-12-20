//Class: Message
//Coder:Asa Brown
package Message;
import java.time.LocalTime;
import java.util.*;
/**
 *
 * @author Asa
 */
public class Message {
    String message;
    String timeSent;
    String senderID;
    String senderName;
   
    
    Message(String Mess, String timeSent, String senderName){
        this.message = Mess;
        this.timeSent = timeSent;
        this.senderName = senderName;
    }
    
    public void setTimeSent(){
        this.timeSent = LocalTime.now().toString();
    } 
    
    public String getTimeSent() {
        return timeSent;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
