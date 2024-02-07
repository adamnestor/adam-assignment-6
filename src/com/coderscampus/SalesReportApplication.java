package com.coderscampus;

import java.util.List;

public class SalesReportApplication {

	public static void main(String[] args) {

		String filePath1 = "model3.csv";
		
		List<List<String>> fileContents1 = FileService.readTeslaData(filePath1);
		
		for (List<String> contents : fileContents1) {
			System.out.println(contents);
		}
		
//		FileService fileService = new FileService();
//		
//		fileService.readTeslaData(filePath1);
		
	}
}
