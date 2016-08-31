package com.jhjavadev.LD36.game;

import com.jhjavadev.LD36.console.TextConsole;

import java.util.Random;

public class CDPlayerStage extends Stage {
	private boolean door = false;
	private boolean light = false;
	private boolean safe = false;
	private boolean cd = false;
	private int tries = 0;

	public void printStart(TextConsole c) {
		c.printText("When you wake up again you are in a completely different place than you remember.");
		c.printText("You appear to be inside some sort of factory.");
	}

	public boolean update(String cmd, String[] args, TextConsole c) {
		switch (cmd) {
			case "look":
				switch (pos) {
					case 0:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are in a large factory building");
							c.printText("There is a staircase to an office next to you and a door behind you.");
							return true;
						}
						return false;
					case 1:
						if (args.length == 0 || args[0].equals("around")) {
							c.printText("You are in an office.");
							c.printText("There is a control panel and a piece of paper in front of you.");
							return true;
						} else if (args[0].equals("controlpanel") || args[0].equals("panel") || args[0].equals("control")) {
							c.printText("There is a button on the control panel that says \'open doors\'");
							return true;
						} else if (args[0].equals("paper")) {
							c.printText("The paper says \'1982\'");
							return true;
						}
						return false;
					case 2:
						if (args.length == 0 || args[0].equals("around")) {
							if (light) {
								c.printText("You are in a room.");
								c.printText("There is a safe in the center.");
							} else {
								c.printText("You are in a dark room.");
								c.printText("You can barely make out a light switch on the wall.");
							}
							return true;
						} else if (args[0].equals("safe") && light) {
							if (safe) {
								c.printText("The safe contains a CD player and a CD.");
							} else {
								c.printText("The safe has a number pad for typing in a code.");
							}
							return true;
						}
						return false;
				}

				return false;
			case "go":
				if (args.length < 1) {
					c.printText("Where do you want to go?");
					return true;
				}

				switch (pos) {
					case 0:
						if (args[0].equals("up") || args[0].equals("staircase") || args[0].equals("office")) {
							c.printText("You go up the staircase to the office.");
							pos = 1;
							return true;
						} else if (args[0].equals("door")) {
							if (door) {
								c.printText("You go through the open door.");
								pos = 2;
							} else {
								c.printText("The door is locked.");
							}
							return true;
						}
					case 1:
						if (args[0].equals("down") || args[0].equals("staircase") || args[0].equals("factory")) {
							c.printText("You go down the staircase to the factory.");
							pos = 0;
							return true;
						}
					case 2:
						if (args[0].equals("door")) {
							c.printText("You go through the open door.");
							pos = 0;
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
							if (door) {
								c.printText("The door is open.");
							} else {
								c.printText("The door is locked.");
							}
							return true;
						}
					case 2:
						if (args[0].equals("safe") && light) {
							if(safe) {
								c.printText("The safe is already open.");
							} else {
								if (c.requestText("What is the code?").equals("1982")) {
									c.printText("The safe opens.");
									safe = true;
								} else {
									c.printText("The safe beeps and stays shut.");
									tries++;
									if (tries == 3) {
										c.printText("The alarms go off.");
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										c.clear();
										c.randomize();
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										System.exit(0);
									}
								}
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
						if(args[0].equals("cd") || args[0].equals("cdplayer") || args[0].equals("player") && safe) {
							if (cd) {
								c.printText("You already took the cd player and cd.");
							} else {
								c.printText("You take the cd player and cd.");
								cd = true;
							}
							return true;
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
						if (args[0].equals("button")) {
							if (door) {
								c.printText("You already pushed the button.");
							} else {
								c.printText("You push the button.");
								c.printText("You you hear the door open below.");
								door = true;
							}
							return true;
						}
					case 2:
						if (args[0].equals("switch") || args[0].equals("light") || args[0].equals("lightswitch")) {
							if (light) {
								c.printText("You already turned the lights on.");
							} else {
								c.printText("You flick the light switch.");
								c.printText("The lights turn on.");
								light = true;
							}
							return true;
						} else if (args[0].equals("safe") && light) {
							if (safe) {
								c.printText("The safe is already open.");
							} else {
								if (c.requestText("What is the code?").equals("1982")) {
									c.printText("The safe opens.");
									safe = true;
								} else {
									c.printText("The safe beeps and stays shut.");
									tries++;
									if (tries == 3) {
										c.printText("The alarms go off.");
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										c.clear();
										c.randomize();
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										System.exit(0);
									}
								}
							}
							return true;
						} else if(args[0].equals("cd") || args[0].equals("cdplayer") || args[0].equals("player") && safe) {
							c.printText("You put the CD in the CD player and turn it on.");
							c.printText("As the music starts to play your vision fades.");
							done = true;
							return true;
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
		door = false;
		light = false;
		safe = false;
		cd = false;
		tries = 0;
	}
}
