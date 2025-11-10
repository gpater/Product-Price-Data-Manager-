package controller;

import java.io.IOException;
import java.util.ArrayList;


import dto.CategoryHighlightDTO;
import dto.MeasurementDTO;
import dto.ProductDTO;
import dto.ProductHighlightDTO;
import dto.ProductStatsDTO;
import dto.Top10AppearanceDTO;
import dto.YearDTO;
import java.util.Properties; // Added Library 

import domain.FileData;

import java.io.FileInputStream; // Added Library 
import java.io.BufferedReader; // Added Library
import java.io.FileReader; // Added Library 
import java.util.ArrayList; // Added Library
import java.util.Arrays; // Added Library
<<<<<<< HEAD
import java.util.List;
=======
import java.util.List; // 
>>>>>>> 330be6a09c1d379fb81c32750996f2c35f60f340

/**
 * Factory class to obtain instances of IController.
 * <p>
 * Clients should only use this factory and never import concrete implementations of IController directly.
 * By providing a ControllerFactory, front-end code only depends on the interface IController. 
 * Concrete classes are hidden. 
 * This supports decoupling, mocking for tests, and future replacement of the implementation without touching clients.
 * <p>
 * Observe the static method: this just simplifies the code, as there is no need to instantiate ControllerFactory
 * via a new object.
 * <p>
 * Also observe the private constructor: you will learn this trick later, in the Singleton pattern, 
 * still, the main idea is to prevent creating objects for this kind of classes with static calls.
 */
public final class ControllerFactory implements IController{
	
	private String dataFile;
	private String metadataFile;
	private FileData dataBase;
	
	
    // Private constructor to prevent instantiation
    private ControllerFactory() { }

    /**
     * Creates a new instance of IController.
     *
     * @return a concrete IController implementation
     */
     IController IC = new ControllerFactory();
    	
   
    
    public static IController createController() {
        return new ControllerFactory() ; //replace with new WhateverYourClassIs();
    }

	@Override
	public int initializeFromIni(String iniPath, String delimiter) throws IOException {
		
		// 1) Initialiaze the number of Years that the method should return
		int numOfYears = 0;
		
		// 2) Load the config.ini file and store the properties 
		Properties props = new Properties();
		props.load(new FileInputStream(iniPath));
		dataFile = props.getProperty("dataFile");
		metadataFile = props.getProperty("metadataFile");
		dataBase = new FileData();
		
		
		// 3) Read from the dataFile
		try (BufferedReader br = new BufferedReader(new FileReader(metadataFile))){
			String line;
			
			// 3a) For each line, increment the numOfYears 
			while ((line = br.readLine()) != null) {
				numOfYears++;				
			}
		}
		// 4) Return
		return numOfYears;
	}

	@Override
	public void loadFile(String path, String delimiter) throws IOException {
		// TODO Auto-generated method stub
		
		// Check if there is a metadataFile, to get the names 
		Boolean MDFisNull = (metadataFile == null);
		
<<<<<<< HEAD
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			String [] header = line.split(delimiter); // Whole 1st line of the file
			
			for(int i = 1; i < header.length-2; i++) { 
				dataBase.addProductName(header[i]); // Insert ONLY the names of the products
			}
			
			
			// Variables i need
			int year;
			YearDTO ydto;
			ArrayList<MeasurementDTO> values;
			MeasurementDTO mr;
			String productName;
			Double value;
			
			
			String [] top10_arr;
			String [] headline_arr;
			ArrayList<String> commodityTop10;
			ArrayList<String> headline;
			
			while((line = br.readLine()) != null) {
				
				String [] dataLine = line.split(delimiter);
				
				year = Integer.parseInt(dataLine[0]);
				values = new ArrayList<MeasurementDTO>();
				
				for (int i = 1; i < dataLine.length - 2 ; i++) {
					 productName = dataBase.getProductName(i - 1);
					 value = Double.parseDouble(dataLine[i]);
					 
					 mr = new MeasurementDTO(year, productName, value);
					 values.add(mr);
				}
				
				top10_arr = dataLine[dataLine.length - 2].replaceAll("\"", "").split("\\s*,\\s*");
				headline_arr = dataLine[dataLine.length - 1].split("|");
				
				commodityTop10 = new ArrayList<>(Arrays.asList(top10_arr));
				headline = new ArrayList<>(Arrays.asList(headline_arr));
				
				ydto = new YearDTO(year, values, commodityTop10, headline);
				dataBase.addYearToDict(ydto);
=======
		if (MDFisNull) {
			try (BufferedReader br = new BufferedReader(new FileReader(dataFile))){
				String header = br.readLine();
				parts = header.split(delimiter);	
				names = new String[parts.length - 3];
				System.arraycopy(parts,1,names,0,parts.length -3);	
				
				String line;
				ArrayList<MeasurementDTO> measurements = new ArrayList<>(); 
				ArrayList<YearDTO> years = new ArrayList<>();
				YearDTO yearDto;
				 
				
				while((line = br.readLine()) != null) {	
					parts = line.split(delimiter);
					
					String yearStr = parts[0];
					int year = Integer.parseInt(yearStr);
					
					int index = 1;  
					MeasurementDTO currMeasurement;
					
					for(String name: names) {
						currMeasurement = new MeasurementDTO(year, name, Double.parseDouble(names[index]));
						measurements.add(currMeasurement);
						index++;
					}
					
					String commodityTop10_str = parts[index];
					String headline_str = parts[index+1];
					
					commodityTop10_str = commodityTop10_str.replace("\"","");
					headline_str = headline_str.replace("\"","");
					
					String [] commodityTop10Arr = commodityTop10_str.split(",");
					String [] headlineArr = headline_str.split(",");
					
					ArrayList<String> commodityTop10 = new ArrayList<>(Arrays.asList(commodityTop10Arr)); 
					ArrayList<String> headline = new ArrayList<>(Arrays.asList(headlineArr));
					
					yearDto = new YearDTO(year, measurements, commodityTop10, headline);
					years.add(yearDto);
				}
			}
		} else {
			try (BufferedReader br = new BufferedReader(new FileReader(metadataFile))){
				String line;
				String lineArr [];
				while((line = br.readLine()) != null) {
					 lineArr = line.split(delimiter);
				}
>>>>>>> 330be6a09c1d379fb81c32750996f2c35f60f340
			}
			
			
		}
		
		
		
	}

	@Override
	public List<YearDTO> listYears() {
		return dataBase.getAllYears();
	}

	@Override
	public List<ProductDTO> listProducts() {
		// TODO Auto-generated method stub
		return dataBase.getAllProducts();
	}

	@Override
	public YearDTO getYearMeasurements(int year) {
		// TODO Auto-generated method stub
		return dataBase.getYearDTO(year);
	}

	@Override
	public ProductDTO getProductMeasurements(String productName) {
		// TODO Auto-generated method stub
		return dataBase.getProductDTO(productName);
	}

	@Override
	public ProductDTO filterProductMeasurements(String productName, int minYear, int maxYear) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductHighlightDTO> reportProductHighlights(String productAlias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryHighlightDTO> reportCategoryHighlights(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductStatsDTO> computeProductStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Top10AppearanceDTO> computeTop10ProductAppearances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Top10AppearanceDTO> computeTop10CategoryAppearances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YearDTO> reportAllYearsAllProductPrices() {
		// TODO Auto-generated method stub
		return null;
	}

}

