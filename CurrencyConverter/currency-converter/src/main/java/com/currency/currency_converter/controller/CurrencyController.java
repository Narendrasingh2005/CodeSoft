package com.currency.currency_converter.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/currency")
@CrossOrigin("*")
public class CurrencyController {

    @GetMapping("/convert")
    public Map<String, Object> convertCurrency(@RequestParam String from,
                                               @RequestParam String to,
                                               @RequestParam double amount) {

        String url = "https://api.frankfurter.app/latest?from=" + from + "&to=" + to;

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        double rate = rates.get(to);

        double converted = amount * rate;

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("from", from);
        result.put("to", to);
        result.put("rate", rate);
        result.put("amount", amount);
        result.put("convertedAmount", converted);

        return result;
    }
}
