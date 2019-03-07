
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import domain.Brotherhood;
import domain.Member;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	@Autowired
	private BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public ModelAndView changeTitle(@RequestParam final String title) {
		ModelAndView res;
		res = new ModelAndView("redirect:/actor/profile.do");
		try {
			Assert.isTrue(title != "" && title != null);
			final Brotherhood brotherhood = this.brotherhoodService.findPrincipal();
			brotherhood.setTitle(title);
			this.brotherhoodService.save(brotherhood);
		} catch (final Exception e) {
			final String messageCode = "brotherhood.commit.error";
			res.addObject("message", messageCode);
		}
		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		res = new ModelAndView("actor/list");
		final List<Brotherhood> actors = this.brotherhoodService.findAll();
		res.addObject("actors", actors);
		res.addObject("brother", true);
		return res;
	}

	@RequestMapping(value = "/brother/members", method = RequestMethod.GET)
	public ModelAndView members(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		res = new ModelAndView("actor/list");
		Brotherhood brotherhood;
		if (id == null || id == 0)
			brotherhood = this.brotherhoodService.findPrincipal();
		else
			brotherhood = this.brotherhoodService.findById(id);
		final List<Member> actors = this.brotherhoodService.findMembers(brotherhood);
		res.addObject("actors", actors);
		return res;
	}
}
