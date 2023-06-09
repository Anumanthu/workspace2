package src.demo;


import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.apache.juneau.xml.XmlSerializer;

public class SerializationDeserializationByNaveenAutomationLabs {

    public static void main(String[] args) throws SerializeException, ParseException {

        // serialization: This is provided by Naveen automation labs
        // pojo to json:
        JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
        String[] sellerNames = {"Neon Enterprise", "ABC Software", "XYZ IT solutions"};
        ProductByNaveenAutomationLabs product = new ProductByNaveenAutomationLabs("MAcbookPro", 1000, "White", sellerNames);
        String json = jsonSerializer.serialize(product);
        System.out.println(json);

        // pojo to xml:
        XmlSerializer xmlSerializer = XmlSerializer.DEFAULT_NS_SQ_READABLE;
        String xml = xmlSerializer.serialize(product);
        System.out.println(xml);

        // pojo to HTML:
        HtmlSerializer htmlSerializer = HtmlSerializer.DEFAULT_SQ_READABLE;
        String html = htmlSerializer.serialize(product);
        System.out.println(html);

        // deserialization:
        // JSON to POJO:
        JsonParser jsonParser = JsonParser.DEFAULT;
        String jsonVal = "{\n" + "	\"color\": \"White\",\n" + "	\"name\": \"MAcbookPro\",\n"
                + "	\"price\": 1000,\n" + "	\"sellerNames\": [\n" + "		\"Neon Enterprise\",\n"
                + "		\"ABC Software\",\n" + "		\"XYZ IT solutions\"\n" + "	]\n" + "}";
        ProductByNaveenAutomationLabs pro = jsonParser.parse(jsonVal, ProductByNaveenAutomationLabs.class);
        System.out.println(pro.getColor());
    }

}
