package test.by.sardyka.parsing.datawriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.parsing.datawriter.DataWriter;

@RunWith(Parameterized.class)
public class DataWriterTest {

	private static File file;
	private String output;

	public DataWriterTest(String output) {
		super();
		this.output = output;
	}

	@Parameters
	public static Collection<Object[]> DataWriterTestValue() {
		Object[][] obj = new Object[][] {
				{ "data/tariffs.xml is valid = true"
						+ "\r\n---------------------------- DOM ----------------------------------"
						+ "\r\n[\r\nFixInternetPack:"
						+ "\r\n\tname=Online1, operatorName=MTS, payroll=15.5," 
						+ "\r\n\tCallPrices [innerCallPrice=0.03, outCallPrice=0.11, landLineCallPrice=0.11], smsPrice=0.03,"
						+ "\r\n\tParameters [numberFavorites=7, tariffing=minute, initPrice=9.9],"
						+ "\r\n\tiNetPackSize=700, additionalINetFee=0, overSizePrice=0.07," 
						+ "\r\nPerMbInternet:"
						+ "\r\n\tname=Free1, operatorName=LIFE, payroll=5.1,"
						+ "\r\n\tCallPrices [innerCallPrice=0.04, outCallPrice=0.15, landLineCallPrice=0.15], smsPrice=0.025,"
						+ "\r\n\tParameters [numberFavorites=10, tariffing=12seconds, initPrice=3.3],"
						+ "\r\n\tperMbPrice=0.3]" },
				{ "---------------------------- SAX ----------------------------------\r\n["
						+ "\r\nFixInternetPack:"
						+ "\r\n\tname=Smart3, operatorName=VELCOM, payroll=27.3,"
						+ "\r\nCallPrices [innerCallPrice=0.02, outCallPrice=0.09, landLineCallPrice=0.09], smsPrice=0.04,"
						+ "\r\n\tParameters [numberFavorites=5, tariffing=minute, initPrice=10.0],"
						+ "\r\n\tiNetPackSize=3000, additionalINetFee=0, overSizePrice=0.1,"
						+ "\r\nFixInternetPack:"
						+ "\r\n\tname=Biggest, operatorName=LIFE, payroll=11.7,"
						+ "\r\n\tCallPrices [innerCallPrice=0.01, outCallPrice=0.05, landLineCallPrice=0.05], smsPrice=0.025,"
						+ "\r\n\tParameters [numberFavorites=10, tariffing=12seconds, initPrice=9.3],"
						+ "\r\n\tiNetPackSize=7000, additionalINetFee=27.7, overSizePrice=0.05]" } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataWriter() throws IOException {
		file = folder.newFile("out.txt");
	}

	@Test
	public void writeStringDataTest() throws IOException {
		DataWriter.writeData(file.getAbsolutePath(), output);
		String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		boolean actual = text.equals(output);
		assertTrue("writeData writes a string incorrectly", actual);
	}

	@Test
	public void writeNullDataTest() throws RuntimeException {
		try {
			DataWriter.writeData(file.getAbsolutePath(), null);
			fail("writeNullDataTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty data to write: null", e.getMessage());
		}
	}

	@Test
	public void writeEmptyDataTest() throws RuntimeException {
		try {
			DataWriter.writeData(file.getAbsolutePath(), "");
			fail("writeEmptyDataTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty data to write: ", e.getMessage());
		}
	}

	@Test
	public void writeNullFileNameTest() throws RuntimeException {
		try {
			DataWriter.writeData(null, output);
			fail("writeNullFileNameTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty file name to write: null", e.getMessage());
		}
	}

	@Test
	public void writeEmptyFileNameTest() throws RuntimeException {
		try {
			DataWriter.writeData("", output);
			fail("writeEmptyFileNameTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty file name to write: ", e.getMessage());
		}
	}

}
