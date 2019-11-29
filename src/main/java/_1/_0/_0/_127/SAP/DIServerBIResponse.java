/**
 * DIServerBIResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class DIServerBIResponse  implements java.io.Serializable {
    private java.lang.String DIServerBIResult;

    public DIServerBIResponse() {
    }

    public DIServerBIResponse(
           java.lang.String DIServerBIResult) {
           this.DIServerBIResult = DIServerBIResult;
    }


    /**
     * Gets the DIServerBIResult value for this DIServerBIResponse.
     * 
     * @return DIServerBIResult
     */
    public java.lang.String getDIServerBIResult() {
        return DIServerBIResult;
    }


    /**
     * Sets the DIServerBIResult value for this DIServerBIResponse.
     * 
     * @param DIServerBIResult
     */
    public void setDIServerBIResult(java.lang.String DIServerBIResult) {
        this.DIServerBIResult = DIServerBIResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DIServerBIResponse)) return false;
        DIServerBIResponse other = (DIServerBIResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DIServerBIResult==null && other.getDIServerBIResult()==null) || 
             (this.DIServerBIResult!=null &&
              this.DIServerBIResult.equals(other.getDIServerBIResult())));
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
        if (getDIServerBIResult() != null) {
            _hashCode += getDIServerBIResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DIServerBIResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">DIServerBIResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIServerBIResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DIServerBIResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
