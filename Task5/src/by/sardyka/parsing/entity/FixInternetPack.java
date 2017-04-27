package by.sardyka.parsing.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import by.sardyka.parsing.entity.TariffType;

/**
 * <p>
 * Java class for FixInternetPack complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="FixInternetPack">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/tariffs}TariffType">
 *       &lt;sequence>
 *         &lt;element name="i-net-pack-size" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *       &lt;/sequence>
 *       &lt;attribute name="additional-i-net-fee" type="{http://www.example.com/tariffs}TariffValue" default="0" />
 *       &lt;attribute name="over-size-price" use="required" type="{http://www.example.com/tariffs}TariffValue" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixInternetPack", propOrder = { "iNetPackSize" })
public class FixInternetPack extends TariffType {

	@XmlElement(name = "i-net-pack-size", required = true, defaultValue = "0")
	@XmlSchemaType(name = "nonNegativeInteger")
	private BigInteger iNetPackSize;
	@XmlAttribute(name = "additional-i-net-fee")
	private BigDecimal additionalINetFee = BigDecimal.ZERO;
	@XmlAttribute(name = "over-size-price", required = true)
	private BigDecimal overSizePrice;

	/**
	 * Gets the value of the iNetPackSize property.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getINetPackSize() {
		return iNetPackSize;
	}

	/**
	 * Sets the value of the iNetPackSize property.
	 * 
	 * @param value
	 *            allowed object is {@link BigInteger }
	 * 
	 */
	public void setINetPackSize(BigInteger value) {
		this.iNetPackSize = value;
	}

	/**
	 * Gets the value of the additionalINetFee property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getAdditionalINetFee() {
		return additionalINetFee;
	}

	/**
	 * Sets the value of the additionalINetFee property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setAdditionalINetFee(BigDecimal value) {
		this.additionalINetFee = value;
	}

	/**
	 * Gets the value of the overSizePrice property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getOverSizePrice() {
		return overSizePrice;
	}

	/**
	 * Sets the value of the overSizePrice property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setOverSizePrice(BigDecimal value) {
		this.overSizePrice = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((additionalINetFee == null) ? 0 : additionalINetFee.hashCode());
		result = prime * result + ((iNetPackSize == null) ? 0 : iNetPackSize.hashCode());
		result = prime * result + ((overSizePrice == null) ? 0 : overSizePrice.hashCode());
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
		FixInternetPack other = (FixInternetPack) obj;
		if (additionalINetFee == null) {
			if (other.additionalINetFee != null)
				return false;
		} else if (!additionalINetFee.equals(other.additionalINetFee))
			return false;
		if (iNetPackSize == null) {
			if (other.iNetPackSize != null)
				return false;
		} else if (!iNetPackSize.equals(other.iNetPackSize))
			return false;
		if (overSizePrice == null) {
			if (other.overSizePrice != null)
				return false;
		} else if (!overSizePrice.equals(other.overSizePrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nFixInternetPack:" + super.toString() + ",\n\tiNetPackSize=" + iNetPackSize + ", additionalINetFee="
				+ additionalINetFee + ", overSizePrice=" + overSizePrice;
	}

}
