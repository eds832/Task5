package by.sardyka.parsing.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Parameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="number-favorites" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="tariffing">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="minute"/>
 *               &lt;enumeration value="12seconds"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="init-price" type="{http://www.example.com/tariffs}TariffValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {
    "numberFavorites",
    "tariffing",
    "initPrice"
})
public class Parameters {

    @XmlElement(name = "number-favorites", required = true, defaultValue = "0")
    @XmlSchemaType(name = "nonNegativeInteger")
    private BigInteger numberFavorites;
    @XmlElement(required = true)
    private String tariffing;
    @XmlElement(name = "init-price", required = true, defaultValue = "0")
    private BigDecimal initPrice;

    /**
     * Gets the value of the numberFavorites property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberFavorites() {
        return numberFavorites;
    }

    /**
     * Sets the value of the numberFavorites property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberFavorites(BigInteger value) {
        this.numberFavorites = value;
    }

    /**
     * Gets the value of the tariffing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariffing() {
        return tariffing;
    }

    /**
     * Sets the value of the tariffing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariffing(String value) {
        this.tariffing = value;
    }

    /**
     * Gets the value of the initPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitPrice() {
        return initPrice;
    }

    /**
     * Sets the value of the initPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitPrice(BigDecimal value) {
        this.initPrice = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((initPrice == null) ? 0 : initPrice.hashCode());
		result = prime * result + ((numberFavorites == null) ? 0 : numberFavorites.hashCode());
		result = prime * result + ((tariffing == null) ? 0 : tariffing.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameters other = (Parameters) obj;
		if (initPrice == null) {
			if (other.initPrice != null)
				return false;
		} else if (!initPrice.equals(other.initPrice))
			return false;
		if (numberFavorites == null) {
			if (other.numberFavorites != null)
				return false;
		} else if (!numberFavorites.equals(other.numberFavorites))
			return false;
		if (tariffing == null) {
			if (other.tariffing != null)
				return false;
		} else if (!tariffing.equals(other.tariffing))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n\tParameters [numberFavorites=" + numberFavorites + ", tariffing=" + tariffing + ", initPrice="
				+ initPrice + "]";
	}

}
