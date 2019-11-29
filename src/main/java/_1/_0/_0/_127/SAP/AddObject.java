/**
 * AddObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class AddObject  implements java.io.Serializable {
    private java.lang.String sessionID;

    private java.lang.String BOM;

    private java.lang.String commandID;

    public AddObject() {
    }

    public AddObject(
           java.lang.String sessionID,
           java.lang.String BOM,
           java.lang.String commandID) {
           this.sessionID = sessionID;
           this.BOM = BOM;
           this.commandID = commandID;
    }


    /**
     * Gets the sessionID value for this AddObject.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this AddObject.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }


    /**
     * Gets the BOM value for this AddObject.
     * 
     * @return BOM
     */
    public java.lang.String getBOM() {
        return BOM;
    }


    /**
     * Sets the BOM value for this AddObject.
     * 
     * @param BOM
     */
    public void setBOM(java.lang.String BOM) {
        this.BOM = BOM;
    }


    /**
     * Gets the commandID value for this AddObject.
     * 
     * @return commandID
     */
    public java.lang.String getCommandID() {
        return commandID;
    }


    /**
     * Sets the commandID value for this AddObject.
     * 
     * @param commandID
     */
    public void setCommandID(java.lang.String commandID) {
        this.commandID = commandID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddObject)) return false;
        AddObject other = (AddObject) obj;
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
            ((this.BOM==null && other.getBOM()==null) || 
             (this.BOM!=null &&
              this.BOM.equals(other.getBOM()))) &&
            ((this.commandID==null && other.getCommandID()==null) || 
             (this.commandID!=null &&
              this.commandID.equals(other.getCommandID())));
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
        if (getBOM() != null) {
            _hashCode += getBOM().hashCode();
        }
        if (getCommandID() != null) {
            _hashCode += getCommandID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">AddObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "SessionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BOM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "BOM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commandID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "CommandID"));
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
