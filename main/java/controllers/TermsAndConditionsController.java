package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/terms")
public class TermsAndConditionsController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView Controller() {
		ModelAndView res;
		res = new ModelAndView("terms-and-conditions/terms");
		return res;
	}
}
