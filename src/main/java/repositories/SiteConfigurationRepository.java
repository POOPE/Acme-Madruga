
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SiteConfig;

@Repository
public interface SiteConfigurationRepository extends JpaRepository<SiteConfig, Integer> {

	@Query("select a from SiteConfiguration a")
	SiteConfig find();
}
