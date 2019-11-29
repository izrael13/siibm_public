package _1._0._0._127.SAP;

public class DIServerSoapProxy implements _1._0._0._127.SAP.DIServerSoap {
  private String _endpoint = null;
  private _1._0._0._127.SAP.DIServerSoap dIServerSoap = null;
  
  public DIServerSoapProxy() {
    _initDIServerSoapProxy();
  }
  
  public DIServerSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initDIServerSoapProxy();
  }
  
  private void _initDIServerSoapProxy() {
    try {
      dIServerSoap = (new _1._0._0._127.SAP.DIServerLocator()).getDIServerSoap();
      if (dIServerSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dIServerSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dIServerSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dIServerSoap != null)
      ((javax.xml.rpc.Stub)dIServerSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public _1._0._0._127.SAP.DIServerSoap getDIServerSoap() {
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap;
  }
  
  public _1._0._0._127.SAP.InformationResponseInformationResult information() throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.information();
  }
  
  public java.lang.String interact(java.lang.String sessionID, java.lang.String SOAPCommand) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.interact(sessionID, SOAPCommand);
  }
  
  public java.lang.String DIServerBI(java.lang.String sessionID, java.lang.String SOAPCommands) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.DIServerBI(sessionID, SOAPCommands);
  }
  
  public java.lang.String login(java.lang.String dataBaseServer, java.lang.String dataBaseName, java.lang.String dataBaseType, java.lang.String dataBaseUserName, java.lang.String dataBasePassword, java.lang.String companyUserName, java.lang.String companyPassword, java.lang.String language, java.lang.String licenseServer, java.lang.String license) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.login(dataBaseServer, dataBaseName, dataBaseType, dataBaseUserName, dataBasePassword, companyUserName, companyPassword, language, licenseServer, license);
  }
  
  public java.lang.String logout(java.lang.String sessionID) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.logout(sessionID);
  }
  
  public boolean validate(java.lang.String sessionID) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.validate(sessionID);
  }
  
  public _1._0._0._127.SAP.GetBusinessObjectTemplateResponseGetBusinessObjectTemplateResult getBusinessObjectTemplate(java.lang.String sessionID, java.lang.String objects) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.getBusinessObjectTemplate(sessionID, objects);
  }
  
  public _1._0._0._127.SAP.ReadObjectsResponseReadObjectsResult readObjects(java.lang.String sessionID, java.lang.String objects, java.lang.String IDName, java.lang.String IDValue) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.readObjects(sessionID, objects, IDName, IDValue);
  }
  
  public _1._0._0._127.SAP.ExecuteSQLResponseExecuteSQLResult executeSQL(java.lang.String sessionID, java.lang.String query) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.executeSQL(sessionID, query);
  }
  
  public _1._0._0._127.SAP.SBObobResponseSBObobResult SBObob(java.lang.String sessionID, java.lang.String objectModel, java.lang.String parameter, java.lang.String parameterValue) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.SBObob(sessionID, objectModel, parameter, parameterValue);
  }
  
  public _1._0._0._127.SAP.AddObjectResponseAddObjectResult addObject(java.lang.String sessionID, java.lang.String BOM, java.lang.String commandID) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.addObject(sessionID, BOM, commandID);
  }
  
  public _1._0._0._127.SAP.UpdateObjectResponseUpdateObjectResult updateObject(java.lang.String sessionID, java.lang.String objects, java.lang.String queryParams, java.lang.String BOMData, java.lang.String commandID) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.updateObject(sessionID, objects, queryParams, BOMData, commandID);
  }
  
  public _1._0._0._127.SAP.DIServicesResponseDIServicesResult DIServices(java.lang.String sessionID, java.lang.String service, java.lang.String SOAPCommand, java.lang.String parameters) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.DIServices(sessionID, service, SOAPCommand, parameters);
  }
  
  public void QR() throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    dIServerSoap.QR();
  }
  
  public java.lang.String token(java.lang.String dataBaseServer, java.lang.String dataBaseName, java.lang.String dataBaseType, java.lang.String dataBaseUserName, java.lang.String dataBasePassword, java.lang.String companyUserName, java.lang.String companyPassword, java.lang.String language, java.lang.String licenseServer, java.lang.String license) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.token(dataBaseServer, dataBaseName, dataBaseType, dataBaseUserName, dataBasePassword, companyUserName, companyPassword, language, licenseServer, license);
  }
  
  public java.lang.String loginSSL(java.lang.String tokenData) throws java.rmi.RemoteException{
    if (dIServerSoap == null)
      _initDIServerSoapProxy();
    return dIServerSoap.loginSSL(tokenData);
  }
  
  
}