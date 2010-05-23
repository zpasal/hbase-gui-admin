package org.hbaseexplorer.domain;

/**
 *
 * @author zaharije
 */
public class HBTriplet {
    private byte[] family;
    private byte[] qualifier;
    private byte[] value;
    private boolean isChanged;

    public HBTriplet() {
    }

    public HBTriplet(byte[] family, byte[] qualifier, byte[] value) {
        this.qualifier = qualifier;
        this.value = value;
        this.family = family;
        this.isChanged = false;
    }

    public byte[] getQualifier() {
        return qualifier;
    }

    public String getQualifierString() {
        return byte2String(qualifier);
    }

    public void setQualifier(byte[] qualifier) {
        this.qualifier = qualifier;
    }

    public byte[] getValue() {
        return value;
    }

    public String getValueString() {
        return byte2String(value);
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public byte[] getFamily() {
        return family;
    }

    public String getFamilyString() {
        return byte2String(family);
    }

    public void setFamily(byte[] family) {
        this.family = family;
    }

    public static String byte2String(byte[] data) {
        return new String(data);
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    @Override
    public String toString() {
        return getFamilyString() + ":" + getQualifierString() + "=" + getValueString();
    }
}
