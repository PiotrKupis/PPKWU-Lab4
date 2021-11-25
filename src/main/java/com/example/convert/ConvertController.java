package com.example.convert;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {

    private final String API = "http://localhost:8080/string/analyze/";

    @GetMapping("convert/{text}/{inFormat}/{outFormat}")
    public String convert(@PathVariable("text") String text,
        @PathVariable("inFormat") String inFormat, @PathVariable("outFormat") String outFormat) {
        return "ok";
    }
}
