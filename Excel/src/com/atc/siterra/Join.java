package com.atc.siterra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Join {

	public boolean validate(String csvFileName) {
		try {
			CsvReader csvFile = new CsvReader(csvFileName);
			csvFile.readHeaders();
			int headerCount = csvFile.getHeaderCount();
			for(int i=0; i<headerCount; i++) {
				System.out.println(csvFile.getHeader(i));
			}
	//		csvFile.readRecord();
//			System.out.println(csvFile.getColumnCount());
//			System.out.println(csvFile.getHeaderCount());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;
	}
	
	public int getHeaderFirstIndex(String headerTitle, CsvReader csvReader) {
		
		int headCount = csvReader.getHeaderCount();
		try {
			for(int i=0; i<headCount; i++) {
				if(headerTitle.equals(csvReader.getHeader(i))) {
					return i;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public void joinCsvFiles2(String csv1FileName, String csv2FileName, String outputCsvFileName, HashMap map, String[] joinColumns) {
		try {
			
			CsvWriter csvWriter = new CsvWriter(outputCsvFileName);
			
			CsvReader csv1Reader = new CsvReader(csv1FileName);
			csv1Reader.readHeaders();

			CsvReader csv2Reader = new CsvReader(csv2FileName);
			csv2Reader.readHeaders();
			
			int headCount = csv2Reader.getHeaderCount();
			for(int i=0; i<headCount; i++) {
				csvWriter.write(csv2Reader.getHeader(i));
			}

			int csv1Count = 0;
			int csvProduct = 1;
			int csvProductMax = 10;
			while (csv1Reader.readRecord())
			{
				csv1Count++;
				
				String oracle1 = csv1Reader.get("Oracle Project Number");
				String tower1 = csv1Reader.get("Tower Number");
//				System.out.println(csv1Count + "\tTower Number: " + tower1 + "\tOracle Project Number: " + oracle1);
				
				csv2Reader = new CsvReader(csv2FileName);
				csv2Reader.readHeaders();
				
				int csv2Count = 0;
				while(csv2Reader.readRecord())
				{
					csv2Count++;
					
					String oracle2 = csv2Reader.get("Oracle Project Number");
					String tower2 = csv2Reader.get("Tower Number");
//					System.out.println(csv2Count + "\tTower Number: " + tower2 + "\tOracle Project Number: " + oracle2);

					if(tower1 != null && tower2 != null && (tower1.equals(tower2) && oracle1.equals(oracle2)))
					{
						String name = csv2Reader.get("*Name");
						String type = "Transfer Switch";
						String parentAssetNumber = csv2Reader.get("Parent Asset Number");
						String atsMake = csv1Reader.get("ATS Make");
						String atsModelNumber = csv1Reader.get("ATS Model Number");
						String atsSerialNumber = csv1Reader.get("Generator Serial No");
						String powerUtilityServiceType = csv2Reader.get("Power Utility Service Type");
						String serviceAmperage = csv2Reader.get("Service Amperage");
						String customerConnected = csv2Reader.get("Customer Name");
						System.out.println(name + ", " + atsModelNumber + ", " + atsMake);
//						String raw = csv2Reader.getRawRecord();
//						System.out.println(raw);
						
						csvWriter.write(tower1);
						csvWriter.write(oracle1);
						csvWriter.write(name);
						csvWriter.write(type);
						csvWriter.write(parentAssetNumber);
						csvWriter.write(atsMake);
						csvWriter.write(atsModelNumber);
						csvWriter.write(atsSerialNumber);
						csvWriter.write(powerUtilityServiceType);
						csvWriter.write(serviceAmperage);
						csvWriter.write(customerConnected);
						csvWriter.endRecord();
					}
					csvProduct = csv1Count * csv2Count;
					if(csv2Count > csvProductMax)
						break;
				}
				csv2Reader.close();
				
				if(csv1Count > csvProductMax)
					break;
			}
			
			csv1Reader.close();
			
			csvWriter.close();
			
/*
			csv2Reader.readRecord();
			while(generators.readRecord())
			{
				String parentAssetNumber = csv2Reader.get("Parent Asset Number");
				System.out.println("Parent Asset Number: " + parentAssetNumber);
			}
			*/
//						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public void joinCsvFiles(
		String csv1FileName,
		String csv2FileName,
		String outputCsvFileName,
		HashMap<String, String> map,
		String[] joinColumns)
	{
		try {
			CsvReader c1 = new CsvReader(csv1FileName);
			CsvWriter o  = new CsvWriter(outputCsvFileName);
			
			c1.readHeaders();
			String[] h1s = c1.getHeaders();
			o.writeRecord(h1s);

			CsvReader c2 = new CsvReader(csv2FileName);
			c2.readHeaders();
			String[] h2s = c2.getHeaders();
			c2.close();
			
			int c1count = 0;
			int c2count = 0;
			int countMax = 6000;
			String h1, h2, v1, v2, joinColumn;

			while(c1.readRecord()) {		// iterate over all the records in c1
				c1count++;
				System.out.println(c1count);
//				/*
				c2 = new CsvReader(csv2FileName);
				c2.readHeaders();
				while(c2.readRecord()) {							// for each record in c1, iterate over records in c2
					c2count++;
					
					int equalCount = 0;								// count how many of the values in the joinColumns match in these records 
					for(int k=0; k<joinColumns.length; k++) {		// for every join column
						for(int i=0; i<h1s.length; i++) {			// find the matching column in c1
							h1 = h1s[i];
							joinColumn = joinColumns[k];
							if(joinColumn.equals(h1)) {				// if there is a matching column
								for(int j=0; j<h2s.length; j++) {	// find matching column in c2
									h2 = h2s[j];
									if(h1.equals(h2)) {				// if there is a matching column
										v1 = c1.get(i);				// check if corresponding field values are same
										v2 = c2.get(j);
										if(v1.equals(v2)) {			// if same, count up
											equalCount++;
										}
									}
								}
							}
						}
					}
					
					// if match count matches number of columns, records match
					if(equalCount == joinColumns.length) {
						
						// get the current row's values
						String[] c1values = c1.getValues();
						
						// iterate over csv1's headers
/*						for(int i=0; i<h1s.length; i++) {
							// header
							h1 = h1s[i];
							
							String value = map.get(h1);
							String header = null;
							if(value != null) {
								if(value.startsWith("csv1")) {
									header = value.substring(value.indexOf('.')+1);
								} else if(value.startsWith("csv2")){
									header = value.substring(value.indexOf('.')+1);
									for(int j=0; j<h2s.length; j++) {
										h2 = h2s[j]; // <---- here
									}
								}
								if(header != null) {
									System.out.println(header);
									
								}
							}
							
						}
*/
//						Iterator<Entry<String, String>> it = map.entrySet().iterator();
//					    while (it.hasNext()) {
//					        Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
//					        System.out.println(pairs.getKey() + " = " + pairs.getValue());
//					        it.remove(); // avoids a ConcurrentModificationException
//					    }
						
						o.writeRecord(c1values);
					}
//					if(c2count > countMax)
	//					break;
				}
				c2.close();
		//		if(c1count > countMax)
			//		break;
//				*/
			}
			
			o.close();
			c1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Join join = new Join();
		//join.validate("/siterra/switches.csv");
		
		HashMap map = new HashMap<String, String>();
		map.put("ATS Make", "csv2.ATS Make");
		map.put("ATS Model", "csv2.ATS Model No");
		map.put("ATS Serial Number", "csv2.Generator Serial No");
		map.put("Customer Connected", "csv2.Customer Name");
		map.put("Generate Asset Number", "");
		
		String[] joinColumns = {"Oracle Project Number", "Tower Number", "Power Utility Service Type"};
		
		join.joinCsvFiles("/siterra/switches.csv", "/siterra/BUP Data Dump 6-25.csv", "/siterra/joined.csv", map, joinColumns);
	}

}
