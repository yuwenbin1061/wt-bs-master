
package wt.bs.xml.parseResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MorphemType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MorphemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Surface" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Reading" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Baseform" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="POS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Feature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MorphemType", namespace = "urn:yahoo:jp:jlp:DAService", propOrder = {
    "surface",
    "reading",
    "baseform",
    "pos",
    "feature"
})
public class MorphemType {

    @XmlElement(name = "Surface", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected String surface;
    @XmlElement(name = "Reading", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected String reading;
    @XmlElement(name = "Baseform", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected String baseform;
    @XmlElement(name = "POS", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected String pos;
    @XmlElement(name = "Feature", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected String feature;

    /**
     * 获取surface属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurface() {
        return surface;
    }

    /**
     * 设置surface属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurface(String value) {
        this.surface = value;
    }

    /**
     * 获取reading属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReading() {
        return reading;
    }

    /**
     * 设置reading属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReading(String value) {
        this.reading = value;
    }

    /**
     * 获取baseform属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseform() {
        return baseform;
    }

    /**
     * 设置baseform属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseform(String value) {
        this.baseform = value;
    }

    /**
     * 获取pos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOS() {
        return pos;
    }

    /**
     * 设置pos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOS(String value) {
        this.pos = value;
    }

    /**
     * 获取feature属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 设置feature属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeature(String value) {
        this.feature = value;
    }

}
