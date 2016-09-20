package com.jhjavadev.textengine.game;

import java.util.HashMap;

import com.jhjavadev.textengine.console.Console;

public class Game {
	private Console console;

	private HashMap<String, Stage> stages;
	private String stage;

	public Game(int w, int h, String title, String script) {
		console = new Console(w, h, title);

		this.stages = Loader.loadScripts();
		loadStage(script);
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
				loadStage(ret);
			}
		}

		stop();
	}

	private void stop() {
		System.exit(0);
	}

	public void loadStage(String stage) {
		if (stages.containsKey(stage)) {
			this.stage = stage;
			stages.get(stage).start(console);
		} else {
			System.err.println("ERROR: stage " + stage + " not in scripts folder");
			System.exit(1);
		}
	}
}
