package com.railway.ticket.management.system.domain;

public class ReturnPolicy {
    private int id;
    private String policyName;
    private int hoursBeforeDeparture;
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
