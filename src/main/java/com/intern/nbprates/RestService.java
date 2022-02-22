package com.intern.nbprates;

import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;
import java.util.Currency;
import java.math.BigDecimal;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService() {
        restTemplate = new RestTemplate();
    }

    public GoldResponse[] getGoldPrices() {
        String url = "http://api.nbp.pl/api/cenyzlota/last/14";
        return restTemplate.getForObject(url, GoldResponse[].class);
    }

    public ExchangeRateResponse getExchangeRates(Currency currencyCode) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/last/5";
        return restTemplate.getForObject(url, ExchangeRateResponse.class);
    }
}

class GoldResponse {
    public BigDecimal cena;
}

class ExchangeRateResponse {
    public String currency;
    public Currency code;
    public Rate[] rates;
}

class Rate {
    public String effectiveDate;
    public BigDecimal mid;
}