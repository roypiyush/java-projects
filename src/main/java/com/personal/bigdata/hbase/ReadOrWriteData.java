package com.personal.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Need to have HBase Installed. Path argument expects
 * $HBASE_HOME/conf/hbase-site.xml
 */
public class ReadOrWriteData {
    private static final String NAME_CF = "Name";
    private static final String FNAME_COL = "firstName";
    private static final String LNAME_COL = "lastName";

    private static final String ADDRESS_CF = "Address";
    private static final String STREET_COL = "street";
    private static final String COUNTRY_COL = "country";

    private static final String INCOME_CF = "Income";
    private static final String SOURCE_COL = "source";
    private static final String SALARY_COL = "salary";

    private static final String rowKeyFormat = "uid%015d";

    private static final Random RANDOM = new Random();

    enum SOURCE {
        SALARY,
        FIXED_DEPOSIT,
        OTHER_SOURCES
    }

    public static void main(final String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Please provide valid hbase-site.xml path");
            return;
        }
        final Connection connection = getConnection(args);
        final HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

        final TableName tableName = createTableIfNotExists(admin, "User");
        final Table table = connection.getTable(tableName);

        final long startTime = System.currentTimeMillis();
        // for (long id = 0; id < 3000000; id++) {
        // putData(table, row(id), getFName(), getLName(), getStreet(), getCountry());
        // }

        scanTable(table);

        System.out.println("Elapsed : " + (System.currentTimeMillis() - startTime) + "ms");
        connection.close();
    }

    private static TableDescriptorBuilder prepareTableDescription(final TableName tableName) {
        final TableDescriptorBuilder desc = TableDescriptorBuilder.newBuilder(tableName);
        desc.setCompactionEnabled(true);
        desc.setColumnFamilies(Arrays.asList(
                ColumnFamilyDescriptorBuilder.newBuilder(NAME_CF.getBytes()).build(),
                ColumnFamilyDescriptorBuilder.newBuilder(ADDRESS_CF.getBytes()).build(),
                ColumnFamilyDescriptorBuilder.newBuilder(INCOME_CF.getBytes()).build()));
        return desc;
    }

    private static TableName createTableIfNotExists(final HBaseAdmin admin,
            final String tableName)
            throws IOException {

        final TableName tableObject = TableName.valueOf(tableName);
        if (!admin.tableExists(tableObject)) {
            final TableDescriptorBuilder desc = prepareTableDescription(tableObject);
            admin.createTable(desc.build());
        }
        return tableObject;
    }

    private static Connection getConnection(final String[] args) throws IOException {
        final Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(args[0]));
        final Connection connection = ConnectionFactory.createConnection(config);
        return connection;
    }

    private static void readColumns(final Table table, final long id) throws IOException {
        final Get get = new Get(row(id).getBytes());
        final Result result = table.get(get);
        while (result.advance()) {
            final Cell cell = result.current();
            final String rowName = getRowName(cell);
            final String qualifierName = getQualifierName(cell);
            final String familyName = getFamilyName(cell);
            final String value = getValue(cell);
            final long timestamp = cell.getTimestamp();

            System.out.printf("Retrieved Cell in row: %s, column: %s, qualifier: %s, data: %s timestamp: %s\n",
                    new String(rowName),
                    new String(familyName),
                    new String(qualifierName),
                    new String(value),
                    timestamp);
        }
    }

    private static String getValue(final Cell cell) {
        return new String(Bytes.copy(cell.getValueArray(),
                cell.getValueOffset(),
                cell.getValueLength()));
    }

    private static String getFamilyName(final Cell cell) {
        return new String(Bytes.copy(cell.getFamilyArray(),
                cell.getFamilyOffset(),
                cell.getFamilyLength()));
    }

    private static String getQualifierName(final Cell cell) {
        return new String(Bytes.copy(cell.getQualifierArray(),
                cell.getQualifierOffset(),
                cell.getQualifierLength()));
    }

    private static String getRowName(final Cell cell) {
        final String rowName = new String(Bytes.copy(cell.getRowArray(),
                cell.getRowOffset(),
                cell.getRowLength()));
        return rowName;
    }

    private static void scanTable(final Table table) throws IOException {
        final Scan scan = new Scan();
        scan.addColumn(INCOME_CF.getBytes(), SALARY_COL.getBytes());
        final SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(
                INCOME_CF.getBytes(),
                SALARY_COL.getBytes(),
                CompareOperator.GREATER_OR_EQUAL,
                Bytes.toBytes(0));
        scan.setFilter(singleColumnValueFilter);
        final ResultScanner scanner = table.getScanner(scan);
        final Iterator<Result> iterator = scanner.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            final Result next = iterator.next();
            count++;
            while (next.advance()) {
                final Cell current = next.current();
                getFamilyName(current);
                final String rowKey = getRowName(current);
                getQualifierName(current);
                final String value = getValue(current);
                // System.out.printf("Got value key=%s value=%s\n", rowKey, value);
            }
        }
        System.out.printf("Records read %s \n", count);
    }

    private static void deleteRow(final String rowKey, final Table table, final HBaseAdmin admin,
            final TableName tableName) throws IOException {
        final Delete delete = new Delete(rowKey.getBytes());
        table.delete(delete);
        table.close();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    private static void putData(final Table table,
            final String rowKey,
            final String fname,
            final String lname,
            final String street,
            final String country) throws IOException {
        final Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(NAME_CF.getBytes(), FNAME_COL.getBytes(), Bytes.toBytes(fname));
        put.addColumn(NAME_CF.getBytes(), LNAME_COL.getBytes(), Bytes.toBytes(lname));
        put.addColumn(ADDRESS_CF.getBytes(), STREET_COL.getBytes(), Bytes.toBytes(street));
        put.addColumn(ADDRESS_CF.getBytes(), COUNTRY_COL.getBytes(), Bytes.toBytes(country));
        final SOURCE value = SOURCE.values()[RANDOM.nextInt(SOURCE.values().length)];
        put.addColumn(INCOME_CF.getBytes(), SOURCE_COL.getBytes(), Bytes.toBytes(value.name()));
        put.addColumn(INCOME_CF.getBytes(), SALARY_COL.getBytes(), Bytes.toBytes(RANDOM.nextLong()));
        table.put(put);
    }

    private static String getFName() {
        return String.format("John %s", Math.abs(RANDOM.nextGaussian()));
    }

    private static String getLName() {
        return String.format("Doe %s", Math.abs(RANDOM.nextGaussian()));
    }

    private static String getStreet() {
        return String.format("Street %s", Math.abs(RANDOM.nextGaussian()));
    }

    private static String getCountry() {
        return String.format("Global %s", Math.abs(RANDOM.nextGaussian()));
    }

    private static String row(final long id) {
        return String.format(rowKeyFormat, id);
    }
}
