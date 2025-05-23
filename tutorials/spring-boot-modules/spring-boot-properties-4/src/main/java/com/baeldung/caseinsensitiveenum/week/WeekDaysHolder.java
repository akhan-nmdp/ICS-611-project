package com.baeldung.caseinsensitiveenum.week;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeekDaysHolder {

    @Value("${monday}")
    private WeekDays monday;

    @Value("${tuesday}")
    private WeekDays tuesday;

    @Value("${wednesday}")
    private WeekDays wednesday;

    @Value("${thursday}")
    private WeekDays thursday;

    @Value("${friday}")
    private WeekDays friday;

    @Value("${saturday}")
    private WeekDays saturday;

    @Value("${sunday}")
    private WeekDays sunday;

    public WeekDays getMonday() {
        return monday;
    }

    public void setMonday(final WeekDays monday) {
        this.monday = monday;
    }

    public WeekDays getTuesday() {
        return tuesday;
    }

    public void setTuesday(final WeekDays tuesday) {
        this.tuesday = tuesday;
    }

    public WeekDays getWednesday() {
        return wednesday;
    }

    public void setWednesday(final WeekDays wednesday) {
        this.wednesday = wednesday;
    }

    public WeekDays getThursday() {
        return thursday;
    }

    public void setThursday(final WeekDays thursday) {
        this.thursday = thursday;
    }

    public WeekDays getFriday() {
        return friday;
    }

    public void setFriday(final WeekDays friday) {
        this.friday = friday;
    }

    public WeekDays getSaturday() {
        return saturday;
    }

    public void setSaturday(final WeekDays saturday) {
        this.saturday = saturday;
    }

    public WeekDays getSunday() {
        return sunday;
    }

    public void setSunday(final WeekDays sunday) {
        this.sunday = sunday;
    }
}
