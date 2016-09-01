package com.jhjavadev.textengine.game;

import java.io.*;
import java.util.HashMap;

import com.jhjavadev.textengine.console.Console;

public class Game {
	private Console console;

	private HashMap<String, Stage> stages;
	private int stageIndex;

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

		stageIndex = 0;
		console = new Console(w, h, title);
	}

	public void start() {
		console.setVisible(true);

		run();
	}

	public void run() {
		while (true) {
			String str = console.requestText("What will you do?");
			String[] input = str.split(" ");

			String cmd = input[0].toLowerCase().replace("_", "").replace("-", "");
			String[] args = new String[input.length - 1];
			for (int i = 1; i < input.length; i++) {
				args[i - 1] = input[i].toLowerCase().replace("_", "").replace("-", "");
			}

			stages.get("main.lua").update(console);

			if (cmd.equals("quit")) {
				break;
			}

			console.println();
		}

		stop();
	}

	public void stop() {
		System.exit(0);
	}

//	private void save() {
//		String file = console.getTitle() + ".sav";
//
//		BufferedWriter writer;
//		try {
//			writer = new BufferedWriter(new FileWriter(file));
//
//			writer.write(Integer.toString(stageIndex));
//
//			writer.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("INFO: File " + file + " does not exist");
//		} catch (IOException e) {
//			System.err.println("ERROR: Could not read file " + file);
//			e.printStackTrace();
//			System.exit(1);
//		}
//	}
//
//	private void load() {
//		String file = console.getTitle() + ".sav";
//
//		BufferedReader reader;
//		try {
//			reader = new BufferedReader(new FileReader(file));
//			String line = reader.readLine();
//
//			stageIndex = Integer.parseInt(line);
//
//			reader.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("INFO: File " + file + " does not exist");
//		} catch (IOException e) {
//			System.err.println("ERROR: Could not read file " + file);
//			e.printStackTrace();
//			System.exit(1);
//		}
//	}
}
