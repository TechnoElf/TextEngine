package com.jhjavadev.LD36.game;

import com.jhjavadev.LD36.console.TextConsole;

import java.util.Random;

public class CassettePlayerStage extends Stage {
	private int road = 0;
	private boolean mailbox = false;
	private int mail = 0;

	public void printStart(TextConsole c) {
		c.printText("You wake up in front of a house.");
		c.printText("As you get up you wonder why you are here and how you got here.");
		c.printText("You don't recognise this place.");
	}

	public boolean update(String cmd, String[] args, TextConsole c) {
		switch (cmd) {
			case "look":
				switch (pos) {
					case 0:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are in front of a house.");
							c.printText("You are standing on the sidewalk of a road.");
							return true;
						}
						return false;
					case 1:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are standing on the front porch of a house.");
							c.printText("There is a small mailbox near you.");
							return true;
						} else {
							switch (args[0]) {
								case "mailbox":
									if (mailbox) {
										c.printText("You look at the open mailbox.");
										c.printText("There is a package inside.");
									} else {
										c.printText("You look at the mailbox.");
										c.printText("It is not locked.");
									}
									return true;
								case "package":
									if (mail == 1) {
										c.printText("You look at the package in your hands.");
										c.printText("It has your name on it.");
										c.printText("You wonder how this is possible.");
										return true;
									} else if (mail == 2) {
										c.printText("You look at the open package in your hands.");
										c.printText("There is something inside it.");
										c.printText("It looks like an old Walkman with a cassette.");
										return true;
									}
							}
							return false;
						}
				}
				return false;
			case "go":
				if (args.length < 1) {
					c.printText("Where do you want to go?");
					return true;
				}
				switch (pos) {
					case 0:
					case 1:
						switch (args[0]) {
							case "road":
								if (road < 2) {
									c.printText("You shouldn't go down that road.");
								} else if (road < 4) {
									c.printText("You really shouldn't go down that road.");
								} else if (road < 5) {
									c.printText("Seriously.");
								} else if (road < 6) {
									c.printText("You don't want to go down that road.");
								} else if (road < 7) {
									c.printText("Fine then.");
								} else if (road < 8) {
									c.printText("As you wish.");
								} else if (road < 9) {
									c.printText("");
								} else if (road < 12) {
									c.printText(Integer.toHexString(new Random().nextInt()));
								} else {
									c.clear();
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									c.randomize();
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									System.exit(0);
								}
								road++;
								return true;
							case "house":
								if (pos == 1) {
									c.printText("You are already in front of the house.");
									return true;
								}
								c.printText("You go towards the house.");
								c.printText("The doors and windows are locked.");
								pos = 1;
								return true;
						}
				}
				return false;
			case "open":
				if (args.length < 1) {
					c.printText("What do you want to open?");
					return true;
				}
				switch (pos) {
					case 1:
						switch (args[0]) {
							case "mailbox":
								if (!mailbox) {
									c.printText("You open the mailbox.");
									mailbox = true;
								} else {
									c.printText("The mailbox is already open.");
								}

								return true;
							case "package":
								if (mail == 1) {
									c.printText("You open the package.");
									c.printText("There is something inside it.");
									mail = 2;
									return true;
								} else if (mail == 2) {
									c.printText("The package is already open");
									return true;
								}
						}
				}
				return false;
			case "take":
				if (args.length < 1) {
					c.printText("What do you want to take?");
					return true;
				}
				switch (pos) {
					case 1:
						switch (args[0]) {
							case "package":
								if (mailbox) {
									if (mail == 0) {
										c.printText("You take the package from the mailbox.");
										mail = 1;
									} else {
										c.printText("You already took the package from the mailbox.");
									}
									return true;
								}
							case "walkman":
								if (mail == 2) {
									c.printText("You take the walkman from the the package.");
									mail = 3;
									return true;
								} else if (mail == 3) {
									c.printText("You already took the walkman from the the package.");
									return true;
								}
						}
				}
				return false;
			case "use":
				if (args.length < 1) {
					c.printText("What do you want to use?");
					return true;
				}
				switch (pos) {
					case 1:
						switch (args[0]) {
							case "walkman":
								if (mail >= 2) {
									c.printText("You turn on the walkman.");
									c.printText("As the music starts to play your vision fades.");
									done = true;
									return true;
								}
						}
				}
				return false;
		}

		return false;
	}

	public void printHelp(TextConsole c) {
		c.printText("look - look around or look at something");
		c.printText("go - walk somewhere");
		c.printText("open - open something");
		c.printText("take - take something");
		c.printText("use - use something");
	}

	public void reset() {
		road = 0;
		mailbox = false;
		mail = 0;
	}
}
