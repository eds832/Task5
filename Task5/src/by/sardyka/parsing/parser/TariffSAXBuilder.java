package by.sardyka.parsing.parser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.sardyka.parsing.entity.TariffType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Set;

public class TariffSAXBuilder extends AbstractTariffBuilder {
	
		private static final Logger LOG = LogManager.getLogger(TariffSAXBuilder.class);
	    private Set<TariffType> tariffs;
	    private TariffHandler th;
	    private XMLReader reader;

	    public TariffSAXBuilder() {
	        th = new TariffHandler();
	        try {
	            reader = XMLReaderFactory.createXMLReader();
	            reader.setContentHandler(th);
	        } catch (SAXException e) {
	        	LOG.log(Level.FATAL, "\nSAX parsing error: " + e);
				throw new RuntimeException("\nSAX parsing error: " + e);
	        }
	    }

	    public Set<TariffType> getTariffs() {
			LOG.log(Level.INFO,
					"\ngetTariffs() is returning a Set with a size = " + ((tariffs == null) ? 0 : tariffs.size()));
	        return tariffs;
	    }

	    public void buildSetTariffs(String fileName) {
		LOG.log(Level.INFO, "\nbuildSetTariffs(fileName) is starting with fileName: " + fileName);
	        try {
	            reader.parse(fileName);
	        } catch (IOException e) {
				LOG.log(Level.ERROR, "\nFile error or I/O error: " + e);
				throw new RuntimeException("\nFile error or I/O error: " + e);
			} catch (SAXException e) {
				LOG.log(Level.ERROR, "\nParsing failure: " + e);
				throw new RuntimeException("\nParsing failure: " + e);
			}
	        tariffs = th.getTariffs();
	    }
	}
