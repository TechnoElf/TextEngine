package com.jhjavadev.textengine.console;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Console extends JFrame {
	private JTextArea text;

	private char key;
	private boolean typed;
	private boolean delete;
	private boolean enter;

	public Console(int w, int h, String title) {
		setTitle(title);

		Dimension dim = new Dimension(w, h);
		setMinimumSize(dim);
		setPreferredSize(dim);
		setResizable(false);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel content = new JPanel(new GridLayout());
		content.setBackground(Color.BLACK);
		text = new JTextArea();
		text.setBorder(BorderFactory.createEmptyBorder());
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setLineWrap(false);
		text.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		DefaultCaret caret = (DefaultCaret) text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		content.add(scrollPane);
		setContentPane(content);

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

	public void println() {
		text.append("\n");
	}

	public void println(String value) {
		text.append(value + "\n");
	}

	public void println(int value) {
		text.append(value + "\n");
	}

	public void println(float value) {
		text.append(value + "\n");
	}

	public void print(String value) {
		text.append(value);
	}

	public void print(int value) {
		text.append(Integer.toString(value));
	}

	public void print(float value) {
		text.append(Float.toString(value));
	}

	public int getCols() {
		return text.getColumns();
	}

	public int getRows() {
		return text.getRows();
	}
}
