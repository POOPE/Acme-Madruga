
package services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BrotherhoodFloatRepository;
import domain.Brotherhood;
import domain.BrotherhoodFloat;
import domain.FloatPicture;
import forms.FloatForm;

@Service
@Transactional
public class BrotherhoodFloatService {

	@Autowired
	private BrotherhoodFloatRepository	brotherhoodFloatRepo;
	@Autowired
	private BrotherhoodService			brotherhoodService;
	@Autowired
	private FloatPictureService			floatPictureService;


	public BrotherhoodFloat parseForm(final FloatForm form) {
		final BrotherhoodFloat res = new BrotherhoodFloat();
		res.setId(form.getId());
		res.setVersion(form.getVersion());
		res.setTitle(form.getTitle());
		res.setDescription(form.getDescription());
		return res;
	}

	public BrotherhoodFloat save(final FloatForm form) {
		BrotherhoodFloat bFloat;
		bFloat = this.parseForm(form);
		bFloat.setOwner(this.brotherhoodService.findPrincipal());
		final BrotherhoodFloat saved = this.save(bFloat);
		if (form.getId() == 0)
			this.floatPictureService.createAll(Arrays.asList(form.getPhotos().split(",")), saved);
		else {
			final List<FloatPicture> pics = this.floatPictureService.findByFloat(saved.getId());
			for (final FloatPicture pic : pics)
				this.floatPictureService.delete(pic.getId());
			this.floatPictureService.createAll(Arrays.asList(form.getPhotos().split(",")), saved);
		}
		return saved;
	}

	public FloatForm formatForm(final BrotherhoodFloat bFloat) {
		final FloatForm res = new FloatForm();
		res.setId(bFloat.getId());
		res.setVersion(bFloat.getVersion());
		res.setTitle(bFloat.getTitle());
		res.setDescription(bFloat.getDescription());
		String urls = "";
		final List<FloatPicture> pics = this.floatPictureService.findByFloat(bFloat.getId());
		for (int i = 0; i < pics.size(); i++)
			if (i < pics.size() - 1)
				urls = urls + pics.get(i).getUrl() + ",";
			else
				urls = urls + pics.get(i).getUrl();
		res.setPhotos(urls);
		res.setOwner(bFloat.getOwner().getId());
		return res;
	}

	public BrotherhoodFloat save(final BrotherhoodFloat brotherhoodFloat) {
		return this.brotherhoodFloatRepo.save(brotherhoodFloat);

	}

	public void delete(final BrotherhoodFloat brotherhoodFloat) {

		final Brotherhood bro = this.brotherhoodService.findPrincipal();
		Assert.isTrue(brotherhoodFloat.getOwner().equals(bro));

		//delete all associated pictures
		final List<FloatPicture> floatPictures = this.floatPictureService.findByFloat(brotherhoodFloat.getId());
		int fPId = 0;
		for (final FloatPicture picture : floatPictures) {
			fPId = picture.getId();

			this.floatPictureService.delete(fPId);
		}
		this.brotherhoodFloatRepo.delete(brotherhoodFloat);
	}

	public BrotherhoodFloat create() {
		final Brotherhood brotherhood = this.brotherhoodService.findPrincipal();
		final BrotherhoodFloat res = new BrotherhoodFloat();
		res.setOwner(brotherhood);
		return res;
	}

	public List<BrotherhoodFloat> findAll() {
		return this.brotherhoodFloatRepo.findAll();
	}

	public BrotherhoodFloat findById(final int id) {
		return this.brotherhoodFloatRepo.findOne(id);
	}

	public List<BrotherhoodFloat> findByBrotherhood(final int brotherhoodId) {
		return this.brotherhoodFloatRepo.findByBrotherhood(brotherhoodId);
	}

}
