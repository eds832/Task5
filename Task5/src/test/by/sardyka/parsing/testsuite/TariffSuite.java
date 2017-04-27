package test.by.sardyka.parsing.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.by.sardyka.parsing.validator.XMLDocumentValidatorTest;
import test.by.sardyka.parsing.parser.TariffStAXBuilderTest;
import test.by.sardyka.parsing.parser.TariffSAXBuilderTest;
import test.by.sardyka.parsing.parser.TariffDOMBuilderTest;
import test.by.sardyka.parsing.datawriter.DataWriterTest;

@Suite.SuiteClasses({ XMLDocumentValidatorTest.class, TariffStAXBuilderTest.class,
	TariffSAXBuilderTest.class, TariffDOMBuilderTest.class, DataWriterTest.class })
@RunWith(Suite.class)
public class TariffSuite {
}
