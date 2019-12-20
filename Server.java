/*
@author Andrew Oppenheimer
 */

import java.io.*;
import java.net.*;
import java.util.*;
import javafx.util.Pair;

public class Server implements Runnable {
    private Socket inc;
    private PrintWriter pOut;
    private ArrayList<Pair<String, PrintWriter>> clients = new ArrayList<Pair<String, PrintWriter>>();

    public void run() {
        try(ServerSocket sock = new ServerSocket(60000)) {
            while (true) {
                inc = sock.accept();
                pOut = new PrintWriter(inc.getOutputStream(), true);
                Thread thread = new Thread(new Threader(inc, pOut));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public class Threader implements Runnable {
        private Socket s;
        private Scanner in;
        private PrintWriter out;

        public Threader(Socket s, PrintWriter p) {
            this.s = s;
            this.out = p;
            try {
                this.in = new Scanner(s.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            System.out.println(s);
            while (true) {
                try {
                    String lineIn = in.nextLine();
                    // Switch Statements for each possible request type
                    /*
                    RG=Register
                    LG=Login
                    DM=Direct Message
                    GM=Group Message
                    PT=Post
                    EP=Edit Profile
                     */
                    String[] args = lineIn.split("\\|");
                    //System.out.println(lineIn);
                    //System.out.println(args.toString());
                    switch (args[0]) {
                        case "RG":
                            if (Register.getInstance().addCredentials(args[1], args[2])) {
                                clients.add(new Pair(args[1], pOut));
                                out.println("RS");
                            } else {
                                out.println("RF");
                            }
                            break;
                        case "LG":
                            if (Login.getInstance().checkCredentials(args[1], args[2])) {
                                clients.add(new Pair(args[1], pOut));
                                out.println("SC");
                            } else {
                                out.println("FL");
                            }
                            break;
                        case "DM":
                            for (Pair<String, PrintWriter> pwrt : clients) {
                                if (args[1].equals(pwrt.getKey()) ||
                                        args[2].equals(pwrt.getKey())) {
                                    System.out.println("DM|"+args[3]);
                                    pwrt.getValue().println("DM|"+args[3]);
                                }
                            }
                            break;
                        case "GM":
                            for (Pair<String, PrintWriter> pwrt : clients) {
                                for (int i = 1; i < args.length - 1; i++) {
                                    if (args[i].equals(pwrt.getKey())) {
                                        pwrt.getValue().println("GM|"+args[args.length-1]);
                                    }
                                }
                            }
                            break;
                        case "PT":
                            for (Pair<String, PrintWriter> pwrt : clients) {
                                System.out.println(args[1]);
                                pwrt.getValue().println("PT|"+args[1]);
                            }
                            break;
                        default:
                            break;
                    }
                } catch (NoSuchElementException e) {
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String [] args) {
        Thread start = new Thread(new Server());
        start.start();
    }
}