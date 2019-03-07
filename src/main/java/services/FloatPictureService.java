
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FloatPictureRepository;
import domain.FloatPicture;

@Service
@Transactional
public class FloatPictureService {

	@Autowired
	private FloatPictureRepository	fPictureRepository;

	public FloatPicture create() {
		return new FloatPicture();
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

	public  FloatPicture findById(final int id) {
		return this.fPictureRepository.findOne(id);
	}

	public  List<FloatPicture> findAllByBFloat(final int bFloatId) {
		return this.fPictureRepository.findAllByBFloat(bFloatId);
	}
}
