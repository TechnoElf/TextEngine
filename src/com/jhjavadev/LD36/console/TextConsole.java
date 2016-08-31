package com.jhjavadev.LD36.console;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TextConsole extends Console {
	private char key;
	private boolean typed;
	private boolean delete;
	private boolean enter;

	public TextConsole(int w, int h, String title) {
		super(w, h, title);

		text.setLineWrap(true);

		text.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {

			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					enter = true;
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
					delete = true;
				} else {
					key = e.getKeyChar();
					typed = true;
				}
			}

			public void keyReleased(KeyEvent e) {

			}
		});
	}

	public void clear() {
		text.setText("");
	}

	public void randomize() {
		for (int i = 0; i < getWidth(); i++) {
			text.append(Integer.toHexString(new Random().nextInt()));
		}
	}

	public void backspace() {
		text.setText(text.getText().substring(0, text.getText().length() - 1));
	}

	public void printText(String str) {
		println(str);
	}

	public void waitForEnter(String str) {
		println(str);

		while(!enter) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String requestText(String str) {
		println(str);
		print("> ");

		String text = "";
		while (!enter) {
			if(delete) {
				if (text.length() >= 1) {
					text = text.substring(0, text.length() - 1);
					backspace();
				}
				delete = false;
			}

			if(typed) {
				text += Character.toString(key);
				print(Character.toString(key));
				typed = false;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		enter = false;
		typed = false;

		println();

		return text;
	}
}
