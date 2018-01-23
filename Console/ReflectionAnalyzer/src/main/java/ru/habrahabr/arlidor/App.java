package ru.habrahabr.arlidor;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        try {
            ReflectionAnalyzer ref = new ReflectionAnalyzer();
            ref.analysis(inputData(args));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String inputData(String args[]) {
        if (args.length > 0) {
            return args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date)");
            return in.nextLine();
        }
    }
}
