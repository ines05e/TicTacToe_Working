import java.util.*;

public class gamev2{
    public static void main (String[]args) {
        boolean ai = !whoplays();
        if (ai) {
            greetings();
            rules();
            play_ai();
        }
        else {
            String[] names = greetings2();
            rules2();
            playppl(names);
        }

    }
    /*In the main method, the computer first takes into account who does the user want to play against (the computer or another player), in order to run
    the correct version of the game (version 1 against the computer or version 2 designed for two players)*/

    static public boolean whoplays(){
        Scanner who = new Scanner(System.in);
        System.out.println("Who would you like to play against ? Answer 'The computer' or 'Another player'");
        String adversary = who.nextLine();
        if (adversary.equals("The computer")) {
            return false;
        }
        else if (adversary.equals("Another player")) {
            return true;
        }
        else{
            System.out.println("Sorry, I didn't quite catch that.");
            return whoplays();
        }
    }
    // this method determines who is the user playing against (the computer or another player)


    static public void greetings(){
        Scanner username = new Scanner(System.in);
        System.out.println("Hello ! What's your name ?");
        String username_store = username.nextLine();
        System.out.println("Hello " + username_store +"! Welcome to this game of Tic Tac Toe");
    }
    //In the greetings method, the computer welcomes the user and asks for their name

    static public void rules() {
        Scanner doesnt_know = new Scanner(System.in);
        System.out.println("Would you like to read the rules of the game ? Answer Yes or No");
        String rulesprint = doesnt_know.nextLine();
        System.out.println(rulesprint);
        if (rulesprint.equals("No"))
        {
            System.out.println("Ok, let's play then !");
            System.out.println("\nAlso, remember you can always check the rules during the game if you're unsure by entering 'Help'. If you don't want to play anymore, you can quit the game by entering 'Quit'. Hope you have a nice time !\n");

        }
        else if (rulesprint.equals("Yes"))
        {
            System.out.println("The game is played on a grid that's 3 squares by 3 squares.\n" +
                    "You are X, your friend (or the computer in this case) is O. ...\n" +
                    "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n" +
                    "When all 9 squares are full, the game is over. \n" +
                    "That's all !");
            System.out.println("\nAlso, remember you can always check the rules during the game if you're unsure by entering 'Help'. \n If you don't want to play anymore, you can quit the game by entering 'Quit'. \n Hope you have a nice time !\n");

        }
        else{
            System.out.println("The answer should be Yes or No");
            rules();
        }
    }
    /*In the rules method, the computer asks the user if they want to read the rules of the game and prints them if
        the player says so */

    static public void play_ai(){
        String[] board = {" "," "," "," "," "," "," "," "," "};
        String role = roles(board);
        displaygrid(board);
        while (readuser(board, role) && !winner(board,role) && !is_full(board)) {
            ai(board, role);
            displaygrid(board);
        }
        end();
    }
    /*In the play method, the computer initialises the grid and runs several methods in order for the game of Tic Tac Toe
        to take place whilst checking that there is no winner/draws */

    static public String roles(String[]board){
        String[] role = {"X", "O"};
        Random letter = new Random();
        String assign = role[letter.nextInt(role.length)];

        String announce = "You are " + assign;
        String begin = null;
        if (assign == "X") {
            begin = "\nYou play first !\n";
        }
        else if (assign == "O") {
            begin = "\nThe computer will play first\n";
            ai(board, assign);
        }
        System.out.println(announce + begin);
        return assign ;
    }
    /* In the roles method, the X and O strings are attributed to the two playing entities (the user and the computer).
        Whoever is X begins to play - the computer's turns take place thanks to the ai method described later on in the code*/

    static public void ai(String[] board, String role) {
        Random position = new Random();
        int index = position.nextInt(9);
        while(board[index] != " ") {
            index = position.nextInt(9);
        }
        if (role == "X") {
            board[index] = "O" ;
        }
        else {
            board[index] = "X";
        }
    }
    /*this method runs the computer's random modifications of the grid by drawing a random box in the grid, checking if
        it's empty and filling it in if so (else it generates another box and continues to do so until a box is found - or not)*/

