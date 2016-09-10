package com.jhjavadev.textengine.game;

import com.jhjavadev.textengine.console.Console;

public class LuaUtil {
	public static String[] split(String str, String regex) {
		return str.split(regex);
	}

	public static String[] getInput(Console c, String text, String regex) {
		return c.requestText(text).split(regex);
	}

	public static boolean actionIs(String[] input, String action) {
		return input[0].equals(action);
	}

	public static boolean par1Is(String[] input, String par) {
		return input[0].equals(par);
	}
}
