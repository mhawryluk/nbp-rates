package com.intern.nbprates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Currency;

@SpringBootApplication
@RestController
public class NbpRatesApplication {

	private final RestService restService = new RestService();

	public static void main(String[] args) {
		SpringApplication.run(NbpRatesApplication.class, args);
	}

	@GetMapping("/api/exchange-rates/{currencyCode}")
	public ExchangeRateResponse exchangeRates(@PathVariable Currency currencyCode) {
		return restService.getExchangeRates(currencyCode);
	}

	@GetMapping("/api/gold-price/average")
	public BigDecimal goldPrice() {
		BigDecimal sum = new BigDecimal(0);
		GoldResponse[] response = restService.getGoldPrices();
		for (GoldResponse entry : response) {
			sum = sum.add(entry.getPrice());
		}
		return sum.divide(new BigDecimal(response.length), 4, RoundingMode.HALF_UP);
	}
}
