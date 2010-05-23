/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbaseexplorer.components.renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.hbaseexplorer.datamodels.EditTableDataModel;

/**
 *
 * @author zaharije
 */
public class EditTableCellRenderer extends DefaultTableCellRenderer {

    public EditTableCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        EditTableDataModel model =  (EditTableDataModel)table.getModel();

        if (model.isChanged(row)) {
            comp.setBackground(Color.gray);
        } else {
            if (!isSelected) {
                comp.setBackground(null);
            }
        }

        return (comp);
    }
}
