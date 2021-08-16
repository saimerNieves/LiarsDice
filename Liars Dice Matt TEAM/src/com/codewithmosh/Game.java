package com.codewithmosh;



import java.util.*;

public class Game {
    static Scanner scanner = new Scanner(System.in);


    public static HashMap<String,Player> allPlayers = new HashMap<>();
    public static ArrayList<String> allPlayerNames = new ArrayList<>();
    public static String currentBid ="";
    public static Player currentBidHolder;



    public Player determineWhoseTurnIsIt() {

        for (int i = 0; i < allPlayers.size(); i++) {

            if(allPlayers.get(allPlayerNames.get(i)).turn == true){

                return allPlayers.get(allPlayerNames.get(i));
            }
        }

        allPlayers.get(allPlayerNames.get(0)).turn = true;
        return allPlayers.get(allPlayerNames.get(0));
    }



    public static void setCurrentBid(String userBid){
        currentBid = userBid;
        //return a response of how good was the bid
    }

    public static Boolean check_If_Bid_Is_A_Lie( int diceNumber, int numberOfTimesItRepeated){

        ArrayList<Integer> allDiceInRound = new ArrayList<>();

        for (int i = 0; i < allPlayerNames.size(); i++) {

          //  System.out.println("\n\nPlayer: " + allPlayers.get(allPlayerNames.get(i)).name);

            for (int j = 0; j < allPlayers.get(allPlayerNames.get(i)).diceCollection.size(); j++) {




                //System.out.println(" DICE :    " +  allPlayers.get(allPlayerNames.get(i)).diceCollection.get(j).faceUpValue);

                allDiceInRound.add( allPlayers.get(allPlayerNames.get(i)).diceCollection.get(j).faceUpValue);
            }
           // System.out.println("");



        }

        int diceCounter = 0;

        for (int i = 0; i < allDiceInRound.size(); i++) {

            if(allDiceInRound.get(i) == diceNumber ){

                diceCounter++;
            }

        }


        Boolean responseForAccusation ;

        //TODO determine if bid is greater than or equal
        if(diceCounter >= numberOfTimesItRepeated){

            responseForAccusation = false;

        }
        else{

            responseForAccusation= true;
        }

        System.out.println("\nThere are " + diceCounter + " Number " + diceNumber + "'s" + " ->       Bid Number Quantity -> " + numberOfTimesItRepeated);

        return responseForAccusation;


    }

    public static void cls()
    {
        for (int i = 0; i < 4; i++) {
            System.out.println(" ");
        }
        System.out.println("--------------------------------------------");
    }

    public void reRollAllDice(int numberOfDice){
        //Todo Gives dice to all players
        for (int i = 0; i < allPlayers.size(); i++) {

            String nameOfPlayer = allPlayerNames.get(i);

            for (int j = 0; j <allPlayers.get(nameOfPlayer).diceCollection.size(); j++) {

                allPlayers.get(nameOfPlayer).diceCollection.get(j).roll();
                Die newDie = new Die();
                newDie.roll();
                allPlayers.get(nameOfPlayer).diceCollection.add(newDie);

            }

            System.out.println("\n "+ numberOfDice +" Dice have been added to " + nameOfPlayer);

        }

    }

