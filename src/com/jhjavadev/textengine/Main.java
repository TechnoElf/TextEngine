package com.jhjavadev.textengine;

import com.jhjavadev.textengine.game.Game;
import com.jhjavadev.textengine.game.Loader;

public class Main {

    public static void main(String[] args) {
		Game game = Loader.loadGame();
		game.start();
	}
}
