//Class: Message History
//Coder:Asa Brown
package Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Asa
 */
public class MessageHist{
 
    File dir = new File("./MessageLog");
    FileWriter file;
    PrintWriter printWriter;
    
    /* Currently all messages are saving with the complete list of userIDs and timestamp. The userID list needs to be removed except for sender.
    UserID also needs to be translated back to user name. Retreiving from a multiuser log file still needs to be implemented*/
    
    public void saveMsg(String Users,String Mess){ //Receives Users+message and saves to a txt file
        try {
            file = new FileWriter("./MessageLog/"+Users+".txt",true);
            printWriter = new PrintWriter(file,true);
            printWriter.println(Mess);
        } catch (IOException ex) {
            Logger.getLogger(MessageHist.class.getName()).log(Level.SEVERE, null, ex);
        }                   
    }
    
    public String retreiveMsgH(String Users){ //receives list of userIDs and pulls all previous messages
        String mess="DM|";
        try {
            dir = new File("./MessageLog/"+Users+".txt");
            BufferedReader br = new BufferedReader(new FileReader(dir));
            String line="";
            while ((line = br.readLine()) != null) {
              mess+=line;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MessageHist.class.getName()).log(Level.SEVERE, null, ex);
            return " ";
        } catch (IOException ex) {
            Logger.getLogger(MessageHist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mess;
          }
    
}
