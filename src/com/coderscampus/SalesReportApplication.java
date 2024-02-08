package com.coderscampus;

public class SalesReportApplication {

	public static void main(String[] args) {

		SalesReportGenerator report = new SalesReportGenerator();
		
		report.generateReport("model3.csv", "Model 3");
		report.generateReport("modelS.csv", "Model S");
		report.generateReport("modelX.csv", "Model X");
		
	}
}
