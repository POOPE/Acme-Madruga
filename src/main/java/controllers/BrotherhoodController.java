
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController {

	@Autowired
	private BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public ModelAndView changeTitle(@RequestParam String title) {
		ModelAndView res;
		res = new ModelAndView("redirect:/actor/profile.do");
		try {
			Assert.isTrue(title != "" && title != null);
			Brotherhood brotherhood = this.brotherhoodService.findPrincipal();
			brotherhood.setTitle(title);
			this.brotherhoodService.save(brotherhood);
		} catch (Exception e) {
			String messageCode = "brotherhood.commit.error";
			res.addObject("message", messageCode);
		}
		return res;
	}
}
