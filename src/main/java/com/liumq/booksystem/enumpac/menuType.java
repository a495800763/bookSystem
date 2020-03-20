package com.liumq.booksystem.enumpac;

public enum menuType {
    /**
     * 当前选项卡打开
     */
    THISTAB(0),
    /**
     * 新窗口打开
     */
    NEWWINDOW(1),
    /**
     * 其他窗口打开
     */
    OTHERWINDOW(2);


    private menuType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Integer type;

}
