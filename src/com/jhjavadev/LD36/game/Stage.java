package com.jhjavadev.LD36.game;

import com.jhjavadev.LD36.console.Console;
import com.jhjavadev.LD36.console.TextConsole;

public abstract class Stage {
	protected int pos = 0;
	protected boolean done = false;

	public abstract void printStart(TextConsole c);
	public abstract boolean update(String cmd, String[] args, TextConsole c);
	public abstract void printHelp(TextConsole c);
	public abstract void reset();

	public boolean isDone() {
		if(done) {
			pos = 0;
			done = false;
			reset();
			return true;
		} else {
			return false;
		}
	}
}
