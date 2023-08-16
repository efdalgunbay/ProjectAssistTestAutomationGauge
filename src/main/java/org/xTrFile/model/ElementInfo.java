package org.xTrFile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementInfo {

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("byType")
    @Expose
    private String byType;

    public String getKey() {return key;}

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getByType() {return byType;}

    public void setByType(String byType) {this.byType = byType;}
}
