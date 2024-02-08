package com.coderscampus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesReportGenerator {

	public void generateReport(String fileName, String modelName) {

		// Printing to console the beginning of a sales report
		System.out.println(modelName + " Yearly Sales Report");
		System.out.println("--------------------------------");

		Map<Integer, Integer> yearlySales = new HashMap<>();
		Map<LocalDate, Integer> monthlySales = FileService.readCSVFile(fileName);

		// Grouping data by year. As monthlySales are added to the groups, the sales are
		// being summed within each group(year)
		yearlySales.putAll(monthlySales.entrySet().stream()
				.collect(Collectors.groupingBy(entry -> entry.getKey().getYear(), Collectors.summingInt(Map.Entry::getValue))));

		// Printing to console the year and the yearly sales sum in the desired format
		yearlySales.entrySet().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

		// Finding the max value of monthly sales and assigning it best month
		String bestMonth = monthlySales.entrySet().stream().max(Map.Entry.comparingByValue())
				.flatMap(entry -> Optional.ofNullable(entry.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM"))))
				.orElse("N/A");

		// Finding the min value of monthly sales and assigning it worst month
		String worstMonth = monthlySales.entrySet().stream().min(Map.Entry.comparingByValue())
				.flatMap(entry -> Optional.ofNullable(entry.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM"))))
				.orElse("N/A");

		// Printing to console the best and worst month for a model of all possible
		// years
		System.out.println("The best month for " + modelName + " was: " + bestMonth);
		System.out.println("The worst month for " + modelName + " was: " + worstMonth);
		System.out.println();

	}

}
