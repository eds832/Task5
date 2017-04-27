package by.sardyka.parsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatorName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OperatorName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Velcom"/>
 *     &lt;enumeration value="MTS"/>
 *     &lt;enumeration value="Life"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperatorName")
@XmlEnum
public enum OperatorName {

    @XmlEnumValue("Velcom")
    VELCOM("Velcom"),
    MTS("MTS"),
    @XmlEnumValue("Life")
    LIFE("Life");
    private final String value;

    OperatorName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperatorName fromValue(String v) {
        for (OperatorName c: OperatorName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
