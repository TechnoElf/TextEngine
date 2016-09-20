package com.jhjavadev.textengine.game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Loader {
	public static Game loadGame() {
		Element root = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("./res/config.xml"));
			root = doc.getDocumentElement();
		} catch (ParserConfigurationException e) {
			System.err.println("ERROR: Unable to create DocumentBuilder");
			e.printStackTrace();
			System.exit(1);
		} catch (SAXException e) {
			System.err.println("ERROR: Problem parsing res/config.xml");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("ERROR: Could not read res/config.xml");
			e.printStackTrace();
			System.exit(1);
		}

		if (root.getElementsByTagName("width").getLength() < 1) {
			System.err.println("ERROR: No width tag in res/config.xml");
			System.exit(1);
		}
		if (root.getElementsByTagName("height").getLength() < 1) {
			System.err.println("ERROR: No height tag in res/config.xml");
			System.exit(1);
		}
		if (root.getElementsByTagName("name").getLength() < 1) {
			System.err.println("ERROR: No name tag in res/config.xml");
			System.exit(1);
		}
		if (root.getElementsByTagName("script").getLength() < 1) {
			System.err.println("ERROR: No script tag in res/config.xml");
			System.exit(1);
		}

		return new Game(Integer.parseInt(root.getElementsByTagName("width").item(0).getTextContent()),
				Integer.parseInt(root.getElementsByTagName("height").item(0).getTextContent()),
				root.getElementsByTagName("name").item(0).getTextContent(),
				root.getElementsByTagName("script").item(0).getTextContent());
	}

	public static HashMap<String, Stage> loadScripts() {
		HashMap<String, Stage> stages = new HashMap<>();

		File[] files = new File("./res/scripts").listFiles();
		if (files == null) {
			System.err.println("ERROR: No scripts in res/scripts");
			System.exit(1);
		}

		for (int i = 0; i < files.length; i++) {
			stages.put(files[i].getName(), new Stage(files[i].getPath()));
		}

		return stages;
	}
}
