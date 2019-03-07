
package controllers;

import java.util.Collection;

import javax.swing.JOptionPane;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Brotherhood;
import domain.Enrollment;
import domain.Member;
import services.BrotherhoodService;
import services.EnrollmentService;
import services.MemberService;
import services.ProcessionService;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController extends AbstractController {

	@Autowired
	private EnrollmentService	enrollmentService;

	@Autowired
	private ProcessionService	processionService;

	@Autowired
	private BrotherhoodService	broService;

	@Autowired
	private MemberService		memberService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	// List de enrollments de brotherhoods -------------------------------------------------------------
	@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Brotherhood bro = this.broService.findPrincipal();
		final Collection<Enrollment> enrollments = this.enrollmentService.findByBrotherhood(bro);
		result = new ModelAndView("enrollment/list");
		result.addObject("enrollments", enrollments);
		result.addObject("requestURI", "enrollments/list.do");
		return result;
	}
	// List de enrollments de members -------------------------------------------------------------

	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		final Member member = this.memberService.findPrincipal();
		final Collection<Enrollment> enrollments = this.enrollmentService.findByMember(member);
		result = new ModelAndView("enrollment/list");
		result.addObject("enrollments", enrollments);
		result.addObject("requestURI", "enrollments/list.do");
		return result;
	}

	// Create & Edit -----------------------------------------------------------
	@RequestMapping(value = "member/create", method = RequestMethod.GET)
	public ModelAndView enrolling(@RequestParam final int brodelID) {

		final Brotherhood brodel = this.broService.findById(brodelID);
		final Enrollment enroll = this.enrollmentService.create(brodel);

		final ModelAndView result = this.createEditModelAndView(enroll);

		return result;
	}

	//DISPLAY	--------------------------------------------------------------
	//	@RequestMapping(value = "/display", method = RequestMethod.GET)
	//	public ModelAndView display(@RequestParam final int processionID) {
	//		ModelAndView result;
	//		Procession r = null;
	//		Brotherhood b = null;
	//
	//		result = new ModelAndView("procession/display");
	//
	//		// Check for null procession
	//		try {
	//			r = this.processionService.findById(processionID);
	//			b = r.getBrotherhood();
	//
	//		} catch (final Exception e) {
	//			JOptionPane.showMessageDialog(null, "Forbidden operation");
	//			result = new ModelAndView("redirect:/");
	//			return result;
	//		}
	//
	//		result.addObject("procession", r);
	//		result.addObject("brotherhood", b);
	//		return result;
	//	}
	//

	//
	//	@RequestMapping(value = "super/update", method = RequestMethod.GET)
	//	public ModelAndView update(@RequestParam final int processionID) {
	//		ModelAndView result;
	//		Procession procession;
	//
	//		try {
	//			procession = this.processionService.findById(processionID);
	//			final Brotherhood principal = this.broService.findPrincipal();
	//			Assert.isTrue(procession.getBrotherhood().equals(principal));
	//		} catch (final Exception e) {
	//			JOptionPane.showMessageDialog(null, "Forbidden operation");
	//			result = new ModelAndView("redirect:/procession/myList.do");
	//			return result;
	//		}
	//
	//		result = this.createEditModelAndView(procession);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "brother/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Procession procession, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		if (binding.hasErrors()) {
	//			final List<ObjectError> errors = binding.getAllErrors();
	//			for (final ObjectError e : errors)
	//				System.out.println(e.toString());
	//			result = this.createEditModelAndView(procession);
	//
	//		} else
	//			try {
	//				if (procession.getId() != 0) {
	//					// Check principal own this procession
	//					final Brotherhood principal = this.broService.findPrincipal();
	//					Assert.isTrue(procession.getBrotherhood().equals(principal));
	//				}
	//
	//				this.processionService.save(procession);
	//				result = new ModelAndView("redirect:/procession/myList.do");
	//			} catch (final Throwable oops) {
	//				result = this.createEditModelAndView(procession, "procession.commit.error");
	//			}
	//		return result;
	//	}
	//
	//	// Delete ------------------------------------------------------
	//	@RequestMapping(value = "brother/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Procession procession, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//
	//			// Check principal own this fixUptask
	//			final Brotherhood principal = this.broService.findPrincipal();
	//			Assert.isTrue(procession.getBrotherhood().equals(principal));
	//
	//			this.processionService.delete(procession);
	//			result = new ModelAndView("redirect:/procession/myList.do");
	//		} catch (final Throwable oops) {
	//			oops.printStackTrace();
	//			result = this.createEditModelAndView(procession, "procession.commit.error");
	//		}
	//
	//		return result;
	//	}

	//Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Enrollment enrollment) {
		ModelAndView result;

		result = this.createEditModelAndView(enrollment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Enrollment enrollment, final String message) {
		ModelAndView result;

		result = new ModelAndView("enrollment/edit");
		result.addObject("enrollment", enrollment);

		return result;
	}
}
