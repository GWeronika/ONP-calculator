import java.util.Scanner;
import java.lang.Math;
import java.util.Stack;

public class Main {
    static double dodawanie(double x, double y) {       //funkcja wykonujaca dodawanie
        return x + y;
    }
    static double odejmowanie(double x, double y) {     //funkcja wykonujaca odejmowanie
        return y - x;
    }
    static double mnozenie(double x, double y) {        //funkcja wykonujaca mnozenie
        return x * y;
    }
    static double dzielenie(double x, double y) {       //division function
        if(y != 0) {        //if the divisor is not equal to 0, division can be performed
            return x / y;
        }
        else {
            System.out.print("Dzielnik nie moze byc rowny ");   //otherwise the message is displayed
            return 0;
        }
    }
    static double modulo(double x, double y) {      //modulo operations
        if(y != 0) {        //divisor is not equal to 0 then it can be executed
            return Math.round(x % y);
        }
        else {
            System.out.print("Dzielnik nie moze byc rowny ");   //otherwise it is not performed
            return 0;
        }
    }
    static double potegowanie(double x, double y) {     //wykonuje potegowanie
        return Math.pow(x, y);
    }
    static double pierwiastek(double x, double y) {     //extraction operation
        if(y > 0) {     //the degree of the root must be greater than 0
            return Math.pow(x, 1/y);        //the second argument is the inversion of the degree of the root
        }                                   //because it is an exponentation operation
        else {
            System.out.print("Stopien nie moze byc mniejszy od ");
            return 0;
        }
    }
    static double operacja(char wybor, double x, double y) {            //function that chooses the operation based on wybor
        switch(wybor) {     //switch in relation to the char
            case '+': return dodawanie(x, y);      //if the sign is +
            case '-': return odejmowanie(x, y);      //if the sign is -
            case '*': return mnozenie(x, y);     //if the sign is *
            case '/': return dzielenie(x, y);      //if the sign is /
            case '%': return modulo(x, y);      //if the sign is %
            case 'P': return potegowanie(x, y);      //if the sign is P
            case 'S': return pierwiastek(x, y);      //if the sign is S
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner otp = new Scanner(System.in);
        System.out.println("Kalkulator - menu:");   //menu
        System.out.println("+ dodawanie");
        System.out.println("- odejmowanie");
        System.out.println("* mnozenie");
        System.out.println("/ dzielenie");
        System.out.println("% modulo");
        System.out.println("P potegowanie");
        System.out.println("S pierwiastkowanie");

        System.out.println("Podaj dzialanie zapisane jako odwrotna notacja polska ");
        String ONP = otp.nextLine();

        Stack<Double> cyfra = new Stack<>();            //stack that holds the numbers needed to perform operations

        double var1, var2;         //variables from the stack

        for(int i = 0; i < ONP.length(); i++) {         //character recognition and stacking
            if(ONP.charAt(i) > 47 && ONP.charAt(i) < 58) {          //if the character is a number (48 - 57 in ASCII)
                int j = i, order = 0;          //this variable is necessary with while loop
                boolean k = false;          //determines whether a digit is in the integer or fractional part of a number
                double liczba = 0.0;
                while(j < ONP.length() && ONP.charAt(j) != ' ') {         //while character indexed j is not a whitespace it converts char to int
                    if(!k) {            //if false, digits are in the integer part of the number
                        if(ONP.charAt(j) != '.') {
                            liczba = liczba * 10 + (ONP.charAt(j) - '0');
                        }
                        else {
                            k = true;
                            order = j;          //remembers the position of the comma
                        }          //changes when there is a comma
                    }
                    else {          //if true, digits are in the fractional part of the number
                        liczba += ONP.charAt(j) / potegowanie(10, j - order);
                    }
                    j++;            //goes to the next character
                }
                cyfra.push(liczba);         //pushed to the stack
                i = j;                  //not to start from the previous one but from whitespace
            }
            else if((ONP.charAt(i) == '+') ||        //+         if it is one of these signs
                    (ONP.charAt(i) == '-') ||        //-
                    (ONP.charAt(i) == '*') ||        //*
                    (ONP.charAt(i) == '/') ||        ///
                    (ONP.charAt(i) == '%') ||        //%
                    (ONP.charAt(i) == 'S') ||        //S
                    (ONP.charAt(i) == 'P')) {        //P
                var1 = cyfra.pop();             //we take the first two doubles from the stack
                var2 = cyfra.pop();
                cyfra.push(operacja(ONP.charAt(i), var1, var2));        //and make the corresponding mathematical operation
            }                                                           //which is next pushed back to the stack
            //if it is a comma - skip
            //if it is whitespace - skip
        }
        System.out.println(cyfra.pop());
    }
}
