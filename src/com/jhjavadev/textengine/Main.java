package com.jhjavadev.textengine;

import com.jhjavadev.textengine.game.*;

public class Main {

    public static void main(String[] args) {
		Stage[] stages = new Stage[] {
				new TestStage()
		};

		Game game = new Game(600, 400, "TextEngine", stages);
		game.start();
	}
}
