package ch.softenvironment.util.test;
/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import ch.softenvironment.util.Tracer;
import ch.softenvironment.util.DOMUtils;

import junit.framework.TestCase;
/**
 * 
 * TestCase for DOMUtils.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-10-28 08:16:33 $
 * @see ch.softenvironment.util.DOMUtils
 */
public class DOMUtilsTestCase extends TestCase {
    private static String text = "<���{}[]��� �� @ ����'$�\" &  \n\r\t>";
    private static String path = System.getProperty("java.io.tmpdir");

    private DocumentBuilder docBuilder = null;
    
    public void setUp() {
        try {
            Tracer.start(Tracer.ALL);
            
            docBuilder = DOMUtils.createDocumentBuilder(false);
        } catch(ParserConfigurationException e) {
            fail(e.getLocalizedMessage());
        }
    }
    public void tearDown() {
        Tracer.getInstance().stop();
    }
    /**
     * Example Test-Document:
     * 
        <?xml version="1.0" encoding="UTF-8"?>
        <soap:Envelope 
            xmlns:soap="http://www.w3.org/2001/12/soap-envelope"    soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
        <soap:Header><TargetApp soap:mustUnderstand="1"/></soap:Header>
        <soap:Body>
            <!--START Data area-->
            <HOST>Sandflyer</HOST>
            <DATA>[@see text]</DATA>
            <soap:Fault>
                <soap:faultstring>Dummy Error</soap:faultstring>
            </soap:Fault>
        </soap:Body>
        </soap:Envelope>
    */
    private Document createDomDocument() {
        Document doc = docBuilder.newDocument();
        
        Element root = doc.createElement("soap:Envelope");
        doc.appendChild(root);
        root.setAttribute("xmlns:soap", "http://www.w3.org/2001/12/soap-envelope");
        root.setAttribute("soap:encodingStyle", "http://www.w3.org/2001/12/soap-encoding"); 
            
        Element header = doc.createElement("soap:Header");
        root.appendChild(header);
        Element child = doc.createElement("TargetApp");
        header.appendChild(child);
        child.setAttribute("soap:mustUnderstand", "1");
                    
        Element body = doc.createElement("soap:Body");
        root.appendChild(body);
        body.appendChild(doc.createComment("START Data area"));
        
        // chreate children
        child = doc.createElement("HOST");
        body.appendChild(child);
        child.appendChild(doc.createTextNode("Sandflyer")); //XmlUtils.encodeUTF8("Sandflyer")));

        child = doc.createElement("DATA");
        body.appendChild(child);
        try {
            child.appendChild(doc.createTextNode(DOMUtils.encodeUTF8(text)));
        } catch(UnsupportedEncodingException e) {
            fail(e.getLocalizedMessage());
        }

        Element fault = doc.createElement("soap:Fault");
        body.appendChild(fault);
        
        child = doc.createElement("soap:faultstring");
        fault.appendChild(child);
        child.appendChild(doc.createTextNode("Dummy Error")); //XmlUtils.encodeUTF8("Dummy Error")));

        return doc;
    }
    
    private static String getTagValue(Document doc, String tag) {
        NodeList nodes = doc.getElementsByTagName(tag);
        Node node = nodes.item(0);  // there is only one entry expected
        Text result = (Text)node.getFirstChild();
        return result.getNodeValue();
    }
    
    public void testEncodeUTF8() {
        try {    
            assertTrue("<".equals(DOMUtils.encodeUTF8("<"))); // mask for XML additionally
            assertFalse("�".equals(DOMUtils.encodeUTF8("�")));
            assertTrue((new String("�".getBytes("UTF-8")).equals(DOMUtils.encodeUTF8("�"))));
            
            String encoded = DOMUtils.encodeUTF8(text);
            assertFalse(text.equals(encoded));
            assertFalse(text.equals(new String(encoded)));
            
            String decoded = new String(encoded.getBytes(), "UTF-8");
            assertTrue(text.equals(decoded));
            assertTrue(decoded.charAt(decoded.length()-1) == '>');
        } catch(UnsupportedEncodingException e) {
            fail("text probably contains a non-encodable Character: " + e.getLocalizedMessage());
        }
    }
    public void testReadWrite() {
        String filename = path + "XmlUtilsTestCase.xml";
        try {
            FileWriter fw = new FileWriter(filename);
            DOMUtils.writeXML(createDomDocument(), fw);
            fw.flush();
            fw.close();
        } catch(Throwable e) {
            fail("writeXML: " + e.getLocalizedMessage());
        }
      
        try {
            Document doc = DOMUtils.read(filename, docBuilder);
            assertTrue("Sandflyer".equals(getTagValue(doc, "HOST")));
            System.out.println("DATA=" +getTagValue(doc, "DATA"));
            assertTrue(text.equals(getTagValue(doc, "DATA")));
            assertTrue("Dummy Error".equals(getTagValue(doc, "soap:faultstring")));
        } catch(Throwable e) {
            fail("read: " + e.getLocalizedMessage());
        }
    }
}