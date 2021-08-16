package com.codewithmosh;


import java.util.ArrayList;
import java.util.List;

    public class Cup {

        //Member Behavior
        public void rollAllDiceInCup(Player player){
            //Roll all the Dice in the Cup
            for (Die oneDieInCup : player.diceCollection){
                oneDieInCup.roll();
            }
        }


        public void rollOneDice(Player player,int diceSelectedIndex){
            //Allows the user to get one dice and re-roll it while keeping the others intact
            player.diceCollection.get(diceSelectedIndex).roll();
        }


        public void rollMultipleDice(Player player,ArrayList<Integer> diceSelectedIndexes){

            for (int oneDieIndex: diceSelectedIndexes) {
                //Calls local method to roll one Die and passes down the selected index
                rollOneDice(player,oneDieIndex);
            }
        }


        //Player throws Cup to table to view all Dice
        public static ArrayList<String> displayAllDice(Player player){

            ArrayList<String> allDiceDisplay = new ArrayList<>();

            for (Die oneDie : player.diceCollection){

                allDiceDisplay.add(String.valueOf(oneDie.faceUpValue) );
            }

            return allDiceDisplay;
        }



//        public List<Integer> parseSelections(String input) {
//            String[] inputArr = input.split(" "); // ["1", "2", "5"]
//            // String[] -> List<Integer>
//            List<Integer> selections = new ArrayList<>();
//            for (String number : inputArr) {
//                selections.add(Integer.parseInt(number) - 1);
//            }
//
//            return selections.contains(-1) ? new ArrayList<Integer>() : selections;
//        }

    }

