public class GameChecker {
    //This Attribute used as a game board
    public static char[][][] gameBoard = new char[8][8][];

    /*
    This method is only for initializing gameBoard which is GameChecker Class Attribute
    Local Variable Used in it are as follows:
    rightBox:
        Primitive-DataType: boolean
        Use:    This local variable used for making black boxes it is first false then skip
                Next Index, and make black box after.
    positionNumber:
        Primitive-DataType: int
        Use:    This Local Variable used for giving black boxes number respective 1-32.
     */

    public static void setGameBoard(){
        boolean rightBox = false;
        int positionNumber = 1;
        for (int row = 0; row < gameBoard.length; row++) {                      //iterating rows
            for (int column = 0; column < gameBoard[row].length; column++) {    //iterating columns
                if(rightBox){                                                   //if this index is right for making black/dark box

                    gameBoard[row][column] = new char[2];                       //Then create an array with 2 indexes
                    gameBoard[row][column][0] =  '.';                            //0 for piece
                    gameBoard[row][column][1] = (char) positionNumber;          //1 for positionNumber

                    positionNumber++;                                           //After one position Setup then Position Number Increment

                }else{                                                          //if index not for black box or for white
                    gameBoard[row][column] =  new char[1];                      //then create an array with one index
                    gameBoard[row][column][0] = '-';                            //0 for '-' it denotes white box
                }

                rightBox = !rightBox;                                           //converting true to false / false to true
                                                                                //because black box after one white box
            }

            rightBox = !rightBox;                                                //after every row, last element state persist
                                                                                 //if last is black next row start with black vise versa
        }
    }


    //This method is only for printing current gameBoard State
    public static void printBoard(){
        for (char[][] rows : gameBoard) {                                       //Iterate Rows
            for (char[] columns : rows) {                                       //Iterate Columns
                System.out.print(columns[0]);                                   //print 0 index
            }
            System.out.println();                                               //After Printing one row Jump next Line
        }
    }

    /*
    This Method is for Moving Piece only Single Step
     */
    public static String singleMove(int x, int y, char c){
        return null;
    }

    public static void main(String[] args) {
        setGameBoard();
        printBoard();
    }


}
