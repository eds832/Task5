package test.by.sardyka.parsing.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import by.sardyka.parsing.validator.XMLDocumentValidator;

@RunWith(Parameterized.class)
public class XMLDocumentValidatorTest {

	private static File correctFile;
	private static File incorrectFile;
	private static File schema;
	private String correctSource;
	private String incorrectSource;
	private String schemaSource;

	public XMLDocumentValidatorTest(String correctSource, String incorrectSource, String schemaSource) {
		super();
		this.correctSource = correctSource;
		this.incorrectSource = incorrectSource;
		this.schemaSource = schemaSource;
	}

	@Parameters
	public static Collection<Object[]> LexemeParserTestValue() {
		Object[][] obj = new Object[][] { { "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<schema1 xmlns=\"http://www.example.org/schema1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "xsi:schemaLocation=\"http://www.example.org/schema1 schema1element.xsd \">\n"
				+ " <schema1-element attr1=\"control attr1\" attr2=\"10\">\n"
				+ "   <string1>control string</string1>\n"
				+ "   <number1>20</number1>\n"
				+ " </schema1-element>\n"
				+ "</schema1>",

				"<schema1 xmlns=\"http://www.example.org/schema1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
						+ "xsi:schemaLocation=\"http://www.example.org/schema1 schema1element.xsd \">\n"
						+ " <schema1-element attr1=\"control attr1\" attr2=\"10\">\n"
						+ "   <string1>control string</string1>\n"
						+ "   <number1></number1>\n"
						+ " </schema1-element>\n"
						+ "</schema1>",

				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.example.org/schema1\""
						+ " xmlns:tns=\"http://www.example.org/schema1\" elementFormDefault=\"qualified\">"
						+ "<element name=\"schema1-element\" type=\"tns:SchemaType\" abstract=\"false\"></element>"
						+ "<element name=\"schema1\">"
						+ " <complexType>"
						+ "  <sequence>"
						+ "   <element ref=\"tns:schema1-element\" minOccurs=\"1\" maxOccurs=\"unbounded\" />"
						+ "  </sequence>"
						+ " </complexType>"
						+ "</element>"
						+ "<complexType name=\"SchemaType\">"
						+ " <sequence>"
						+ "  <element name=\"string1\" type=\"string\"/>"
						+ "  <element name=\"number1\" type=\"nonNegativeInteger\" />"
						+ " </sequence>"
						+ " <attribute name=\"attr1\" type=\"string\" use=\"required\" />"
						+ " <attribute name=\"attr2\" type=\"nonNegativeInteger\" use=\"optional\" />"
						+ "</complexType>" + "</schema>" },
			{ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<schema1 xmlns=\"http://www.example.org/schema1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
					+ "xsi:schemaLocation=\"http://www.example.org/schema1 schema1element.xsd \">\n"
					+ " <schema1-element attr1=\"control attr1\" attr2=\"10\">\n"
					+ "   <string1>control string</string1>\n"
					+ "   <number1>20</number1>\n"
					+ " </schema1-element>\n"
					+ "</schema1>",

					"<schema1 xmlns=\"http://www.example.org/schema1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
							+ "xsi:schemaLocation=\"http://www.example.org/schema1 schema1element.xsd \">\n"
							+ " <schema1-element attr2=\"10\">\n"
							+ "   <string1>control string</string1>\n"
							+ "   <number1>20</number1>\n"
							+ " </schema1-element>\n"
							+ "</schema1>",

					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
							+ "<schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.example.org/schema1\""
							+ " xmlns:tns=\"http://www.example.org/schema1\" elementFormDefault=\"qualified\">"
							+ "<element name=\"schema1-element\" type=\"tns:SchemaType\" abstract=\"false\"></element>"
							+ "<element name=\"schema1\">"
							+ " <complexType>"
							+ "  <sequence>"
							+ "   <element ref=\"tns:schema1-element\" minOccurs=\"1\" maxOccurs=\"unbounded\" />"
							+ "  </sequence>"
							+ " </complexType>"
							+ "</element>"
							+ "<complexType name=\"SchemaType\">"
							+ " <sequence>"
							+ "  <element name=\"string1\" type=\"string\"/>"
							+ "  <element name=\"number1\" type=\"nonNegativeInteger\" />"
							+ " </sequence>"
							+ " <attribute name=\"attr1\" type=\"string\" use=\"required\" />"
							+ " <attribute name=\"attr2\" type=\"nonNegativeInteger\" use=\"optional\" />"
							+ "</complexType>" + "</schema>" } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initValidator() throws IOException {
		correctFile = folder.newFile("correct.xml");
		FileWriter fw = new FileWriter(correctFile);
		fw.write(correctSource);
		fw.flush();
		fw.close();
		incorrectFile = folder.newFile("incorrect.xml");
		fw = new FileWriter(incorrectFile);
		fw.write(incorrectSource);
		fw.flush();
		fw.close();
		schema = folder.newFile("schema1.xsd");
		fw = new FileWriter(schema);
		fw.write(schemaSource);
		fw.flush();
		fw.close();
	}

	@Test
	public void validateCorrectTest() {
		boolean actual = XMLDocumentValidator.validate(correctFile.getAbsolutePath(), schema.getAbsolutePath());
		assertTrue("Method validate works incorrectly for correct xml", actual);
	}

	@Test
	public void validateIncorrectTest() {
		boolean actual = XMLDocumentValidator.validate(incorrectFile.getAbsolutePath(), schema.getAbsolutePath());
		assertFalse("Method validate works incorrectly for incorrect xml", actual);
	}

	@Test(expected = RuntimeException.class)
	public void validateEmptySourceStringDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = XMLDocumentValidator.validate("", schema.getAbsolutePath());
		assertEquals("For empty source string there aren't RuntimException", expected, actual);
	}

	@Test(expected = RuntimeException.class)
	public void validateEmptySchemaStringDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = XMLDocumentValidator.validate(correctFile.getAbsolutePath(), "");
		assertEquals("For empty schema string there aren't RuntimException", expected, actual);
	}

	@Test(expected = RuntimeException.class)
	public void validateNullSourceStringDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = XMLDocumentValidator.validate(null, schema.getAbsolutePath());
		assertEquals("For null source string there aren't RuntimException", expected, actual);
	}

	@Test(expected = RuntimeException.class)
	public void validateNullSchemaStringDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = XMLDocumentValidator.validate(correctFile.getAbsolutePath(), null);
		assertEquals("For null schema string there aren't RuntimException", expected, actual);
	}
}
