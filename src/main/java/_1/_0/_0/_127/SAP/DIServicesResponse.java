/**
 * DIServicesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class DIServicesResponse  implements java.io.Serializable {
    private _1._0._0._127.SAP.DIServicesResponseDIServicesResult DIServicesResult;

    public DIServicesResponse() {
    }

    public DIServicesResponse(
           _1._0._0._127.SAP.DIServicesResponseDIServicesResult DIServicesResult) {
           this.DIServicesResult = DIServicesResult;
    }


    /**
     * Gets the DIServicesResult value for this DIServicesResponse.
     * 
     * @return DIServicesResult
     */
    public _1._0._0._127.SAP.DIServicesResponseDIServicesResult getDIServicesResult() {
        return DIServicesResult;
    }


    /**
     * Sets the DIServicesResult value for this DIServicesResponse.
     * 
     * @param DIServicesResult
     */
    public void setDIServicesResult(_1._0._0._127.SAP.DIServicesResponseDIServicesResult DIServicesResult) {
        this.DIServicesResult = DIServicesResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DIServicesResponse)) return false;
        DIServicesResponse other = (DIServicesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DIServicesResult==null && other.getDIServicesResult()==null) || 
             (this.DIServicesResult!=null &&
              this.DIServicesResult.equals(other.getDIServicesResult())));
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
        if (getDIServicesResult() != null) {
            _hashCode += getDIServicesResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DIServicesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">DIServicesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIServicesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DIServicesResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">>DIServicesResponse>DIServicesResult"));
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
