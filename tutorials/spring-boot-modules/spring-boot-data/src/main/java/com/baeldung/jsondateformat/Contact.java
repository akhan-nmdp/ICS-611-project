package com.baeldung.jsondateformat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contact {

    private String name;
    private String address;
    private String phone;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Contact() {
    }

    public Contact(String name, String address, String phone, LocalDate birthday, LocalDateTime lastUpdate) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.lastUpdate = lastUpdate;
    }
}
