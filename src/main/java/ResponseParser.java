import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.w3c.dom.CharacterData;
import org.xml.sax.InputSource;

public class ResponseParser {
  /**
   * This method is doing the heavy lifting of parsing the XML DOM elements and then building all
   * our Truck objects and passing them to our FoodTruckLister.
   */
  public void buildResultsList(String response) {
    try {
      FoodTruckService foodTruckService = new FoodTruckService();
      DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      InputSource is = new InputSource();
      is.setCharacterStream(new StringReader(response));
      Document doc = db.parse(is);
      NodeList nodes = doc.getElementsByTagName("row");

      for (int i = 0; i < nodes.getLength(); i++) {
        Element element = (Element) nodes.item(i);

        NodeList applicant = element.getElementsByTagName("applicant");
        Element line = (Element) applicant.item(0);
        String name = getCharacterDataFromDOMElement(line);
        NodeList location = element.getElementsByTagName("location");
        line = (Element) location.item(0);
        String address = getCharacterDataFromDOMElement(line);
        foodTruckService.truckBuilder(address, name);
      }
      foodTruckService.printList();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /* Utility method to get a string from the DOM element, requires a cast to CharacterData. */
  public static String getCharacterDataFromDOMElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }
}
