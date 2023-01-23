package fr.tennis.domain;

import fr.tennis.util.DisplayInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public
class Game {

    private static final Integer THIRTY_SCORE = 2;
    private static final Integer FORTY_SCORE = 3;
    private static final Integer ADVANTAGE_SCORE = 4;
    private static final List<String> pointsList = Arrays.asList("0", "15", "30", "40", "ADV");
    private static final Long SCORE_DELAY = 500L;

    private Player player1;
    private Player player2;

    private Integer gameScorePlayer1;
    private Integer gameScorePlayer2;

    private String gameScoreTextPlayer1;
    private String gameScoreTextPlayer2;

    private Player winner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameScorePlayer1 = 0;
        gameScorePlayer2 = 0;
        gameScoreTextPlayer1 = "";
        gameScoreTextPlayer2 = "";
        winner = null;
    }

    public void play(DisplayInformation displayInformation, String scoreInput) {
        do {
            if (scoreInput.length() > 0) {
                char scoredPlayer = scoreInput.charAt(0);
                Player pointPlayer = scoredPlayer == getPlayer1().getUsername() ? getPlayer1() : getPlayer2();
                incrementGameScorePlayer(pointPlayer, displayInformation);
                displayGameScore(displayInformation);
                scoreInput = scoreInput.substring(1);
            }
        } while (winner == null);
    }

    void displayGameScore(DisplayInformation displayInformation) {
        if (winner == null) {
            displayInformation.displayGameScore(getScoreDescription(gameScorePlayer1), getScoreDescription(gameScorePlayer2));
        } else {
            announceWinner(displayInformation);
        }
    }

    private void announceWinner(DisplayInformation displayInformation) {
        displayInformation.displayGameWinner(winner);
    }

    private void incrementGameScore(Player player) {
        if (player1.equals(player)) {
            gameScorePlayer1++;
        } else {
            gameScorePlayer2++;
        }
    }

    void incrementGameScorePlayer(Player player, DisplayInformation displayInformation) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);

        // Game Score with {X<40} is ( X - 40 ) or ( 40 - X ) leading to score above 40 => designate a winner
        if ((gameScorePlayer1.equals(FORTY_SCORE) && gameScorePlayer2 < FORTY_SCORE && player1Scoring)
                || (gameScorePlayer2.equals(FORTY_SCORE) && gameScorePlayer1 < FORTY_SCORE && player2Scoring)) {

            designateWinner(player);
            // Game Score is ( 40 - 40 ) or above => activate deuce rule
        } else if (gameScorePlayer1 >= FORTY_SCORE && gameScorePlayer2 >= FORTY_SCORE) {

            activateDeuceRule(player, displayInformation);
            // All other cases => increment scores
        } else {
            incrementGameScore(player);
        }
    }

    private void activateDeuceRule(Player player, DisplayInformation displayInformation) {
        boolean player1Scoring = player.equals(player1);
        boolean player2Scoring = player.equals(player2);

        // Game Score is ( 40 - 40 ) => increment score to ADV
        if (gameScorePlayer1.equals(FORTY_SCORE) && gameScorePlayer2.equals(FORTY_SCORE)) {

            incrementGameScore(player);

            // Game Score is ( ADV - 40 ) or ( 40 - ADV ) leading to score above ADV => designate a winner
        } else if ((gameScorePlayer1.equals(ADVANTAGE_SCORE) && gameScorePlayer2.equals(FORTY_SCORE) && player1Scoring)
                || (gameScorePlayer2.equals(ADVANTAGE_SCORE) && gameScorePlayer1.equals(FORTY_SCORE) && player2Scoring)) {

            designateWinner(player);

            // Game Score is ( ADV - 40 ) or ( 40 - ADV ) leading to score ( ADV - ADV ) => increment scores & activate deuce rule
        } else if ((gameScorePlayer1.equals(FORTY_SCORE) && gameScorePlayer2.equals(ADVANTAGE_SCORE) && player1Scoring)
                || (gameScorePlayer2.equals(FORTY_SCORE) && gameScorePlayer1.equals(ADVANTAGE_SCORE) && player2Scoring)) {

            incrementGameScore(player);
            resetScoresDeuceRule(displayInformation);
        }
    }

    private void resetScoresDeuceRule(DisplayInformation displayInformation) {
        displayInformation.announceDeuceRule();
        setGameScorePlayer1(FORTY_SCORE);
        setGameScorePlayer2(FORTY_SCORE);
    }

    private void designateWinner(Player player) {
        if (player1.equals(player)) {
            winner = player1;
        } else {
            winner = player2;
        }
    }

    private String getScoreDescription(Integer gameScore) {
        return pointsList.get(gameScore);
    }

}
