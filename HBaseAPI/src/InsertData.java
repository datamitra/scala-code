import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;

public class InsertData {
@SuppressWarnings("deprecation")
public static void main(String[] args) throws IOException {
	// Instantiating configuration class
	/*Configuration config = HBaseConfiguration.create();

    // Instantiating HTable class
	Connection connection = ConnectionFactory.createConnection(config);
    HTable table = new HTable("test", connection);
*/
    
    Configuration c = HBaseConfiguration.create(); // Instantiate Configuration class

    HTable hTable = new HTable(c, "student");       // Instantiate HTable class

    Put P1 = new Put(Bytes.toBytes("row1"));  // Instantiate put Class

    // accepts column family name, row name  and its value

    P1.add(Bytes.toBytes("college"),   Bytes.toBytes("id"),Bytes.toBytes("20"));

    P1.add(Bytes.toBytes("college"),Bytes.toBytes("name"),Bytes.toBytes("rishi"));

    hTable.put(P1);

    System.out.println("Data is inserted");       // Save the put Instance to the HTable.

    hTable.close();      // close HTable

    }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Instantiating Get class
    Get g = new Get(Bytes.toBytes("row1"));

    // Reading the data
    Result result = table.get(g);

    // Reading values from Result class object
    byte [] value = result.getValue(Bytes.toBytes("personal"),Bytes.toBytes("name"));

    byte [] value1 = result.getValue(Bytes.toBytes("personal"),Bytes.toBytes("city"));

    // Printing the values
    String name = Bytes.toString(value);
    String city = Bytes.toString(value1);
    
    System.out.println("name: " + name + " city: " + city);
}
}
