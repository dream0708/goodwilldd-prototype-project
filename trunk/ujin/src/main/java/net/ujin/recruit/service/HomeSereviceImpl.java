package net.ujin.recruit.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.ujin.recruit.mapper.IHomeMapper;
import net.ujin.recruit.model.Tuples;
import net.ujin.recruit.model.Tuples.Tuple4;
import net.ujin.recruit.model.UjinCustomerDataModel;
import net.ujin.recruit.model.UjinFinancesDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class HomeSereviceImpl implements HomeService {
	private static final Logger log = LoggerFactory
			.getLogger(HomeSereviceImpl.class);

	private IHomeMapper mapper;

	public void setMapper(IHomeMapper mapper) {
		this.mapper = mapper;
	}

	public List<UjinFinancesDataModel> getFinancesList(String year,
			String month, String day, int _idx) {

		if (!StringUtils.hasLength(year))
			year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		if (!StringUtils.hasLength(month))
			month = String.format("%02d",
					(Calendar.getInstance().get(Calendar.MONTH) + 1));
		if (!StringUtils.hasLength(day))
			day = String.format("%02d",
					Calendar.getInstance().get(Calendar.DATE));

		Tuple4<String, String, String, Integer> param = Tuples.tuple(year,
				month, day, _idx);
		return mapper.getFinancesList(param);
	}

	@Transactional
	public void doFinancesInsert(JSONObject inParam) {
		UjinFinancesDataModel dateParam = new UjinFinancesDataModel();
		dateParam.setFyear(inParam.getString("year"));
		dateParam.setFmonth(inParam.getString("month"));
		dateParam.setFday(inParam.getString("day"));
		mapper.doDeleteAllCurrentDateData(dateParam);

		for (UjinFinancesDataModel dataModel : convertInParam(inParam)) {
			log.debug(dataModel.toString());
			mapper.doFinancesInsert(dataModel);
		}
	}

	private List<UjinFinancesDataModel> convertInParam(JSONObject inParam) {
		List<UjinFinancesDataModel> result = new ArrayList<UjinFinancesDataModel>();
		if (inParam != null && inParam.containsKey("fName")) {
			int allSize = 0;
			if (inParam.getString("fName").startsWith("[")) {
				JSONArray fName = JSONArray.fromObject(inParam
						.getString("fName"));
				allSize = fName.size();
			}

			UjinFinancesDataModel ufModel = null;
			switch (allSize) {
			case 0:
				ufModel = new UjinFinancesDataModel();
				ufModel.setCustomerIdx(inParam.getInt("customer"));
				ufModel.setFyear(inParam.getString("year"));
				ufModel.setFmonth(inParam.getString("month"));
				ufModel.setFday(inParam.getString("day"));
				ufModel.setfName(inParam.getString("fName"));
				ufModel.setfMoney(inParam.getString("fMoney"));
				ufModel.setfEtc(inParam.getString("fEtc"));
				result.add(ufModel);
				break;
			default:
				JSONArray fName = JSONArray.fromObject(inParam
						.getString("fName"));
				JSONArray fMoney = JSONArray.fromObject(inParam
						.getString("fMoney"));
				JSONArray fEtc = JSONArray
						.fromObject(inParam.getString("fEtc"));
				for (int i = 0; i < allSize; i++) {
					ufModel = new UjinFinancesDataModel();
					ufModel.setCustomerIdx(inParam.getInt("customer"));
					ufModel.setFyear(inParam.getString("year"));
					ufModel.setFmonth(inParam.getString("month"));
					ufModel.setFday(inParam.getString("day"));
					ufModel.setfName(fName.getString(i));
					ufModel.setfMoney(fMoney.getString(i));
					ufModel.setfEtc(fEtc.getString(i));
					result.add(ufModel);
				}
				break;
			}
		}
		return result;
	}

	public List<UjinCustomerDataModel> getCustomerList() {
		return mapper.getCustomerList();
	}

	public void doInsertCustomer(JSONObject inParam) {
		UjinCustomerDataModel dataModel = new UjinCustomerDataModel();
		dataModel.setCustomerName(inParam.getString("customerName"));
		dataModel.setCustomerEmail(inParam.getString("customerEmail"));
		mapper.doInsertCustomerData(dataModel);
	}

	public UjinCustomerDataModel getCustomer(int _idx) {
		return mapper.getCustomer(_idx);
	}

	public String getSummeryMoney(String year, String month, String day,
			int custIdx) {
		if (!StringUtils.hasLength(year))
			year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		if (!StringUtils.hasLength(month))
			month = String.format("%02d",
					(Calendar.getInstance().get(Calendar.MONTH) + 1));
		if (!StringUtils.hasLength(day))
			day = String.format("%02d",
					Calendar.getInstance().get(Calendar.DATE));

		return mapper.getSummeryMoney(Tuples.tuple(year,
				month, day, custIdx));
	}

	public List<Map<String, String>> getSummeryMoneyMonth(String year, int custIdx) {
		if (!StringUtils.hasLength(year))
			year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

		return mapper.getSummeryMoneyMonth(Tuples.tuple(year, custIdx));
	}
}
