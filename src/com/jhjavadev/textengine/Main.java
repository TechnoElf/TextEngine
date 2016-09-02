package com.jhjavadev.textengine;

import com.jhjavadev.textengine.game.*;

public class Main {

    public static void main(String[] args) {
		Game game = new Game(600, 400, "TextEngine", "res/scripts/");
		game.start();
	}
}
