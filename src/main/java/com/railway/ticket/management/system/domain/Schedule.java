package com.railway.ticket.management.system.domain;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class Schedule {
    private int id;
    @Positive
    private int trainId;
    private int sourceStationId;
    private int destinationStationId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Schedule(int id, int trainId, int sourceStationId, int destinationStationId,
                    LocalDateTime departureTime, LocalDateTime arrivalTime) {
        setId(id);
        setTrainId(trainId);
        setSourceStationId(sourceStationId);
        setDestinationStationId(destinationStationId);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTrainId() { return trainId; }
    public void setTrainId(int trainId) { this.trainId = trainId; }
    public int getSourceStationId() { return sourceStationId; }
    public void setSourceStationId(int sourceStationId) { this.sourceStationId = sourceStationId; }
    public int getDestinationStationId() { return destinationStationId; }
    public void setDestinationStationId(int destinationStationId) { this.destinationStationId = destinationStationId; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
}
