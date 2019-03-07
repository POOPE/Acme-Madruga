
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BrotherhoodFloatRepository;
import domain.Brotherhood;
import domain.BrotherhoodFloat;
import domain.FloatPicture;

@Service
@Transactional
public class BrotherhoodFloatService {

	@Autowired
	private BrotherhoodFloatRepository	brotherhoodFloatRepo;
	@Autowired
	private BrotherhoodService			brotherhoodService;
	@Autowired
	private FloatPictureService		floatPictureService;

	public BrotherhoodFloat save(final BrotherhoodFloat brotherhoodFloat) {
		return this.brotherhoodFloatRepo.save(brotherhoodFloat);

	}

	public void delete(final BrotherhoodFloat brotherhoodFloat) {


		Brotherhood bro = this.brotherhoodService.findPrincipal();
		Assert.isTrue(brotherhoodFloat.getBrotherhood().equals(bro));

		//delete all associated pictures
		List<FloatPicture> floatPictures = this.floatPictureService.findAllByBFloat(brotherhoodFloat.getId());
		int fPId = 0;
		for (FloatPicture picture : floatPictures) {
			fPId = picture.getId();
			
			this.floatPictureService.delete(fPId);
		}
		this.brotherhoodFloatRepo.delete(brotherhoodFloat);
	}

	public BrotherhoodFloat create() {
		Brotherhood brotherhood = this.brotherhoodService.findPrincipal();
		BrotherhoodFloat  res = new BrotherhoodFloat();
		res.setBrotherhood(brotherhood);
		return res;
	}


	public List<BrotherhoodFloat> findAll() {
		return this.brotherhoodFloatRepo.findAll();
	}

	public BrotherhoodFloat findById(int id) {
		return this.brotherhoodFloatRepo.findOne(id);
	}

	public List<BrotherhoodFloat> findByBrotherhood(final int brotherhoodId) {
		return this.brotherhoodFloatRepo.findByBrotherhood(brotherhoodId);
}

}
