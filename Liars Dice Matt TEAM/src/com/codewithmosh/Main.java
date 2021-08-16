package com.codewithmosh;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    //TODO edit who will begin thte next round

    public static HashMap<String,String> gameData = new HashMap<>();

    public static String rollDice(int diceCount, int numberOfSides){

        String allDice = "";
        int scoreSum = 0;

        for (int i = 0; i < diceCount; i++) {

            double diceRandomNumber = (Math.random() *numberOfSides) + 1;

            allDice +=  ((int)diceRandomNumber) + " "; // casts a double to an Int in an integer 6.999 will get cut to a 6
            scoreSum+=  ((int)diceRandomNumber);
        }


        return allDice;


    }


    public static void main(String[] args) throws Exception{
	// write your code here

//
        Game liarsDice = new Game(); //bought the game at the factory
        liarsDice.initializeGame(); // turned the game on










    }
}
