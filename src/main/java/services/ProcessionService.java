
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ProcessionRepository;
import domain.Brotherhood;
import domain.Procession;

@Service
@Transactional
public class ProcessionService {

	@Autowired
	private ProcessionRepository	processionRepo;
	@Autowired
	private BrotherhoodService			brotherhoodService;

	public Procession save(final Procession procession) {
		return this.processionRepo.save(procession);

	}

	public void delete(final Procession procession) {


		Brotherhood bro = this.brotherhoodService.findPrincipal();
		Assert.isTrue(procession.getBrotherhood().equals(bro));
		this.processionRepo.delete(procession);
	}

	public Procession create() {
		Brotherhood brotherhood = this.brotherhoodService.findPrincipal();
		Procession  result = new Procession();
		result.setBrotherhood(brotherhood);
		final Date date = new Date();
		date.setTime(date.getTime() - 5000);
		result.setMoment(date);
		result.setTicker(result.generateTicker());
		return result;
	}


	public List<Procession> findAll() {
		return this.processionRepo.findAll();
	}

	public Procession findById(int id) {
		return this.processionRepo.findOne(id);
	}

	public List<Procession> findByBrotherhood(final int brotherhoodId) {
		return this.processionRepo.findByBrotherhood(brotherhoodId);
}

}
