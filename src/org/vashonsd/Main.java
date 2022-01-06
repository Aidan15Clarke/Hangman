package org.vashonsd;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File(
                "/Users/clarke.aidan/IdeaProjects/Hangman V2/src/org/vashonsd/resources/google-10000-english-usa-no-swears-medium.txt");
        Scanner reader;
        ArrayList<String> wordBank = new ArrayList<>();
        ArrayList<String> wrongLetters = new ArrayList<>();

        //Reads the txt file and adds the words to wordBank
        try {
            reader = new Scanner(file);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                wordBank.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);

        //Main loop
        while(true){
            String randWord = wordBank.get((int) (Math.random() * 5460));
            System.out.println(randWord);

            String dashLine  = "";
            String strikes = "Strikes: ";
            int strikeNum = 0;

            for (int i = 0; i < randWord.length(); i++) {
                dashLine += "_ ";
            }

            while(true){
                System.out.println(dashLine + "\n");
                System.out.print("Wrong letters: ");

                if(wrongLetters.size() != 0) {
                    for (int i = 0; i < wrongLetters.size(); i++) {
                        System.out.print(wrongLetters.get(i) + " ");
                    }
                }

                System.out.println("\n" + strikes);

                System.out.print("\nEnter a letter: ");
                String letter = input.nextLine();

                int j = 0;
                for(int i = 0; i < randWord.length(); i++) {
                    if (randWord.substring(i, i + 1).equals(letter)) {
                        String firstHalf = dashLine.substring(0, j);
                        String secondHalf = dashLine.substring(j + 1);
                        dashLine = firstHalf + letter + secondHalf;
                    }
                    j += 2;
                }

                if(!randWord.contains(letter)){
                    wrongLetters.add(letter);
                    strikes += "x ";
                    strikeNum ++;
                }

                if(!dashLine.contains("_")){
                    System.out.println(randWord);
                    System.out.println("You won!");
                    break;
                } else if(strikeNum == 3){
                    System.out.println("You lose!");
                    break;
                } else {
                    continue;
                }
            }
            System.out.print("Would you like to play again? (y/n) ");
            if(input.nextLine().equals("y")){
                continue;
            } else {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
}
