
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import domain.Actor;

@Controller
@RequestMapping(value = "/user")
public class UserAccountController {

	@Autowired
	private ActorService	actorService;
	@Autowired
	private LoginService	loginService;


	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public ModelAndView passwordChange() {
		ModelAndView result;
		Actor actor;
		try {
			actor = this.actorService.findPrincipal();
			result = this.createPassEditModelAndView(actor.getUserAccount());
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../welcome/index.do");
		}
		return result;
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST, params = "save")
	public ModelAndView savePassword(@Valid final UserAccount user, final BindingResult bindingResult) {
		ModelAndView result;
		final Actor actor = this.actorService.findPrincipal();
		if (bindingResult.hasErrors())
			result = this.createPassEditModelAndView(user, "actor.commit.error");
		else
			try {
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hash = encoder.encodePassword(user.getPassword(), null);
				user.setPassword(hash);
				actor.setUserAccount(user);
				this.actorService.save(actor);
				SecurityContextHolder.getContext().setAuthentication(null);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				result = this.createPassEditModelAndView(user, "actor.commit.error");
			}
		return result;
	}

	// Check pass
	@RequestMapping(value = "/check", headers = "Accept=*/*", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	int checkPass(@RequestParam final String pass) {
		int res = 0;

		if (this.loginService.checkPassword(pass))
			res = 1;
		return res;
	}

	protected ModelAndView createPassEditModelAndView(final UserAccount user) {
		return this.createPassEditModelAndView(user, null);
	}

	protected ModelAndView createPassEditModelAndView(final UserAccount user, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("actor/password");
		result.addObject("user", user);
		result.addObject("message", messageCode);
		return result;
	}
}
