package com.codewithmosh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;




public class Player {

    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public String name;
    public boolean turn = false;
    public ArrayList<Die> diceCollection = new ArrayList<>();


    //public HashMap<String,Integer> allBidsFromThatPlayer = new HashMap<>();


    //public HashMap<String, Integer> getallBidsFromThatPlayer() {
      //  return allBidsFromThatPlayer;
    //}

//    public void setANewBidForThatPlayer(String diceNumber, Integer repatition) {
//        allBidsFromThatPlayer.put(diceNumber, repatition);
//    }






//    public String toString(){
//       return getallBidsFromThatPlayer().toString();
//    }


    //MemberData
    public Player(String name){
        this.name = name;
    }


    //Member Behavior
    public void makeBid() throws Exception{
        //User will provide input of 5 x4
        Game.setCurrentBid( bf.readLine());

    }

    public void accuseLiar(){



    }

}
