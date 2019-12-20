/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Asa Brown
 */
public class Threading extends Thread{
    InputStream inStream;
    OutputStream outStream;
    Socket s;
    
    public Threading(Socket s, InputStream inStream,OutputStream outStream){
        this.inStream = inStream;
        this.outStream = outStream;
        this.s = s;
    }
    
    @Override
        public void run(){
        System.out.println("HERE2");
            boolean when = false;
            String inLine = "";
            Scanner inSoc = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream,true);
            while(!when){
                System.out.println("1");
                if(inSoc.hasNextLine()){
                    System.out.println("2");
                    inLine = (inSoc.nextLine());
                    String[] args = inLine.split("\\|", 0);
                    System.out.println(args[0]);
                    switch (args[0]) {
                        case "DM":
                            GUI.dm(args[1]);
                            break;
                        case "GM":
                            GUI.gm(args[1]);
                            break;
                        case "PT":
                            GUI.pt(args[1]);
                            break;
                        case "RS":
                            GUI.login();
                            break;
                        case "RF":
                            System.err.println("Failure to Register");
                            break;
                        case "SC":
                            GUI.login();
                            break;
                        case "FL":
                            System.err.println("Failure to Login");
                            break;
                        default:
                            break;
                    }
                }
            }
        }
}