    static public void displaygrid(String[] board) {
        String grid =  "  |  a  |  b  |  c  |   \n" +
                "--+-----+-----+-----+---\n" +
                "1 | " + " " + board[0] +" " + " | "+ " " +board[1] + " " +" | " + " " +board[2] +" " + " | 1 \n" +
                "--+-----+-----+-----+---\n" +
                "2 |  " + board[3] + "  |  " + board[4] + "  |  " + board[5] + "  | 2 \n" +
                "--+-----+-----+-----+---\n" +
                "3 |  " + board[6] + "  |  " + board[7] + "  |  " + board[8] + "  | 3 \n" +
                "--+-----+-----+-----+---\n" +
                "  |  a  |  b  |  c  |   \n";
        System.out.println(grid);
    }
    /*the displaygrid method generates the grid which user inputs and computer modifications will modify (it is constituted
        of a string containing an array of strings named board (one index of the array per box)*/

    static public boolean readuser(String[] board, String role) {
        Scanner userinput = new Scanner(System.in);
        String input = userinput.nextLine();
        switch(input){
            case "Help" :
                rules();
                return true;
            case "Quit":
                System.out.println("Bye bye then!");
                return false;
            default:
                usersturn(board, role, input);
                return true;
        }

    }
    /* the readuser method scans user inputs to understand is they are asking for help (the rules method would then be
        called), is they want to exit the game (which would then call the end method), or if it's their turn to play and fill
        in the grid (calling the usersturn method)*/

