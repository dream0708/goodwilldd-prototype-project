package net.ujin.recruit;

import java.util.List;

import net.sf.json.JSONObject;
import net.ujin.recruit.model.UjinCustomerDataModel;
import net.ujin.recruit.service.HomeService;
import net.ujin.recruit.service.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);
	
	@Autowired
	private HomeService service;
	
	@Autowired
	private MailService mailService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, @RequestParam(required=false, value="year") String year,
			@RequestParam(required=false, value="month") String month,
			@RequestParam(required=false, value="customer") String customer,
			@RequestParam(required=false, value="day") String day) {
		// skin 설정
		model.addAttribute("skin", "sunny");
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
				
		List<UjinCustomerDataModel> clist = service.getCustomerList();
		int custIdx = 0;
		if ( !StringUtils.hasLength(customer) ) {
			if (clist.size() > 0 ) {
				UjinCustomerDataModel first = clist.get(0);
				custIdx = first.get_idx();
			}
		} else {
			custIdx = Integer.parseInt(customer);
		}
		model.addAttribute("list", service.getFinancesList(year, month, day, custIdx));
		
		model.addAttribute("customerList", clist);
		model.addAttribute("summeryMoney", service.getSummeryMoney(year, month, day, custIdx));
		model.addAttribute("summeryMonthMoney", service.getSummeryMoneyMonth(year, custIdx));
		return "home";
	}

	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public void doSave(Model model, @RequestParam(required=false, value="data") String data) {
		log.debug(data);
		service.doFinancesInsert(JSONObject.fromObject(data));
	}
	
	@RequestMapping(value="/registerCustomer", method=RequestMethod.POST)
	public void doRegisterCustomer(Model model, @RequestParam(required=false, value="data") String data) {
		service.doInsertCustomer(JSONObject.fromObject(data));
	}
	
	@RequestMapping(value="/doSendMail", method={RequestMethod.POST, RequestMethod.GET})
	public void doSendMail(Model model, @RequestParam(required=false, value="data") String data) {
		JSONObject inParam = JSONObject.fromObject(data);
		String year = inParam.getString("year");
		String month = inParam.getString("month");
		String day = inParam.getString("day");
		int _idx = inParam.getInt("_idx");
		mailService.sendMail(year, month, day, service.getCustomer(_idx), service.getFinancesList(year, month, day, _idx));
		//mailService.sendAlertMail("test");
		//mailService.sendMail("ujin.recruit@gmail.com", to, subject, body);
	}
}
