package com.test;

import java.io.Serializable;
import java.util.Date;

public class Attribute implements Serializable {
    private Date timestamp;
    private String cameraId;
    private Double posX;
    private Double posY;

    public Attribute() {
    }

    public Attribute(Date timestamp, String cameraId, Double posX, Double posY) {
        this.timestamp = timestamp;
        this.cameraId = cameraId;
        this.posX = posX;
        this.posY = posY;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

        if (getTimestamp() != null ? !getTimestamp().equals(attribute.getTimestamp()) : attribute.getTimestamp() != null) return false;
        if (getCameraId() != null ? !getCameraId().equals(attribute.getCameraId()) : attribute.getCameraId() != null) return false;
        if (getPosX() != null ? !getPosX().equals(attribute.getPosX()) : attribute.getPosX() != null) return false;
        return getPosY() != null ? getPosY().equals(attribute.getPosY()) : attribute.getPosY() == null;
    }

    @Override
    public int hashCode() {
        int result = getTimestamp() != null ? getTimestamp().hashCode() : 0;
        result = 31 * result + (getCameraId() != null ? getCameraId().hashCode() : 0);
        result = 31 * result + (getPosX() != null ? getPosX().hashCode() : 0);
        result = 31 * result + (getPosY() != null ? getPosY().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "timestamp=" + timestamp +
                ", cameraId='" + cameraId + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
