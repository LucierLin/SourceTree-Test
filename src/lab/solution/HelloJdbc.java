package lab.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
// 第一個JDBC程式，本程式可以
// 1. 刪除(DROP) Product表格
// 2. 能夠新建(CREATE) Product表格
// 3. 能夠新增(INSERT INTO)三筆Product紀錄

public class HelloJdbc {
	public static void main(String[] args) {
		
		// 刪除(DROP) Product表格，如果Product表格不存在就不刪除Product表格
		String dropTable = "DROP TABLE IF EXISTS Product;";

		// 能夠新建(CREATE) Product表格
		String createTable = "CREATE TABLE  Product (" 
				+ " PRODUCT_ID 		INT 	NOT  NULL  AUTO_INCREMENT, "
				+ " PRODUCT_NAME	VARCHAR(40), " 
				+ " PRICE		 	DECIMAL(9, 1), " 
				+ " QUANTITY   		INT, "
				+ " PRODUCT_DATE	DateTime, " 
				+ " IMAGE			LONGBLOB, " 
				+ " REMARK			LONGTEXT, "
				+ " PRIMARY KEY(PRODUCT_ID)" 
				+ " ) ENGINE=INNODB;";

		// 能夠新增(INSERT INTO)三筆Product紀錄 :
		String insert = "INSERT INTO Product (PRODUCT_ID, PRODUCT_NAME, PRICE, QUANTITY, PRODUCT_DATE)" 
				+ " VALUES"
				+ " (NULL, '氣墊運動鞋', 3500.0, 18, '2015-10-25 17:21:40'), "
				+ " (NULL, '登山杖', 1700.0, 25, '2016-3-17 10:16:23'),"
				+ " (NULL, '登山背包', 2500.0, 10, '2017-1-20 13:45:30');";

		String url = "jdbc:mysql://localhost:3306/jdbcdb" 
		+ "?useSSL=true&useUnicode=yes&characterEncoding=UTF-8";
		int n = -1;
		try (
			// 建立Java程式與資料庫的連線
			Connection conn = DriverManager.getConnection(url, "root", "password");
			// 由Connection物件來取得java.sql.Statement家族的物件
			Statement stmt = conn.createStatement();
		) {
			// 執行該Statement物件的 executeUpdate(SQL命令)方法來刪除表格
			n = stmt.executeUpdate(dropTable);
			if (n==0)
			System.out.println("刪除表格Product成功, n=" + n);

			// 執行該Statement物件的 executeUpdate(SQL命令)方法來新建表格
			n = stmt.executeUpdate(createTable);
			if (n==0)
			System.out.println("新建表格Product成功, n=" + n);

			// 執行該Statement物件的 executeUpdate(SQL命令)方法來新建表格
			n = stmt.executeUpdate(insert);
			if (n <= 0){
		    	System.out.println("尚未修改程式");
		    } else {
		    	System.out.println("新增表格Product的紀錄成功, n=" + n);
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
