package com.coderscampus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {

	public static List<List<String>> readTeslaData(String filePath) {

		List<List<String>> fileContents = new ArrayList<>();

		Path dataFilePath = Paths.get(filePath);

		if (Files.exists(dataFilePath)) {
			try {
				List<String> lines = Files.readAllLines(dataFilePath);

				for (String line : lines) {
					String[] values = line.split(",");
					List<String> lineValues = new ArrayList<>();

					for (String value : values) {
						lineValues.add(value);
					}

					fileContents.add(lineValues);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("CSV file at '" + filePath + "' does not exist.");
		}
		return fileContents;
	}
}
