package com.intern.nbprates;

import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Service
public class RestService {

    private final RestTemplate restTemplate;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RestService() {
        this.restTemplate = new RestTemplate();
    }

    public GoldResponse[] getGoldPrices() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fortnightAgo = now.minusDays(14);

        String url = "http://api.nbp.pl/api/cenyzlota/" + dateFormat.format(fortnightAgo) + "/"
                + dateFormat.format(now);

        return this.restTemplate.getForObject(url, GoldResponse[].class);
    }

    public ExchangeRateResponse getExchangeRates(Currency currencyCode) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/last/5";
        return this.restTemplate.getForObject(url, ExchangeRateResponse.class);
    }
}

class GoldResponse {
    public String data;
    public double cena;

    @Override
    public String toString() {
        return "" + data + ": " + cena;
    }
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