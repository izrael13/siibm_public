/**
 * ReadObjects.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class ReadObjects  implements java.io.Serializable {
    private java.lang.String sessionID;

    private java.lang.String objects;

    private java.lang.String IDName;

    private java.lang.String IDValue;

    public ReadObjects() {
    }

    public ReadObjects(
           java.lang.String sessionID,
           java.lang.String objects,
           java.lang.String IDName,
           java.lang.String IDValue) {
           this.sessionID = sessionID;
           this.objects = objects;
           this.IDName = IDName;
           this.IDValue = IDValue;
    }


    /**
     * Gets the sessionID value for this ReadObjects.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this ReadObjects.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }


    /**
     * Gets the objects value for this ReadObjects.
     * 
     * @return objects
     */
    public java.lang.String getObjects() {
        return objects;
    }


    /**
     * Sets the objects value for this ReadObjects.
     * 
     * @param objects
     */
    public void setObjects(java.lang.String objects) {
        this.objects = objects;
    }


    /**
     * Gets the IDName value for this ReadObjects.
     * 
     * @return IDName
     */
    public java.lang.String getIDName() {
        return IDName;
    }


    /**
     * Sets the IDName value for this ReadObjects.
     * 
     * @param IDName
     */
    public void setIDName(java.lang.String IDName) {
        this.IDName = IDName;
    }


    /**
     * Gets the IDValue value for this ReadObjects.
     * 
     * @return IDValue
     */
    public java.lang.String getIDValue() {
        return IDValue;
    }


    /**
     * Sets the IDValue value for this ReadObjects.
     * 
     * @param IDValue
     */
    public void setIDValue(java.lang.String IDValue) {
        this.IDValue = IDValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReadObjects)) return false;
        ReadObjects other = (ReadObjects) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sessionID==null && other.getSessionID()==null) || 
             (this.sessionID!=null &&
              this.sessionID.equals(other.getSessionID()))) &&
            ((this.objects==null && other.getObjects()==null) || 
             (this.objects!=null &&
              this.objects.equals(other.getObjects()))) &&
            ((this.IDName==null && other.getIDName()==null) || 
             (this.IDName!=null &&
              this.IDName.equals(other.getIDName()))) &&
            ((this.IDValue==null && other.getIDValue()==null) || 
             (this.IDValue!=null &&
              this.IDValue.equals(other.getIDValue())));
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
        if (getSessionID() != null) {
            _hashCode += getSessionID().hashCode();
        }
        if (getObjects() != null) {
            _hashCode += getObjects().hashCode();
        }
        if (getIDName() != null) {
            _hashCode += getIDName().hashCode();
        }
        if (getIDValue() != null) {
            _hashCode += getIDValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReadObjects.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">ReadObjects"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "SessionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objects");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "Objects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "IDName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "IDValue"));
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
