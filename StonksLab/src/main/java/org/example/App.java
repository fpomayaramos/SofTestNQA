package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // ask for user for a ticker and print out the price
        User wsbPerson = new User();
        Scanner keyboard = new Scanner(System.in);
        Scanner number = new Scanner(System.in);
        ArrayList<String> ar;
        boolean StockAlreadyInPortfolio;

        System.out.println("Please enter your \"@gmail.com\" e-mail:");
        String email = keyboard.nextLine();

        while(true) {
            wsbPerson.showMenu();
            String answer = keyboard.nextLine();
            StockAlreadyInPortfolio = false;
            ar = new ArrayList<>();

            wsbPerson.loadPortfolio();

            if(answer.equals("1")) {
                System.out.println("Please enter ticker: ");
                String ticker = keyboard.nextLine();
                System.out.println(wsbPerson.printStonkPrice(ticker) + "\n");
                JavaMailUtil.sendMail(email, "Price of " + ticker + " is $" + wsbPerson.printStonkPrice(ticker));
            }
            if (answer.equals("2")) {
                // print out portfolio total value
                double totalValue = wsbPerson.getPortfolio().getTotalValue();
                System.out.println(wsbPerson.getPortfolio());
                System.out.println("The value of your portfolio is: " + String.format("%.2f", totalValue));
                System.out.println("");
                JavaMailUtil.sendMail(email, wsbPerson.getPortfolio() + "\n" + "The value of your portfolio is: $" + String.format("%.2f", totalValue));
            }
            if (answer.equals("3")) {
                System.out.println("Please enter the ticker of the stock you would like to add to porfolio: ");
                String ticker = keyboard.nextLine().toUpperCase();
                System.out.println("Please enter the number of stocks you would like to add: ");
                int shares = number.nextInt();

                try {
                    String line = FileUtils.readFileToString(new File("portfolio.txt"), "UTF-8");
                    String[] array = line.split(",");

                    for (int i = 0; i < array.length; i++) {
                        String t = array[i].split(":")[0];
                        int s = Integer.parseInt(array[i].split(":")[1]);

                        if (ticker.equalsIgnoreCase(t)) {
                            s += shares;
                            StockAlreadyInPortfolio = true;
                        }

                        if (i < array.length-1)
                            ar.add(t + ":" + s +",");
                        else
                            ar.add(t + ":" + s);
                    }

                    if(StockAlreadyInPortfolio == false) {
                        ar.add(","+ticker+":"+shares);
                    }
                    File f = new File ("portfolio.txt");
                    FileWriter fw = new FileWriter(f);
                    // GME:20,NOK:100,TSLA:2

                    for (int i = 0; i < ar.size(); i++) {
                        fw.write(ar.get(i));
                    }
                    fw.close();
                    System.out.println("Portfolio updated\n");

                } catch(IOException e) {
                    throw new RuntimeException("Stonk data could not be retrieved!");
                }
            }
            if (answer.equals("4")) {
                System.out.println("Please enter the ticker of the stock you would like to remove from portfolio: ");
                String ticker = keyboard.nextLine().toUpperCase();
                System.out.println("Please enter the number of stocks you would like to remove");
                int shares = number.nextInt();

                try {
                    String line = FileUtils.readFileToString(new File("portfolio.txt"), "UTF-8");
                    String[] array = line.split(",");

                    for (int i = 0; i < array.length; i++) {
                        String t = array[i].split(":")[0];
                        int s = Integer.parseInt(array[i].split(":")[1]);
                        if (ticker.equalsIgnoreCase(t)) {
                            if (s-shares < 0 )
                                throw new IOException();

                            s -= shares;
                            StockAlreadyInPortfolio = true;
                            //File myFile = new File("portfolio.txt");
                            //FileOutputStream fooStream = new FileOutputStream(myFile, false);

                        }

                        if (i < array.length-1)
                            ar.add(t + ":" + s +",");
                        else
                            ar.add(t + ":" + s);
                    }

                    if(StockAlreadyInPortfolio == false) {
                        throw new IOException();
                    }
                    File f = new File ("portfolio.txt");
                    FileWriter fw = new FileWriter(f);
                    // GME:20,NOK:100,TSLA:2

                    for (int i = 0; i < ar.size(); i++) {
                        fw.write(ar.get(i));
                    }
                    fw.close();
                    System.out.println("Portfolio updated\n");

                } catch(IOException e) {
                    throw new RuntimeException("Invalid number of Stonks! or Stonk not found!");
                }
            }
        }
    }
}
