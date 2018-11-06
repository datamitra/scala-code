import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;

public class CreateTable {
public static void main(String[] args) throws IOException {
	// Instantiating configuration class
	Configuration config = HBaseConfiguration.create();
	TableName table1 = TableName.valueOf("emp");
	String family1 = "personal";
	String family2 = "professional";
	Connection connection = ConnectionFactory.createConnection(config);
			Admin admin = connection.getAdmin();
			
	HTableDescriptor tableDescriptor = new HTableDescriptor(table1);
	tableDescriptor.addFamily(new HColumnDescriptor(family1));
	tableDescriptor.addFamily(new HColumnDescriptor(family2));
	admin.createTable(tableDescriptor);
    // Execute the table through admin
  
    System.out.println(" Table created ");
    TableName[] tbls=admin.listTableNames();
    for(TableName name:tbls) {
    	System.out.println("Tables are--->::"+name.getNameAsString());
    }
}
}
