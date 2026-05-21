package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utils {

    static public String readLineFromConsole(String strPrompt) {
        try {
            System.out.print(strPrompt);
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int readIntFromConsole(String strPrompt) {
        do {
            try {
                String strInt = readLineFromConsole(strPrompt);
                int iInt = Integer.parseInt(strInt);
                return iInt;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static double readDoubleFromConsole(String strPrompt) {
        do {
            try {
                String strDouble = readLineFromConsole(strPrompt);
                double iDouble = Double.parseDouble(strDouble);
                return iDouble;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Data readDateFromConsole(String strPrompt) {
        do {
            try {
                String strData = readLineFromConsole(strPrompt + "(formato: dd-MM-yyyy)");
                // dd-MM-yyyy
                String[] arr = strData.split("-");
                int dia = Integer.parseInt(arr[0]);
                int mes = Integer.parseInt(arr[1]);
                int ano = Integer.parseInt(arr[2]);
                return new Data(ano, mes, dia);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("--> " + ex.toString());
            }
        } while (true);
    }

    static public boolean confirma(String sMessage) {
        String strConfirma;
        do {
            strConfirma = Utils.readLineFromConsole(sMessage);
        } while (!strConfirma.equalsIgnoreCase("s") && !strConfirma.equalsIgnoreCase("n"));

        return strConfirma.equalsIgnoreCase("s");
    }

    public static String lerStringSegura(java.util.Scanner scanner, String mensagem) {
        while (true) {
            String input = readLineFromConsole(mensagem);
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("[Erro] O campo não pode ficar vazio.");
        }
    }

    // Reaproveita o teu método 'readIntFromConsole' para o menu
    public static int lerInteiroSeguro(java.util.Scanner scanner, String mensagem) {
        return readIntFromConsole(mensagem);
    }

    // Lê um inteiro e garante que ele está dentro dos limites das opções do menu ou notas (1 a 10)
    public static int lerInteiroNoIntervalo(java.util.Scanner scanner, String mensagem, int min, int max) {
        while (true) {
            int valor = readIntFromConsole(mensagem);
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("[Erro] O número inserido deve estar entre " + min + " e " + max + ".");
        }
    }
}
