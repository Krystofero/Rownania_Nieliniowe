package Rownania_Nieliniowe;

import java.util.Scanner;

public class RownaniaNieliniowe {

    public static double funkcja(double x) {
    //    return (double) (Math.pow(x,2) + x - 5);
        return (double) (Math.pow(x,2) + 4.1 * x - 9);
    }

    public static boolean warunekKonieczny(double a, double b) { //sprawdza czy funkcja przebija oś x(jest pierwiastek)
        if((funkcja(a) * funkcja(b)) < 0) return true;  //sprawdza czy funkcj mają różny znak
        else return false;
    }

    public static double pochodna1(double x) {
    //    return (double) (2 * x + 1);
        return (double) (2 * x + 4.1);
    }

    public static double pochodna2(double x) {
        return (double) 2;
    }

    public static boolean warunekZbieznosci(double a, double b) { //sprawdza czy pochodna1 a oraz b mają taki sam znak oraz pochodne2 mają taki sam znak
        if((pochodna1(a) * pochodna1(b)) >= 0 && (pochodna2(a) * pochodna2(b)) >= 0) return true;
        else return false;
    }

    public static double Bisekcja(double a, double b, double eps) {
        System.out.println("\n-------------------------------------------------Metoda Bisekcji--------------------------------------------------\n");
        double xsr;
        int iter = 0;
        if(warunekKonieczny(a, b)) {
            while (true) {
                iter++;

                xsr = (a + b) / 2;

                if (funkcja(xsr) == 0) break;
                else if (warunekKonieczny(xsr, a)) b = xsr;
                else a = xsr;

                if (Math.abs(funkcja(xsr)) < eps) break;
            }
            System.out.println("Rozwiązaniem Metodą Bisekcji dla epsilon = " + eps + " ,w przedziale <" + a + ", " + b +"> , jest xsr = " + xsr);
            System.out.println("Wartość funkcji wejściowej dla znalezionego x = " + funkcja(xsr));
            System.out.println("Ilość iteracji niezbędnych do uzyskania pierwiastka = " + iter);
            return xsr;
        }
        else{ System.out.println("Warunek konieczny nie jest spełniony!"); return -1;}
    }

    public static double Styczne(double a, double b, double eps) {
        System.out.println("\n-------------------------------------------------Metoda Stycznych(Newtona)----------------------------------------\n");
        double xn, xn1; // xn oraz xn+1
        int iter = 0;
        if(warunekKonieczny(a, b)) {
            if (warunekZbieznosci(a,b))System.out.println("Warunki zbieżności są spełnione");
            else System.out.println("Warunki zbieżności nie są spełnione!");

        //    if((pochodna2(a) >= 0 && funkcja(a) >= 0) || (pochodna2(a) < 0 && funkcja(a) < 0)) xn = a;
            if(pochodna2(a) * funkcja(a) >= 0) xn = a;
            else xn = b;

            while (true) {
                iter++;

                xn1 = xn - (funkcja(xn) / pochodna1(xn));

                if ((Math.abs(funkcja(xn1)) < eps) || (Math.abs(xn1 - xn) < eps)) break;
                else xn = xn1;
            }
            System.out.println("Rozwiązaniem Metodą Stycznych(Newtona) dla epsilon = " + eps + " ,w przedziale <" + a + ", " + b +"> , jest x" + iter +" = " + xn1);
            System.out.println("Wartość funkcji wejściowej dla znalezionego x = " + funkcja(xn1));
            System.out.println("Ilość iteracji niezbędnych do uzyskania pierwiastka = " + iter);
            return xn1;
        }
        else{ System.out.println("Warunek konieczny nie jest spełniony!"); return -1;}
    }

    public static double Sieczne(double a, double b, double eps) {
        System.out.println("\n-------------------------------------------------Metoda Siecznych-------------------------------------------------\n");
        double xn, xn1; // xn oraz xn+1
        double nr; //punkt nieruchomy
        int iter = 0;
        if(warunekKonieczny(a, b)) {
            if(pochodna2(a) * funkcja(a) >= 0) nr = a;
            else nr = b;

            if(nr == a) { xn = b;
                while (true) {
                    iter++;

                    xn1 = xn - (funkcja(xn) / (funkcja(xn) - funkcja(a))) * (xn - a);

                    if ((Math.abs(funkcja(xn1)) < eps) || (Math.abs(xn1 - xn) < eps)) break;
                    else xn = xn1;
                }
            }
            else { xn = a;
                while (true) {
                    iter++;

                    xn1 = xn - (funkcja(xn) / (funkcja(b) - funkcja(xn))) * (b - xn);

                    if ((Math.abs(funkcja(xn1)) < eps) || (Math.abs(xn1 - xn) < eps)) break;
                    else xn = xn1;
                }
            }

            System.out.println("Rozwiązaniem Metodą Siecznych dla epsilon = " + eps + " ,w przedziale <" + a + ", " + b +"> , jest x" + iter +" = " + xn1);
            System.out.println("Wartość funkcji wejściowej dla znalezionego x = " + funkcja(xn1));
            System.out.println("Ilość iteracji niezbędnych do uzyskania pierwiastka = " + iter);
            return xn1;
        }
        else{ System.out.println("Warunek konieczny nie jest spełniony!"); return -1;}
    }

    public static void main(String[] args) {
        double eps; //dokładność (epsilon)
        double a, b; //przedział

        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj dolny przedzial (a): ");
        a = Double.parseDouble(sc.nextLine());
        System.out.println("Podaj górny przedzial (b): ");
        b = Double.parseDouble(sc.nextLine());
        System.out.println("Podaj dokładność (epsilon) :");
        eps = Double.parseDouble(sc.nextLine());

        Bisekcja(a, b, eps);
        Styczne(a, b, eps);
        Sieczne(a, b, eps);

        sc.close();
    }
}
