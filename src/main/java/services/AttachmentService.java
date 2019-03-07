
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AttachmentRepository;
import domain.Actor;
import domain.Attachment;

@Service
@Transactional
public class AttachmentService {

	@Autowired
	private AttachmentRepository	attachmentRepository;
	@Autowired
	private ActorService			actorService;


	public ArrayList<Attachment> createAll(final List<String> urls, final Actor actor) {
		final ArrayList<Attachment> res = new ArrayList<>();
		for (final String url : urls) {
			final Attachment pic = this.create();
			pic.setURL(url);
			pic.setOwner(actor);
			final Attachment saved = this.save(pic);
			res.add(saved);
		}
		return res;
	}

	public Attachment create() {
		final Attachment res = new Attachment();
		return res;
	}

	public ArrayList<Attachment> findByOwner(final Actor owner) {
		return this.attachmentRepository.findByOwner(owner.getId());
	}

	public Attachment save(final Attachment attachment) {
		return this.attachmentRepository.save(attachment);
	}
}
