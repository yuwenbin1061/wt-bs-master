
package wt.bs.xml.parseResponse;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ChunkType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ChunkType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Dependency" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="MorphemList" type="{urn:yahoo:jp:jlp:DAService}MorphemListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChunkType", namespace = "urn:yahoo:jp:jlp:DAService", propOrder = {
    "id",
    "dependency",
    "morphemList"
})
public class ChunkType {

    @XmlElement(name = "Id", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected BigInteger id;
    @XmlElement(name = "Dependency", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected BigInteger dependency;
    @XmlElement(name = "MorphemList", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected MorphemListType morphemList;

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * 获取dependency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDependency() {
        return dependency;
    }

    /**
     * 设置dependency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDependency(BigInteger value) {
        this.dependency = value;
    }

    /**
     * 获取morphemList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MorphemListType }
     *     
     */
    public MorphemListType getMorphemList() {
        return morphemList;
    }

    /**
     * 设置morphemList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MorphemListType }
     *     
     */
    public void setMorphemList(MorphemListType value) {
        this.morphemList = value;
    }

}
