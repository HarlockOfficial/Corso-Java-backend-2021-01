package Lampadina2;

import Lampadina.Lampadina;
import Lampadina3.Impianto;

import java.util.Scanner;

public class TestClass {
    public void test(){
        Lampadina l = new Lampadina(1);
        Interruttore a = new Interruttore(l),
                b = new Interruttore(l);
        int choice;
        Scanner sc = new Scanner(System.in);
        boolean result = false;
        do{
            System.out.println("""
                    Inserire:
                    \t1 per premere l'interruttore 1
                    \t2 per premere l'interruttore 2
                    \t0 per uscire""");
            choice = sc.nextInt();
            if(choice == 1){
                result |= a.switchLamp();
            }else if(choice == 2){
                result |= b.switchLamp();
            }
        }while(choice != 0 && result);
        if(!result){
            System.out.println("Lampadina Rotta");
        }
    }
    public static void test2(){
        Impianto i = new Impianto(3);
        System.out.println(i.getCurrentStatus());
        i.clickLamp(0);
        System.out.println(i.getCurrentStatus());
        i.switchCurrent();
        System.out.println(i.getCurrentStatus());
        i.clickLamp(2);
        System.out.println(i.getCurrentStatus());
        i.switchCurrent();
        System.out.println(i.getCurrentStatus());
    }

    public static void main(String[] args) {
        test2();
    }
}
