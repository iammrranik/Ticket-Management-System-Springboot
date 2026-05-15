package com.railway.ticket.management.system.domain;

import jakarta.validation.constraints.NotBlank;

public class Station {
    private int id;
    @NotBlank
    private String stationCode;
    @NotBlank
    private String stationName;
    @NotBlank
    private String city;

    public Station(int id, String stationCode, String stationName, String city) {
        setId(id);
        setStationCode(stationCode);
        setStationName(stationName);
        setCity(city);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStationCode() { return stationCode; }
    public void setStationCode(String stationCode) { this.stationCode = stationCode; }
    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
