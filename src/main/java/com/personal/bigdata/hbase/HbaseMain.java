package com.personal.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Arrays;

/**
 * Need to have HBase Installed. Path argument expects $HBASE_HOME/conf/hbase-site.xml
 */
public class HbaseMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Please provide valid hbase-site.xml path");
            return;
        }
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(args[0]));
        Connection connection = ConnectionFactory.createConnection(config);
        TableName userTableName = TableName.valueOf("User");

        String rowKey = "uid1";
        // Column Family
        String nameCf = "Name";
        String addressCf = "Address";

        TableDescriptorBuilder desc = TableDescriptorBuilder.newBuilder(userTableName);
        ColumnFamilyDescriptor colDesc1 = ColumnFamilyDescriptorBuilder.newBuilder(nameCf.getBytes()).build();
        ColumnFamilyDescriptor colDesc2 = ColumnFamilyDescriptorBuilder.newBuilder(addressCf.getBytes()).build();
        desc.setColumnFamilies(Arrays.asList(colDesc1, colDesc2));

        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        if (!admin.tableExists(userTableName)) {
            admin.createTable(desc.build());
        }
        Table table = connection.getTable(userTableName);

        String firstNameCol = "firstName";
        String lastNameCol = "lastName";
        String streetCol = "street";
        String countryCol = "country";

        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(nameCf.getBytes(), firstNameCol.getBytes(), Bytes.toBytes("John"));
        put.addColumn(nameCf.getBytes(), lastNameCol.getBytes(), Bytes.toBytes("Doe"));
        put.addColumn(addressCf.getBytes(), streetCol.getBytes(), Bytes.toBytes("some street"));
        put.addColumn(addressCf.getBytes(), countryCol.getBytes(), Bytes.toBytes("Global"));
        table.put(put);

        Get get = new Get(rowKey.getBytes());
        Result result = table.get(get);
        while (result.advance()) {
            Cell cell = result.current();
            byte[] rowName = Bytes.copy(cell.getRowArray(),
                    cell.getRowOffset(),
                    cell.getRowLength());
            byte[] qualifierName = Bytes.copy(cell.getQualifierArray(),
                    cell.getQualifierOffset(),
                    cell.getQualifierLength());
            byte[] familyName = Bytes.copy(cell.getFamilyArray(),
                    cell.getFamilyOffset(),
                    cell.getFamilyLength());
            byte[] value = Bytes.copy(cell.getValueArray(),
                    cell.getValueOffset(),
                    cell.getValueLength());
            long timestamp = cell.getTimestamp();

            System.out.printf("Retrieved Cell in row: %s, column: %s, qualifier: %s, data: %s timestamp: %s\n",
                    new String(rowName),
                    new String(familyName),
                    new String(qualifierName),
                    new String(value),
                    timestamp
                    );
        }
        Delete delete = new Delete(rowKey.getBytes());
        table.delete(delete);
        table.close();
        admin.disableTable(userTableName);
        admin.deleteTable(userTableName);
        connection.close();
    }
}
