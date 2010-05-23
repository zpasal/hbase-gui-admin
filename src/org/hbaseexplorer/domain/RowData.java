package org.hbaseexplorer.domain;

import java.util.ArrayList;
import org.apache.hadoop.hbase.client.Put;

/**
 *
 * @author zaharije
 */
public class RowData {
    private ArrayList<HBTriplet> triplets;
    private byte[] rowKey;

    public RowData() {
        triplets = new ArrayList<HBTriplet>();
    }

    public RowData(byte[] rowKey) {
        triplets = new ArrayList<HBTriplet>();
        this.rowKey = rowKey;
    }

    public void add(HBTriplet column) {
        triplets.add(column);
    }

    public HBTriplet get(int index) {
        return triplets.get(index);
    }

    public int size() {
        return triplets.size();
    }

    public RowData getChangedData() {
        RowData changed = new RowData(rowKey);
        for(HBTriplet hbt : triplets) {
            if (hbt.isChanged()) {
                changed.add(hbt);
            }
        }
        return changed;
    }

    public Put convertToPut() {
        Put put = new Put(rowKey);

        RowData changedData = getChangedData();
        for(HBTriplet hbt : changedData.getTriplets()) {
            put.add(hbt.getFamily(), hbt.getQualifier(), hbt.getValue());
        }

        return put;
    }

    public ArrayList<HBTriplet> getTriplets() {
        return triplets;
    }

    public String getRowKeyString() {
        return new String(rowKey);
    }

    public byte[] getRowKey() {
        return rowKey;
    }

    public void setRowKey(byte[] rowKey) {
        this.rowKey = rowKey;
    }


}
