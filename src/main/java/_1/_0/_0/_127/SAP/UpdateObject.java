/**
 * UpdateObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class UpdateObject  implements java.io.Serializable {
    private java.lang.String sessionID;

    private java.lang.String objects;

    private java.lang.String queryParams;

    private java.lang.String BOMData;

    private java.lang.String commandID;

    public UpdateObject() {
    }

    public UpdateObject(
           java.lang.String sessionID,
           java.lang.String objects,
           java.lang.String queryParams,
           java.lang.String BOMData,
           java.lang.String commandID) {
           this.sessionID = sessionID;
           this.objects = objects;
           this.queryParams = queryParams;
           this.BOMData = BOMData;
           this.commandID = commandID;
    }


    /**
     * Gets the sessionID value for this UpdateObject.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this UpdateObject.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }


    /**
     * Gets the objects value for this UpdateObject.
     * 
     * @return objects
     */
    public java.lang.String getObjects() {
        return objects;
    }


    /**
     * Sets the objects value for this UpdateObject.
     * 
     * @param objects
     */
    public void setObjects(java.lang.String objects) {
        this.objects = objects;
    }


    /**
     * Gets the queryParams value for this UpdateObject.
     * 
     * @return queryParams
     */
    public java.lang.String getQueryParams() {
        return queryParams;
    }


    /**
     * Sets the queryParams value for this UpdateObject.
     * 
     * @param queryParams
     */
    public void setQueryParams(java.lang.String queryParams) {
        this.queryParams = queryParams;
    }


    /**
     * Gets the BOMData value for this UpdateObject.
     * 
     * @return BOMData
     */
    public java.lang.String getBOMData() {
        return BOMData;
    }


    /**
     * Sets the BOMData value for this UpdateObject.
     * 
     * @param BOMData
     */
    public void setBOMData(java.lang.String BOMData) {
        this.BOMData = BOMData;
    }


    /**
     * Gets the commandID value for this UpdateObject.
     * 
     * @return commandID
     */
    public java.lang.String getCommandID() {
        return commandID;
    }


    /**
     * Sets the commandID value for this UpdateObject.
     * 
     * @param commandID
     */
    public void setCommandID(java.lang.String commandID) {
        this.commandID = commandID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateObject)) return false;
        UpdateObject other = (UpdateObject) obj;
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
            ((this.queryParams==null && other.getQueryParams()==null) || 
             (this.queryParams!=null &&
              this.queryParams.equals(other.getQueryParams()))) &&
            ((this.BOMData==null && other.getBOMData()==null) || 
             (this.BOMData!=null &&
              this.BOMData.equals(other.getBOMData()))) &&
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
        if (getObjects() != null) {
            _hashCode += getObjects().hashCode();
        }
        if (getQueryParams() != null) {
            _hashCode += getQueryParams().hashCode();
        }
        if (getBOMData() != null) {
            _hashCode += getBOMData().hashCode();
        }
        if (getCommandID() != null) {
            _hashCode += getCommandID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">UpdateObject"));
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
        elemField.setFieldName("queryParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "QueryParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BOMData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "BOMData"));
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
