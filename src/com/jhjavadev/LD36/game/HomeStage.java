package com.jhjavadev.LD36.game;

import com.jhjavadev.LD36.console.TextConsole;

public class HomeStage extends Stage {

	public void printStart(TextConsole c) {
		c.printText("After a long journey you arrive back home in front of your computer.");
		c.printText("You are listening to music.");
		c.printText("Type exit to finish the game.");
	}

	public boolean update(String cmd, String[] args, TextConsole c) {
		switch (cmd) {
			case "look":
				switch (pos) {
					case 0:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are in front of your computer, listening to music.");
							c.printText("Type exit to finish the game.");
							return true;
						}
						return false;
				}

				return false;

			case "exit":
				switch (pos) {
					case 0:
						if (args.length == 0) {
							c.printText("You finished the game!");
							c.printText("Great job!");
							done = true;
							return true;
						}
						return false;
				}

				return false;
		}

		return false;
	}

	public void printHelp(TextConsole c) {
		c.printText("look - look around or look at something");
		c.printText("exit - finish the game");
	}

	public void reset() {

	}
}
