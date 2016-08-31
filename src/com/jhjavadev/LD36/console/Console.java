package com.jhjavadev.LD36.console;

import com.sun.prism.paint.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.Color;

public class Console extends JFrame {
	protected JTextArea text;

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
