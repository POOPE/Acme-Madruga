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

import domain.BrotherhoodFloat;

@Component
@Transactional
public class BrotherhoodFloatToStringConverter implements Converter<BrotherhoodFloat, String> {

	@Override
	public String convert(final BrotherhoodFloat brotherhoodFloat) {
		String result;

		if (brotherhoodFloat == null)
			result = null;
		else
			result = String.valueOf(brotherhoodFloat.getId());

		return result;
	}

}
