package ch.softenvironment.util.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import ch.softenvironment.util.Tracer;

import junit.framework.TestCase;

/**
 * <Description>
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2006-06-29 22:31:57 $
 */
public class TracerTestCase extends TestCase {
    public void testLogFile() {
        try {
	        FileOutputStream stream = new FileOutputStream(System.getProperty("java.io.tmpdir") + "TracerTestCase.log", false);
	//      PrintWriter printer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"); 
	                
	        Tracer.start(new PrintStream(stream), Tracer.ALL);
	        Tracer.getInstance().developerError("do you see a developerError entry?");
	        Tracer.getInstance().stop();
        } catch(FileNotFoundException e) {
            fail("testLogFile: " + e.getLocalizedMessage());
        }
    }
}
