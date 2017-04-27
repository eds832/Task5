package by.sardyka.parsing.factory;

import by.sardyka.parsing.parser.AbstractTariffBuilder;
import by.sardyka.parsing.parser.TariffDOMBuilder;
import by.sardyka.parsing.parser.TariffStAXBuilder;
import by.sardyka.parsing.parser.TariffSAXBuilder;

public class TariffBuilderFactory {
	private enum TypeParser {
		SAX, STAX, DOM
	}

	public AbstractTariffBuilder createTariffBuilder(String typeParser) {
		
		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
		switch (type) {
		case DOM:
			return new TariffDOMBuilder();
		case STAX:
			return new TariffStAXBuilder();
		case SAX:
			return new TariffSAXBuilder();
		default:
			throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
		}
	}
}
