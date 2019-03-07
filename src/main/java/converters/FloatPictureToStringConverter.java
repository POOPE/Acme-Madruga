/*
 * AdministratorToStringConverter.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.FloatPicture;

@Component
@Transactional
public class FloatPictureToStringConverter implements Converter<FloatPicture, String> {

	@Override
	public String convert(final FloatPicture floatPicture) {
		String result;

		if (floatPicture == null)
			result = null;
		else
			result = String.valueOf(floatPicture.getId());

		return result;
	}

}
