/**
 * LoginSSL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class LoginSSL  implements java.io.Serializable {
    private java.lang.String tokenData;

    public LoginSSL() {
    }

    public LoginSSL(
           java.lang.String tokenData) {
           this.tokenData = tokenData;
    }


    /**
     * Gets the tokenData value for this LoginSSL.
     * 
     * @return tokenData
     */
    public java.lang.String getTokenData() {
        return tokenData;
    }


    /**
     * Sets the tokenData value for this LoginSSL.
     * 
     * @param tokenData
     */
    public void setTokenData(java.lang.String tokenData) {
        this.tokenData = tokenData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoginSSL)) return false;
        LoginSSL other = (LoginSSL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tokenData==null && other.getTokenData()==null) || 
             (this.tokenData!=null &&
              this.tokenData.equals(other.getTokenData())));
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
        if (getTokenData() != null) {
            _hashCode += getTokenData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginSSL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">LoginSSL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tokenData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "TokenData"));
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
