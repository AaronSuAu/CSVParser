package com.stock.dao;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stock.model.Stock;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;


public class StockDao {
	private static StockDao stockDao;
	private static final String FILE_PATH = "stock.csv";
	private List<Stock> list;
	private StockDao() {
		// TODO Auto-generated constructor stub
	}
	private StockDao(String filePath){
		CsvParserSettings settings = new CsvParserSettings();
		//the file used in the example uses '\n' as the line separator sequence.
		//the line separator sequence is defined here to ensure systems such as MacOS and Windows
		//are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
		settings.getFormat().setLineSeparator("\n");
		System.out.println(filePath);
		File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
		
		CsvParser parser = new CsvParser(settings);
		
		// call beginParsing to read records one by one, iterator-style.
		parser.beginParsing(file);
		
		String[] row;
		list = new ArrayList<>();
		
		while ((row = parser.parseNext()) != null) {
			if(row[0].equals("symbol"))	continue;
			try {
				Stock stock = new Stock(row[0], new SimpleDateFormat("yyyy-MM-dd").parse(row[1]),
						new BigDecimal(row[2]), new BigDecimal(row[3]), new BigDecimal(row[4]), 
						new BigDecimal(row[5]), new BigDecimal(row[6]), new BigDecimal(row[6]));
				list.add(stock);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static StockDao getcsvDao(){
		if(stockDao == null)
			return new StockDao(FILE_PATH);
		return stockDao;
	}
	
	public List<Stock> getAll(){ return this.list;}
	
	public List<Stock> findStockByName(String name){
		List<Stock> lStocks = new ArrayList<>();
		for(Stock stock:list){
			if(stock.getSymbol().equals(name))
				lStocks.add(stock);
		}
		return lStocks;
	}
	public List<Stock> findStockByDate(Date date){
		List<Stock> lStocks = new ArrayList<>();
		for(Stock stock:list){
			if(stock.getDate().equals(date))
				lStocks.add(stock);
		}
		return lStocks;
	}
	public List<Stock> findStockByNameAndDate(String name, Date date){
		List<Stock> lStocks = new ArrayList<>();
		for(Stock stock:list){
			if(stock.getSymbol().equals(name) && stock.getDate().equals(date))
				lStocks.add(stock);
		}
		return lStocks;
	}
	
	public List<Stock> findAveragePriceByDate(Date date){
		List<Stock> lStocks = new ArrayList<>();
		BigDecimal open = new BigDecimal("0");
		BigDecimal high = new BigDecimal("0");
		BigDecimal low = new BigDecimal("0");
		BigDecimal close = new BigDecimal("0");
		BigDecimal volume = new BigDecimal("0");
		BigDecimal adjClose = new BigDecimal("0");
		int number = 0;
		for(Stock stock:list){
			if(stock.getDate().equals(date)){
				number++;
				open = open.add(stock.getOpen());
				high = high.add(stock.getHigh());
				low = low.add(stock.getLow());
				close = close.add(stock.getClose());
				volume = volume.add(stock.getVolume());
				adjClose = adjClose.add(stock.getAdjClose());
			}
		}
		BigDecimal num = new BigDecimal(number);
		System.out.println(num);
		Stock avgStock = new Stock("AVG", date, open.divide(num,6, BigDecimal.ROUND_HALF_UP), 
				high.divide(num,6, BigDecimal.ROUND_HALF_UP), low.divide(num,6, BigDecimal.ROUND_HALF_UP), 
				close.divide(num,6, BigDecimal.ROUND_HALF_UP), volume.divide(num,6, BigDecimal.ROUND_HALF_UP), 
				adjClose.divide(num,6, BigDecimal.ROUND_HALF_UP));
		lStocks.add(avgStock);
		return lStocks;
	}
}
