package com.jhjavadev.LD36.game;

import com.jhjavadev.LD36.console.TextConsole;

public class MP3PlayerStage extends Stage {
	private boolean frontDoor = false;
	private boolean key = false;
	private boolean officeDoor = false;
	private boolean drawer = false;
	private boolean mp3 = false;

	public void printStart(TextConsole c) {
		c.printText("You wake up in a completely different place yet again.");
		c.printText("\'Why does this keep on happening?\' you ask yourself.");
		c.printText("This time you are in front of a school.");
		c.printText("There appears to be no one here.");
	}

	public boolean update(String cmd, String[] args, TextConsole c) {
		switch (cmd) {
			case "look":
				switch (pos) {
					case 0:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are in front of a school.");
							c.printText("There is a door in front of you.");
							return true;
						}
					case 1:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are inside the school.");
							c.printText("There are two doors leading to a classroom and an office.");
							return true;
						}
					case 2:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are inside a classroom with a door to the corridor.");
							c.printText("There are rows of desks in front of a blackboard.");
							return true;
						} else if (args[0].equals("desk") || args[0].equals("desks")) {
							c.printText("There is something shiny on the teachers desk.");
							c.printText("It looks like a key.");
							return true;
						}
					case 3:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are inside an office with a door to the corridor.");
							c.printText("There is a drawer nearby.");
							return true;
						} else if (args[0].equals("drawer")) {
							if (drawer) {
								c.printText("The open drawer contains an mp3 player.");
							} else {
								c.printText("The drawer is closed.");
								c.printText("It is labelled \'confiscated items\'.");
							}
							return true;
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
						if (args[0].equals("door")) {
							if (frontDoor) {
								c.printText("You walk through the door.");
								pos = 1;
							} else {
								c.printText("The door is closed.");
							}
							return true;
						}
					case 1:
						if (args[0].equals("door")) {
							c.printText("You walk through the door.");
							pos = 0;
							return true;
						} else if (args[0].equals("classroom")) {
							c.printText("You step into the classroom.");
							pos = 2;
							return true;
						} else if (args[0].equals("office")) {
							if (officeDoor) {
								c.printText("You go into the office.");
								pos = 3;
							} else {
								c.printText("The office door is locked.");
							}
							return true;
						}
					case 2:
						if (args[0].equals("door") || args[0].equals("corridor")) {
							c.printText("You walk through the door.");
							pos = 1;
							return true;
						}
					case 3:
						if (args[0].equals("door") || args[0].equals("corridor")) {
							c.printText("You walk through the door.");
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
					case 0:
						if (args[0].equals("door")) {
							if (frontDoor) {
								c.printText("The door is already open.");
							} else {
								c.printText("You open the door.");
								frontDoor = true;
							}
							return true;
						}
					case 1:
						if (args[0].equals("classroom")) {
							c.printText("The classroom door is already open.");
							return true;
						} else if (args[0].equals("office")) {
							if (key) {
								if (!officeDoor) {
									c.printText("You use the key to open the office door.");
									officeDoor = true;
								} else {
									c.printText("The door is already open.");
								}
							} else {
								c.printText("The office door is locked.");
							}
							return true;
						}
					case 3:
						if (args[0].equals("drawer")) {
							if (drawer) {
								c.printText("The drawer is already open.");
							} else {
								c.printText("You open the drawer.");
								drawer = true;
							}
							return true;
						}
				}

				return false;
			case "take":
				if (args.length < 1) {
					c.printText("What do you want to take?");
					return true;
				}

				switch (pos) {
					case 2:
						if (args[0].equals("key")) {
							if (!key) {
								c.printText("You take the key.");
								key = true;
							} else {
								c.printText("You already took the key.");
							}
							return true;
						}
					case 3:
						if (args[0].equals("mp3") || args[0].equals("player") || args[0].equals("mp3player")) {
							if (drawer) {
								if (mp3) {
									c.printText("You already took the mp3 player.");
								} else {
									c.printText("You take the mp3 player.");
									mp3 = true;
								}
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
					case 3:
						if (args[0].equals("mp3") || args[0].equals("player") || args[0].equals("mp3player")) {
							if (drawer) {
								c.printText("You select a song on the mp3 player and play it.");
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
		frontDoor = false;
		key = false;
		officeDoor = false;
		drawer = false;
		mp3 = false;
	}
}
