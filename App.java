package aous.st;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws GeneralError {
        try {
            Pattern paternOfMove = Pattern.compile("(\\w\\d_\\w+)-(\\w\\d_\\w+)");
            Pattern paternOfKill = Pattern.compile("((\\w\\d_\\w+):)+(\\w+)");

            Scanner sc = new Scanner(System.in);
            String whiteCheckersInputPosetions = sc.nextLine();
            String blackCheckersInputPosetions = sc.nextLine();
            whiteCheckersInputPosetions = whiteCheckersInputPosetions.trim();
            blackCheckersInputPosetions = blackCheckersInputPosetions.trim();
            Board board = new Board(whiteCheckersInputPosetions, blackCheckersInputPosetions);
            while (sc.hasNext()) {
                String readLine = sc.nextLine();
                String[] line = readLine.split(" ");
                Matcher matcherOfMove0 = paternOfMove.matcher(line[0]);
                Matcher matcherOfMove1 = paternOfMove.matcher(line[1]);
                Matcher matcherOfKill0 = paternOfKill.matcher(line[0]);
                Matcher matcherOfKill1 = paternOfKill.matcher(line[1]);
                if (matcherOfMove0.find()) {
                    board.move(line[0], 1);
                } else if (matcherOfKill0.find()) {
                    board.kill(line[0], 1);
                } else {
                    throw new GeneralError("general error");
                }
                if (matcherOfMove1.find()) {
                    board.move(line[1], 2);
                } else if (matcherOfKill1.find()) {
                    board.kill(line[1], 2);
                } else {
                    throw new GeneralError("general error");
                }
            }
            sc.close();
            board.printOutput();
        } catch (BusyCell | WhiteCell | InvalidMove | GeneralError e) {
            System.out.println(e.getMessage());
        }

    }
}
