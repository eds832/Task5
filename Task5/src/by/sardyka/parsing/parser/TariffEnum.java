package by.sardyka.parsing.parser;

public enum TariffEnum {
	TARIFFS("tariffs"),
	FIX_INTERNET_PACK("fix-internet-pack"),
    PER_MB_INTERNET("per-mb-internet"),
    CALL_PRICES("call-prices"),
    PARAMETERS("parameters"),
    NAME("name"),
    OPERATOR_NAME("operator-name"),
    PAYROLL("payroll"),
    SMS_PRICE("sms-price"),
    INNER_CALL_PRICE("inner-call-price"),
    OUT_CALL_PRICE("out-call-price"),
    LAND_LINE_CALL_PRICE("land-line-call-price"),
    INIT_PRICE("init-price"),
    NUMBER_FAVORITES("number-favorites"),
    TARIFFING("tariffing"),
    PER_MB_PRICE("per-mb-price"),
    I_NET_PACK_SIZE("i-net-pack-size"),
    ADDITIONAL_I_NET_FEE("additional-i-net-fee"),
    OVER_SIZE_PRICE("over-size-price");

    private String value;

    private TariffEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