    public void initializeGame(){

        Boolean isAddingAnotherPlayer;


        //Todo Creates new users and adds them to game -> Example Matt - PLAYERMATT
        do{
            System.out.print("\nEnter Player Name: ");
            String playerName = scanner.nextLine();

            Player newPerson  = new Player(playerName);
            allPlayerNames.add(playerName);



            allPlayers.put(playerName, newPerson);

            System.out.print("Adding Another Player ? ");
            String response = scanner.nextLine();

            isAddingAnotherPlayer = response.equals("y") ? true : false;

        }while(isAddingAnotherPlayer == true);


        System.out.println("\nHow many Dice should each player start with ? ");
        int numberOfDice = scanner.nextInt();


        cls();

        //Todo Gives dice to all players
        for (int i = 0; i < allPlayers.size(); i++) {

            String nameOfPlayer = allPlayerNames.get(i);

            for (int j = 0; j <numberOfDice; j++) {

                Die newDie = new Die();
                newDie.roll();
                allPlayers.get(nameOfPlayer).diceCollection.add(newDie);

            }

            System.out.println("\n "+ numberOfDice +" Dice have been added to " + nameOfPlayer);

        }



        while (collectAllPlayersWithGoodDice().size() > 1 ) {

            Scanner scanner = new Scanner(System.in);
            Player currentPlayer = determineWhoseTurnIsIt();

            printCurrentTable();

            if( currentBid.equals("")){

                System.out.println( "\n\nThere are no Current Bids");



                System.out.println("What bid would you like to make " + currentPlayer.name + " ? example 1 x4");
                String inputBid = scanner.nextLine();
                currentBid = inputBid;
                currentBidHolder = currentPlayer;


            }
            else{



                System.out.println("\nPlayers turn: " + currentPlayer.name + "\n");
                System.out.println("Hey  " + currentPlayer.name + " ? Bid or accuse Liar");
                String answerAction = scanner.nextLine();

                if(answerAction.toLowerCase().equals("bid")){

                    System.out.println("What bid would you like to make " + currentPlayer.name + " ? example 1 x4");
                    String inputBid = scanner.nextLine();





                    while(ValidationLibrary.validateNewBid_vs_OldBid(inputBid,currentBid) == false){

                        System.out.println("What bid would you like to make " + currentPlayer.name + " ? example 1 x4");
                        inputBid = scanner.nextLine();

                    }
                    currentBid = inputBid;
                    currentBidHolder = currentPlayer;

                }
                else if(answerAction.toLowerCase().equals("liar")){

                    System.out.println("\n"+currentPlayer.name + " you've chosen liar");

                    String[] bidSplit = currentBid.split("x");

                    int diceNumber = Integer.parseInt(bidSplit[0].strip());
                    int numbersOfTimesRepeated = Integer.parseInt(bidSplit[1].strip());

                    //Check if bid is true or false

                    //System.out.println("\n\n         Current Table    "    );
                    System.out.println("---------------------------------------"    );
                    Boolean liarAnswer = check_If_Bid_Is_A_Lie( diceNumber, numbersOfTimesRepeated);
                    System.out.println("---------------------------------------"    );

                    System.out.println( liarAnswer==true ? "\nYes It was a lie!!!" : "\n It was the truth! ");


                    if(liarAnswer == false){


                    removeDieFromPlayer( currentPlayer);

                    System.out.println(currentPlayer.name +  " now has " +currentPlayer.diceCollection.size() + " dice ");


                    }
                    else{

                        System.out.println("\n\n" + currentBidHolder.name + " now has lost 1 Die ");
                        removeDieFromPlayer(currentBidHolder);
                        setCurrentBid("");

                        //print table

                    }

//
                    if(collectAllPlayersWithGoodDice().size() <= 2){

                        nextTurn(currentPlayer);
                        currentPlayer = determineWhoseTurnIsIt();

                    }

                }

            }

            //chooseNextPersonToPlay();



//

             if(collectAllPlayersWithGoodDice().size() >= 2){
                nextTurn(currentPlayer);
            }
            //TODO this is changegin the turns
            ;


            System.out.println("Press Enter for Next Turn");
            scanner.nextLine();

        }



        for (int i = 0; i < allPlayers.size(); i++) {


            if(allPlayers.get(allPlayerNames.get(i)).diceCollection.size() >= 1){

                System.out.println( allPlayers.get(allPlayerNames.get(i)).name + " HAS WON THE GAME WITH "+  allPlayers.get(allPlayerNames.get(i)).diceCollection.size() +" Dice ! CONGRATS!!!!!");

            }


        }




//
//        for (String player : playerNames) {
//
//            String allDice_numbers =  rollDice(6, 6);
//            gameData.put(player,allDice_numbers);
//        }
    }

    public void nextTurn(Player currentPlayer){
        for (int i = 0; i < collectAllPlayersWithGoodDice().size(); i++) {



            if(collectAllPlayersWithGoodDice().get(i) == currentPlayer){



                currentPlayer.turn = false;

                if( i == collectAllPlayersWithGoodDice().size() -1 ){

                    collectAllPlayersWithGoodDice().get(0).turn = true;
                }
                else{

                    int nextTurn = i + 1;
                    collectAllPlayersWithGoodDice().get(nextTurn).turn = true;
                }







                break;


            }

        }

    }



    public void removeDieFromPlayer( Player player){

        int indexOfLastDie =  player.diceCollection.size() - 1;
        player.diceCollection.remove(indexOfLastDie);


        if( player.diceCollection.size() == 0){

            System.out.println("You are eliminated , " + player.name);
            collectAllPlayersWithGoodDice().remove(player);
        }

    }


    public ArrayList<Player> collectAllPlayersWithGoodDice(){

        ArrayList<Player> listOfPlayers = new ArrayList<>();

        for (int i = 0; i < allPlayers.size(); i++) {


            if(allPlayers.get(allPlayerNames.get(i)).diceCollection.size() > 0){
                listOfPlayers.add(allPlayers.get(allPlayerNames.get(i)));
            }


        }


        return listOfPlayers;

    }


    public void printCurrentTable(){

        Player currentPlayer = determineWhoseTurnIsIt();

        System.out.println("\n\n         Current Table    "    );
        System.out.println("---------------------------------------"    );
        System.out.println("Current Good Player Sizing: " + collectAllPlayersWithGoodDice().size());

        for (int i = 0; i < allPlayerNames.size(); i++) {

            Player temp_player = allPlayers.get(allPlayerNames.get(i));


            if(temp_player == currentPlayer){
                System.out.println("\nPlayer: " + allPlayers.get(allPlayerNames.get(i)).name + " (TURN)");
                for (int j = 0; j < allPlayers.get(allPlayerNames.get(i)).diceCollection.size(); j++) {




                    if(temp_player== currentPlayer ){
                        System.out.println(" DICE :    " +  allPlayers.get(allPlayerNames.get(i)).diceCollection.get(j).faceUpValue );

                    }


                }
            }
            else{
                System.out.println("\nPlayer: " + allPlayers.get(allPlayerNames.get(i)).name);
                if(temp_player.diceCollection.size() == 0){
                    System.out.println(" DICE :    ELIMINATED" );

                }
                else{
                    System.out.println(" DICE :    " +   temp_player.diceCollection.size() + " Hidden Dice" );

                }

            }






        }




        if(currentBid != ""){
            String[] currentBidSplit = currentBid.split("x");
            String oldDiceNumber = currentBidSplit[0].strip();
            String oldNumbersOfTimesRepeated = currentBidSplit[1].strip();

            System.out.println("---------------------------------------"    );
            System.out.println("Current Bid:        -   Dice: " + oldDiceNumber + " Quantity: " + oldNumbersOfTimesRepeated);
            System.out.println("Current Bid Holder: -   " + currentBidHolder.name);
            System.out.println("---------------------------------------"    );
        }

    }


}
