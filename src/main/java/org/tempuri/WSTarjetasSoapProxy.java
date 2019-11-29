package org.tempuri;

public class WSTarjetasSoapProxy implements org.tempuri.WSTarjetasSoap {
  private String _endpoint = null;
  private org.tempuri.WSTarjetasSoap wSTarjetasSoap = null;
  
  public WSTarjetasSoapProxy() {
    _initWSTarjetasSoapProxy();
  }
  
  public WSTarjetasSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSTarjetasSoapProxy();
  }
  
  private void _initWSTarjetasSoapProxy() {
    try {
      wSTarjetasSoap = (new org.tempuri.WSTarjetasLocator()).getWSTarjetasSoap();
      if (wSTarjetasSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSTarjetasSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSTarjetasSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSTarjetasSoap != null)
      ((javax.xml.rpc.Stub)wSTarjetasSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WSTarjetasSoap getWSTarjetasSoap() {
    if (wSTarjetasSoap == null)
      _initWSTarjetasSoapProxy();
    return wSTarjetasSoap;
  }
  
  public java.lang.String grabarSimbolo() throws java.rmi.RemoteException{
    if (wSTarjetasSoap == null)
      _initWSTarjetasSoapProxy();
    return wSTarjetasSoap.grabarSimbolo();
  }
  
  
}