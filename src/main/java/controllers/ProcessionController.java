
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

import services.BrotherhoodService;
import services.FloatPictureService;
import services.ProcessionService;
import domain.Brotherhood;
import domain.FloatPicture;
import domain.Procession;

@Controller
@RequestMapping("/procession")
public class ProcessionController extends AbstractController {

	@Autowired
	private ProcessionService	processionService;

	@Autowired
	private BrotherhoodService	broService;

	@Autowired
	private FloatPictureService	fPictureService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Procession> processions = this.processionService.findAll();
		result = new ModelAndView("procession/list");
		result.addObject("processions", processions);
		result.addObject("requestURI", "procession/list.do");
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

		final Collection<Procession> processions = this.processionService.findByBrotherhood(bro.getId());
		result = new ModelAndView("procession/myList");
		result.addObject("processions", processions);
		result.addObject("requestURI", "procession/myList.do");

		return result;
	}

	//DISPLAY	--------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int processionID) {
		ModelAndView result;
		Procession r = null;
		Brotherhood b = null;

		result = new ModelAndView("procession/display");

		// Check for null procession
		try {
			r = this.processionService.findById(processionID);
			b = r.getBrotherhood();

		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Forbidden operation");
			result = new ModelAndView("redirect:/");
			return result;
		}

		result.addObject("procession", r);
		result.addObject("brotherhood", b);
		return result;
	}

	// Create & Edit -----------------------------------------------------------
	@RequestMapping(value = "super/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final Procession procession = this.processionService.create();

		final ModelAndView result = this.createEditModelAndView(procession);

		return result;
	}

	@RequestMapping(value = "super/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam final int processionID) {
		ModelAndView result;
		Procession procession;

		try {
			procession = this.processionService.findById(processionID);
			final Brotherhood principal = this.broService.findPrincipal();
			Assert.isTrue(procession.getBrotherhood().equals(principal));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Forbidden operation");
			result = new ModelAndView("redirect:/procession/myList.do");
			return result;
		}

		result = this.createEditModelAndView(procession);

		return result;
	}

	@RequestMapping(value = "brother/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Procession procession, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(procession);

		} else
			try {
				if (procession.getId() != 0) {
					// Check principal own this procession
					final Brotherhood principal = this.broService.findPrincipal();
					Assert.isTrue(procession.getBrotherhood().equals(principal));
				}

				this.processionService.save(procession);
				result = new ModelAndView("redirect:/procession/myList.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(procession, "procession.commit.error");
			}
		return result;
	}

	// Delete ------------------------------------------------------
	@RequestMapping(value = "brother/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Procession procession, final BindingResult binding) {
		ModelAndView result;

		try {

			// Check principal own this fixUptask
			final Brotherhood principal = this.broService.findPrincipal();
			Assert.isTrue(procession.getBrotherhood().equals(principal));

			this.processionService.delete(procession);
			result = new ModelAndView("redirect:/procession/myList.do");
		} catch (final Throwable oops) {
			oops.printStackTrace();
			result = this.createEditModelAndView(procession, "procession.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Procession procession) {
		ModelAndView result;

		result = this.createEditModelAndView(procession, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Procession procession, final String message) {
		ModelAndView result;
		final int processionId = procession.getId();
		final Collection<FloatPicture> fPictures = this.fPictureService.findByFloat(processionId);

		result = new ModelAndView("procession/edit");
		result.addObject("procession", procession);
		result.addObject("floatPictures", fPictures);
		return result;
	}
}
