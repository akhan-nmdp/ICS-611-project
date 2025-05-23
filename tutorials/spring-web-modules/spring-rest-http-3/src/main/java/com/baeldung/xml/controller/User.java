package com.baeldung.xml.controller;

import java.util.Objects;

public class User {

    private Long id;
    private String firstName;
    private String secondName;
    public User() {
    }

    public User(final Long id, final String firstName, final String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final User user = (User) o;

        if (!Objects.equals(id, user.id)) {
            return false;
        }
        if (!Objects.equals(firstName, user.firstName)) {
            return false;
        }
        return Objects.equals(secondName, user.secondName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", secondName='" + secondName + '\'' +
               '}';
    }
}
