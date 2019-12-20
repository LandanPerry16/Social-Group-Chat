//Class: Direct Message
//Coder:Asa Brown
package Message;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.*;
/**
 *
 * @author Asa
 */
public class DirectMessage extends Message{
    String sendee;
    
    public DirectMessage(String Mess, String timeSent, String senderName) {
        super(Mess, timeSent, senderName);
    }
 
    public void setUsers(String senderName, String sendee){
        this.senderName = senderName;
        this.sendee = sendee;
    }
    
    public void sendMessage(String Mess, PrintWriter out){
        setTimeSent(); //sets time sent
        String Users = senderName+"|"+sendee; //Takes userIDs and concates to string
        String Log = senderName+" ("+timeSent+")\n"+Mess; //Switches userID to username then concates message and timestamp and bundles as the message log
        //out.println("sMsg:\n"+Users+"\n"+Log);//Tells server how to store msg
        out.print("sDM|"+Users+"|"+Log); //Tells server who to send the message to
        out.println("DM|"+Users+"|"+Log);
    }
    
    public String getMsgH(PrintWriter out, Scanner inSoc){
        String Users = senderID+":"+sendee; //Takes userIDs and concates to string
        out.print("GDMH|"+Users);
        return inSoc.next();
    }
    
    public void setSendee(String sendee){
        this.sendee = sendee;
    }
    
    public String getSendee() {
        return sendee;
    }
    
}
