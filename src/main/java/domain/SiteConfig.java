
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
public class SiteConfig extends DomainEntity {

	private String	siteName;
	private String	bannerUrl;
	private String	welcomeMessage;
	private Integer	countryCode;


	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getWelcomeMessage() {
		return this.welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@Size(min = 1, max = 3)
	public Integer getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

}
