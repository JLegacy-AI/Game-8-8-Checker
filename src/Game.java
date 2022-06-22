
import java.util.Scanner;

public class Game {
    static Scanner read = new Scanner(System.in);

    public static  String[][] oldBoard = new String[8][8];
    public static String[][] board = new String[8][8];

    public Game(){
        boolean position = false;
        int k = 0;
        for (int i = 0; i < oldBoard.length; i++) {
            for (int j = 0; j < oldBoard[i].length; j++) {
                if(position){
                    k++;
                    if(k==1){
                        oldBoard[i][j]= k +","+"B";
                    }else if(1 <= k && k <= 12){
                        oldBoard[i][j]= k +","+"b";
                    }else if(21<= k && k<=31) {
                        oldBoard[i][j]= k +","+"w";
                    }else if(k==32){
                        oldBoard[i][j]= k +","+"W";
                    }else{
                        oldBoard[i][j]= k +","+".";
                    }
                }else{
                    oldBoard[i][j]= "-";
                }
                position= !position;
            }
            position=!position;
        }
    }

    public static void printBoard(){
        for (String[] strings : oldBoard) {
            for (String s : strings) {
                if(s.contains(","))
                    System.out.print(s.split(",")[1]);
                else
                    System.out.print(s);
            }
            System.out.println();
        }
    }

    public static String singleMove(int x, int y, char c){
        int[] index1 = getIndex(x);
        int[] index2 = getIndex(y);

        if (board[index1[0]][index1[1]].split(",")[1].equalsIgnoreCase("" + c)){
            if (isValidSingleMove(x, y)) {
                changePlace(index1, index2);
                return "Single Step Moved Successfully";
            }
        }
        return "Sorry We Can't Move";
    }

    private static String jumpMove(int x, int y, char c) {
        int[] index1 = getIndex(x);
        int[] index2 = getIndex(y);

        if (board[index1[0]][index1[1]].split(",")[1].equalsIgnoreCase("" + c)){
            if (isValidJumpMove(x, y)) {

                changePlace(index1, index2);
                killEnemy(index1, index2);

                return "Jump Successfull";

            }
        }
            return "Sorry We Can't Move";
    }

    public static void killEnemy(int[] x, int[] y){

        int x1 = (x[0]+y[0])/2;
        int y1 = (x[1]+y[1])/2;

        String[] part = oldBoard[x1][y1].split(",");
        oldBoard[x1][y1] = part[0]+",.";

    }

    private static boolean isValidJumpMove(int x, int y){
        int[] index1 = getIndex(x);
        int[] index2 = getIndex(y);

        if(isEnemyBetween(index1, index2))
        if(isMoveDown(x,y) && ( isBlackPublic(index1) || isKing(index1) && isFree(index2))){

            if(index1[0]+2 == index2[0] && index1[1]+2==index2[1])
                return  true;

            if(index1[0]+2 == index2[0] && index1[1]-2==index2[1])
                return  true;

        }else if(isMoveUp(x,y) && ( isWhitePublic(index1)|| isKing(index1)) && isFree(index2)){

            if(index1[0]-2 == index2[0] && index1[1]+2==index2[1])
                return  true;

            if(index1[0]-2 == index2[0] && index1[1]-2==index2[1])
                return  true;

        }
        return false;
    }

    public static void changePlace(int[] index1, int[] index2){
        String pos1 = oldBoard[index1[0]][index1[1]];
        String pos2 =  oldBoard[index2[0]][index2[1]];

        String[] part1 = pos1.split(",");
        String[] part2 = pos2.split(",");

        pos1 = part1[0] + "," + part2[1];
        pos2 = part2[0] + "," + part1[1];

        oldBoard[index1[0]][index1[1]] = pos1;
        oldBoard[index2[0]][index2[1]] = pos2;

    }

    public static  boolean isEnemyBetween(int[]x, int[]y){
        String jumper = oldBoard[x[0]][x[1]].split(",")[1];
        int x1 = (x[0]+y[0])/2;
        int y1 = (x[1]+y[1])/2;
        String enemy = oldBoard[ x1 ][  y1 ].split(",")[1];
        return !jumper.equalsIgnoreCase(enemy);
    }


    private static boolean isValidSingleMove(int x, int y){
        int[] index1 = getIndex(x);
        int[] index2 = getIndex(y);

        if(isMoveDown(x,y) && (isBlackPublic(index1) || isKing(index1) && isFree(index2))){

            if(index1[0]+1 == index2[0] && index1[1]+1==index2[1])
                return  true;

            if(index1[0]+1 == index2[0] && index1[1]-1==index2[1])
                return  true;

        }else if(isMoveUp(x,y) && ( isWhitePublic(index1) || isKing(index1)) && isFree(index2)){

            if(index1[0]-1 == index2[0] && index1[1]+1==index2[1])
                return  true;

            if(index1[0]-1 == index2[0] && index1[1]-1==index2[1])
                return  true;

        }
        return false;
    }

    public static boolean isMoveDown(int x, int y){
        return x<y;
    }

    public static boolean isMoveUp(int x, int y){
        return x>y;
    }

    public static boolean isWhitePublic(int[] x){
        return oldBoard[x[0]][x[1]].contains("w");
    }

    public static boolean isKing(int[] x){
        return oldBoard[x[0]][x[1]].contains("B")  || oldBoard[x[0]][x[1]].contains("W") ;
    }

    public static boolean isBlackPublic(int[] x){
        return oldBoard[x[0]][x[1]].contains("b");
    }

    public static boolean isFree(int[] x){
        return oldBoard[x[0]][x[1]].contains(".");
    }

    public static int[] getIndex(int x){
        for (int i = 0; i < oldBoard.length; i++) {
            for (int j = 0; j < oldBoard[i].length; j++) {

                   if(oldBoard[i][j].split(",")[0].equals(""+x)){
                       return  new int[]{ i,j};
                   }

            }
        }
        return new int[]{-1,-1};
    }

    public  static  void printDoubleBoard(){
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if(board[i][j].contains(",")) {
                    System.out.print(board[i][j].split(",")[1]);
                }
                else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("                                ");
            for (int j = 0; j < 8; j++) {
                if(oldBoard[i][j].contains(","))
                    System.out.print(oldBoard[i][j].split(",")[1]);
                else
                    System.out.print(oldBoard[i][j]);
            }

            System.out.println();
        }
    }



    public static void start(){
        new Game();
        boolean gameOver = false;
        copy(board, oldBoard);
        while(!gameOver){
            printDoubleBoard();
            String in = read.nextLine();
            copy(board, oldBoard);
            String player = in.split(" ")[0].trim();
            String moves = in.split(" ")[1].trim();
            char c = player.charAt(0);
            for (int i = 0; i <  Integer.parseInt(moves); i++) {
                String move = read.nextLine();
                if(move.contains("-")){
                    int x = Integer.parseInt(move.split("-")[0]);
                    int y = Integer.parseInt(move.split("-")[1]);
                    System.out.println( singleMove(x,y, c)+"   Single  "+move);
                }else if(move.contains("x")){
                    String[] jumpList = move.split("x");
                    int x = Integer.parseInt(jumpList[0]);
                    for (int j = 1; j < jumpList.length ; j++) {
                        int y =  Integer.parseInt(jumpList[0]);
                        System.out.println( jumpMove(x,y, c)+"   Jump  "+move);
                    }
                }
            }
        }
    }

    public static void copy(String[][] x, String[][] y){
        for (int i = 0; i < 8; i++) {
            System.arraycopy(y[i], 0, x[i], 0, 8);
        }
    }



    public static void main(String[] args) {
        start();
    }


}
