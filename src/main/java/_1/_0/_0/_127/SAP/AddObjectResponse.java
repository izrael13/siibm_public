/**
 * AddObjectResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class AddObjectResponse  implements java.io.Serializable {
    private _1._0._0._127.SAP.AddObjectResponseAddObjectResult addObjectResult;

    public AddObjectResponse() {
    }

    public AddObjectResponse(
           _1._0._0._127.SAP.AddObjectResponseAddObjectResult addObjectResult) {
           this.addObjectResult = addObjectResult;
    }


    /**
     * Gets the addObjectResult value for this AddObjectResponse.
     * 
     * @return addObjectResult
     */
    public _1._0._0._127.SAP.AddObjectResponseAddObjectResult getAddObjectResult() {
        return addObjectResult;
    }


    /**
     * Sets the addObjectResult value for this AddObjectResponse.
     * 
     * @param addObjectResult
     */
    public void setAddObjectResult(_1._0._0._127.SAP.AddObjectResponseAddObjectResult addObjectResult) {
        this.addObjectResult = addObjectResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddObjectResponse)) return false;
        AddObjectResponse other = (AddObjectResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addObjectResult==null && other.getAddObjectResult()==null) || 
             (this.addObjectResult!=null &&
              this.addObjectResult.equals(other.getAddObjectResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAddObjectResult() != null) {
            _hashCode += getAddObjectResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddObjectResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">AddObjectResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addObjectResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "AddObjectResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">>AddObjectResponse>AddObjectResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
