package com.example.convert;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {

    private final String API = "http://localhost:8080/string/analyze/";

    @PostMapping("convert/{inFormat}/{outFormat}")
    public String convert(@PathVariable("inFormat") String inFormat,
        @PathVariable("outFormat") String outFormat, @RequestBody Text text) {

        Response response;
        String data = text.getText();
        System.out.println(data);

        switch (inFormat) {
            case "XML":
                response = convertXmlToResponse(data);
                break;
            case "JSON":
                response = convertJsonToResponse(data);
                break;
            case "TXT":
                response = convertTxtToResponse(data);
                break;
            case "CSV":
                response = convertCsvToResponse(data);
                break;
            default:
                return "Incorrect format";
        }

        System.out.println(response);

        if (outFormat.equals("XML")) {
            return "<analyze><uppercase>" + response.getUppercase() + "</uppercase>"
                + "<lowercase>" + response.getLowercase() + "</lowercase>"
                + "<specialChars>" + response.getSpecialChars() + "</specialChars>"
                + "<numbers>" + response.getNumbers() + "</numbers>"
                + "<combination>" + response.getCombination() + "</combination></analyze>";
        } else if (outFormat.equals("JSON")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("upperCase", response.getUppercase());
            jsonObject.put("lowerCase", response.getLowercase());
            jsonObject.put("specialChars", response.getSpecialChars());
            jsonObject.put("numbers", response.getNumbers());
            jsonObject.put("combination", response.getCombination());
            return jsonObject.toString();
        }

        return "ok";
    }

    private Response convertXmlToResponse(String data) {
        Response response = new Response();
        response.setUppercase(data.substring(data.indexOf("<uppercase>") + "<uppercase>".length(),
            data.indexOf("</uppercase>")));
        response.setLowercase(data.substring(data.indexOf("<lowercase>") + "<lowercase>".length(),
            data.indexOf("</lowercase>")));
        response.setSpecialChars(data.substring(
            data.indexOf("<specialChars>") + "<specialChars>".length(),
            data.indexOf("</specialChars>")));
        response.setNumbers(data.substring(data.indexOf("<numbers>") + "<numbers>".length(),
            data.indexOf("</numbers>")));
        response.setCombination(
            data.substring(data.indexOf("<combination>") + "<combination>".length(),
                data.indexOf("</combination>")));
        return response;
    }

    private Response convertTxtToResponse(String data) {
        Response response = new Response();
        String[] rows = data.split("\n");
        response.setUppercase(rows[0]);
        response.setLowercase(rows[1]);
        response.setSpecialChars(rows[2]);
        response.setNumbers(rows[3]);
        response.setCombination(rows[4]);
        return response;
    }

    private Response convertJsonToResponse(String data) {
        Response response = new Response();
        data = data.replaceAll("\\\\", "");
        JSONObject jsonObject = new JSONObject(data);
        response.setUppercase(jsonObject.get("uppercase").toString());
        response.setLowercase(jsonObject.get("lowercase").toString());
        response.setSpecialChars(jsonObject.get("specialChars").toString());
        response.setNumbers(jsonObject.get("numbers").toString());
        response.setCombination(jsonObject.get("combination").toString());
        return response;
    }

    private Response convertCsvToResponse(String data) {
        Response response = new Response();
        String[] rows = data.split("\n");
        String[] values = rows[1].split(",");
        response.setUppercase(values[0]);
        response.setLowercase(values[1]);
        response.setSpecialChars(values[2]);
        response.setNumbers(values[3]);
        response.setCombination(values[4]);
        return response;
    }
}
