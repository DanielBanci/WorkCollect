package com.example.workcollect.ui.store;

public class StoreItem {
    private Long lId;
    private String strTitle;
    private Integer iValue;
    private String strItemTypeMapKey;

    public StoreItem(Long lId, String strTitle, Integer iValue, String strItemTypeMapKey){
        this.lId = lId;
        this.strTitle = strTitle;
        this.iValue = iValue;
        this.strItemTypeMapKey = strItemTypeMapKey;
    }

    public StoreItem(Long lId, String strTitle, String strItemTypeMapKey){
        this.lId = lId;
        this.strTitle = strTitle;
        this.iValue = -1;
        this.strItemTypeMapKey = strItemTypeMapKey;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public Integer getiValue() {
        return iValue;
    }

    public void setiValue(Integer iValue) {
        this.iValue = iValue;
    }

    public String getStrItemTypeMapKey() {
        return strItemTypeMapKey;
    }

    public void setStrItemTypeMapKey(String strItemTypeMapKey) {
        this.strItemTypeMapKey = strItemTypeMapKey;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }
}
