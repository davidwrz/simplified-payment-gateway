# Simple Payment Gateway

---

## Project Overview

Welcome to the Simplified Payment Gateway!

This project is a system that enables users to 'make' credit card payments using Adyen's sandbox environment. The
following requirements have been implemented:

- Payment initialization via HTTP request
- Validation of provided card details
- Integration with Adyen Sandbox API
- Error handling and logging
- Documentation
- Authorization and authentication with JWT
- User management in the database
- Unit and integration tests
- Pipelines for automatic testing, building, dockerizing, and deploying the application on Heroku

## About Adyen

Adyen is a global payment company that enables businesses to accept payments in multiple currencies and payment methods.
It provides a unified platform for online, mobile, and in-store payments, allowing businesses to streamline their
payment processes and expand their reach to customers worldwide. Adyen's solutions include payment gateway services,
risk management, and analytics tools, empowering businesses to optimize their payment operations and enhance the
customer experience.

For more detailed information and to explore the Adyen API, visit
the [Adyen API Explorer](https://docs.adyen.com/api-explorer/).

--- 

## Authorization

To use endpoints in the application, users must be authorized. To do this, send the following body:

```json
{
  "name": "<username>",
  "password": "<password>"
}
```

to the following endpoint:

```
/api/v1/register
```

A JWT will be returned in response, which should be attached to every subsequent request.

If you need to generate a new token or have forgotten the token, you can log in with your credentials:

```json
{
  "name": "<username>",
  "password": "<password>"
}
```

to the following endpoint:

```
/api/v1/login
```

## Initialize Payment

To initialize a payment, you need to provide JSON with the following values:

```json
{
  "amount": {
    "currency": "<currency>",
    "value": "<value>"
  },
  "paymentMethod": {
    "number": "<cardnumber>",
    "expiryMonth": "<expirymonth>",
    "expiryYear": "<expiryyear>",
    "holderName": "<firstName lastName>",
    "cvc": "<cvc>"
  }
}
```

to the following URL:

```
/api/v1/pay
```

More details about the fields can be found in
the [Adyen API Explorer](https://docs.adyen.com/api-explorer/Checkout/71/post/payments). All values are required. The
card is validated using the Luhn algorithm and expiration date. The application calls the Adyen API to verify the
payment details.
Upon receiving the response message, it contains:

```json
{
  "message": "Authorised",
  "reference": "f2136bd1-e9da-4a21-8cd0-70d23cc4f7c6"
}
```

The reference is a UUID generated to track payment details. This feature is not implemented in the gateway.

--- 

## How to Run the Project

### Building the Application

Use the following command to build the application:

```bash
mvn clean install
```

Please note that the application locally uses the latest Postgres Docker image, so it must be running in order to start
the app.

---

### Running the Application as Docker Image

First, pull the Docker image:

```
docker pull davidwrz/simplifiedpaymentgateway:latest
```

Then, run the Docker container:

```
docker run -p 8080:8080 -d davidwrz/simplifiedpaymentgateway:latest
```

The application will be available at [http://localhost:8080](http://localhost:8080).

--- 

### Running the Application in an IDE

To run the application in your IDE, locate the class with the `main` method:

### [SimplifiedPaymentGatewayApplication.java](src/main/java/io/starfish/simplifiedpaymentgateway/SimplifiedPaymentGatewayApplication.java)

```java

@SpringBootApplication
public class SimplifiedPaymentGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplifiedPaymentGatewayApplication.class, args);
    }

}
```

Then, execute it by right-clicking and choosing "Run" or "Debug."

--- 

### Running Tests

Execute the following commands to run the tests:

Unit tests:
```mvn test```

Integration tests:
```mvn verify```

--- 