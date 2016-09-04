package com.jhjavadev.textengine.game;

import java.util.HashMap;

import com.jhjavadev.textengine.console.Console;

public class Game {
	private Console console;

	private HashMap<String, Stage> stages;
	private String stage;

	public Game(int w, int h, String title, String script) {
		this.stages = Loader.loadScripts();

		if (!stages.containsKey(script)) {
			System.err.println("ERROR: Script " + script + "not in scripts folder");
		}

		stage = script;
		console = new Console(w, h, title);
	}

	public void start() {
		console.setVisible(true);

		run();
	}

	private void run() {
		while (true) {
			String ret = stages.get(stage).update(console);

			if (ret.equals("quit")) {
				break;
			} else if (ret.endsWith(".lua")) {
				if (stages.containsKey(ret)) {
					stage = ret;
				} else {
					System.err.println("ERROR: stage " + ret + "not in scripts folder");
				}
			}
		}

		stop();
	}

	private void stop() {
		System.exit(0);
	}
}
