
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SiteConfigurationService;
import domain.SiteConfig;

@Controller
@RequestMapping("/config")
public class SiteConfigController extends AbstractController {

	@Autowired
	private SiteConfigurationService	siteConfigService;


	//EDIT
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final SiteConfig config = this.siteConfigService.find();
		Assert.notNull(config);
		res = this.createEditModelAndView(config);
		return res;
	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SiteConfig config, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(config);
		else
			try {
				final SiteConfig saved = this.siteConfigService.update(config);
				res = new ModelAndView("redirect:/#");
			} catch (final Exception e) {
				res = this.createEditModelAndView(config, "config.commit.error");
			}
		return res;
	}

	//AUX
	protected ModelAndView createEditModelAndView(final SiteConfig siteConfig) {
		ModelAndView res;
		res = this.createEditModelAndView(siteConfig, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(final SiteConfig siteConfig, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("config/edit");
		res.addObject("config", siteConfig);
		res.addObject("message", messageCode);
		return res;
	}
}
