
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SiteConfigurationRepository;
import domain.SiteConfig;

@Service
@Transactional
public class SiteConfigurationService {

	@Autowired
	private SiteConfigurationRepository	siteConfigurationRepository;
	@Autowired
	private AdminService				adminService;


	public SiteConfig find() {
		return this.siteConfigurationRepository.find();
	}

	public SiteConfig update(final SiteConfig siteConfig) {
		return this.siteConfigurationRepository.save(siteConfig);
	}

	public SiteConfig restore() {
		final SiteConfig siteConfig = this.find();
		siteConfig.setSiteName("Acme Handy Worker");
		siteConfig.setBannerUrl("http://www.sample-banner.com/banner.png");
		siteConfig.setWelcomeMessage("");
		siteConfig.setCountryCode(34);

		return this.update(siteConfig);
	}

}
