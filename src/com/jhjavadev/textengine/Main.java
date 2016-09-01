package com.jhjavadev.textengine;

import com.jhjavadev.textengine.game.*;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Main {

    public static void main(String[] args) {
		Game game = new Game(600, 400, "TextEngine", "res/scripts/");
		game.start();
	}
}
