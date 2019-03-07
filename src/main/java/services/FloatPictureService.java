
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FloatPictureRepository;
import domain.BrotherhoodFloat;
import domain.FloatPicture;

@Service
@Transactional
public class FloatPictureService {

	@Autowired
	private FloatPictureRepository	fPictureRepository;


	public FloatPicture create() {
		return new FloatPicture();
	}

	public ArrayList<FloatPicture> createAll(final List<String> urls, final BrotherhoodFloat bFloat) {
		final ArrayList<FloatPicture> res = new ArrayList<>();
		for (final String url : urls) {
			final FloatPicture pic = this.create();
			pic.setUrl(url);
			pic.setOwner(bFloat);
			final FloatPicture saved = this.save(pic);
			res.add(saved);
		}
		return res;
	}

	public void delete(final int id) {
		this.fPictureRepository.delete(id);
	}

	public FloatPicture save(final FloatPicture fPicture) {
		return this.fPictureRepository.save(fPicture);
	}

	public List<FloatPicture> findAll() {
		return this.fPictureRepository.findAll();
	}

	public FloatPicture findById(final int id) {
		return this.fPictureRepository.findOne(id);
	}

	public List<FloatPicture> findByFloat(final int bFloatId) {
		return this.fPictureRepository.findByFloat(bFloatId);
	}
}
