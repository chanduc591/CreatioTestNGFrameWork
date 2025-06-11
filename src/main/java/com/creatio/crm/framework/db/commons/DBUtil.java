package com.creatio.crm.framework.db.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	
	//Common method to connect with DB and get the raw data
	public static ResultSet getData(String query) throws SQLException {
		ResultSet dataSet = null;
		String db_url="jdbc:postgresql://localhost:5432/dvdshop";
		String db_user="postgres";
		String db_password="admin";
		Connection connection = DriverManager.getConnection(db_url, db_user, db_password);		
		dataSet =connection.createStatement().executeQuery(query);
		return dataSet;
	}
	
	//Common method to convert the data into List of HashMap
	public static List<Map<String, String>> readData(String query) throws SQLException {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		
		//get the raw data from DB
		ResultSet dataSet = getData(query);
		
		//copy the data into List of HashMap
		while(dataSet.next()) {  //verify next row is available and if yes, move to that row
			Map<String, String> rowData = new HashMap<String, String>();
			for(int c=1;c<=dataSet.getMetaData().getColumnCount();c++) { //loop through all columns
				String columnName = dataSet.getMetaData().getColumnName(c); //get the column name
				String columnValue = dataSet.getString(c); //get the column value
				rowData.put(columnName, columnValue); //put the column name and value into map
			}
			dataList.add(rowData); //add the map to the list
		}
		
		return dataList;
	}

}
