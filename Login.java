/*
@author Andrew Oppenheimer
 */

import java.util.*;
import java.io.*;
import org.jasypt.encryption.pbe.PBEStringEncryptor;

public class Login {
    private static Login instance = new Login();

    public static Login getInstance() {
        return instance;
    }

    public boolean checkCredentials(String username, String password) {
        try {
//        BasicTextEncryptor tE = new BasicTextEncryptor();
//        tE.setPassword("MATSONISCOOLPLEASEGIVEA");
            //BufferedReader read = new BufferedReader(new FileReader(".\\users.txt"));
            Scanner read = new Scanner(new File(".\\users.txt"));
            String line = "";
//        line = tE.decrypt(line);
            String[] sections = null;
            line = read.nextLine();
            while (true) {
                sections = line.split("\\|");
                if (sections[0].equals(username) && sections[1].equals(password)) {
                    read.close();
                    return true;
                }
                //line = read.readLine();
//            line = tE.decrypt(line);
                try {
                    line = read.nextLine();
                } catch (Exception e) {
                    break;
                }
                sections = line.split("\\|");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
