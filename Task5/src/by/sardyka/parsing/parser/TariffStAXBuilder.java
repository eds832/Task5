package by.sardyka.parsing.parser;

import by.sardyka.parsing.entity.CallPrices;
import by.sardyka.parsing.entity.FixInternetPack;
import by.sardyka.parsing.entity.Parameters;
import by.sardyka.parsing.entity.PerMbInternet;
import by.sardyka.parsing.entity.TariffType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static by.sardyka.parsing.entity.OperatorName.*;
import static by.sardyka.parsing.parser.TariffEnum.*;

public class TariffStAXBuilder extends AbstractTariffBuilder {

	private static final Logger LOG = LogManager.getLogger(TariffStAXBuilder.class);
	private HashSet<TariffType> tariffs = new HashSet<>();
	private XMLInputFactory inputFactory;

	public TariffStAXBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public Set<TariffType> getTariffs() {
		LOG.log(Level.INFO,
				"\ngetTariffs() is returning a Set with a size = " + ((tariffs == null) ? 0 : tariffs.size()));
		return tariffs;
	}

	public void buildSetTariffs(String fileName) {
		LOG.log(Level.INFO, "\nbuildSetTariffs(fileName) is starting with fileName: " + fileName);
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (TARIFFS.getValue().equals(name)) {
						reader.next();
						continue;
					}
					if (FIX_INTERNET_PACK.getValue().equals(name) || PER_MB_INTERNET.getValue().equals(name)) {
						TariffType tariff = buildTariff(reader, name);
						tariffs.add(tariff);
					}
				}
			}
		} catch (XMLStreamException e) {
			LOG.log(Level.FATAL, "\nStAX parsing error: " + e.getMessage());
			throw new RuntimeException("\nStAX parsing error: " + e.getMessage());
		} catch (FileNotFoundException e) {
			LOG.log(Level.FATAL, "\nFile " + fileName + " not found: " + e);
			throw new RuntimeException("\nFile " + fileName + " not found: " + e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				LOG.log(Level.ERROR, "\nImpossible close file " + fileName + " : " + e.getMessage());
			}
		}
	}

	private TariffType buildTariff(XMLStreamReader reader, String tariffType) throws XMLStreamException {
		TariffType tariff = null;
		switch (TariffEnum.valueOf(tariffType.toUpperCase().replace('-', '_'))) {
		case FIX_INTERNET_PACK:
			tariff = new FixInternetPack();
			((FixInternetPack) tariff)
					.setOverSizePrice(new BigDecimal(reader.getAttributeValue(null, OVER_SIZE_PRICE.getValue())));
			String fee = reader.getAttributeValue(null, ADDITIONAL_I_NET_FEE.getValue());
			if (fee == null || fee.isEmpty()) {
				((FixInternetPack) tariff).setAdditionalINetFee(BigDecimal.ZERO);
			} else {
				((FixInternetPack) tariff).setAdditionalINetFee(new BigDecimal(fee));
			}
			break;
		case PER_MB_INTERNET:
			tariff = new PerMbInternet();
			break;
		default:
			LOG.log(Level.ERROR, "\nUnknown element in tag: " + tariffType);
			throw new XMLStreamException("\nUnknown element in tag: " + tariffType);
		}
		tariff.setName(reader.getAttributeValue(null, NAME.getValue()));
		tariff.setOperatorName(fromValue(reader.getAttributeValue(null, OPERATOR_NAME.getValue())));
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (TariffEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
				case PAYROLL:
					tariff.setPayroll(new BigDecimal(getXMLText(reader)));
					break;
				case SMS_PRICE:
					tariff.setSmsPrice(new BigDecimal(getXMLText(reader)));
					break;
				case CALL_PRICES:
					tariff.setCallPrices(getXMLCallPrices(reader));
					break;
				case PARAMETERS:
					tariff.setParameters(getXMLParameters(reader));
					break;
				case I_NET_PACK_SIZE:
					((FixInternetPack) tariff).setINetPackSize(new BigInteger(getXMLText(reader)));
					break;
				case PER_MB_PRICE:
					((PerMbInternet) tariff).setPerMbPrice(new BigDecimal(getXMLText(reader)));
					break;
				default:
					LOG.log(Level.ERROR, "\nUnknown element in tag tag: " + name);
					throw new XMLStreamException("\nUnknown element in tag tag: " + name);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (FIX_INTERNET_PACK.getValue().equals(name) || PER_MB_INTERNET.getValue().equals(name)) {
					return tariff;
				}
				break;
			}
		}
		LOG.log(Level.ERROR, "\nUnknown element in tag Tafiff");
		throw new XMLStreamException("\nUnknown element in tag Tafiff");
	}

	private Parameters getXMLParameters(XMLStreamReader reader) throws XMLStreamException {
		Parameters parameters = new Parameters();
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (TariffEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
				case NUMBER_FAVORITES:
					parameters.setNumberFavorites(new BigInteger(getXMLText(reader)));
					break;
				case TARIFFING:
					parameters.setTariffing(getXMLText(reader));
					break;
				case INIT_PRICE:
					parameters.setInitPrice(new BigDecimal(getXMLText(reader)));
					break;
				default:
					LOG.log(Level.ERROR, "\nUnknown element in tag: " + name);
					throw new XMLStreamException("\nUnknown element in tag: " + name);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(PARAMETERS.getValue())) {
					return parameters;
				}
				break;
			}
		}
		LOG.log(Level.ERROR, "\nUnknown element in tag Parameters");
		throw new XMLStreamException("\nUnknown element in tag Parameters");
	}

	private CallPrices getXMLCallPrices(XMLStreamReader reader) throws XMLStreamException {
		CallPrices callPrices = new CallPrices();
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (TariffEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
				case INNER_CALL_PRICE:
					callPrices.setInnerCallPrice(new BigDecimal(getXMLText(reader)));
					break;
				case OUT_CALL_PRICE:
					callPrices.setOutCallPrice(new BigDecimal(getXMLText(reader)));
					break;
				case LAND_LINE_CALL_PRICE:
					callPrices.setLandLineCallPrice(new BigDecimal(getXMLText(reader)));
					break;
				default:
					LOG.log(Level.ERROR, "\nUnknown element in tag: " + name);
					throw new XMLStreamException("\nUnknown element in tag: " + name);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(CALL_PRICES.getValue())) {
					return callPrices;
				}
				break;
			}
		}
		LOG.log(Level.ERROR, "Unknown element in tag CallPrices");
		throw new XMLStreamException("Unknown element in tag CallPrices");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
