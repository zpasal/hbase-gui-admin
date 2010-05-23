/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hbaseexplorer.domain;

/**
 *
 * @author zaharije
 */
public class FilterModel {
    private String family, column, value;
    private boolean enabled = true;

    public FilterModel() {
        family = "";
        column = "";
        value = "";
    }

    public boolean isEmpty() {
        return (!enabled) || (family.isEmpty() && column.isEmpty() && value.isEmpty());
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return getFamily() + ":" + getColumn() + "=" + getValue();
    }
    
}
