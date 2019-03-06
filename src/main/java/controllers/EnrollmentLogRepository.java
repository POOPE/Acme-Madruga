
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.EnrollmentLogService;
import domain.EnrollmentLog;

@Controller
@RequestMapping("/logs")
public class EnrollmentLogRepository {

	@Autowired
	private EnrollmentLogService	logService;


	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView res;
		res = new ModelAndView("log/list");
		final List<EnrollmentLog> logs = this.logService.findAll();
		res.addObject("logs", logs);
		return res;
	}

}
