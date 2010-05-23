package org.hbaseexplorer.exception;

import javax.swing.JOptionPane;

/**
 *
 * @author zaharije
 */
public class ExplorerException extends RuntimeException {
    public ExplorerException(String msg) {
        super(msg);
        JOptionPane.showMessageDialog(null, msg);
    }
}
