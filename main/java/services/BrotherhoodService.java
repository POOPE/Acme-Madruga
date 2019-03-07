
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BrotherhoodRepository;
import domain.Brotherhood;

@Service
@Transactional
public class BrotherhoodService {

	@Autowired
	private BrotherhoodRepository	brotherhoodRepo;
	@Autowired
	private ActorService			actorService;


	public Brotherhood save(final Brotherhood Brotherhood) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Brotherhood res = this.brotherhoodRepo.save(Brotherhood);
		return res;

	}
}
