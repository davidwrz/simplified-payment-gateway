package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

public record PaymentAuthorizationResponseDto(
        AdditionalData additionalData,
        String pspReference,
        String resultCode,
        Amount amount,
        String merchantReference,
        PaymentMethod paymentMethod
) {
    public record AdditionalData(
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

    public record Amount(String currency, long value) {
    }

    public record PaymentMethod(String brand, String type) {
    }
}
