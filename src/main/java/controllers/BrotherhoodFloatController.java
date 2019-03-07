
package controllers;

import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodFloatService;
import services.BrotherhoodService;
import services.FloatPictureService;
import domain.Brotherhood;
import domain.BrotherhoodFloat;
import domain.FloatPicture;
import forms.FloatForm;

@Controller
@RequestMapping("/bfloat")
public class BrotherhoodFloatController extends AbstractController {

	@Autowired
	private BrotherhoodFloatService	bFloatService;

	@Autowired
	private BrotherhoodService		broService;

	@Autowired
	private FloatPictureService		fPictureService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<BrotherhoodFloat> bFloats = this.bFloatService.findAll();
		result = new ModelAndView("bfloat/list");
		result.addObject("brotherhoodFloats", bFloats);
		result.addObject("requestURI", "bfloat/list.do");
		return result;
	}

	// My List -------------------------------------------------------------
	@RequestMapping(value = "brother/list", method = RequestMethod.GET)
	public ModelAndView mylist(@RequestParam(required = false) final Integer id) {
		ModelAndView result;
		final Brotherhood bro;
		if (id == null || id == 0)
			bro = this.broService.findPrincipal();
		else {
			bro = this.broService.findById(id);
			Assert.notNull(bro);
		}

		final Collection<BrotherhoodFloat> bFloats = this.bFloatService.findByBrotherhood(bro.getId());
		result = new ModelAndView("bfloat/myList");
		result.addObject("brotherhoodFloats", bFloats);
		result.addObject("requestURI", "bfloat/myList.do");

		return result;
	}

	//DISPLAY	--------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int bFloatID) {
		ModelAndView result;
		BrotherhoodFloat r = null;
		Brotherhood b = null;
		List<FloatPicture> fps;

		result = new ModelAndView("bfloat/display");

		// Check for null float
		try {
			r = this.bFloatService.findById(bFloatID);
			b = r.getOwner();
			fps = this.fPictureService.findByFloat(bFloatID);

		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Forbidden operation");
			result = new ModelAndView("redirect:/");
			return result;
		}

		result.addObject("brotherhoodFloat", r);
		result.addObject("brotherhood", b);
		result.addObject("floatPictures", fps);
		return result;
	}

	// Create & Edit -----------------------------------------------------------
	@RequestMapping(value = "super/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final BrotherhoodFloat bFloat = this.bFloatService.create();

		final ModelAndView result = this.createEditModelAndView(bFloat);

		return result;
	}

	@RequestMapping(value = "super/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam final int bFloatID) {
		ModelAndView result;
		BrotherhoodFloat bFloat;

		try {
			bFloat = this.bFloatService.findById(bFloatID);
			final Brotherhood principal = this.broService.findPrincipal();
			Assert.isTrue(bFloat.getOwner().equals(principal));
			result = this.createEditModelAndView(bFloat);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Forbidden operation");
			result = new ModelAndView("redirect:/bfloat/myList.do");
			return result;
		}

		return result;
	}

	@RequestMapping(value = "super/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FloatForm bFloat, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(this.bFloatService.parseForm(bFloat));

		} else
			try {
				this.bFloatService.save(bFloat);
				result = new ModelAndView("redirect:/bfloat/myList.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(this.bFloatService.parseForm(bFloat), "bFloat.commit.error");
			}
		return result;
	}

	// Delete ------------------------------------------------------
	@RequestMapping(value = "super/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final BrotherhoodFloat bFloat, final BindingResult binding) {
		ModelAndView result;

		try {

			// Check principal own this float
			final Brotherhood principal = this.broService.findPrincipal();
			Assert.isTrue(bFloat.getOwner().equals(principal));

			this.bFloatService.delete(bFloat);
			result = new ModelAndView("redirect:/bfloat/myList.do");
		} catch (final Throwable oops) {
			oops.printStackTrace();
			result = this.createEditModelAndView(bFloat, "bFloat.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final BrotherhoodFloat bFloat) {
		return this.createEditModelAndView(bFloat, null);

	}

	protected ModelAndView createEditModelAndView(final BrotherhoodFloat bFloat, final String message) {
		ModelAndView result;
		final int bFloatId = bFloat.getId();

		final Collection<FloatPicture> fPictures = this.fPictureService.findByFloat(bFloatId);
		final FloatForm form = this.bFloatService.formatForm(bFloat);

		result = new ModelAndView("bfloat/edit");
		result.addObject("floatForm", form);
		return result;
	}
}
