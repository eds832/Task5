package by.sardyka.parsing.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for TariffType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TariffType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payroll" type="{http://www.example.com/tariffs}TariffValue"/>
 *         &lt;element name="call-prices" type="{http://www.example.com/tariffs}CallPrices"/>
 *         &lt;element name="sms-price" type="{http://www.example.com/tariffs}TariffValue"/>
 *         &lt;element name="parameters" type="{http://www.example.com/tariffs}Parameters"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.example.com/tariffs}Name" />
 *       &lt;attribute name="operator-name" use="required" type="{http://www.example.com/tariffs}OperatorName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TariffType", propOrder = {
    "payroll",
    "callPrices",
    "smsPrice",
    "parameters"
})
@XmlSeeAlso({
    FixInternetPack.class,
    PerMbInternet.class
})
public class TariffType {

    @XmlElement(required = true, defaultValue = "0")
    private BigDecimal payroll;
    @XmlElement(name = "call-prices", required = true)
    private CallPrices callPrices;
    @XmlElement(name = "sms-price", required = true, defaultValue = "0")
    private BigDecimal smsPrice;
    @XmlElement(required = true)
    private Parameters parameters;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String name;
    @XmlAttribute(name = "operator-name", required = true)
    private OperatorName operatorName;

    /**
     * Gets the value of the payroll property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPayroll() {
        return payroll;
    }

    /**
     * Sets the value of the payroll property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPayroll(BigDecimal value) {
        this.payroll = value;
    }

    /**
     * Gets the value of the callPrices property.
     * 
     * @return
     *     possible object is
     *     {@link CallPrices }
     *     
     */
	public CallPrices getCallPrices() {
		if (callPrices == null) {
			callPrices = new CallPrices();
		}
		return callPrices;
	}

    /**
     * Sets the value of the callPrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallPrices }
     *     
     */
    public void setCallPrices(CallPrices value) {
        this.callPrices = value;
    }

    /**
     * Gets the value of the smsPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSmsPrice() {
        return smsPrice;
    }

    /**
     * Sets the value of the smsPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSmsPrice(BigDecimal value) {
        this.smsPrice = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
	public Parameters getParameters() {
		if (parameters == null) {
			parameters = new Parameters();
		}
		return parameters;
	}

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the operatorName property.
     * 
     * @return
     *     possible object is
     *     {@link OperatorName }
     *     
     */
    public OperatorName getOperatorName() {
        return operatorName;
    }

    /**
     * Sets the value of the operatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatorName }
     *     
     */
    public void setOperatorName(OperatorName value) {
        this.operatorName = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callPrices == null) ? 0 : callPrices.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((operatorName == null) ? 0 : operatorName.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((payroll == null) ? 0 : payroll.hashCode());
		result = prime * result + ((smsPrice == null) ? 0 : smsPrice.hashCode());
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
		TariffType other = (TariffType) obj;
		if (callPrices == null) {
			if (other.callPrices != null)
				return false;
		} else if (!callPrices.equals(other.callPrices))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operatorName != other.operatorName)
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (payroll == null) {
			if (other.payroll != null)
				return false;
		} else if (!payroll.equals(other.payroll))
			return false;
		if (smsPrice == null) {
			if (other.smsPrice != null)
				return false;
		} else if (!smsPrice.equals(other.smsPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n\tname=" + name + ", operatorName=" + operatorName + ", payroll=" + payroll + "," + callPrices
				+ ", smsPrice=" + smsPrice + "," + parameters;
	}

}
