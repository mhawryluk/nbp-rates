package com.intern.nbprates;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    @JsonProperty("cena")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

class ExchangeRateResponse {
    private String currency;
    private Currency code;
    private Rate[] rates;

    public String getCurrency() {
        return currency;
    }

    public Currency getCode() {
        return code;
    }

    public Rate[] getRates() {
        return rates;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCode(Currency code) {
        this.code = code;
    }

    public void setRates(Rate[] rates) {
        this.rates = rates;
    }
}

class Rate {
    private String effectiveDate;
    private BigDecimal mid;

    @JsonProperty("date")
    public String getEffectiveDate() {
        return effectiveDate;
    }

    @JsonProperty("rate")
    public BigDecimal getMid() {
        return mid;
    }

    @JsonProperty("effectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @JsonProperty("mid")
    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
}