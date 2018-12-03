
package wt.bs.xml.parseResponse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MorphemListType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MorphemListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Morphem" type="{urn:yahoo:jp:jlp:DAService}MorphemType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MorphemListType", namespace = "urn:yahoo:jp:jlp:DAService", propOrder = {
    "morphem"
})
public class MorphemListType {

    @XmlElement(name = "Morphem", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected List<MorphemType> morphem;

    /**
     * Gets the value of the morphem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the morphem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMorphem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MorphemType }
     * 
     * 
     */
    public List<MorphemType> getMorphem() {
        if (morphem == null) {
            morphem = new ArrayList<MorphemType>();
        }
        return this.morphem;
    }

}
