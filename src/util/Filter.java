/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author Loi Lam
 */
public class Filter {
    
    private static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n;
        while(true) {
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if(n < lowerBound || n > upperBound)
                    throw new Exception();
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static boolean checkYesNo(String inputMsg, String errorMsg) {
        String check;
        while(true) {
            try {
                System.out.println(inputMsg);
                check = sc.nextLine();
                if(check.equals("Y"))
                    return true;
                else if(check.equals("N"))
                    return false;
                else
                    throw new Exception();
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static String getString(String inputMsg, String errorMsg) {
        String model;
        while(true) {
            System.out.println(inputMsg);
            model = sc.nextLine();
            if(model.length() == 0 || model.isEmpty())
                System.out.println(errorMsg);
            else
                return model;
        }
    }
    
    public static double getDouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double d;
        while(true) {
            try {
                System.out.println(inputMsg);
                d = Double.parseDouble(sc.nextLine());
                if(d < lowerBound || d > upperBound)
                    throw new Exception();
                return d;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static String getISBN (String errorMsg) {
        String isbn;
        while (true) {            
            try {
                isbn = sc.nextLine();
                isbn = isbn.replace("(|-)", "");
                boolean isValid = false;
                if(isbn.length() == 10)
                    isValid = checkISBN10(isbn);
                else if(isbn.length() == 13)
                    isValid = checkISBN13(isbn);
                else
                    throw new Exception();
                return isbn;
                } catch (Exception e) {
                    System.out.println(errorMsg);
                }
        }
    }
    
    public static boolean checkISBN10(String isbn) {
        int sum = 0;
        String dStr;
        for (int d = 0; d < 10; d++) {
            dStr = isbn.substring(d, d + 1);
            if(d < 9 || dStr != "X") {
                sum += Integer.parseInt(dStr) * (10 - d);
            } else {
                sum += 10;
            }
        }
        return (sum % 11 == 0);
    }
    
    public static boolean checkISBN13(String isbn) {
        int sum =  0;
        int dVal;
        for (int d = 0; d < 12; d++) {
            dVal = Integer.parseInt(isbn.substring(d, d + 1));
            if(d % 2 == 0) {
                sum += dVal * 1;
            } else {
                sum += dVal * 3;
            }
        }
        return (sum % 10 == 0);
    }
}
