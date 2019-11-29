/**
 * Login.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class Login  implements java.io.Serializable {
    private java.lang.String dataBaseServer;

    private java.lang.String dataBaseName;

    private java.lang.String dataBaseType;

    private java.lang.String dataBaseUserName;

    private java.lang.String dataBasePassword;

    private java.lang.String companyUserName;

    private java.lang.String companyPassword;

    private java.lang.String language;

    private java.lang.String licenseServer;

    private java.lang.String license;

    public Login() {
    }

    public Login(
           java.lang.String dataBaseServer,
           java.lang.String dataBaseName,
           java.lang.String dataBaseType,
           java.lang.String dataBaseUserName,
           java.lang.String dataBasePassword,
           java.lang.String companyUserName,
           java.lang.String companyPassword,
           java.lang.String language,
           java.lang.String licenseServer,
           java.lang.String license) {
           this.dataBaseServer = dataBaseServer;
           this.dataBaseName = dataBaseName;
           this.dataBaseType = dataBaseType;
           this.dataBaseUserName = dataBaseUserName;
           this.dataBasePassword = dataBasePassword;
           this.companyUserName = companyUserName;
           this.companyPassword = companyPassword;
           this.language = language;
           this.licenseServer = licenseServer;
           this.license = license;
    }


    /**
     * Gets the dataBaseServer value for this Login.
     * 
     * @return dataBaseServer
     */
    public java.lang.String getDataBaseServer() {
        return dataBaseServer;
    }


    /**
     * Sets the dataBaseServer value for this Login.
     * 
     * @param dataBaseServer
     */
    public void setDataBaseServer(java.lang.String dataBaseServer) {
        this.dataBaseServer = dataBaseServer;
    }


    /**
     * Gets the dataBaseName value for this Login.
     * 
     * @return dataBaseName
     */
    public java.lang.String getDataBaseName() {
        return dataBaseName;
    }


    /**
     * Sets the dataBaseName value for this Login.
     * 
     * @param dataBaseName
     */
    public void setDataBaseName(java.lang.String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }


    /**
     * Gets the dataBaseType value for this Login.
     * 
     * @return dataBaseType
     */
    public java.lang.String getDataBaseType() {
        return dataBaseType;
    }


    /**
     * Sets the dataBaseType value for this Login.
     * 
     * @param dataBaseType
     */
    public void setDataBaseType(java.lang.String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }


    /**
     * Gets the dataBaseUserName value for this Login.
     * 
     * @return dataBaseUserName
     */
    public java.lang.String getDataBaseUserName() {
        return dataBaseUserName;
    }


    /**
     * Sets the dataBaseUserName value for this Login.
     * 
     * @param dataBaseUserName
     */
    public void setDataBaseUserName(java.lang.String dataBaseUserName) {
        this.dataBaseUserName = dataBaseUserName;
    }


    /**
     * Gets the dataBasePassword value for this Login.
     * 
     * @return dataBasePassword
     */
    public java.lang.String getDataBasePassword() {
        return dataBasePassword;
    }


    /**
     * Sets the dataBasePassword value for this Login.
     * 
     * @param dataBasePassword
     */
    public void setDataBasePassword(java.lang.String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
    }


    /**
     * Gets the companyUserName value for this Login.
     * 
     * @return companyUserName
     */
    public java.lang.String getCompanyUserName() {
        return companyUserName;
    }


    /**
     * Sets the companyUserName value for this Login.
     * 
     * @param companyUserName
     */
    public void setCompanyUserName(java.lang.String companyUserName) {
        this.companyUserName = companyUserName;
    }


    /**
     * Gets the companyPassword value for this Login.
     * 
     * @return companyPassword
     */
    public java.lang.String getCompanyPassword() {
        return companyPassword;
    }


    /**
     * Sets the companyPassword value for this Login.
     * 
     * @param companyPassword
     */
    public void setCompanyPassword(java.lang.String companyPassword) {
        this.companyPassword = companyPassword;
    }


    /**
     * Gets the language value for this Login.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this Login.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the licenseServer value for this Login.
     * 
     * @return licenseServer
     */
    public java.lang.String getLicenseServer() {
        return licenseServer;
    }


    /**
     * Sets the licenseServer value for this Login.
     * 
     * @param licenseServer
     */
    public void setLicenseServer(java.lang.String licenseServer) {
        this.licenseServer = licenseServer;
    }


    /**
     * Gets the license value for this Login.
     * 
     * @return license
     */
    public java.lang.String getLicense() {
        return license;
    }


    /**
     * Sets the license value for this Login.
     * 
     * @param license
     */
    public void setLicense(java.lang.String license) {
        this.license = license;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Login)) return false;
        Login other = (Login) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataBaseServer==null && other.getDataBaseServer()==null) || 
             (this.dataBaseServer!=null &&
              this.dataBaseServer.equals(other.getDataBaseServer()))) &&
            ((this.dataBaseName==null && other.getDataBaseName()==null) || 
             (this.dataBaseName!=null &&
              this.dataBaseName.equals(other.getDataBaseName()))) &&
            ((this.dataBaseType==null && other.getDataBaseType()==null) || 
             (this.dataBaseType!=null &&
              this.dataBaseType.equals(other.getDataBaseType()))) &&
            ((this.dataBaseUserName==null && other.getDataBaseUserName()==null) || 
             (this.dataBaseUserName!=null &&
              this.dataBaseUserName.equals(other.getDataBaseUserName()))) &&
            ((this.dataBasePassword==null && other.getDataBasePassword()==null) || 
             (this.dataBasePassword!=null &&
              this.dataBasePassword.equals(other.getDataBasePassword()))) &&
            ((this.companyUserName==null && other.getCompanyUserName()==null) || 
             (this.companyUserName!=null &&
              this.companyUserName.equals(other.getCompanyUserName()))) &&
            ((this.companyPassword==null && other.getCompanyPassword()==null) || 
             (this.companyPassword!=null &&
              this.companyPassword.equals(other.getCompanyPassword()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.licenseServer==null && other.getLicenseServer()==null) || 
             (this.licenseServer!=null &&
              this.licenseServer.equals(other.getLicenseServer()))) &&
            ((this.license==null && other.getLicense()==null) || 
             (this.license!=null &&
              this.license.equals(other.getLicense())));
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
        if (getDataBaseServer() != null) {
            _hashCode += getDataBaseServer().hashCode();
        }
        if (getDataBaseName() != null) {
            _hashCode += getDataBaseName().hashCode();
        }
        if (getDataBaseType() != null) {
            _hashCode += getDataBaseType().hashCode();
        }
        if (getDataBaseUserName() != null) {
            _hashCode += getDataBaseUserName().hashCode();
        }
        if (getDataBasePassword() != null) {
            _hashCode += getDataBasePassword().hashCode();
        }
        if (getCompanyUserName() != null) {
            _hashCode += getCompanyUserName().hashCode();
        }
        if (getCompanyPassword() != null) {
            _hashCode += getCompanyPassword().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getLicenseServer() != null) {
            _hashCode += getLicenseServer().hashCode();
        }
        if (getLicense() != null) {
            _hashCode += getLicense().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Login.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", ">Login"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataBaseServer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DataBaseServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataBaseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DataBaseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataBaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DataBaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataBaseUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DataBaseUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataBasePassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DataBasePassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "CompanyUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "CompanyPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "Language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenseServer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "LicenseServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license");
        elemField.setXmlName(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "License"));
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
