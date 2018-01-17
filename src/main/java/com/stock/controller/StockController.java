package com.stock.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stock.dao.StockDao;
import com.stock.model.Stock;

@Controller
public class StockController {
	private StockDao stockDao = StockDao.getcsvDao();
	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		model.put("stockList", stockDao.getAll());
		return "welcome";
	}
	
	@RequestMapping(value = "/average", method = RequestMethod.GET)
	public String average(Map<String, Object> model, @RequestParam("dateAvg") String dateAvg) {
		try {
			Date date= new SimpleDateFormat("yyyy-MM-dd").parse(dateAvg);
			model.put("stockList", stockDao.findAveragePriceByDate(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "welcome";
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Map<String, Object> model, @RequestParam("date") String date_str, @RequestParam("name") String name) {
		List<Stock> list = new ArrayList<>();
		name = name.trim().toUpperCase();
		date_str = date_str.trim();
		if(!name.isEmpty() && date_str.isEmpty()){
			list = stockDao.findStockByName(name);
		}else if(!date_str.isEmpty()){
			try {
				Date date= new SimpleDateFormat("yyyy-MM-dd").parse(date_str);
				//model.put("stockList", stockDao.findAveragePriceByDate(date));
				if(name.isEmpty())
					list = stockDao.findStockByDate(date);
				else
					list = stockDao.findStockByNameAndDate(name, date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.put("stockList",list);
		return "welcome";
	}
	
}
