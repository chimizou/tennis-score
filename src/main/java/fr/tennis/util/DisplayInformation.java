package fr.tennis.util;

import fr.tennis.domain.Player;

public interface DisplayInformation {

    void displayGameScore(String gameScorePlayer1, String gameScorePlayer2);

    void announceDeuceRule();

    void displayGameWinner(Player player);

    void displayOpeningBanner();

}
