package org.hbaseexplorer.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.hbaseexplorer.exception.ExplorerException;

/**
 *
 * @author zaharije
 */
public class Connection implements Serializable {
    private HBaseConfiguration hbaseConfiguration;

    private HBaseAdmin hbaseAdmin;

    private ArrayList<Table> tableList;

    public Connection(Configuration configuration) {
        hbaseConfiguration = new HBaseConfiguration(configuration);
        tableList = new ArrayList<Table>();
    }

    public void connect() {
        try {
            hbaseAdmin = new HBaseAdmin(hbaseConfiguration);
            refreshTables();
        }
        catch(MasterNotRunningException me) {
            throw new ExplorerException("Cannot connect to " + hbaseConfiguration.get("hbase.zookeeper.quorum"));
        }
    }

    public void refreshTables() {
        try {
            tableList = new ArrayList<Table>();
            HTableDescriptor hTables[] = hbaseAdmin.listTables();

            for(HTableDescriptor tableDescriptor : hTables) {
                tableList.add(new Table(tableDescriptor, this));
            }
        }
        catch(IOException ioe) {
            throw new ExplorerException("Error while getting table list!");
        }
    }

    public String getName() {
        return hbaseConfiguration.get("hbase.zookeeper.quorum");
    }
    
    public ArrayList<Table> getTableList() {
        return tableList;
    }

    public HBaseConfiguration getHbaseConfiguration() {
        return hbaseConfiguration;
    }

    public HBaseAdmin getHbaseAdmin() {
        return hbaseAdmin;
    }

    public String toString() {
        return getName();
    }
}
