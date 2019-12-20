/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
// imports for hashing
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

/**
 *
 * @author Asa Brown
 */
public class client {
    
    Scanner in = new Scanner(System.in);
    String IP = "localhost";
    int port = 60000;
    Socket s;
    OutputStream outStream; // the OUTPUT stream handler
    PrintWriter out; //setup of output
    InputStream inStream; // the INPUT stream handler
    Scanner inSoc; //setup of input
    
    public void startSocket(){
        try{
        s = new Socket(IP, port);
        outStream = s.getOutputStream();
        out = new PrintWriter(outStream,true);
        inStream = s.getInputStream();
        inSoc = new Scanner(inStream);
        
        Threading thread = new Threading(s, inStream, outStream);
        thread.start();
        
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void send(String mess){
        System.out.println("SEND:\t" + mess);
        out.println(mess);
    }

    public String hash(String pass) {
        //hash password
        Hasher hasher = Hashing.sha512().newHasher();
        hasher.putString(pass, Charsets.UTF_8);
        HashCode hash = hasher.hash();
        return hash.toString();
    }

    public Socket getSocket(){
        return s;
    }

    public InputStream getIn(){
        return inStream;
    }

    public OutputStream getOut(){
        return outStream;
    }

}
