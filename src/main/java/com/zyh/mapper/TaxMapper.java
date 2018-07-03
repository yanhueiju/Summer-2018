package com.zyh.mapper;

import java.util.HashMap;
import java.util.List;

import com.zyh.po.Tax;

public interface TaxMapper {

	public void addTax(Tax tax);
	public List<Tax> findAllTax();
	public void deleteTaxById(int id);
	public Tax findTaxById(String id);
	public void updateTax(Tax tax);
//	public List<Tax> fuzzyInfo(String info_category, String begin, String end, String info_title);
	public List<Tax> fuzzyInfo(HashMap<String, String> info);
}
