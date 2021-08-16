package com.codewithmosh;

public class ValidationLibrary {

    public static Boolean validateNewBid_vs_OldBid(String newBid, String oldBid){

        String[] currentBidSplit = oldBid.split("x");
        int oldDiceNumber = Integer.parseInt(currentBidSplit[0].strip());
        int oldNumbersOfTimesRepeated = Integer.parseInt(currentBidSplit[1].strip());

        String[] newBidSplit = newBid.split("x");
        int newDiceNumber = Integer.parseInt(newBidSplit[0].strip());
        int newNumbersOfTimesRepeated = Integer.parseInt(newBidSplit[1].strip());

        if(oldDiceNumber == newDiceNumber){
            if(newNumbersOfTimesRepeated > oldNumbersOfTimesRepeated){
                return true;
            }else{
                System.out.println("\n\nERROR : Bid quantity must be greater than previosu bid | Current Bid Dice Quantity:  " + oldNumbersOfTimesRepeated);
                return false;
            }
        }
        else{
            System.out.println("\n\nERROR : Bid must have the same dice Number | Current Bid Dice Num:  " + oldDiceNumber);
            return false;
        }
    }
}
