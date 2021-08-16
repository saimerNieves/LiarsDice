package com.codewithmosh;

public class Die {


    //Member Data
    public int totalSidesOnDice;
    public int faceUpValue;


    //Member Constructor
    public Die(){

        totalSidesOnDice = 6; // This will be our default constructor because normally most dice have six sides

    }

    public Die(int newTotalSidesOnDice){

        totalSidesOnDice = newTotalSidesOnDice; // This will update the object to a new number of dice other than the
        // default
    }


    //Member Behavior
    public void roll(){

        faceUpValue = (int) ((Math.random() * totalSidesOnDice) + 1); //This will create a random number between 0-1
        // * 6 + 1
    }
}



