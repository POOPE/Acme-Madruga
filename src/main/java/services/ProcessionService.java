
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import domain.Brotherhood;
import domain.BrotherhoodFloat;
import domain.Procession;
import forms.ProcessionForm;

@Service
@Transactional
public class ProcessionService {

	@Autowired
	private ProcessionRepository	processionRepo;
	@Autowired
	private BrotherhoodService		brotherhoodService;
	@Autowired
	private BrotherhoodFloatService	brotherhoodFloatService;


	public Procession parseForm(final ProcessionForm form) {
		Procession res = this.create();
		if (form.getId() != 0) {
			res = this.findById(form.getId());
			Assert.isTrue(res.getBrotherhood().equals(this.brotherhoodService.findPrincipal()));
		} else
			res.setBrotherhood(this.brotherhoodService.findPrincipal());
		res.setTitle(form.getTitle());
		res.setDescription(form.getDescription());
		final String[] floats = form.getbFloats().split(",");
		final List<BrotherhoodFloat> bFloats = new ArrayList<>();
		for (int i = 0; i < floats.length; i++) {
			final BrotherhoodFloat bFloat = this.brotherhoodFloatService.findById(Integer.valueOf(floats[i]));
			bFloats.add(bFloat);
		}
		res.setbFloats(bFloats);
		return res;
	}
	public ProcessionForm formatForm(final Procession procession) {
		final ProcessionForm res = new ProcessionForm();
		res.setId(procession.getId());
		res.setVersion(procession.getVersion());
		res.setTitle(procession.getTitle());
		res.setMoment(procession.getMoment());
		String floats = "";
		for (int i = 0; i < procession.getbFloats().size(); i++)
			if (i < procession.getbFloats().size() - 1)
				floats = floats + procession.getbFloats().get(i) + ",";
			else
				floats = floats + procession.getbFloats().get(i);
		res.setbFloats(floats);
		return res;
	}

	public Procession save(final ProcessionForm form) {
		final Procession procession = this.parseForm(form);
		procession.setTicker(procession.generateTicker());
		return this.save(procession);
	}

	public Procession save(final Procession procession) {
		return this.processionRepo.save(procession);

	}

	public void delete(final Procession procession) {

		final Brotherhood bro = this.brotherhoodService.findPrincipal();
		Assert.isTrue(procession.getBrotherhood().equals(bro));
		this.processionRepo.delete(procession);
	}

	public Procession create() {
		final Brotherhood brotherhood = this.brotherhoodService.findPrincipal();
		final Procession result = new Procession();
		result.setBrotherhood(brotherhood);
		return result;
	}

	public List<Procession> findAll() {
		return this.processionRepo.findAll();
	}

	public Procession findById(final int id) {
		return this.processionRepo.findOne(id);
	}

	public List<Procession> findByBrotherhood(final int brotherhoodId) {
		return this.processionRepo.findByBrotherhood(brotherhoodId);
	}

}