    static public void usersturn(String[] board, String roles, String userinput) {
        switch (userinput) {
            case "1a":
                if (board[0] == " ") {
                    board[0] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "1b":
                if (board[1] == " ") {
                    board[1] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "1c":
                if (board[2] == " ") {
                    board[2] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "2a":
                if (board[3] == " ") {
                    board[3] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "2b":
                if (board[4] == " ") {
                    board[4] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "2c":
                if (board[5] == " ") {
                    board[5] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "3a":
                if (board[6] == " ") {
                    board[6] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "3b":
                if (board[7] == " ") {
                    board[7] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            case "3c":
                if (board[8] == " ") {
                    board[8] = roles;
                } else {
                    wrongindex();
                    readuser(board,roles);

                }
                break;
            default:
                wronginput();
                readuser(board,roles);
                break;

        }
    }
    /* This method scans userinput to see where do they want to place their assigned X or O. If the input isn't a valid index
        combination, the computer asks for a valid answer (calling the wronginput method). If the box has already been filled
        in, the computer asks for another index which is empty (the wrongindex method is then called)*/

    static public void wrongindex() {
        System.out.println("Sorry, but this grid has already been filled. Could you enter an empty one's index ?");
    }
    //this method indicates to the user that the box which they are trying to fill in is already containing an X or O

    static public void wronginput(){
        System.out.println("Sorry, I didn't quite catch that. Could you enter an valid grid index ?");
    }
    /*this method indicates to the user that their input is not a valid index - it doesn't lead to a box to be filled in the grid */

    static public boolean is_full(String[] board) {
        for (int i = 0; i < 9; i++ ) {
            if (board[i] == " "){
                return false;
            }
        }
        return true;
    }
    // this method scans if the grid is full in order for the computer to call the game a draw if this is the case

    static public boolean winner(String[] board,String role) {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX")) {
                whowon("X",role);
                return true;
            } else if (line.equals("OOO")) {
                whowon("O",role);
                return true;
            }
            else if (is_full(board)) {
                System.out.println("This is a draw...");
                return true;
            }
        }
        return false;
    }
    /* this method scans for all possible combinations of X or O which could lead one of the to playing entities to victory
        it also scans if the grid is full, and if so, it calls the game a draw*/

    static public void whowon(String winner, String role){
        if (winner == role) {
            System.out.println("Congratulations! You won !");
        }
        else {
            System.out.println("The computer won... Better luck next time!");
        }
    }
    /* this method scans whether it is X or O who won, and also takes into account which playing entity was attributed x
        and which one was attributed O, to declare who won the game*/

    static public void end(){
        Scanner next = new Scanner(System.in);
        System.out.println("Would you like to play again ? Answer Yes or No");
        String playorno = next.nextLine();
        if (playorno.equals("Yes")) {
            play_ai();
        }
        else {
            System.out.println("Bye bye then!");
        }
    }
    // this last method offers the user to play another game or to quit the program

    static public String[] greetings2(){
        Scanner username = new Scanner(System.in);
        System.out.println("Hello ! What's the first player's name ?");
        String username_store = username.nextLine();
        System.out.println("Hello " + username_store +"! Welcome to this game of Tic Tac Toe");
        Scanner username2 = new Scanner(System.in);
        System.out.println("What's the second player's name ?");
        String username2_store = username2.nextLine();
        System.out.println("Hello " + username2_store +"! Welcome to this game of Tic Tac Toe");
        String[] names = {username_store, username2_store};
        return names;
    }
    //In the greetings2 method, the computer welcomes the two user and asks for their names (which will be stored for later purposes)


    static public void rules2() {
        Scanner doesnt_know = new Scanner(System.in);
        System.out.println("Would you like to read the rules of the game ? Answer Yes or No");
        String rulesprint = doesnt_know.nextLine();
        System.out.println(rulesprint);
        if (rulesprint.equals("No")) {
            System.out.println("Ok, let's play then !");
            System.out.println("\nAlso, remember you can always check the rules during the game if you're unsure by entering 'Help'. If you don't want to play anymore, you can quit the game by entering 'Quit'. Hope you have a nice time !\n");

        } else if (rulesprint.equals("Yes")) {
            System.out.println("The game is played on a grid that's 3 squares by 3 squares.\n" +
                    "If one is X, one's opponent is O. ...\n" +
                    "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n" +
                    "When all 9 squares are full, the game is over. \n" +
                    "That's all !");
            System.out.println("\nAlso, remember you can always check the rules during the game if you're unsure by entering 'Help'. \n If you don't want to play anymore, you can quit the game by entering 'Quit'. \n Hope you have a nice time !\n");

        } else {
            System.out.println("The answer should be Yes or No");
            rules();
        }
    }
    /*In the rules2 method, the computer asks the users if they want to read the rules of the game and prints them if
           the players say so */

    static public void playppl(String[] names){
        String[] board = {" "," "," "," "," "," "," "," "," "};
        String role = rolesppl(board, names);
        displaygrid(board);
        String symbol = "X";
        while (readuser(board, symbol) && !winnerppl(board,role,names) && !is_full(board)) {
            displaygrid(board);
            if (symbol.equals("X")){
                symbol = "O";
            }
            else {
                symbol = "X";
            }
        }
        end2(names);
    }
    /*In the play method, the computer initialises the grid and runs several methods in order for the game of Tic Tac Toe
        to take place whilst checking that there is no winner/draws */

    static public String rolesppl(String[]board, String[] names){
        String[] roleplayer1 = {"X", "O"};
        Random letter = new Random();
        String assign1 = roleplayer1[letter.nextInt(roleplayer1.length)];

        String begin = null;
        if (assign1 == "X") {
            begin = "\n" +names[0] + " is X and plays first!\n" + names[1] + " is O and plays second ...";
        }
        else if (assign1 == "O") {
            begin = "\n" + names[1] + " is X and plays first!\n" + names[0] + " is O and plays second ...";
        }
        System.out.println(begin);
        return assign1;
    }
    /* In the rolesppl method, the X and O strings are attributed to the two players (this is done by using the names which the latter entered previously).
        and which were stored in the greetongs method. Whoever is X begins to play*/

    static public boolean winnerppl(String[] board,String role, String[] names) {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX")) {
                whowon2("X",role, names);
                return true;
            } else if (line.equals("OOO")) {
                whowon2("O",role,names);
                return true;
            }
            else if (is_full(board)) {
                System.out.println("This is a draw...");
                return true;
            }
        }
        return false;
    }
    /* this method scans for all possible combinations of X or O which could lead one of the two players to victory
        it also scans if the grid is full, and if so, it calls the game a draw*/
    static public void whowon2(String winner, String role, String[] names){
        if (winner == role) {
            System.out.println("Congratulations! You won " + names[0] + "! Better luck next time " + names[1]);
        }
        else {
            System.out.println("Congratulations! You won " + names[1] + "! Better luck next time " + names[0]);
        }
    }
    /* this method scans whether it is X or O who won, and also takes into account which player was attributed x
        and which one was attributed O, to declare who won the game*/

    static public void end2(String [] names){
        Scanner next = new Scanner(System.in);
        System.out.println("Would you like to play again ? Answer Yes or No");
        String playorno = next.nextLine();
        if (playorno.equals("Yes")) {
            playppl(names);
        }
        else {
            System.out.println("Bye bye then!");
        }
    }
    // this last method offers the users to play another game or to quit the program



}
