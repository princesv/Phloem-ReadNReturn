package com.prince.phloem;

public class Status {
    private String idNum;
    private int s;


    public Status(){}

    public Status(String idNum, int s) {
        this.idNum = idNum;
        this.s = s;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String id) {
        this.idNum = id;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

}
