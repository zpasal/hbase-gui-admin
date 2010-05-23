package org.hbaseexplorer.domain;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HTable;
import org.hbaseexplorer.exception.ExplorerException;

/**
 *
 * @author zaharije
 */
public class Table implements Serializable {

    private HTableDescriptor tableDescriptor;
    private Connection connection;
    private HTable hTable;

    public Table(HTableDescriptor tableDescriptor, Connection connection) {
        this.tableDescriptor = tableDescriptor;
        this.connection = connection;
        hTable = null;
    }

    public Connection getConnection() {
        return connection;
    }

    public HTable getHTable() {
        try {
            if (hTable == null) {
                hTable = new HTable(connection.getHbaseConfiguration(), tableDescriptor.getName());
                return hTable;
            }
            else {
                return hTable;
            }
        }
        catch (IOException ioe) {
            throw new ExplorerException("Error creating HTable for table " + getFullName());
        }
    }

    public String getName() {
        return new String(tableDescriptor.getName(), Charset.forName("UTF8"));
    }

    public String getFullName() {
        return getName() + "@" + connection.getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}
