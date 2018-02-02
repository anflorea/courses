package com.example.exam.flo.examproject.domain;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by flo on 02/02/2018.
 */

public class Record {

    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("patientId")
    private Integer patientId;

    @Expose
    @SerializedName("details")
    private String details;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("date")
    private Integer date;

    public Record() {}

    public Record(Integer id, Integer patientId, String type, String details, String status, Integer date) {
        this.id = id;
        this.patientId = patientId;
        this.type = type;
        this.details = details;
        this.status = status;
        this.date = date;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (id != null ? !id.equals(record.id) : record.id != null) return false;
        if (type != null ? !type.equals(record.type) : record.type != null) return false;
        if (details != null ? !details.equals(record.details) : record.details != null)
            return false;
        if (status != null ? !status.equals(record.status) : record.status != null) return false;
        return date != null ? date.equals(record.date) : record.date == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}
