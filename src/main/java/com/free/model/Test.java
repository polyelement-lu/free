package com.free.model;

import java.util.Date;
import javax.persistence.*;

public class Test {
    @Id
    private String id;

    private String name;

    private String care;

    private Date time;

    @Column(name = "date_time")
    private Date dateTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return care
     */
    public String getCare() {
        return care;
    }

    /**
     * @param care
     */
    public void setCare(String care) {
        this.care = care;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return date_time
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}