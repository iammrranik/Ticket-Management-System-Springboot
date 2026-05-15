package com.railway.ticket.management.system.domain;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class TrainStatus {
    private int id;
    @Positive
    private int trainId;
    private int currentStationId;
    private int nextStationId;
    private String status;
    private LocalDateTime lastUpdated;

    public TrainStatus(int id, int trainId, int currentStationId, int nextStationId, String status, LocalDateTime lastUpdated) {
        setId(id);
        setTrainId(trainId);
        setCurrentStationId(currentStationId);
        setNextStationId(nextStationId);
        setStatus(status);
        setLastUpdated(lastUpdated);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTrainId() { return trainId; }
    public void setTrainId(int trainId) { this.trainId = trainId; }
    public int getCurrentStationId() { return currentStationId; }
    public void setCurrentStationId(int currentStationId) { this.currentStationId = currentStationId; }
    public int getNextStationId() { return nextStationId; }
    public void setNextStationId(int nextStationId) { this.nextStationId = nextStationId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
