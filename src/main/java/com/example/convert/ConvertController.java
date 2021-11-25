package com.example.convert;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@RestController
public class ConvertController {

    private final String API = "http://localhost:8080/string/analyze/";

    @PostMapping("convert/{inFormat}/{outFormat}")
    public String convert(@PathVariable("inFormat") String inFormat,
        @PathVariable("outFormat") String outFormat, @RequestBody Text text)
        throws ParserConfigurationException, IOException, SAXException {

        String data = text.getText();
        Response response = new Response();

        String uppercase = "";
        String lowercase= "";
        String specialChars= "";
        String numbers= "";
        String combination= "";

        System.out.println(text);

        if (inFormat.equals("XML")) {
            uppercase = data.substring(data.indexOf("<uppercase>")+"<uppercase>".length(), data.indexOf("</uppercase>"));
            lowercase = data.substring(data.indexOf("<lowercase>")+"<lowercase>".length(), data.indexOf("</lowercase>"));
            specialChars = data.substring(data.indexOf("<specialChars>")+"<specialChars>".length(), data.indexOf("</specialChars>"));
            numbers = data.substring(data.indexOf("<numbers>")+"<numbers>".length(), data.indexOf("</numbers>"));
            combination = data.substring(data.indexOf("<combination>")+"<combination>".length(), data.indexOf("</combination>"));
        }

        System.out.println(uppercase);
        System.out.println(lowercase);
        System.out.println(numbers);
        System.out.println(specialChars);
        System.out.println(combination);

        return "ok";
    }
}
