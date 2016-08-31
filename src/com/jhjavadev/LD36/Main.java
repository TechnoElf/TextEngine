package com.jhjavadev.LD36;

import com.jhjavadev.LD36.console.Console;
import com.jhjavadev.LD36.console.TextConsole;
import com.jhjavadev.LD36.game.*;

public class Main {

    public static void main(String[] args) {
		Stage[] stages = new Stage[] {
				new CassettePlayerStage(),
				new CDPlayerStage(),
				new MP3PlayerStage(),
				new HomeStage()
		};

		Game game = new Game(600, 400, "Lyrics", stages);
		game.start();
	}
}
