package com.example.postpc_ex8;

import java.io.Serializable;
import java.util.UUID;

public class CalculationItem implements Serializable, Comparable<CalculationItem> {

    private Long number;
    private long root1;
    private long root2;
    private String status;
    private final String id;
    private UUID workerId;
    private double previousCalcTime;
    private long previousStopped;
    private int calculationProgress;

    public CalculationItem(long number){
        this.number = number;
        this.id = UUID.randomUUID().toString();
        this.root1 = -1;
        this.root2 = -1;
        this.status = "currently_calculation";
        this.previousCalcTime = 0;
        this.calculationProgress = 0;
        this.workerId = null;
        this.previousStopped = 0;
    }

    public void updateRoots(long root1, long root2, double calculationTime){
        this.root1 = root1;
        this.root2 = root2;
        this.status = "calculation_done";
        this.previousCalcTime = calculationTime;
        this.calculationProgress = 100;
    }

    @Override
    public int compareTo(CalculationItem o) {
        if (this.status.equals("currently_calculation") && !o.status.equals("currently_calculation")){
            return -1;
        }
        else if (!this.status.equals("currently_calculation") && o.status.equals("currently_calculation")){
            return 1;
        }
        return this.number.compareTo(o.number);
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof CalculationItem)){
            return false;
        }
        CalculationItem item = (CalculationItem) obj;
        return this.getId().equals(item.getId());
    }

    public long getNumber() {
        return number;
    }

    public long getRoot1() {
        return root1;
    }

    public long getRoot2() {
        return root2;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setRoot1(long root1) {
        this.root1 = root1;
    }

    public void setRoot2(long root2) {
        this.root2 = root2;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPreviousCalcTime() {
        return previousCalcTime;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setPreviousCalcTime(double previousCalcTime) {
        this.previousCalcTime = previousCalcTime;
    }

    public void setPreviousProgress(int previousProgress) {
        this.calculationProgress = previousProgress;
    }

    public UUID getWorkerId() {
        return workerId;
    }


    public void setWorkerId(UUID workerId) {
        this.workerId = workerId;
    }


    public void setStopped(long stopped, double timeStopped){
        this.previousStopped = stopped;
        this.previousCalcTime = timeStopped;
        this.status = "calculation_stopped";
    }

    public long getPreviousStopped() {
        return previousStopped;
    }

    public void setPreviousStopped(long previousStopped) {
        this.previousStopped = previousStopped;
    }

    public int getCalculationProgress() {
        return calculationProgress;
    }

    public void setCalculationProgress(int calculationProgress) {
        this.calculationProgress = calculationProgress;
    }
}
