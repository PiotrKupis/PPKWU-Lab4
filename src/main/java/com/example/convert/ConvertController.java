package com.example.convert;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConvertController {

    private final String API = "http://localhost:8081/analyze/";

    @GetMapping("convert/analyze/{text}/{indirectFormat}/{returnFormat}")
    public String analyze(@PathVariable("text") String text,
        @PathVariable("indirectFormat") String indirectFormat,
        @PathVariable("returnFormat") String returnFormat) {
        Response response;

        System.out.println(text);
        RestTemplate restTemplate = new RestTemplate();

        String path = String.format("%s/%s/%s", API, text, indirectFormat);
        System.out.println(path);
        String data = restTemplate.getForObject(path, String.class);

        System.out.println(data);

        switch (indirectFormat) {
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

        switch (returnFormat) {
            case "XML":
                return "<analyze><uppercase>" + response.getUppercase() + "</uppercase>"
                    + "<lowercase>" + response.getLowercase() + "</lowercase>"
                    + "<specialChars>" + response.getSpecialChars() + "</specialChars>"
                    + "<numbers>" + response.getNumbers() + "</numbers>"
                    + "<combination>" + response.getCombination() + "</combination></analyze>";
            case "JSON":
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("upperCase", response.getUppercase());
                jsonObject.put("lowerCase", response.getLowercase());
                jsonObject.put("specialChars", response.getSpecialChars());
                jsonObject.put("numbers", response.getNumbers());
                jsonObject.put("combination", response.getCombination());
                return jsonObject.toString();
            case "TXT":
                return response.getUppercase() + "\n" + response.getLowercase() + "\n"
                    + response.getSpecialChars() + "\n" + response.getNumbers() + "\n"
                    + response.getCombination() + "\n";
            case "CSV":
                return "uppercase,lowercase,number,specialChars,combination\n"
                    + response.getUppercase()
                    + "," + response.getLowercase() + "," + response.getSpecialChars() + ","
                    + response.getNumbers() + "," + response.getCombination() + ",";
            default:
                return "Incorrect format";
        }
    }


    @PostMapping("convert/{inFormat}/{outFormat}")
    public String convert(@PathVariable("inFormat") String inFormat,
        @PathVariable("outFormat") String outFormat, @RequestBody Text text) {
        Response response;
        String data = text.getText();

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

        switch (outFormat) {
            case "XML":
                return "<analyze><uppercase>" + response.getUppercase() + "</uppercase>"
                    + "<lowercase>" + response.getLowercase() + "</lowercase>"
                    + "<specialChars>" + response.getSpecialChars() + "</specialChars>"
                    + "<numbers>" + response.getNumbers() + "</numbers>"
                    + "<combination>" + response.getCombination() + "</combination></analyze>";
            case "JSON":
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("upperCase", response.getUppercase());
                jsonObject.put("lowerCase", response.getLowercase());
                jsonObject.put("specialChars", response.getSpecialChars());
                jsonObject.put("numbers", response.getNumbers());
                jsonObject.put("combination", response.getCombination());
                return jsonObject.toString();
            case "TXT":
                return response.getUppercase() + "\n" + response.getLowercase() + "\n"
                    + response.getSpecialChars() + "\n" + response.getNumbers() + "\n"
                    + response.getCombination() + "\n";
            case "CSV":
                return "uppercase,lowercase,number,specialChars,combination\n"
                    + response.getUppercase()
                    + "," + response.getLowercase() + "," + response.getSpecialChars() + ","
                    + response.getNumbers() + "," + response.getCombination() + ",";
            default:
                return "Incorrect format";
        }
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
