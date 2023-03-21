import java.util.Scanner;
import java.lang.Math;
import java.util.Stack;

public class Main {
    static double dodawanie(double x, double y) {       //funkcja wykonujaca dodawanie
        return x + y;
    }
    static double odejmowanie(double x, double y) {     //funkcja wykonujaca odejmowanie
        return x - y;
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
    static double modulo(double x, double y) {      //performs modulo operations
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
    static double pierwiastek(double x, double y) {     //extraction of a root
        if(y > 0) {     //the degree of the root must be greater than 0
            return Math.pow(x, 1/y);        //the second argument is the inversion of the degree of the root
        }                                   //because it is an exponentation operation
        else {
            System.out.print("Stopien nie moze byc mniejszy od ");
            return 0;
        }
    }
    static void operacja(char wybor, double x, double y) {
        switch(wybor) {     //switch in relation to the char
            case '+':       //if the sign is +
                System.out.print(dodawanie(x, y));
                break;
            case '-':       //if the sign is -
                System.out.print(odejmowanie(x, y));
                break;
            case '*':       //if the sign is *
                System.out.print(mnozenie(x, y));
                break;
            case '/':       //if the sign is /
                System.out.print(dzielenie(x, y));
                break;
            case '%':       //if the sign is %
                System.out.print(modulo(x, y));
                break;
            case 'P':       //if the sign is P
                System.out.print(potegowanie(x, y));
                break;
            case 'S':       //if the sign is S
                System.out.print(pierwiastek(x, y));
                break;
        }
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

        Stack<Double> cyfra = new Stack<>();
        Stack<Character> znak = new Stack<>();

        for(int i = ONP.length() - 1; i >= 0; i--) {         //character recognition and stacking
            if(ONP.charAt(i) > 47 && ONP.charAt(i) < 58) {
                    int j = i;
                    double liczba = 0.0;
                    while(j >= 0 && ONP.charAt(j) != ' ') {
                        if(ONP.charAt(j) == '.') {
                            liczba *= potegowanie(10, -(j - i));
                        }
                        liczba += (ONP.charAt(j) - '0') * potegowanie(10, j - i);
                        j--;
                    }
                    cyfra.push(liczba);
                    i = j;                  //not to start from the previous one, only from white space
            }
            else if((ONP.charAt(i) == '+') ||        //+         if it is one of these signs
                    (ONP.charAt(i) == '-') ||        //-
                    (ONP.charAt(i) == '*') ||        //*
                    (ONP.charAt(i) == '/') ||        ///
                    (ONP.charAt(i) == '%') ||        //%
                    (ONP.charAt(i) == 'S') ||        //S
                    (ONP.charAt(i) == 'P')) {        //P
                znak.push(ONP.charAt(i));           //put it on the operator stack
            }
            //if it is a comma - skip
            //if it is whitespace - skip
        }
        while(!(znak.empty())){
            System.out.println(znak.pop());
        }
        while(!(cyfra.empty())){
            System.out.println(cyfra.pop());
        }
    }
}