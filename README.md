# NBP Rates API


## Table of Contents
- [NBP Rates API](#nbp-rates-api)
  - [Table of Contents](#table-of-contents)
  - [Description](#description)
  - [Endpoints](#endpoints)
  - [Technologies Used](#technologies-used)
  - [Usage](#usage)


## Description

The project offers an API to acquire currency exchange rates from PLN, as well as average gold prices. It uses an API offered by the NBP (National Bank of Poland).

## Endpoints

The service exposes the following endpoints:


**GET /api/exchange-rates/{currencyCode}**

Returns currency exchange rate PLN to {currencyCode} for the last 5 business days.

Response format: JSON
Response structure:

* *currency* - The name of the currency in Polish
* *code* - The currency code (ISO 4217)
* *rates* - A list of 5 most recent entries of the exchange rate from PLN to given currency
  * *date* - date in format "YYYY-MM-DD"
  * *rate* - exchange rate, decimal value
  
Possible error status: 404 (Not found)

---

**GET /api/gold-price/average**

Returns the average gold price calculated for the last 14 business days.

Response format: decimal value rounded to the 4th decimal place.

Possible error status: 404 (Not found)



## Technologies Used
- **Java**
- Spring Boot
- Jackson Annotations
- Maven

## Usage

To run the service, please run the *mvnw* script with the argument *spring-boot:run*