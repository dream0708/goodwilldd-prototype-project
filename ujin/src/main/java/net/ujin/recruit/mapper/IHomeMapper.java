package net.ujin.recruit.mapper;

import java.util.List;
import java.util.Map;

import net.ujin.recruit.model.Tuples.Tuple2;
import net.ujin.recruit.model.Tuples.Tuple3;
import net.ujin.recruit.model.Tuples.Tuple4;
import net.ujin.recruit.model.UjinCustomerDataModel;
import net.ujin.recruit.model.UjinFinancesDataModel;

public interface IHomeMapper {
	public List<UjinFinancesDataModel> getFinancesList(
			Tuple3<String, String, String> param);

	public void doFinancesInsert(UjinFinancesDataModel dataModel);

	public void doDeleteAllCurrentDateData(UjinFinancesDataModel dataModel);

	public List<UjinCustomerDataModel> getCustomerList();

	public void doInsertCustomerData(UjinCustomerDataModel dataModel);

	public UjinCustomerDataModel getCustomer(int _idx);

	public String getSummeryMoney(Tuple4<String, String, String, Integer> param);

	public List<Map<String, String>> getSummeryMoneyMonth(Tuple2<String, Integer> param);
}
