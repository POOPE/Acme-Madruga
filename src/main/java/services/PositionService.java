
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionRepository;
import domain.Position;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepository	positionRepository;
	@Autowired
	private AdminService		adminService;


	public Position create() {
		return new Position();
	}

	public void delete(final int id) {
		this.positionRepository.delete(id);
	}

	public Position save(final Position position) {
		return this.positionRepository.save(position);
	}

	public List<Position> findAll() {
		return this.positionRepository.findAll();
	}

	public Position findByTitle(final String title) {
		return this.positionRepository.findByTitle(title);
	}

	public Position findById(final int id) {
		return this.positionRepository.findOne(id);
	}
}
