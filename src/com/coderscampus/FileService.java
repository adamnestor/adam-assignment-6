package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FileService {
	
	private static final Map<String, Integer> MONTH_MAP = new HashMap<>();

    static {
        MONTH_MAP.put("Jan", 1);
        MONTH_MAP.put("Feb", 2);
        MONTH_MAP.put("Mar", 3);
        MONTH_MAP.put("Apr", 4);
        MONTH_MAP.put("May", 5);
        MONTH_MAP.put("Jun", 6);
        MONTH_MAP.put("Jul", 7);
        MONTH_MAP.put("Aug", 8);
        MONTH_MAP.put("Sep", 9);
        MONTH_MAP.put("Oct", 10);
        MONTH_MAP.put("Nov", 11);
        MONTH_MAP.put("Dec", 12);
    }
	
	public static Map<LocalDate, Integer> readCSVFile(String fileName) {
		Map<LocalDate, Integer> monthlySales = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			reader.lines() 
				  .skip(1)
				  .map(line -> line.split(","))
				  .forEach(fields -> {
					  String dateString = fields[0];
					  
					  String[] dateParts = dateString.split("-");
					  int month = MONTH_MAP.get(dateParts[0]);
					  int year = Integer.parseInt("20" + dateParts[1]);
					  
					  LocalDate date = LocalDate.of(year, month, 1);
					  int sales = Integer.parseInt(fields[1]);
					  
					  monthlySales.merge(date, sales, Integer::sum);
				  });
		} catch (IOException e) {
			e.printStackTrace();
		}
		return monthlySales;
	}
}
