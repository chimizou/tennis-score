package fr.tennis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private char username;

    public Player(char username) {

        this.username = username;
    }

}
