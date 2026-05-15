package com.railway.ticket.management.system.domain;

import com.railway.ticket.management.system.domain.enums.CoachType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Coach {
    private int id;
    @Positive
    private int trainId;
    @NotNull
    private CoachType coachType;
    @Positive
    private int capacity;
    @Positive
    private float baseFare;

    public Coach(int id, int trainId, CoachType coachType, int capacity, float baseFare) {
        setId(id);
        setTrainId(trainId);
        setCoachType(coachType);
        setCapacity(capacity);
        setBaseFare(baseFare);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTrainId() { return trainId; }
    public void setTrainId(int trainId) { this.trainId = trainId; }
    public CoachType getCoachType() { return coachType; }
    public void setCoachType(CoachType coachType) { this.coachType = coachType; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public float getBaseFare() { return baseFare; }
    public void setBaseFare(float baseFare) { this.baseFare = baseFare; }
}
