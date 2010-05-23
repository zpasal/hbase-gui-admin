package org.hbaseexplorer.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.apache.hadoop.conf.Configuration;
import org.hbaseexplorer.HBaseExplorerView;
import org.hbaseexplorer.domain.Connection;
import org.hbaseexplorer.domain.Table;

/**
 *
 * @author zaharije
 */
public class ConnectionTree extends JTree {
    private Table table;
    private HBaseExplorerView mainApp;

    public ConnectionTree() {
        super();

        // Double click handler
        addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int selRow = getRowForLocation(e.getX(), e.getY());
                    TreePath selPath = getPathForLocation(e.getX(), e.getY());
                    if(selRow != -1) {
                        if(e.getClickCount() == 1) {
                        //    mySingleClick(selRow, selPath);
                        }
                        else if(e.getClickCount() == 2) {
                          //  myDoubleClick(selRow, selPath);
                          doubleClick(selPath);
                        }
                    }
                }
            }
        );
    }

    public void createConnection(Configuration conf) {
        Connection conn = new Connection(conf);
        conn.connect();
        addConnectionToTree(conn);
    }

    public void doubleClick(TreePath selectionPath) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)selectionPath.getLastPathComponent();

        Object userObject = selectedNode.getUserObject();
        if (userObject instanceof Table) {
            mainApp.getTabPane().showTable((Table)userObject);
        }
    }


    private void addConnectionToTree(Connection conn) {
        DefaultTreeModel defTreeModel = (DefaultTreeModel)getModel();

        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)getModel().getRoot();

        DefaultMutableTreeNode nameNode = new DefaultMutableTreeNode(conn.getName(), true);
        DefaultMutableTreeNode tablesNode = new DefaultMutableTreeNode("Tables", true);
        DefaultMutableTreeNode confNode = new DefaultMutableTreeNode("Configuration", true);

        nameNode.setUserObject(conn);
        nameNode.add(tablesNode);
        nameNode.add(confNode);

        for(Table mtable : conn.getTableList()) {
            DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(mtable.getName(), true);
            tablesNode.add(tableNode);
            tableNode.setUserObject(mtable);
        }

        rootNode.add(nameNode);
        defTreeModel.setRoot(rootNode);

        for(int i=0; i<getRowCount(); i++) {
            expandRow(i);
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public HBaseExplorerView getMainApp() {
        return mainApp;
    }

    public void setMainApp(HBaseExplorerView mainApp) {
        this.mainApp = mainApp;
    }
}
