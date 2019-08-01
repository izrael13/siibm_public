package com.websystique.springmvc.utilities;

//import com.sap.smb.sbo.api.*;

public class DIAPI {
	/*
	public ICompany company;
	private SBOErrorMessage errMsg = null;
	
	public int conn() {
		int rc = 0;
		try {
			//String property = System.getProperty("java.library.path");
			//System.out.println(property);
			System.loadLibrary("sbojni");
			company = SBOCOMUtil.newCompany();
			company.setServer("(WIN-I1A2FG7OBS9)");
			company.setCompanyDB("BARCAPRUEBAS");
			company.setUserName("manager");
			company.setPassword("Admin#2009");
			company.setDbServerType(SBOCOMConstants.BoDataServerTypes_dst_MSSQL2008);
			//company.setUseTrusted(new Boolean(false));
			company.setLanguage(SBOCOMConstants.BoSuppLangs_ln_Spanish_La);
			company.setDbUserName("sa");
			company.setDbPassword("B4rc4.2018");
			//company.setAddonIdentifier("...");	
			company.setLicenseServer("WIN-I1A2FG7OBS9");

			rc = company.connect();
			if (rc == 0) {
				System.out.println("Connected!");
			} else {
				errMsg = company.getLastError();
				System.out.println(
					"I cannot connect to database server: "
						+ errMsg.getErrorMessage()
						+ " "
						+ errMsg.getErrorCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return rc;

	}
	
	public static void main(String[] args) {
		DIAPI company = new DIAPI();		
		company.conn();
	}*/
}
