package by.sardyka.parsing.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.sardyka.parsing.entity.CallPrices;
import by.sardyka.parsing.entity.FixInternetPack;
import by.sardyka.parsing.entity.Parameters;
import by.sardyka.parsing.entity.PerMbInternet;
import by.sardyka.parsing.entity.TariffType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static by.sardyka.parsing.entity.OperatorName.*;
import static by.sardyka.parsing.parser.TariffEnum.*;

public class TariffDOMBuilder extends AbstractTariffBuilder {

	private static final Logger LOG = LogManager.getLogger(TariffDOMBuilder.class);
	private Set<TariffType> tariffs;
	private DocumentBuilder docBuilder;

	public TariffDOMBuilder() {
		this.tariffs = new HashSet<TariffType>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOG.log(Level.ERROR, "\nParser configuration error: " + e);
		}
	}

	public Set<TariffType> getTariffs() {
		LOG.log(Level.INFO,
				"\ngetTariffs() is returning a Set with a size = " + ((tariffs == null) ? 0 : tariffs.size()));
		return tariffs;
	}

	public void buildSetTariffs(String fileName) {
		LOG.log(Level.INFO, "\nbuildSetTariffs(fileName) is starting with fileName: " + fileName);
		Document doc;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList fixIntrnetPackList = root.getElementsByTagName(FIX_INTERNET_PACK.getValue());
			for (int i = 0; i < fixIntrnetPackList.getLength(); i++) {
				Element tariffElement = (Element) fixIntrnetPackList.item(i);
				TariffType tariff = buildFixInternetPack(tariffElement);
				tariffs.add(tariff);
			}

			NodeList perMbInternetList = root.getElementsByTagName(PER_MB_INTERNET.getValue());
			for (int i = 0; i < perMbInternetList.getLength(); i++) {
				Element tariffElement = (Element) perMbInternetList.item(i);
				TariffType tariff = buildPerMbInternet(tariffElement);
				tariffs.add(tariff);
			}
		} catch (IOException e) {
			LOG.log(Level.ERROR, "\nFile error or I/O error: " + e);
			throw new RuntimeException("\nFile error or I/O error: " + e);
		} catch (SAXException e) {
			LOG.log(Level.ERROR, "\nParsing failure: " + e);
			throw new RuntimeException("\nParsing failure: " + e);
		}
	}

	private TariffType fillTariff(TariffType tariff, Element tariffElement) {
		tariff.setName(tariffElement.getAttribute(NAME.getValue()));
		tariff.setOperatorName(fromValue(tariffElement.getAttribute(OPERATOR_NAME.getValue())));
		tariff.setPayroll(new BigDecimal(getElementTextContent(tariffElement, PAYROLL.getValue())));
		tariff.setSmsPrice(new BigDecimal(getElementTextContent(tariffElement, SMS_PRICE.getValue())));
		CallPrices callPrices = new CallPrices();
		callPrices.setInnerCallPrice(new BigDecimal(getElementTextContent(tariffElement, INNER_CALL_PRICE.getValue())));
		callPrices.setOutCallPrice(new BigDecimal(getElementTextContent(tariffElement, OUT_CALL_PRICE.getValue())));
		callPrices.setLandLineCallPrice(
				new BigDecimal(getElementTextContent(tariffElement, LAND_LINE_CALL_PRICE.getValue())));
		tariff.setCallPrices(callPrices);
		Parameters parameters = new Parameters();
		parameters.setInitPrice(new BigDecimal(getElementTextContent(tariffElement, INIT_PRICE.getValue())));
		parameters
				.setNumberFavorites(new BigInteger(getElementTextContent(tariffElement, NUMBER_FAVORITES.getValue())));
		parameters.setTariffing(getElementTextContent(tariffElement, TARIFFING.getValue()));
		tariff.setParameters(parameters);
		return tariff;
	}

	private TariffType buildFixInternetPack(Element tariffElement) {
		TariffType tariff = new FixInternetPack();
		fillTariff(tariff, tariffElement);
		((FixInternetPack) tariff)
				.setINetPackSize(new BigInteger(getElementTextContent(tariffElement, I_NET_PACK_SIZE.getValue())));
		String fee = tariffElement.getAttribute(ADDITIONAL_I_NET_FEE.getValue());
		if (fee == null || fee.isEmpty()) {
			((FixInternetPack) tariff).setAdditionalINetFee(BigDecimal.ZERO);
		} else {
			((FixInternetPack) tariff).setAdditionalINetFee(new BigDecimal(fee));
		}
		((FixInternetPack) tariff)
				.setOverSizePrice(new BigDecimal(tariffElement.getAttribute(OVER_SIZE_PRICE.getValue())));
		return tariff;
	}

	private TariffType buildPerMbInternet(Element tariffElement) {
		TariffType tariff = new PerMbInternet();
		fillTariff(tariff, tariffElement);
		((PerMbInternet) tariff)
				.setPerMbPrice((new BigDecimal(getElementTextContent(tariffElement, PER_MB_PRICE.getValue()))));
		return tariff;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
