package com.jhjavadev.textengine.game;

import com.jhjavadev.textengine.console.Console;

public abstract class Stage {
	protected int pos = 0;
	protected boolean done = false;

	public abstract void printStart(Console c);
	public abstract boolean update(String cmd, String[] args, Console c);
	public abstract void printHelp(Console c);
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
