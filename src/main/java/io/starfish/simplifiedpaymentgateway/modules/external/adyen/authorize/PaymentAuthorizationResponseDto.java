package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

public record PaymentAuthorizationResponseDto(
        AdditionalData additionalData,
        String pspReference,
        String resultCode,
        Amount amount,
        String merchantReference,
        PaymentMethod paymentMethod
) {
    record AdditionalData(
            String cvcResult,
            String authCode,
            String avsResult,
            String scaExemptionRequested,
            String avsResultRaw,
            String cvcResultRaw,
            String refusalReasonRaw,
            String acquirerCode,
            String acquirerReference
    ) {
    }

    record Amount(String currency, int value) {
    }

    record PaymentMethod(String brand, String type) {
    }
}
