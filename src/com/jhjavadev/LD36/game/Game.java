package com.jhjavadev.LD36.game;

import com.jhjavadev.LD36.console.TextConsole;

import java.io.*;

public class Game {
	private TextConsole console;

	private Stage[] stages;
	private int stageIndex;

	public Game(int w, int h, String title, Stage[] stages) {
		if (stages.length < 1) {
			System.err.println("ERROR: No stages in Stage array");
			System.exit(1);
		}

		this.stages = stages;
		stageIndex = 0;
		console = new TextConsole(w, h, title);
	}

	public void start() {
		load();
		console.setVisible(true);

		console.printText("Lyrics by JHjavaDev");
		console.printText("");
		console.printText("Type \'help\' for a list of actions");
		console.printText("");

		stages[stageIndex].printStart(console);
		console.printText("");

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

			if (!stages[stageIndex].update(cmd, args, console)) {
				if (cmd.equals("quit")) {
					if(console.requestText("Do you really want to quit? Your game will not be saved! (y/n)").toLowerCase().equals("y")) {
						break;
					}
				} else if (cmd.equals("clear")) {
					console.clear();
				} else if (cmd.equals("save")) {
					save();
					console.printText("Saved game");
					console.printText("");
				} else if (cmd.equals("load")) {
					load();
					console.printText("Loaded game");
					console.printText("");
					stages[stageIndex].printStart(console);
				} else if (cmd.equals("restart")) {
					if(console.requestText("Do you really want to restart? (y/n)").toLowerCase().equals("y")) {
						stageIndex = 0;
						console.printText("Restarted game");
						console.printText("");
						stages[stageIndex].printStart(console);
					}
				} else if (cmd.equals("help")) {
					console.printText("help - display this text");
					console.printText("quit - quit the game");
					console.printText("clear - clear the console");
					console.printText("save - save the game to a file");
					console.printText("load - load the game from a file");
					console.printText("restart - restart the game");
					stages[stageIndex].printHelp(console);
				} else {
					console.printText("I didn't understand you.");
				}
			}

			if (stages[stageIndex].isDone()) {
				stageIndex++;
				if (stageIndex >= stages.length) {
					console.println();
					stageIndex--;
					if(console.requestText("Do you want to save? (y/n)").toLowerCase().equals("y")) {
						save();
					}
					console.waitForEnter("Press enter to quit.");
					break;
				}
				console.printText("");
				stages[stageIndex].printStart(console);
			}

			console.println();
		}

		stop();
	}

	public void stop() {
		System.exit(0);
	}

	private void save() {
		String file = console.getTitle() + ".sav";

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));

			writer.write(Integer.toString(stageIndex));

			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("INFO: File " + file + " does not exist");
		} catch (IOException e) {
			System.err.println("ERROR: Could not read file " + file);
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void load() {
		String file = console.getTitle() + ".sav";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			stageIndex = Integer.parseInt(line);

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("INFO: File " + file + " does not exist");
		} catch (IOException e) {
			System.err.println("ERROR: Could not read file " + file);
			e.printStackTrace();
			System.exit(1);
		}
	}
}
