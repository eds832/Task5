package by.sardyka.parsing.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import by.sardyka.parsing.entity.FixInternetPack;
import by.sardyka.parsing.entity.PerMbInternet;
import by.sardyka.parsing.entity.TariffType;

import static by.sardyka.parsing.entity.OperatorName.*;
import static by.sardyka.parsing.parser.TariffEnum.*;

public class TariffHandler extends DefaultHandler {

	private Set<TariffType> tariffs;
	private TariffType current = null;
	private TariffEnum currentEnum = null;
	private EnumSet<TariffEnum> withText;

	public TariffHandler() {
		tariffs = new HashSet<TariffType>();
		withText = EnumSet.range(TariffEnum.PAYROLL, TariffEnum.I_NET_PACK_SIZE);
	}

	public Set<TariffType> getTariffs() {
		return tariffs;
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		switch (TariffEnum.valueOf(localName.toUpperCase().replace('-', '_'))) {
		case FIX_INTERNET_PACK:
			current = new FixInternetPack();
			current.setName(attrs.getValue(0));
			current.setOperatorName(fromValue(attrs.getValue(1)));
			if (attrs.getLength() == 4) {
				((FixInternetPack) current).setAdditionalINetFee(new BigDecimal(attrs.getValue(2)));
				((FixInternetPack) current).setOverSizePrice(new BigDecimal(attrs.getValue(3)));
			} else {
				((FixInternetPack) current).setOverSizePrice(new BigDecimal(attrs.getValue(2)));
			}
			break;
		case PER_MB_INTERNET:
			current = new PerMbInternet();
			current.setName(attrs.getValue(0));
			current.setOperatorName(fromValue(attrs.getValue(1)));
			break;
		default:
			TariffEnum temp = TariffEnum.valueOf(localName.toUpperCase().replace('-', '_'));
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if (FIX_INTERNET_PACK.getValue().equals(localName) || PER_MB_INTERNET.getValue().equals(localName)) {
			tariffs.add(current);
		}
	}

	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case PAYROLL:
				current.setPayroll(new BigDecimal(s));
				break;
			case SMS_PRICE:
				current.setSmsPrice(new BigDecimal(s));
				break;
			case INNER_CALL_PRICE:
				current.getCallPrices().setInnerCallPrice(new BigDecimal(s));
				break;
			case OUT_CALL_PRICE:
				current.getCallPrices().setOutCallPrice(new BigDecimal(s));
				break;
			case LAND_LINE_CALL_PRICE:
				current.getCallPrices().setLandLineCallPrice(new BigDecimal(s));
				break;
			case NUMBER_FAVORITES:
				current.getParameters().setNumberFavorites(new BigInteger(s));
				break;
			case TARIFFING:
				current.getParameters().setTariffing(s);
				break;
			case INIT_PRICE:
				current.getParameters().setInitPrice(new BigDecimal(s));
				break;
			case I_NET_PACK_SIZE:
				((FixInternetPack) current).setINetPackSize(new BigInteger(s));
				break;
			case PER_MB_PRICE:
				((PerMbInternet) current).setPerMbPrice(new BigDecimal(s));
				break;
			default:
				throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}
}
