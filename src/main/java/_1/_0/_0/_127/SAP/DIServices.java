/**
 * DIServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class DIServices  implements java.io.Serializable {
    private java.lang.String sessionID;

    private java.lang.String service;

    private java.lang.String SOAPCommand;

    private java.lang.String parameters;

    public DIServices() {
    }

    public DIServices(
           java.lang.String sessionID,
           java.lang.String service,
           java.lang.String SOAPCommand,
           java.lang.String parameters) {
           this.sessionID = sessionID;
           this.service = service;
           this.SOAPCommand = SOAPCommand;
           this.parameters = parameters;
    }


    /**
     * Gets the sessionID value for this DIServices.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this DIServices.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }


    /**
     * Gets the service value for this DIServices.
     * 
     * @return service
     */
    public java.lang.String getService() {
        return service;
    }


    /**
     * Sets the service value for this DIServices.
     * 
     * @param service
     */
    public void setService(java.lang.String service) {
        this.service = service;
    }


    /**
     * Gets the SOAPCommand value for this DIServices.
     * 
     * @return SOAPCommand
     */
    public java.lang.String getSOAPCommand() {
        return SOAPCommand;
    }


    /**
     * Sets the SOAPCommand value for this DIServices.
     * 
     * @param SOAPCommand
     */
    public void setSOAPCommand(java.lang.String SOAPCommand) {
        this.SOAPCommand = SOAPCommand;
    }


    /**
     * Gets the parameters value for this DIServices.
     * 
     * @return parameters
     */
    public java.lang.String getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this DIServices.
     * 
     * @param parameters
     */
    public void setParameters(java.lang.String parameters) {
        this.parameters = parameters;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DIServices)) return false;
        DIServices other = (DIServices) obj;
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
            ((this.service==null && other.getService()==null) || 
             (this.service!=null &&
              this.service.equals(other.getService()))) &&
            ((this.SOAPCommand==null && other.getSOAPCommand()==null) || 
             (this.SOAPCommand!=null &&
              this.SOAPCommand.equals(other.getSOAPCommand()))) &&
            ((this.parameters==null && other.getParameters()==null) || 
             (this.parameters!=null &&
              this.parameters.equals(other.getParameters())));
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
        if (getService() != null) {
            _hashCode += getService().hashCode();
        }
        if (getSOAPCommand() != null) {
            _hashCode += getSOAPCommand().hashCode();
        }
        if (getParameters() != null) {
            _hashCode += getParameters().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DIServices.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">DIServices"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "SessionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("service");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "Service"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOAPCommand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "SOAPCommand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "Parameters"));
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
