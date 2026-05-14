package com.railway.ticket.management.system.domain;

public class Train {
    private int id;
    private String trainRegistrationNumber;
    private String trainName;
    private int totalCoaches;
    private int totalAcCoaches;
    private int totalNonAcCoaches;
    private int totalCompartmentCoaches;

    public Train(int id, String trainRegistrationNumber, String trainName, int totalCoaches,
                 int totalAcCoaches, int totalNonAcCoaches, int totalCompartmentCoaches) {
        setId(id);
        setTrainRegistrationNumber(trainRegistrationNumber);
        setTrainName(trainName);
        setTotalCoaches(totalCoaches);
        setTotalAcCoaches(totalAcCoaches);
        setTotalNonAcCoaches(totalNonAcCoaches);
        setTotalCompartmentCoaches(totalCompartmentCoaches);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTrainRegistrationNumber() { return trainRegistrationNumber; }
    public void setTrainRegistrationNumber(String trainRegistrationNumber) { this.trainRegistrationNumber = trainRegistrationNumber; }
    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public int getTotalCoaches() { return totalCoaches; }
    public void setTotalCoaches(int totalCoaches) { this.totalCoaches = totalCoaches; }
    public int getTotalAcCoaches() { return totalAcCoaches; }
    public void setTotalAcCoaches(int totalAcCoaches) { this.totalAcCoaches = totalAcCoaches; }
    public int getTotalNonAcCoaches() { return totalNonAcCoaches; }
    public void setTotalNonAcCoaches(int totalNonAcCoaches) { this.totalNonAcCoaches = totalNonAcCoaches; }
    public int getTotalCompartmentCoaches() { return totalCompartmentCoaches; }
    public void setTotalCompartmentCoaches(int totalCompartmentCoaches) { this.totalCompartmentCoaches = totalCompartmentCoaches; }
}
