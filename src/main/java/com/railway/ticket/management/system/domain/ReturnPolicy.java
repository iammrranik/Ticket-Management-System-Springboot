package com.railway.ticket.management.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ReturnPolicy {
    private int id;
    @NotBlank
    private String policyName;
    @Positive
    private int hoursBeforeDeparture;
    @Positive
    private float deductionPercentage;

    public ReturnPolicy(int id, String policyName, int hoursBeforeDeparture, float deductionPercentage) {
        setId(id);
        setPolicyName(policyName);
        setHoursBeforeDeparture(hoursBeforeDeparture);
        setDeductionPercentage(deductionPercentage);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }
    public int getHoursBeforeDeparture() { return hoursBeforeDeparture; }
    public void setHoursBeforeDeparture(int hoursBeforeDeparture) { this.hoursBeforeDeparture = hoursBeforeDeparture; }
    public float getDeductionPercentage() { return deductionPercentage; }
    public void setDeductionPercentage(float deductionPercentage) { this.deductionPercentage = deductionPercentage; }
}
