package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.stock.dao.StockDao;
import com.stock.model.Stock;

public class MyTest {
	
	public void print(List<Stock> list){
		for(Stock stock:list){
			System.out.print(stock.getSymbol());
			System.out.print(" "+ stock.getDate());
			System.out.print(" "+ stock.getOpen());
			System.out.print(" "+ stock.getHigh());
			System.out.print(" "+ stock.getLow());
			System.out.print(" "+ stock.getClose());
			System.out.print(" "+ stock.getVolume());
			System.out.print(" "+ stock.getAdjClose());
			System.out.println();
		}
	}
	@Test
	public void findAll() {
		StockDao csvDao = StockDao.getcsvDao();
		List<Stock> list= csvDao.getAll();
		print(list);
	}
	
	@Test
	public void findAvgByDate(){
		StockDao csvDao = StockDao.getcsvDao();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-31");
			List<Stock> list= csvDao.findAveragePriceByDate(date);
			print(list);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findStock() throws ParseException{
		String name = "CH";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-31");
		StockDao csvDao = StockDao.getcsvDao();
		List<Stock> list= csvDao.findStockByName(name);
		print(list);
		System.out.println("****");
		list = csvDao.findStockByDate(date);
		print(list);
		System.out.println("****");
		list = csvDao.findStockByNameAndDate(name, date);
		print(list);
	}

}
