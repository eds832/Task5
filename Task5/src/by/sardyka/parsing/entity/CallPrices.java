package by.sardyka.parsing.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CallPrices complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallPrices">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inner-call-price" type="{http://www.example.com/tariffs}TariffValue"/>
 *         &lt;element name="out-call-price" type="{http://www.example.com/tariffs}TariffValue"/>
 *         &lt;element name="land-line-call-price" type="{http://www.example.com/tariffs}TariffValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrices", propOrder = {
    "innerCallPrice",
    "outCallPrice",
    "landLineCallPrice"
})
public class CallPrices {

    @XmlElement(name = "inner-call-price", required = true, defaultValue = "0")
    private BigDecimal innerCallPrice;
    @XmlElement(name = "out-call-price", required = true, defaultValue = "0")
    private BigDecimal outCallPrice;
    @XmlElement(name = "land-line-call-price", required = true, defaultValue = "0")
    private BigDecimal landLineCallPrice;

    /**
     * Gets the value of the innerCallPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInnerCallPrice() {
        return innerCallPrice;
    }

    /**
     * Sets the value of the innerCallPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInnerCallPrice(BigDecimal value) {
        this.innerCallPrice = value;
    }

    /**
     * Gets the value of the outCallPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOutCallPrice() {
        return outCallPrice;
    }

    /**
     * Sets the value of the outCallPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOutCallPrice(BigDecimal value) {
        this.outCallPrice = value;
    }

    /**
     * Gets the value of the landLineCallPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLandLineCallPrice() {
        return landLineCallPrice;
    }

    /**
     * Sets the value of the landLineCallPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLandLineCallPrice(BigDecimal value) {
        this.landLineCallPrice = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((innerCallPrice == null) ? 0 : innerCallPrice.hashCode());
		result = prime * result + ((landLineCallPrice == null) ? 0 : landLineCallPrice.hashCode());
		result = prime * result + ((outCallPrice == null) ? 0 : outCallPrice.hashCode());
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
		CallPrices other = (CallPrices) obj;
		if (innerCallPrice == null) {
			if (other.innerCallPrice != null)
				return false;
		} else if (!innerCallPrice.equals(other.innerCallPrice))
			return false;
		if (landLineCallPrice == null) {
			if (other.landLineCallPrice != null)
				return false;
		} else if (!landLineCallPrice.equals(other.landLineCallPrice))
			return false;
		if (outCallPrice == null) {
			if (other.outCallPrice != null)
				return false;
		} else if (!outCallPrice.equals(other.outCallPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n\tCallPrices [innerCallPrice=" + innerCallPrice + ", outCallPrice=" + outCallPrice
				+ ", landLineCallPrice=" + landLineCallPrice + "]";
	}

}
