
package services;

import java.util.ArrayList;

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


	public Attachment createForPrincipal(final String URL) {
		return this.create(this.actorService.findPrincipal(), URL);
	}

	public Attachment create(final Actor actor, final String URL) {
		final Attachment res = new Attachment();
		res.setOwner(actor);
		res.setURL(URL);
		return this.save(res);
	}

	public ArrayList<Attachment> findByOwner(final Actor owner) {
		return this.attachmentRepository.findByOwner(owner.getId());
	}

	public Attachment save(final Attachment attachment) {
		return this.attachmentRepository.save(attachment);
	}
}
