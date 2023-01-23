package fr.tennis;

import fr.tennis.domain.Game;
import fr.tennis.domain.Player;
import fr.tennis.util.DisplayInformation;
import fr.tennis.util.DisplayInformationImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // DisplayInformation Instantiation
        DisplayInformation displayScoresImplSystemOut = new DisplayInformationImpl();

        // Display opening banner
        displayScoresImplSystemOut.displayOpeningBanner();

        Player player1 = new Player('A');
        Player player2 = new Player('B');

        // Score Input
        String scoreInput;
        do {
            Scanner scannerInScore = new Scanner(System.in);
            System.out.print("Please enter the score input containing only 'A' and 'B' letters: ");
            scoreInput = scannerInScore.nextLine();
        } while (!scoreInput.matches("^[AB]+$"));

        // Match Instantiation
        Game game = new Game(player1, player2);
        game.play(displayScoresImplSystemOut, scoreInput);

    }
}
