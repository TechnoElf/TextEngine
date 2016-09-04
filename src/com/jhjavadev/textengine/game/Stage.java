package com.jhjavadev.textengine.game;

import com.jhjavadev.textengine.console.Console;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Stage {
	private Globals globals;

	public Stage(String file) {
		globals = JsePlatform.standardGlobals();
		globals.loadfile(file).call();
	}

	public void start(Console c) {
		globals.get("start").call(CoerceJavaToLua.coerce(c));
	}

	public String update(Console c) {
		return globals.get("update").call(CoerceJavaToLua.coerce(c)).toString();
	}
}
