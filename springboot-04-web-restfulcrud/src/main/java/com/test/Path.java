package com.test;

import java.io.Serializable;
import java.util.List;

public class Path implements Serializable {
    private String carNumber;
    private List<Attribute> attributes;

    public Path() {
    }

    public Path(String carNumber, List<Attribute> attributes) {
        this.carNumber = carNumber;
        this.attributes = attributes;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Path{" +
                "carNumber='" + carNumber + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;

        Path path = (Path) o;

        if (getCarNumber() != null ? !getCarNumber().equals(path.getCarNumber()) : path.getCarNumber() != null) return false;
        return getAttributes() != null ? getAttributes().equals(path.getAttributes()) : path.getAttributes() == null;
    }

    @Override
    public int hashCode() {
        int result = getCarNumber() != null ? getCarNumber().hashCode() : 0;
        result = 31 * result + (getAttributes() != null ? getAttributes().hashCode() : 0);
        return result;
    }
}
