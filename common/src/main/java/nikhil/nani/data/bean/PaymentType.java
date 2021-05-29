package nikhil.nani.data.bean;

public enum PaymentType
{
    CREDIT_CARD,
    CASH,
    CHECK;

    public static PaymentType getPaymentType(String paymentType)
    {
        switch (paymentType)
        {
            case "CREDIT_CARD":
                return CREDIT_CARD;
            case "CASH":
                return CASH;
            case "CHECK":
                return CHECK;
            default:
                throw new IllegalArgumentException("Unsupported PaymentType:" + paymentType);
        }
    }
}
