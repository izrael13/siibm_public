/**
 * SBObob.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class SBObob  implements java.io.Serializable {
    private java.lang.String sessionID;

    private java.lang.String objectModel;

    private java.lang.String parameter;

    private java.lang.String parameterValue;

    public SBObob() {
    }

    public SBObob(
           java.lang.String sessionID,
           java.lang.String objectModel,
           java.lang.String parameter,
           java.lang.String parameterValue) {
           this.sessionID = sessionID;
           this.objectModel = objectModel;
           this.parameter = parameter;
           this.parameterValue = parameterValue;
    }


    /**
     * Gets the sessionID value for this SBObob.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this SBObob.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }


    /**
     * Gets the objectModel value for this SBObob.
     * 
     * @return objectModel
     */
    public java.lang.String getObjectModel() {
        return objectModel;
    }


    /**
     * Sets the objectModel value for this SBObob.
     * 
     * @param objectModel
     */
    public void setObjectModel(java.lang.String objectModel) {
        this.objectModel = objectModel;
    }


    /**
     * Gets the parameter value for this SBObob.
     * 
     * @return parameter
     */
    public java.lang.String getParameter() {
        return parameter;
    }


    /**
     * Sets the parameter value for this SBObob.
     * 
     * @param parameter
     */
    public void setParameter(java.lang.String parameter) {
        this.parameter = parameter;
    }


    /**
     * Gets the parameterValue value for this SBObob.
     * 
     * @return parameterValue
     */
    public java.lang.String getParameterValue() {
        return parameterValue;
    }


    /**
     * Sets the parameterValue value for this SBObob.
     * 
     * @param parameterValue
     */
    public void setParameterValue(java.lang.String parameterValue) {
        this.parameterValue = parameterValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SBObob)) return false;
        SBObob other = (SBObob) obj;
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
            ((this.objectModel==null && other.getObjectModel()==null) || 
             (this.objectModel!=null &&
              this.objectModel.equals(other.getObjectModel()))) &&
            ((this.parameter==null && other.getParameter()==null) || 
             (this.parameter!=null &&
              this.parameter.equals(other.getParameter()))) &&
            ((this.parameterValue==null && other.getParameterValue()==null) || 
             (this.parameterValue!=null &&
              this.parameterValue.equals(other.getParameterValue())));
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
        if (getObjectModel() != null) {
            _hashCode += getObjectModel().hashCode();
        }
        if (getParameter() != null) {
            _hashCode += getParameter().hashCode();
        }
        if (getParameterValue() != null) {
            _hashCode += getParameterValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SBObob.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">SBObob"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "SessionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "ObjectModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "Parameter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "ParameterValue"));
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
