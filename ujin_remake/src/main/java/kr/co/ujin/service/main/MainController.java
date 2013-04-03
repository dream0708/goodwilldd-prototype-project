package kr.co.ujin.service.main;

import kr.co.ujin.service.main.entity.Finance;
import kr.co.ujin.service.main.service.CustomerService;
import kr.co.ujin.service.main.service.FinanceService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/main")
public class MainController {

	private static final Log logger = LogFactory.getLog(MainController.class);

	@Autowired
	FinanceService financeService;

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/{pageNumber}/work")
	public String work(Model model, @PathVariable int pageNumber) {
		Page<Finance> page = financeService.findAll(pageNumber);
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());
	    
	    model.addAttribute("financeList", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    
	    model.addAttribute("customerList", customerService.findAll());
	    
		return "tiles/main";
	}

	@RequestMapping(value = "/getData/{selectedDate}")
	@ResponseBody
	public Finance getData(Model model, @PathVariable String selectedDate) {
		financeService.findAll();
		return new Finance();
	}
}
