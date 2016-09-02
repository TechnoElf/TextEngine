package com.jhjavadev.textengine.game;

import java.util.HashMap;

import com.jhjavadev.textengine.console.Console;

public class Game {
	private Console console;

	private HashMap<String, Stage> stages;
	private String stage;

	public Game(int w, int h, String title, String folder) {
		this.stages = Loader.loadGame(folder);

		if (stages.size() < 1) {
			System.err.println("ERROR: No files in folder " + folder);
			System.exit(1);
		}
		if (!stages.containsKey("main.lua")) {
			System.err.println("ERROR: No main script in folder " + folder);
			System.exit(1);
		}

		stage = "main.lua";
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
