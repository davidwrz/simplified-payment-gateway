package io.starfish.simplifiedpaymentgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSimplifiedPaymentGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.from(SimplifiedPaymentGatewayApplication::main).with(TestSimplifiedPaymentGatewayApplication.class).run(args);
	}

}
