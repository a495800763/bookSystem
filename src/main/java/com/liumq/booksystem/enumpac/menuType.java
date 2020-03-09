package com.liumq.booksystem.enumpac;

public enum menuType {
    THISTAB(0),
    NEWWINDOW(1),
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
