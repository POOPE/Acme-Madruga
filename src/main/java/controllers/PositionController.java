
package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import domain.Position;

@Controller
@RequestMapping("/position")
public class PositionController extends AbstractController {

	@Autowired
	private PositionService	positionService;


	//LIST
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Position position = this.positionService.create();
		res = this.createEditModelAndView(position);
		return res;
	}

	//delete
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView res;
		this.positionService.delete(id);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	//edit
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		final ModelAndView res;
		final Position position = this.positionService.findById(id);
		Assert.notNull(position);
		res = this.createEditModelAndView(position);
		return res;
	}

	//edit
	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Position position, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(position);
		else
			try {
				this.positionService.save(position);
				res = new ModelAndView("redirect:list.do");
			} catch (final Exception e) {
				res = this.createEditModelAndView(position, "position.commit.error");
			}
		return res;
	}

	//AUX
	protected ModelAndView createEditModelAndView(final Position position) {
		ModelAndView res;
		res = this.createEditModelAndView(position, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(final Position position, final String messageCode) {
		ModelAndView res;
		final List<Position> positions = this.positionService.findAll();

		res = new ModelAndView("position/list");
		res.addObject("positions", positions);
		res.addObject("position", position);
		res.addObject("message", messageCode);
		return res;
	}
}
