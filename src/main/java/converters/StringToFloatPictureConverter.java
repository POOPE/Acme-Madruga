/*
 * StringToFloatPictureConverter.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.FloatPictureRepository;
import domain.FloatPicture;

@Component
@Transactional
public class StringToFloatPictureConverter implements Converter<String, FloatPicture> {

	@Autowired
	FloatPictureRepository	floatPictureRepository;


	@Override
	public FloatPicture convert(final String text) {
		FloatPicture result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.floatPictureRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
