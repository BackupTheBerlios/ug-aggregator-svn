package it.xpug.aggregator;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for it.xpug.aggregator");
		//$JUnit-BEGIN$
		suite.addTestSuite(ChronologicalTest.class);
		suite.addTestSuite(NewsFormatTest.class);
		suite.addTestSuite(NewsValidatorTest.class);
		suite.addTestSuite(FakeNewsListTest.class);
		suite.addTestSuite(JSPDesignTest.class);
		suite.addTestSuite(InsertGuiNewsTest.class);
		suite.addTestSuite(NewsFilterTest.class);
		suite.addTestSuite(NewsFileWriterTest.class);
		suite.addTestSuite(NewsTest.class);
		suite.addTestSuite(XpugServletTest.class);
		suite.addTestSuite(FileFinderTest.class);
		//$JUnit-END$
		return suite;
	}

}
