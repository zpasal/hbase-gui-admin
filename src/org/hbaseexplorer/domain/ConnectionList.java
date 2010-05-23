package org.hbaseexplorer.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zaharije
 */
public class ConnectionList implements Serializable {

    private ArrayList<Connection> connectionList;

    public ConnectionList() {
        connectionList = new ArrayList<Connection>();
    }

    public void addConnection(Connection conn) {
        connectionList.add(conn);
    }

}
