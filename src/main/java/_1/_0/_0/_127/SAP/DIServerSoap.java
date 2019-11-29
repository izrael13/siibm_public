/**
 * DIServerSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _1._0._0._127.SAP;

public interface DIServerSoap extends java.rmi.Remote {

    /**
     * SAP Developer partner information.
     */
    public _1._0._0._127.SAP.InformationResponseInformationResult information() throws java.rmi.RemoteException;

    /**
     * Interact is used to run the SOAP Command on the DI Server directly.
     */
    public java.lang.String interact(java.lang.String sessionID, java.lang.String SOAPCommand) throws java.rmi.RemoteException;

    /**
     * BatchInteract is used to run the SOAP Commands on the DI Server
     * directly.
     */
    public java.lang.String DIServerBI(java.lang.String sessionID, java.lang.String SOAPCommands) throws java.rmi.RemoteException;

    /**
     * Login to company.
     */
    public java.lang.String login(java.lang.String dataBaseServer, java.lang.String dataBaseName, java.lang.String dataBaseType, java.lang.String dataBaseUserName, java.lang.String dataBasePassword, java.lang.String companyUserName, java.lang.String companyPassword, java.lang.String language, java.lang.String licenseServer, java.lang.String license) throws java.rmi.RemoteException;

    /**
     * Logout to company.
     */
    public java.lang.String logout(java.lang.String sessionID) throws java.rmi.RemoteException;

    /**
     * This option is useful to check if your session is valid.
     */
    public boolean validate(java.lang.String sessionID) throws java.rmi.RemoteException;

    /**
     * Use the GetBusinessObjectTemplate to retrieve the XML template
     * of a specified business object.
     */
    public _1._0._0._127.SAP.GetBusinessObjectTemplateResponseGetBusinessObjectTemplateResult getBusinessObjectTemplate(java.lang.String sessionID, java.lang.String objects) throws java.rmi.RemoteException;

    /**
     * Get All Info from an Business Object.
     */
    public _1._0._0._127.SAP.ReadObjectsResponseReadObjectsResult readObjects(java.lang.String sessionID, java.lang.String objects, java.lang.String IDName, java.lang.String IDValue) throws java.rmi.RemoteException;

    /**
     * ExecuteSQL is limited to perform SELECT statement only. Performing
     * an SQL statement, in the <Data> element, other than SELECT causes
     * a failure and an error is reported.
     */
    public _1._0._0._127.SAP.ExecuteSQLResponseExecuteSQLResult executeSQL(java.lang.String sessionID, java.lang.String query) throws java.rmi.RemoteException;

    /**
     * The SBObob object is raw data access object that enables you
     * to retrieve information quickly and easily.
     */
    public _1._0._0._127.SAP.SBObobResponseSBObobResult SBObob(java.lang.String sessionID, java.lang.String objectModel, java.lang.String parameter, java.lang.String parameterValue) throws java.rmi.RemoteException;

    /**
     * Use the AddObject command to add records to a business object.
     * You can set only the Read/Write properties as defined in the business
     * Object and Service schemas.
     */
    public _1._0._0._127.SAP.AddObjectResponseAddObjectResult addObject(java.lang.String sessionID, java.lang.String BOM, java.lang.String commandID) throws java.rmi.RemoteException;

    /**
     * Use the UpdateObject command to update records to a business
     * object. You can set only the Read/Write properties as defined in the
     * business Object and Service schemas.
     */
    public _1._0._0._127.SAP.UpdateObjectResponseUpdateObjectResult updateObject(java.lang.String sessionID, java.lang.String objects, java.lang.String queryParams, java.lang.String BOMData, java.lang.String commandID) throws java.rmi.RemoteException;

    /**
     * The DI Services provide interfaces to additional logic within
     * SAP Business One, which is not necessarily encapsulated in a business
     * object. That is, the services approach enables to automate the implementation
     * process.
     */
    public _1._0._0._127.SAP.DIServicesResponseDIServicesResult DIServices(java.lang.String sessionID, java.lang.String service, java.lang.String SOAPCommand, java.lang.String parameters) throws java.rmi.RemoteException;

    /**
     * Create QR image.
     */
    public void QR() throws java.rmi.RemoteException;

    /**
     * Generates the encryption of your information to navegate safely
     * over the network.
     */
    public java.lang.String token(java.lang.String dataBaseServer, java.lang.String dataBaseName, java.lang.String dataBaseType, java.lang.String dataBaseUserName, java.lang.String dataBasePassword, java.lang.String companyUserName, java.lang.String companyPassword, java.lang.String language, java.lang.String licenseServer, java.lang.String license) throws java.rmi.RemoteException;

    /**
     * Login to company with Data Encrypted.
     */
    public java.lang.String loginSSL(java.lang.String tokenData) throws java.rmi.RemoteException;
}
