/*
 * Copyright (c) 2024 Sunil
 * Automation Framework Selenium
 */

package com.tavant.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tavant.constants.FrameworkConstants;
import com.tavant.report.ExtentReportManager;
import com.tavant.utils.LogUtils;

public class DatabaseHelpers {
	private Statement statement;
	private String sConnectionUrlPV;
	private String sUserNamePV;
	private String sPasswordPV;
	private String sPVOConnectionUrl;
	private String sPVOUserName;
	private String sPVOPassword;
	ExcelHelpers excel = new ExcelHelpers();
    public DatabaseHelpers() {
        super();
        excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Common");
      
      		sPVOConnectionUrl = excel.getCellData("PVODBConnectionURL", "value");//client.testData.GetTestData("Common_PVODBConnectionURL");
      		sPVOUserName = excel.getCellData("PVODBUserName", "value");//client.testData.GetTestData("Common_PVODBUserName");
      		sPVOPassword = excel.getCellData("PVODBPassword", "value");//client.testData.GetTestData("Common_PVODBPassword");
      		
      		
      		sConnectionUrlPV = excel.getCellData("DBConnectionURLPV", "value");//client.testData.GetTestData("Common_DBConnectionURLPV");
      		sUserNamePV = excel.getCellData("DBUserNamePV", "value");//client.testData.GetTestData("Common_DBUserNamePV");
      		sPasswordPV = excel.getCellData("DBPasswordPV", "value");//client.testData.GetTestData("Common_DBPasswordPV");
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {

        
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);

        return conn;
    }
    public Connection EstablishDBConnection(String sConnectionUrl, String sUserName, String sPassword) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection=DriverManager.getConnection(sConnectionUrl,sUserName,sPassword);
		statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
		return connection;
	}
    
    
    public static Connection getOracleConnection(String hostName, String port, String dbName, String userName, String password) throws SQLException {
        // Define the Oracle connection URL (TNS format is commonly used)
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":" + port + ":" + dbName;
        
        try {
            // Load Oracle JDBC driver (for older JDBC versions, this step is needed)
            Class.forName("oracle.jdbc.OracleDriver");

            // Create and return the connection to the database
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);

            return conn;
        } catch (ClassNotFoundException e) {
            // Handle driver not found
            System.err.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("Oracle JDBC Driver not found.", e);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            System.err.println("Error while connecting to Oracle DB.");
            e.printStackTrace();
            throw e;
        }
    }
    
    public void fnVerifyPVOTablesUsingEmail(String sEmail,String sTableName) throws Exception
	{
    	
        excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Common");
		ArrayList sOffer_Patron_ID,sOrgin_Type_ID;
		String [][]DBResults = fnGetQueryResultFromDB("pvo.patron_info.email='"+sEmail+"'",sTableName,"*");
		if(DBResults.length >0 )
		{

			sOffer_Patron_ID = fnValueFetchDB(DBResults,"PATRON_ID");
			sOrgin_Type_ID = fnValueFetchDB(DBResults,"ORIGIN_TYPE_ID");

			String sOfferPatronVal =(String) sOffer_Patron_ID.get(0);
			String sOrginTypeIDVal =(String) sOrgin_Type_ID.get(0);
			if(sOffer_Patron_ID.size()==1 )
			{
				ExtentReportManager.pass("Table is displayed with expected value as: "+sEmail+" for corresponding patron id :"+sOfferPatronVal);
			} else {
				LogUtils.info("Table is not displayed with expected value as: "+sEmail+" for corresponding patron id :"+sOfferPatronVal);
			} 
			if(sOrginTypeIDVal.equals(excel.getCellData("PAYMENT_CODE_MASTER", "value")))
			{
				ExtentReportManager.pass("Table is displayed with expected value as: "+excel.getCellData("PAYMENT_CODE_MASTER", "value")+"\nAcutal Fetch Value from DB :"+sOrginTypeIDVal);

			} else {
				LogUtils.info("Table is displayed with expected value as: "+excel.getCellData("PAYMENT_CODE_MASTER", "value")+"\nAcutal Fetch Value from DB :"+sOrginTypeIDVal);
			} 
		}
	}
    
    public String[][] fnGetQueryResultFromDB(String sConditionParameter, String sTableName, String sParameterToRetrieve) throws Exception
	{
		int RwCnt=0,ColCnt=0;
		String [][]dataarray=null;
		String sQueryBuilder;
		
		System.out.println("Connection info for PVO patron and diposition" + sPVOConnectionUrl + sPVOUserName + sPVOPassword);
		Connection connection= EstablishDBConnection(sPVOConnectionUrl,sPVOUserName,sPVOPassword);
		
		if (connection != null) 
		{
			try
			{
				if(sConditionParameter.isEmpty()) {
					sQueryBuilder ="select "+sParameterToRetrieve+" from "+sTableName;
					System.out.println("Query: "+sQueryBuilder);
				}
				else {
					sQueryBuilder ="select "+sParameterToRetrieve+" from "+sTableName+" where "+sConditionParameter;
					System.out.println("Query: "+sQueryBuilder);
				}
				ResultSet resultSet=statement.executeQuery(sQueryBuilder);
				ResultSetMetaData val = resultSet.getMetaData();
				ColCnt = val.getColumnCount();
				ColCnt=ColCnt+1;			
				resultSet.last();
				RwCnt = resultSet.getRow();
				resultSet.beforeFirst();
				dataarray=new String[RwCnt+1][ColCnt];
				for(int colN =1;colN<ColCnt;colN++) {
					dataarray[0][colN] = val.getColumnName(colN);
				}
				for(int row=0;row<RwCnt;row++) {
					resultSet.next();
					for(int col=1;col<ColCnt;col++) {
						dataarray[row+1][col]=resultSet.getString(col);
					}
				}
				connection.close();
			}
			catch(Exception e)
			{
				LogUtils.info("Error Message: "+e);
			}
		}
		else {
			LogUtils.info("Failed to make connection!");
		}
		return dataarray;
	}
    
    public ArrayList  fnValueFetchDB(String [][] DataArrayResult, String sColumnName)
	{
		ArrayList<String> list=new ArrayList<String>();
		int sLengthofSubArray = 0;
		int sIterator =0;
		
		System.out.println("DataArrayResult.length: "+DataArrayResult.length);
		if(DataArrayResult.length >0) 
		{
//			client.ValidateTest(true, "DB results are processed with total number of rows: "+DataArrayResult.length);
			String subArray[] = DataArrayResult[0]; 
			sLengthofSubArray =subArray.length;
			for (sIterator = 0; sIterator < sLengthofSubArray; sIterator ++)
			{
				String sitem = subArray[sIterator];
				if((sitem != null) && (sitem.equals(sColumnName)))
				{	
					ExtentReportManager.pass(sColumnName +" is getting displayed in output result");
					break;
				}
			}	   	    
			for (int sSecondIter = 1; sSecondIter < DataArrayResult.length; sSecondIter ++)
			{
				String subArray1[] = DataArrayResult[sSecondIter]; 
				String sOutputValue = subArray1[sIterator];  	
				list.add(sOutputValue);
			}
		}
		else
		{
			LogUtils.info("No record found in database");
		}
		return list;
	}

}
