package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileService {

	public static Map<LocalDate, Integer> readCSVFile(String fileName) {
		Map<LocalDate, Integer> monthlySales = new HashMap<>();
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				.parseCaseInsensitive()
				.appendPattern("MMM-yy")
				.toFormatter(Locale.ENGLISH);
		
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			reader.lines()
				  .skip(1)
				  .map(line -> line.split(","))
				  .forEach(fields -> {
					  String dateString = fields[0];
					  System.out.println("Date String: " + dateString);
					  LocalDate date = LocalDate.parse(fields[0], formatter);
					  int sales = Integer.parseInt(fields[1]);
					  
					  monthlySales.merge(date, sales, Integer::sum);
				  });
		} catch (IOException e) {
			e.printStackTrace();
		}
		return monthlySales;
	}
}
