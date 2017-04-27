package test.by.sardyka.parsing.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import by.sardyka.parsing.parser.TariffSAXBuilder;

@RunWith(Parameterized.class)
public class TariffSAXBuilderTest {

	private static File file;
	private TariffSAXBuilder builder;
	private String source;
	private String correctOutput;

	public TariffSAXBuilderTest(String source, String correctOutput) {
		super();
		this.source = source;
		this.correctOutput = correctOutput;
	}

	@Parameters
	public static Collection<Object[]> LexemeParserTestValue() {
		Object[][] obj = new Object[][] { { "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<tariffs xmlns=\"http://www.example.com/tariffs\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ " xsi:schemaLocation=\"http://www.example.com/tariffs tariff.xsd \">\n"
				+ "<fix-internet-pack name=\"Smart4\" operator-name=\"Velcom\"\n"
				+ " over-size-price=\"0.1\">\n"
				+ "<payroll>37.4</payroll>\n"
				+ "<call-prices>\n"
				+ "<inner-call-price>0.01</inner-call-price>\n"
				+ "<out-call-price>0.07</out-call-price>\n"
				+ "<land-line-call-price>0.07</land-line-call-price>\n"
				+ "</call-prices>\n"
				+ "<sms-price>0.04</sms-price>\n"
				+ "<parameters>\n"
				+ "<number-favorites>5</number-favorites>\n"
				+ "<tariffing>minute</tariffing>\n"
				+ "<init-price>10.0</init-price>\n"
				+ "</parameters>\n"
				+ "<i-net-pack-size>5000</i-net-pack-size>\n"
				+ "</fix-internet-pack>\n"
				+ "</tariffs>",

					"[\n"
				+ "FixInternetPack:\n"
				+ "\tname=Smart4, operatorName=VELCOM, payroll=37.4,\n"
				+ "\tCallPrices [innerCallPrice=0.01, outCallPrice=0.07, landLineCallPrice=0.07], smsPrice=0.04,\n"
				+ "\tParameters [numberFavorites=5, tariffing=minute, initPrice=10.0],\n"
				+ "\tiNetPackSize=5000, additionalINetFee=0, overSizePrice=0.1]" },
			{"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<tariffs xmlns=\"http://www.example.com/tariffs\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
					+ " xsi:schemaLocation=\"http://www.example.com/tariffs tariff.xsd \">\n"
					+ "<per-mb-internet name=\"Free0\" operator-name=\"Life\">\n"
					+ "<payroll>3.0</payroll>\n"
					+ "<call-prices>\n"
					+ "<inner-call-price>0.05</inner-call-price>\n"
					+ "<out-call-price>0.25</out-call-price>\n"
					+ "<land-line-call-price>0.25</land-line-call-price>\n"
					+ "</call-prices>\n"
					+ "<sms-price>0.025</sms-price>\n"
					+ "<parameters>\n"
					+ "<number-favorites>10</number-favorites>\n"
					+ "<tariffing>12seconds</tariffing>\n"
					+ "<init-price>3.3</init-price>\n"
					+ "</parameters>\n"
					+ "<per-mb-price>0.4</per-mb-price>\n"
					+ "</per-mb-internet>\n"
					+ "</tariffs>",
					
					"[\n"
					+"PerMbInternet:\n"
					+ "\tname=Free0, operatorName=LIFE, payroll=3.0,\n"
					+ "\tCallPrices [innerCallPrice=0.05, outCallPrice=0.25, landLineCallPrice=0.25], smsPrice=0.025,\n"
					+ "\tParameters [numberFavorites=10, tariffing=12seconds, initPrice=3.3],\n"
					+ "\tperMbPrice=0.4]"} };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initValidator() throws IOException {
		file = folder.newFile("tariffs.xml");
		FileWriter fw = new FileWriter(file);
		fw.write(source);
		fw.flush();
		fw.close();
		builder = new TariffSAXBuilder();
		builder.buildSetTariffs(file.getAbsolutePath());
	}

	@Test
	public void buildSetTariffsTest() {
		boolean actual = builder.getTariffs().toString().equals(correctOutput);
		assertTrue("Method buildSetTariffs works incorrectly", actual);
	}

	@Test(expected = RuntimeException.class)
	public void buildEmptySourceStringDataTest() throws RuntimeException {
		Object expected = null;
		builder.buildSetTariffs("");
		Object actual = null;
		assertEquals("For empty source string there aren't RuntimException", expected, actual);
	}

	@Test(expected = RuntimeException.class)
	public void buildNullSourceStringDataTest() throws RuntimeException {
		Object expected = null;
		builder.buildSetTariffs(null);
		Object actual = null;
		assertEquals("For null source string there aren't RuntimException", expected, actual);
	}

}
