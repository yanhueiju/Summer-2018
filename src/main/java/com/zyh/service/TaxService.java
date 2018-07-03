package com.zyh.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.TaxMapper;
import com.zyh.po.Tax;

@Service
public class TaxService {
	@Autowired
	private TaxMapper taxMapper;
	
	public void addTax(Tax tax) {
		
		taxMapper.addTax(tax);
	}
	
	public List<Tax> findAllTax(){
		
		return taxMapper.findAllTax();
	}
	
	public void deleteTaxById(int id) {
		
		taxMapper.deleteTaxById(id);
	}
	
	public Tax findTaxById(String id) {
		return taxMapper.findTaxById(id);
	}

	public void updateTax(Tax tax) {
		// TODO Auto-generated method stub
		taxMapper.updateTax(tax);
	}

	/*public List<Tax> fuzzyInfo(String info_category, String begin, String end, String info_title) {
		// TODO Auto-generated method stub
		return taxMapper.fuzzyInfo(info_category, begin, end, info_title);
	}*/

	public List<Tax> fuzzyInfo(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		return taxMapper.fuzzyInfo(info);
	}


}
