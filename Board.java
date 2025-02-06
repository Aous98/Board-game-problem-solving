package aous.st;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
    private ArrayList<String> whiteOutput = new ArrayList<String>();
    private ArrayList<String> blackOutput = new ArrayList<String>();
    private final int boardSize = 8;
    private final int numbers = 49;
    private final int letters = 97;
    private String[][] myBoard = new String[boardSize][boardSize];

    Board(String initialPosetionsWhite, String initialPosetionsBlack) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                myBoard[i][j] = "emp";
            }
        }
        Pattern p = Pattern.compile("(\\w\\d)_(\\w+)");
        Matcher mw = p.matcher(initialPosetionsWhite);
        Matcher mb = p.matcher(initialPosetionsBlack);
        String temp1;
        String temp2;
        int x;
        int y;
        while (mw.find()) {
            temp1 = mw.group(1);
            temp2 = mw.group(2);
            x = (int) temp1.charAt(0) - letters;
            y = (int) temp1.charAt(1) - numbers;
            myBoard[x][y] = temp2;
        }
        while (mb.find()) {
            temp1 = mb.group(1);
            temp2 = mb.group(2);
            x = (int) temp1.charAt(0) - letters;
            y = (int) temp1.charAt(1) - numbers;
            myBoard[x][y] = temp2;
        }

    }

    public final void printOutput() {
        final int letter = 97;
        final int number = 49;
        String s = "";
        int temp1;
        int temp2;
        char n;
        char l;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                s = "";
                if (!(myBoard[i][j].equals("emp"))) {
                    temp1 = i + letter;
                    temp2 = j + number;
                    l = (char) temp1;
                    n = (char) temp2;
                    s = s + l + n + '_' + myBoard[i][j];
                    if (myBoard[i][j].charAt(0) == 'w' || myBoard[i][j].charAt(0) == 'W') {
                        whiteOutput.add(s);
                    } else {
                        blackOutput.add(s);
                    }

                }
            }
        }
        Collections.sort(whiteOutput);
        Collections.sort(blackOutput);
        for (int i = 0; i < whiteOutput.size(); i++) {
            System.out.print(whiteOutput.get(i));
            System.out.print(' ');
        }
        System.out.println();
        for (int i = 0; i < blackOutput.size(); i++) {
            System.out.print(blackOutput.get(i));
            System.out.print(' ');
        }
    }

    public final boolean checkWhiteCell(int x, int y) {
        if ((x + y) % 2 != 0) {
            return true;
        }
        return false;
    }

    public final boolean checkBusyCell(int x, int y) {
        if (!myBoard[x][y].equals("emp")) {
            return true;
        }
        return false;
    }

    public final boolean checkNeighbors(int x, int y, int id) throws GeneralError {
        int[] coor = {x + 1, x - 1, y + 1, y - 1};
        int d0 = coor[0] - x; // 1
        int d1 = coor[1] - x; // -1
        int d2 = coor[2] - y; // 1
        int d3 = coor[3] - y; // -1
        int temp1;
        int temp2;
        char s1;
        char c1;
        char s2;
        char c2;
        int idOfsecondPlayer;
        if (id == 1) {
            idOfsecondPlayer = 2;
            s1 = 'w';
            c1 = 'W';
            s2 = 'b';
            c2 = 'B';

        } else {
            idOfsecondPlayer = 1;
            s2 = 'w';
            c2 = 'W';
            s1 = 'b';
            c1 = 'B';
        }
        if (myBoard[x][y].charAt(0) == c1) {
            temp1 = coor[0] + d0 - 1;
            temp2 = coor[2] + d2 - 1;
            while (temp1 <= 7 && temp2 <= 7) {
                if (myBoard[temp1][temp2].charAt(0) == s2 || myBoard[temp1][temp2].charAt(0) == c2) {
                    if (!(temp1 + d0 > 7 || temp2 + d2 > 7)) {
                        if (myBoard[temp1 + d0][temp2 + d2].equals("emp")) {
                            return true;
                        }
                    }
                }
                temp1 += d0;
                temp2 += d2;
            }
            temp1 = coor[1] + d1 + 1;
            temp2 = coor[2] + d2 - 1;
            while (temp1 >= 0 && temp2 <= 7) {
                if (myBoard[temp1][temp2].charAt(0) == s2 || myBoard[temp1][temp2].charAt(0) == c2) {
                    if (!(temp1 + d1 < 0 || temp2 + d2 > 7)) {
                        if (myBoard[temp1 + d1][temp2 + d2].equals("emp")) {
                            return true;
                        }
                    }
                }
                temp1 += d1;
                temp2 += d2;
            }
            temp1 = coor[1] + d1 + 1;
            temp2 = coor[3] + d3 + 1;
            while (temp1 >= 0 && temp2 >= 0) {
                if (myBoard[temp1][temp2].charAt(0) == s2 || myBoard[temp1][temp2].charAt(0) == c2) {
                    if (!(temp1 + d1 < 0 || temp2 + d3 < 0)) {
                        if (myBoard[temp1 + d1][temp2 + d3].equals("emp")) {
                            return true;
                        }
                    }
                }
                temp1 += d1;
                temp2 += d3;
            }
            temp1 = coor[0] + d0 - 1;
            temp2 = coor[3] + d3 + 1;
            while (temp1 <= 7 && temp2 >= 0) {
                if (myBoard[temp1][temp2].charAt(0) == s2 || myBoard[temp1][temp2].charAt(0) == c2) {
                    if (!(temp1 + d0 > 7 || temp2 + d3 < 0)) {
                        if (myBoard[temp1 + d0][temp2 + d3].equals("emp")) {
                            return true;
                        }
                    }
                }
                temp1 += d0;
                temp2 += d3;
            }
        } else if (myBoard[x][y].charAt(0) == s1) {
            if (!(coor[0] > 7 || coor[2] > 7)) {
                if (myBoard[coor[0]][coor[2]].charAt(0) == s2 || myBoard[coor[0]][coor[2]].charAt(0) == c2) {
                    if (!(coor[0] + 1 > 7 || coor[2] + 1 > 7)) {
                        if (myBoard[coor[0] + 1][coor[2] + 1].equals("emp")) {
                            return true;
                        }
                    }
                }
            }
            if (!(coor[0] > 7 || coor[3] < 0)) {
                if (myBoard[coor[0]][coor[3]].charAt(0) == s2 || myBoard[coor[0]][coor[3]].charAt(0) == c2) {
                    if (!(coor[0] + 1 > 7 || coor[3] - 1 < 0)) {
                        if (myBoard[coor[0] + 1][coor[3] - 1].equals("emp")) {
                            return true;
                        }
                    }
                }
            }
            if (!(coor[1] < 0 || coor[2] > 7)) {
                if (myBoard[coor[1]][coor[2]].charAt(0) == s2 || myBoard[coor[1]][coor[2]].charAt(0) == c2) {
                    if (!(coor[1] - 1 < 0 || coor[2] + 1 > 7)) {
                        if (myBoard[coor[1] - 1][coor[2] + 1].equals("emp")) {
                            return true;
                        }
                    }
                }
            }
            if (!(coor[1] < 0 || coor[3] < 0)) {
                if (myBoard[coor[1]][coor[3]].charAt(0) == s2 || myBoard[coor[1]][coor[3]].charAt(0) == c2) {
                    if (!(coor[1] - 1 < 0 || coor[3] - 1 < 0)) {
                        if (myBoard[coor[1] - 1][coor[3] - 1].equals("emp")) {
                            return true;
                        }
                    }
                }
            }
        } else {
            throw new GeneralError("general error");
        }

        return false;
    }

    public final boolean checkInvalidMove(int id) throws GeneralError {
        boolean isAllNeighborsNull;
        char playerS;
        char playerC;
        if (id == 1) {
            playerS = 'w';
            playerC = 'W';
        } else {
            playerS = 'b';
            playerC = 'B';
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (myBoard[i][j].charAt(0) == playerS || myBoard[i][j].charAt(0) == playerC) {
                    isAllNeighborsNull = checkNeighbors(i, j, id);
                    if (isAllNeighborsNull) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public final void move(String move, int playerId) throws BusyCell, WhiteCell, InvalidMove, GeneralError {
        String[] moveChain = move.split("-");
        int x1;
        int y1;
        int x2;
        int y2;
        String temp1;
        String temp2;
        Pattern p = Pattern.compile("(\\w\\d)_(\\w+)");
        Matcher m0 = p.matcher(moveChain[0]);
        Matcher m1 = p.matcher(moveChain[1]);
        m0.find();
        m1.find();
        boolean isInvalidMove = checkInvalidMove(playerId);
        if (moveChain.length > 2) {
            throw new GeneralError("general error");
        }
        temp1 = m0.group(1);
        x1 = (int) temp1.charAt(0) - letters;
        y1 = (int) temp1.charAt(1) - numbers;
        myBoard[x1][y1] = "emp";
        temp1 = m1.group(1);
        temp2 = m1.group(2);
        x2 = (int) temp1.charAt(0) - letters;
        y2 = (int) temp1.charAt(1) - numbers;
        boolean isBusyCell = checkBusyCell(x2, y2);
        myBoard[x2][y2] = temp2;
        if (playerId == 1 && y2 == 7 && myBoard[x2][y2].charAt(0) == 'w') {
            myBoard[x2][y2] = myBoard[x2][y2].replaceFirst("w", "W");
        }
        if (playerId == 2 && y2 == 0 && myBoard[x2][y2].charAt(0) == 'b') {
            myBoard[x2][y2] = myBoard[x2][y2].replaceFirst("b", "B");
        }
        boolean isCellWhite = checkWhiteCell(x2, y2);
        if (isInvalidMove) {
            throw new InvalidMove("invalid move");
        }
        if (isCellWhite) {
            throw new WhiteCell("white cell");
        }
        if (isBusyCell) {
            throw new BusyCell("busy cell");
        }
    }

    public final void killAChecker(int x1, int y1, int x2, int y2, int id) {
        int xDelta;
        int yDelta;
        int dx;
        int dy;
        int x;
        int y;
        char s;
        char c;
        if (id == 1) {
            s = 'b';
            c = 'B';
        } else {
            s = 'w';
            c = 'W';
        }
        xDelta = x1 - x2;
        yDelta = y1 - y2;
        if (xDelta > 0) {
            dx = -1;
        } else dx = 1;
        if (yDelta > 0) {
            dy = -1;
        } else dy = 1;
        for (int r = 1; r < Math.abs(xDelta); r++) {
            x = x1 + r * dx;
            y = y1 + r * dy;
            if (myBoard[x][y].charAt(0) == s || myBoard[x][y].charAt(0) == c) {
                myBoard[x][y] = myBoard[x][y].substring(1, myBoard[x][y].length());
                break;
            }
        }

    }

    public final void kill(String kill, int playerId) throws BusyCell, WhiteCell, InvalidMove, GeneralError {
        int x1;
        int y1;
        int x2;
        int y2;
        String temp1;
        String temp2;
        String[] killChain = kill.split(":");
        Pattern p = Pattern.compile("(\\w\\d)_(\\w+)");
        /////////////
        for (int i = 1; i < killChain.length; i++) {
            Matcher m0 = p.matcher(killChain[i - 1]);
            Matcher m1 = p.matcher(killChain[i]);
            m0.find();
            m1.find();
            temp1 = m0.group(1);
            x1 = (int) temp1.charAt(0) - letters;
            y1 = (int) temp1.charAt(1) - numbers;
            myBoard[x1][y1] = "emp";
            temp1 = m1.group(1);
            temp2 = m1.group(2);
            x2 = (int) temp1.charAt(0) - letters;
            y2 = (int) temp1.charAt(1) - numbers;
            boolean isBusyCell = checkBusyCell(x2, y2);
            myBoard[x2][y2] = temp2;
            if (playerId == 1 && y2 == 7 && myBoard[x2][y2].charAt(0) == 'w') {
                myBoard[x2][y2] = myBoard[x2][y2].replaceFirst("w", "W");
            }
            if (playerId == 2 && y2 == 0 && myBoard[x2][y2].charAt(0) == 'b') {
                myBoard[x2][y2] = myBoard[x2][y2].replaceFirst("b", "B");
            }
            boolean isCellWhite = checkWhiteCell(x2, y2);
            if (isCellWhite) {
                throw new WhiteCell("white cell");
            }
            if (isBusyCell) {
                throw new BusyCell("busy cell");
            }
            killAChecker(x1, x2, y1, y2, playerId);
        }
    }
}
