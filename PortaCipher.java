package portacipher;

import java.util.Scanner;

public class PortaCipher {
    
    private static String dec(String out, int table[][]) {
        String[] dec = out.split(",");
        String outh = "";

        for (int o = 0; o < dec.length; o++) {
            for (int j = 1; j < 27; j++) {
                for (int k = 1; k < 27; k++) {
                    if(table[j][k] == Integer.parseInt(dec[o])) {
                        outh += (char)(j - 1 + 'a');
                        outh += (char)(k - 1 + 'a');
                    }
                }
            }
        }
        
        return outh;
    }
    
    private static int[][] generateTable() {
        int[][] table = new int[27][27];
        
        int c = 0;
        
        for (int i = 1; i < 27; i++) {
            for (int j = 1; j < 27; j++) {
                if(j == 0) {
                    c = j * 27;
                } else {
                    c += 1;
                }
                
                System.out.print(c + " ");
                table[i][j] = c;
            }
            System.out.println("");
        }
        
        return table;
    }
    
    private static String en(String msg, int[][] table) {
        String[] no;
        int c = 0;
        
        if(msg.length() % 2 == 0) {
            no = new String[msg.length() / 2];
        } else {
            no = new String[msg.length() / 2 + 1];
        }
        
        String pair = "";
        
        for (int i = 0; i < msg.length() / 2; i++) {
            try {
                pair = msg.charAt(i * 2) + "" + msg.charAt(i * 2 + 1);
            } catch (Exception e) {
            } finally {
                no[i] = pair;
                System.out.println(pair);
            }
        }
        
        if(!(msg.length() % 2 == 0)) {
            no[no.length - 1] = msg.charAt(msg.length() - 1) + "" + 'a';
        }
        
        int should = 0;
        int shouldt = 0;
        String out = "";
        
        for (int i = 0; i < no.length; i++) {
            c = 0;

            should = (no[i].charAt(0) - 'a');
            shouldt = (no[i].charAt(1) - 'a');
            
            for (int j = 1; j < 27; j++) {
                for (int k = 1; k < 27; k++) {
                    c = table[j][k];
                    
                    if((j - 1 == should && k - 1 == shouldt)) {
                        out += c + ",";
                        break;
                    }
                }
            }
        }
        
        return out;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String msg = scan.nextLine().replaceAll(" ", "").toLowerCase();
            
        int[][] table = generateTable();
        
        String enc = en(msg, table);
        System.out.println(enc);
        
        String dec = dec(enc, table);
        System.out.println(dec);
    }
}