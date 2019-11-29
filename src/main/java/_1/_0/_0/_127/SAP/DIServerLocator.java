/**
 * DIServerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public class DIServerLocator extends org.apache.axis.client.Service implements _1._0._0._127.SAP.DIServer {

    public DIServerLocator() {
    }


    public DIServerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DIServerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DIServerSoap
    private java.lang.String DIServerSoap_address = "http://192.169.1.50/SAP/DIServer.asmx";

    public java.lang.String getDIServerSoapAddress() {
        return DIServerSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DIServerSoapWSDDServiceName = "DIServerSoap";

    public java.lang.String getDIServerSoapWSDDServiceName() {
        return DIServerSoapWSDDServiceName;
    }

    public void setDIServerSoapWSDDServiceName(java.lang.String name) {
        DIServerSoapWSDDServiceName = name;
    }

    public _1._0._0._127.SAP.DIServerSoap getDIServerSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DIServerSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDIServerSoap(endpoint);
    }

    public _1._0._0._127.SAP.DIServerSoap getDIServerSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            _1._0._0._127.SAP.DIServerSoapStub _stub = new _1._0._0._127.SAP.DIServerSoapStub(portAddress, this);
            _stub.setPortName(getDIServerSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDIServerSoapEndpointAddress(java.lang.String address) {
        DIServerSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (_1._0._0._127.SAP.DIServerSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                _1._0._0._127.SAP.DIServerSoapStub _stub = new _1._0._0._127.SAP.DIServerSoapStub(new java.net.URL(DIServerSoap_address), this);
                _stub.setPortName(getDIServerSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DIServerSoap".equals(inputPortName)) {
            return getDIServerSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DIServer");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://127.0.0.1/SAP/", "DIServerSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DIServerSoap".equals(portName)) {
            setDIServerSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
