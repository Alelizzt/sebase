package ch.softenvironment.util.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ch.softenvironment.util.NlsUtils;

/**
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-08-26 10:16:19 $
 */
public class NlsUtilsTestCase extends junit.framework.TestCase {
/**
 * StringUtilsTestCase constructor comment.
 * @param name java.lang.String
 */
public NlsUtilsTestCase(String name) {
	super(name);
}
/**
 * Depends on current Locale.
 */
public void testDate() {
    GregorianCalendar cal = new java.util.GregorianCalendar(2003, java.util.Calendar.JANUARY, 25);
    String date = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT).format(cal.getTime());
	assertTrue("NlsUtils", date.equals(NlsUtils.formatDate(cal)));
}
/**
 * 
 */
public void testFormatMessage() {
	assertTrue("NlsUtils#testFormatMessage", "This is 17".equals(NlsUtils.formatMessage("This is {0}", 17)));
	assertTrue("NlsUtils#testFormatMessage", "The langugage is english".equals(NlsUtils.formatMessage("The langugage is {0}", "english")));
	Object[] tokens = { new Integer(3), "messages" };
	assertTrue("NlsUtils#testFormatMessage", "These are 3 messages".equals(NlsUtils.formatMessage("These are {0} {1}", tokens)));
}
public void testGetTime24Hours() {
    assertTrue("NlsUtils#formatTime24Hours", NlsUtils.formatTime24Hours((new GregorianCalendar(2004, Calendar.APRIL, 26, 18, 17)).getTime(), false).equals("18:17"));
}
}