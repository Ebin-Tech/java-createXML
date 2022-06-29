package sample_csv_project;
import java.io.File;
import java.util.ArrayList;
import sample_csv_project.ReadCSV;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 

public class CreateXMLFileJava {
	public static final String xmlFilePath = "C:\\Users\\EbinBThomas\\Desktop\\xmlfile.xml";
	 
    public static void main(String argv[]) {
 
        try {
        	
 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("node");
            document.appendChild(root);
            Attr attr = document.createAttribute("path");
            attr.setValue(" ");
            root.setAttributeNode(attr);
            // employee element
            ArrayList<TranslationRule>arrayList = ReadCSV.main();            
            for(TranslationRule translationRule :arrayList) {
            Element properties = document.createElement("properties");
            root.appendChild(properties);
            Attr attri1 = document.createAttribute("inherit");
            attri1.setValue(translationRule.getInherit());
            properties.setAttributeNode(attri1);
            Attr attri2 = document.createAttribute("name");
            attri2.setValue(translationRule.getName());
            properties.setAttributeNode(attri2);
            Attr attri3 = document.createAttribute("translate");
            attri3.setValue(translationRule.getTranslate());
            properties.setAttributeNode(attri3);
            Attr attri4 = document.createAttribute("updationDesignationLanguage");
            attri4.setValue("false");
            properties.setAttributeNode(attri4);
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
