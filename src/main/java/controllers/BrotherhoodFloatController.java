
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

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.BrotherhoodService;
import services.FloatPictureService;

import services.BrotherhoodFloatService;
import domain.Brotherhood;
import domain.BrotherhoodFloat;
import domain.FloatPicture;

@Controller
@RequestMapping("/bfloat")
public class BrotherhoodFloatController extends AbstractController {
	

		@Autowired
		private BrotherhoodFloatService		bFloatService;
	
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
			Collection<BrotherhoodFloat> bFloats = this.bFloatService.findAll();
			result = new ModelAndView("bfloat/list");
			result.addObject("brotherhoodFloats", bFloats);
			result.addObject("requestURI", "bfloat/list.do");
			return result;
		}
	
		// My List -------------------------------------------------------------
		@RequestMapping(value = "/myList", method = RequestMethod.GET)
		public ModelAndView mylist() {
			ModelAndView result;
			
			final UserAccount userAccount = LoginService.getPrincipal();
			final Authority broAuthority = new Authority();
			broAuthority.setAuthority("BROTHERHOOD");
			Assert.isTrue(userAccount.getAuthorities().contains(broAuthority));
	
			final Brotherhood bro = this.broService.findPrincipal();
			final int broId = bro.getId();
			final Collection<BrotherhoodFloat> bFloats = this.bFloatService.findByBrotherhood(broId);
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
				b = r.getBrotherhood();
				fps = this.fPictureService.findAllByBFloat(bFloatID);
				
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
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {

	
			BrotherhoodFloat bFloat = this.bFloatService.create();
	
			ModelAndView result = this.createEditModelAndView(bFloat);
	
			return result;
		}
	
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam final int bFloatID) {
			ModelAndView result;
			BrotherhoodFloat bFloat;
	
			try {
				bFloat = this.bFloatService.findById(bFloatID);
				final Brotherhood principal = this.broService.findPrincipal();
				Assert.isTrue(bFloat.getBrotherhood().equals(principal));
			} catch (final Exception e) {
				JOptionPane.showMessageDialog(null, "Forbidden operation");
				result = new ModelAndView("redirect:/bfloat/myList.do");
				return result;
			}
	
			result = this.createEditModelAndView(bFloat);
	
			return result;
		}
	
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final BrotherhoodFloat bFloat, final BindingResult binding) {
			ModelAndView result;
	
			if (binding.hasErrors()) {
				final List<ObjectError> errors = binding.getAllErrors();
				for (final ObjectError e : errors)
					System.out.println(e.toString());
				result = this.createEditModelAndView(bFloat);
	
			} else
				try {
					if (bFloat.getId() != 0) {
						// Check principal own this float
						final Brotherhood principal = this.broService.findPrincipal();
						Assert.isTrue(bFloat.getBrotherhood().equals(principal));
					}
	
					this.bFloatService.save(bFloat);
					result = new ModelAndView("redirect:/bfloat/myList.do");
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(bFloat, "bFloat.commit.error");
				}
			return result;
		}
	
		// Delete ------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(final BrotherhoodFloat bFloat, final BindingResult binding) {
			ModelAndView result;
	
			try {
	
				// Check principal own this float
				final Brotherhood principal = this.broService.findPrincipal();
				Assert.isTrue(bFloat.getBrotherhood().equals(principal));
	
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
			ModelAndView result;
	
			result = this.createEditModelAndView(bFloat, null);
	
			return result;
		}
	
		protected ModelAndView createEditModelAndView(final BrotherhoodFloat bFloat, final String message) {
			ModelAndView result;
			final int bFloatId = bFloat.getId();
			final Collection<FloatPicture> fPictures = this.fPictureService.findAllByBFloat(bFloatId);
	
			result = new ModelAndView("bfloat/edit");
			result.addObject("brotherhoodfFoat", bFloat);
			result.addObject("floatPictures", fPictures);	
			return result;
		}
	}