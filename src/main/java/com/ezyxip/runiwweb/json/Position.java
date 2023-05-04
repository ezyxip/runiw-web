package com.ezyxip.runiwweb.json;

public class Position {
    String type;
    String count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Position{" +
                "type='" + type + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
