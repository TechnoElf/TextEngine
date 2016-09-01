package com.jhjavadev.textengine.game;

import com.jhjavadev.textengine.console.Console;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Stage {
	private Globals globals;

	public Stage(String file) {
		globals = JsePlatform.standardGlobals();
		globals.loadfile(file).call();
	}

	public void update(Console c) {
		c.printText(globals.get("update").call().toString());
	}
}
