package Projects.Minesweeper;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int col;
    int row;
    Random random=new Random();
    Scanner input=new Scanner(System.in);
    int qRow,qCol;

    //constructer oyunun kac sutun ve kolon olacagini belirliyoruz
    MineSweeper(int col, int row){
        this.col=col;
        this.row=row;
    }

    //oyun alanini tamamen mayisiz olarak olusturuyoruz
    void createBoard(String[][] copyGame ){
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                copyGame[i][j]="-";
            }
        }
    }

    // oyun alanina alan buyuklugu ile orantili olarak
    // rastgele mayinlari yerlestiriyoruz
    void createMines(String[][] game, String[][] copyGame){
        createBoard(copyGame);
        int mine=(row*col)/4;
        int counter=0;
        while(counter<mine){
            int mineRow=random.nextInt(0,row);
            int mineCol=random.nextInt(0,col);
            game[mineRow][mineCol]="*";
            counter++;
        }

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                int mineNum=0;
                if(game[i][j]!="*"){
                    if(i==0 && j==0){
                        for (int k = i; k <= i + 1; k++) {
                            for (int n = j; n <= j + 1; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if (i != 0 && j != 0 && i < row - 1 && j < col - 1) {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }

                    } else if(i == 0 & j != 0 && j < col - 1){
                        for (int k = i; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if(i != 0 & j == 0 && i < row - 1){
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int n = j; n <= j + 1; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if (i==row-1 && j==col-1) {
                        for (int k = i - 1; k <= i; k++) {
                            for (int n = j - 1; n <= j; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if (i == row - 1 && j != col - 1 && j > 0) {
                        for (int k = i - 1; k <= i; k++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if (i != row - 1 && j == col - 1 && i > 0) {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if (i == row - 1 && j == 0) {
                        for (int k = i - 1; k <= i; k++) {
                            for (int n = j; n <= j + 1; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    } else if (i == 0 && j == col - 1) {
                        for (int k = i; k <= i + 1; k++) {
                            for (int n = j - 1; n <= j; n++) {
                                if (game[k][n] == "*") {
                                    mineNum++;
                                }
                            }
                        }
                    }
                    String count=Integer.toString(mineNum);
                    game[i][j]=count;
                }
            }
        }

    }

    boolean isWin(int qRow,int qCol, String [][]game, int counter){
        if(game[qRow][qCol]=="*"){
            System.out.println("BOOOOM Oyunu Kaybettiniz");
            return false;
        }else if(counter==((row*col)-(row*col)/4)){
            System.out.println("TEBRIKLER OYUNU KAZANDINIZ");
            return false;
        }
        return true;
    }

    //girilen satir ve surun numarasinin uygunlugunu kontrol etme
    void checkRowCol(int row, int col, String[][]game, String[][] copyGame){
        int counter=0;
        while(isWin(qRow,qCol,game,counter)){
            System.out.println("Satir numarasini giriniz");
            qRow=input.nextInt();
            while(qRow>row-1){
                System.out.println("Gecersiz Satir Numarasi! tekrar giriniz");
                qRow=input.nextInt();
            }
            System.out.println("Sutun numarasini giriniz");
            qCol=input.nextInt();
            while(qCol>col-1){
                System.out.println("Gecersiz Sutun Numarasi! tekrar giriniz");
                qCol=input.nextInt();
            }
            checkMine(qRow, qCol, copyGame, game, counter );
            counter++;
            System.out.println("===============================");
        }
    }

    void checkMine(int qRow, int qCol, String[][] copyGame, String[][] game, int counter ){
        copyGame[qRow][qCol]=game[qRow][qCol];
        for(String[] row1: copyGame){
            for(String col1:row1){
                System.out.print(col1);
            }
            System.out.println();
        }
        isWin(qRow,qCol,game, counter);
    }

    //Oyunu baslatma fonksiyonu
    void run(){
        String game [][]=new String[row][col];
        String copyGame[][]=new String[row][col];
        createMines(game, copyGame);
        for(String[] row: game){
            for(String col:row){
                System.out.print(col);
            }
            System.out.println();
        }
        checkRowCol(row, col,game, copyGame);

    }

}
