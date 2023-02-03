package itstep.learning;

import itstep.learning.files.DirDemo;
import itstep.learning.files.IoDemo;
import itstep.learning.oop.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        new IoDemo().run();
        new DirDemo().run();
    }

    public static void oop( String[] args )
    {
        Library library = new Library();
        library.add(new Book("Knuth", "Art of programming"));
        library.add(new Book("Shevchenko", "Kobzar"));
        library.add(new Journal(10, "ArgC & ArgV"));
        library.add(new Journal(5, "Nature"));
        library.add(new Newspaper(LocalDate.now(), "Today's newspaper"));
        library.add(new Newspaper(LocalDate.of(2022, 10, 15), "Last year newspaper"));

        try
        {
            library.add(new Comics("Marvel", 1, "13.09.2022"));
            library.add(new Hologram("Historic Hologram", "01.02.2023"));
        }
        catch (ParseException ignored)
        {
            System.err.println("Date parse error");
        }

        library.add(new AudioBook("Kobzar. Shevchenko", "SuperSound Studio"));

        library.printFunds();
        System.out.println("-----------------------------------------");
        library.showPrinted();
        System.out.println("-----------------------------------------");
        library.playAll();
        System.out.println("-----------------------------------------");
        library.showPresentable();
    }

    public static void hello( String[] args )
    {
        // region Переменные и типы данных

        byte byteVar = 10;
        short shortVar = 30000;
        int intVar = 2000000000;
        long longVar = (long)1e15;

        float floatVar = 0.01f;
        double doubleVar = 1e-5;

        char charVar = 'A';
        String stringVar = "A string";
        String stringVar2 = "A string";
        String stringVar3 = String.format("-= %s, %d =-", stringVar, shortVar);

        // Сравнение строк stringVar.equals(stringVar2)

        boolean boolVar = true;

        // Константы
        final int constVar = 100;

        // endregion

        // region Input/Output

        System.out.println("println - Output with new line");
        System.out.print("print - Output with new line");
        System.out.printf("printf - formatted Output x=%d %n", byteVar);
        //System.err.println("err.println - Error output");

        Scanner scanner = new Scanner(System.in);

        //int x = scanner.nextInt();
        //String s = scanner.next();

        //System.out.printf("x = %d, s = %s %n", x , s);

        // endregion

        // region Guess Game

        /* comment

        int min = 1;
        int max = 100;
        boolean guess = false;

        int number = min + (int)(Math.random() * max);

        System.out.println("Try to guess the number from 1 to 100!");

        while(true){

            System.out.println("Enter your number: ");
            int num = scanner.nextInt();

            if(num == number){
                System.out.printf("You guessed the number and win! The number was %d %n", num);
                break;
            }

            if(num < number){
                System.err.println("The number you need to guess in bigger!");
            }
            else if(num > number){
                System.err.println("The number you need to guess in smaller!");
            }
        }

         */
        // endregion


    }
}
