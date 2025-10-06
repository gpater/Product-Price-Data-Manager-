package controller;

import java.io.IOException;
import java.util.List;


import dto.CategoryHighlightDTO;
import dto.ProductDTO;
import dto.ProductHighlightDTO;
import dto.ProductStatsDTO;
import dto.Top10AppearanceDTO;
import dto.YearDTO;
import java.util.Properties; // Added Library 
import java.io.FileInputStream; // Added Library 
import java.io.BufferedReader; // Added Library
import java.io.FileReader; // Added Library 
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
		String [] names;
		String [] parts;
		
		if (MDFisNull) {
			try (BufferedReader br = new BufferedReader(new FileReader(dataFile))){
				String header = br.readLine();
				parts = header.split(delimiter);	
				names = new String[parts.length - 1];
				System.arraycopy(parts,1,names,0,parts.length -1);	
				
				String line;
				while((line = br.readLine()) != null) {	
					parts = line.split(delimiter);
					 
				}
				
				
				
			
			
			
			
			}
		} else {
			try (BufferedReader br = new BufferedReader(new FileReader(metadataFile))){
				String line;
				String lineArr [];
				while((line = br.readLine()) != null) {
					 lineArr = line.split(delimiter);
				}
			}
		}
		
		
	}

	@Override
	public List<YearDTO> listYears() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> listProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YearDTO getYearMeasurements(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO getProductMeasurements(String productName) {
		// TODO Auto-generated method stub
		return null;
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
