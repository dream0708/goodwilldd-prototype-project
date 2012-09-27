package net.ujin.recruit.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.ujin.recruit.model.UjinCustomerDataModel;
import net.ujin.recruit.model.UjinFinancesDataModel;

import org.springframework.stereotype.Service;

@Service
public interface HomeService {

	public List<UjinFinancesDataModel> getFinancesList(String year,
			String month, String day, int _idx);

	public void doFinancesInsert(JSONObject inParam);

	public List<UjinCustomerDataModel> getCustomerList();

	public void doInsertCustomer(JSONObject fromObject);

	public UjinCustomerDataModel getCustomer(int _idx);

	public String getSummeryMoney(String year, String month, String day,
			int custIdx);

	public List<Map<String, String>> getSummeryMoneyMonth(String year, int custIdx);
}
