package com.baeldung.lambda.shipping;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Checkin {
    private String timeStamp;
    private String location;

    @Column(name = "timestamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
