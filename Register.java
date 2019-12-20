/*
@author Andrew Oppenheimer
 */

import java.io.*;
import java.util.Scanner;

public class Register {
    private static Register instance = new Register();

    public static Register getInstance() {
        return instance;
    }

    public boolean addCredentials(String username, String password) {
        try {
    //        BasicTextEncryptor tE = new BasicTextEncryptor();
    //        tE.setPassword("MATSONISCOOLPLEASEGIVEA");
            FileWriter f = new FileWriter("users.txt", true);
//            BufferedReader read = new BufferedReader(new FileReader(".\\users.txt"));
            Scanner read = new Scanner(new File(".\\users.txt"));
            String line = read.nextLine();
            String[] sections = null;
            try {
                while (true) {
                    sections = line.split("\\|");
                    if (sections[0].equals(username) && sections[1].equals(password)) {
                        return false;
                    }
                    try {
                        line = read.nextLine();
                    } catch (Exception e) {
                        break;
                    }
                    sections = line.split("\\|");
                }
                read.close();
    //            f.write(tE.encrypt(username+"|"+password));
                f.write(username+"|"+password+"\n");
                f.flush();
                f.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
