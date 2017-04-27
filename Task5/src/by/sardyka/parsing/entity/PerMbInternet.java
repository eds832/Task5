package by.sardyka.parsing.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import by.sardyka.parsing.entity.TariffType;

/**
 * <p>Java class for PerMbInternet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PerMbInternet">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/tariffs}TariffType">
 *       &lt;sequence>
 *         &lt;element name="per-mb-price" type="{http://www.example.com/tariffs}TariffValue"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerMbInternet", propOrder = {
    "perMbPrice"
})
public class PerMbInternet
    extends TariffType
{

    @XmlElement(name = "per-mb-price", required = true, defaultValue = "0")
    private BigDecimal perMbPrice;

    /**
     * Gets the value of the perMbPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPerMbPrice() {
        return perMbPrice;
    }

    /**
     * Sets the value of the perMbPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPerMbPrice(BigDecimal value) {
        this.perMbPrice = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((perMbPrice == null) ? 0 : perMbPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerMbInternet other = (PerMbInternet) obj;
		if (perMbPrice == null) {
			if (other.perMbPrice != null)
				return false;
		} else if (!perMbPrice.equals(other.perMbPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nPerMbInternet:" + super.toString() + ",\n\tperMbPrice=" + perMbPrice;
	}

}
