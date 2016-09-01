package com.jhjavadev.textengine.game;

import java.io.File;
import java.util.HashMap;

public class Loader {
	public static HashMap<String, Stage> loadGame(String folder) {
		HashMap<String, Stage> stages = new HashMap<>();

		File[] files = new File(folder).listFiles();
		for (int i = 0; i < files.length; i++) {
			stages.put(files[i].getName(), new Stage(files[i].getPath()));
		}

		return stages;
	}
}
