package fr.tennis.util;


import fr.tennis.domain.Player;

public class DisplayInformationImpl implements DisplayInformation {

    @Override
    public void displayGameScore(String gameScorePlayer1, String gameScorePlayer2) {
        System.out.println("Player A: " + gameScorePlayer1 + " / Player B: " + gameScorePlayer2 );
    }

    @Override
    public void announceDeuceRule() {
        System.out.println("Deuce Rule applied!");
    }

    @Override
    public void displayGameWinner(Player player) {
        System.out.println("\n" + player.getUsername() + " wins the game");
    }

    @Override
    public void displayOpeningBanner() {

        System.out.println("                ,---.                      ,-.                  ");
        System.out.println("                  |               o       (   `                 ");
        System.out.println("                  |   ,-. ;-. ;-. . ,-.    `-.  ,-. ,-. ;-. ,-. ");
        System.out.println("                  |   |-' | | | | | `-.   .   ) |   | | |   |-' ");
        System.out.println("                  '   `-' ' ' ' ' ' `-'    `-'  `-' `-' '   `-' \n\n\n");

    }
}
