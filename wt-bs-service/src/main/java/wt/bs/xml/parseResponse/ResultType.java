
package wt.bs.xml.parseResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResultType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChunkList" type="{urn:yahoo:jp:jlp:DAService}ChunkListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultType", namespace = "urn:yahoo:jp:jlp:DAService", propOrder = {
    "chunkList"
})
public class ResultType {

    @XmlElement(name = "ChunkList", namespace = "urn:yahoo:jp:jlp:DAService", required = true)
    protected ChunkListType chunkList;

    /**
     * 获取chunkList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChunkListType }
     *     
     */
    public ChunkListType getChunkList() {
        return chunkList;
    }

    /**
     * 设置chunkList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChunkListType }
     *     
     */
    public void setChunkList(ChunkListType value) {
        this.chunkList = value;
    }

}
