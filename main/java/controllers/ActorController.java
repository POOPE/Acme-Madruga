
package controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdminService;
import services.BrotherhoodService;
import services.MemberService;
import forms.RegisterForm;

@Controller
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private MemberService		memberService;
	@Autowired
	private BrotherhoodService	brotherhoodService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private AdminService		adminService;


	//REGISTER
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView res;
		final RegisterForm registerForm = new RegisterForm();
		res = this.createEditModelAndView(registerForm);
		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("regForm") @Valid final RegisterForm registerForm, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(registerForm);
		else
			try {
				this.actorService.register(registerForm);
				res = new ModelAndView("redirect:../security/login.do");
				;
			} catch (final Exception e) {
				res = this.createEditModelAndView(registerForm, "actor.commit.error");
			}
		return res;
	}

	//AUX
	protected ModelAndView createEditModelAndView(final RegisterForm registerForm) {
		return this.createEditModelAndView(registerForm, null);
	}

	protected ModelAndView createEditModelAndView(final RegisterForm registerForm, final String messageCode) {
		ModelAndView result;
		final ArrayList<String> roles = new ArrayList<>();
		roles.add("MEMBER");

		result = new ModelAndView("security/register");
		result.addObject("regForm", registerForm);
		result.addObject("roles", roles);
		result.addObject("message", messageCode);
		return result;
	}
}
