/**
 * SBObobResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class SBObobResponse  implements java.io.Serializable {
    private _1._0._0._127.SAP.SBObobResponseSBObobResult SBObobResult;

    public SBObobResponse() {
    }

    public SBObobResponse(
           _1._0._0._127.SAP.SBObobResponseSBObobResult SBObobResult) {
           this.SBObobResult = SBObobResult;
    }


    /**
     * Gets the SBObobResult value for this SBObobResponse.
     * 
     * @return SBObobResult
     */
    public _1._0._0._127.SAP.SBObobResponseSBObobResult getSBObobResult() {
        return SBObobResult;
    }


    /**
     * Sets the SBObobResult value for this SBObobResponse.
     * 
     * @param SBObobResult
     */
    public void setSBObobResult(_1._0._0._127.SAP.SBObobResponseSBObobResult SBObobResult) {
        this.SBObobResult = SBObobResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SBObobResponse)) return false;
        SBObobResponse other = (SBObobResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SBObobResult==null && other.getSBObobResult()==null) || 
             (this.SBObobResult!=null &&
              this.SBObobResult.equals(other.getSBObobResult())));
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
        if (getSBObobResult() != null) {
            _hashCode += getSBObobResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SBObobResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">SBObobResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SBObobResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "SBObobResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">>SBObobResponse>SBObobResult"));
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
