package org.hbaseexplorer.components;

import java.util.ArrayList;
import javax.swing.JTabbedPane;
import org.hbaseexplorer.domain.Table;

/**
 *
 * @author zaharije
 */
public class DataTabPane extends JTabbedPane {

    private ArrayList<Table> tables;

    public DataTabPane() {
        super();

        tables = new ArrayList<Table>();
    }

    public void showTable(Table table) {
        int index = tableExists(table);

        if (index == -1) {
            tables.add(table);
            this.addTab(table.getFullName(), new EditTableData(table));
            index = tables.size() - 1;
        }

        this.getModel().setSelectedIndex(index);
    }

    public int tableExists(Table table) {
        for (int i=0; i<tables.size(); i++) {
            Table t = tables.get(i);
            if (t.getFullName().equals(table.getFullName())) {
                return i;
            }
        }
        return -1;
    }
}
