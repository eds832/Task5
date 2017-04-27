package by.sardyka.parsing.validator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class XMLDocumentValidator {
	private static final Logger LOG = LogManager.getLogger(XMLDocumentValidator.class);
	private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
	
	public static boolean validate(String fileName, String schemaName) {
		
		if(fileName == null || schemaName == null || fileName.isEmpty() || schemaName.isEmpty()) {
			LOG.log(Level.ERROR, "\nWrong fileName: " + fileName + " or schemaName: " + schemaName);
			throw new RuntimeException("\nWrong fileName: " + fileName + " or schemaName: " + schemaName);
		}
		boolean valid = false;
		Schema schema = null;
		SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
		try {
			schema = factory.newSchema(new File(schemaName));
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(fileName);
            validator.validate(source);
            valid = true;
            LOG.log(Level.INFO, "\n" + fileName + " is valid.");
		} catch (SAXException e) {
			LOG.log(Level.ERROR, "\n" + fileName + " SAX error: " + e.getMessage());
		} catch (IOException e) {
			LOG.log(Level.ERROR, "\nI/O error: " + e.getMessage());
		}
		return valid;
	}
}
