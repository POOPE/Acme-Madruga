
package forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class RegisterForm {

	private String				role;
	private String				firstName;
	private String				middleName;
	private String				lastName;
	private String				countryCode;
	private String				areaCode;
	private String				phoneNumber;
	private String				email;
	private String				address;
	private String				username;
	private String				password;
	private String				photo;

	public static final String	MEMBER		= "MEMBER";
	public static final String	BROTHERHOOD	= "BROTHERHOOD";


	@NotBlank
	@Size(min = 4, max = 32, message = "Username must have 4 to 32 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Characters must be alphanumeric")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Size(min = 4, max = 32, message = "Password must have 4 to 32 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Characters must be alphanumeric")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Pattern(regexp = "^" + RegisterForm.MEMBER + "|" + RegisterForm.BROTHERHOOD + "$")
	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	@NotBlank
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(final String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

}
