package com.bridgelabz;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {
    @CsvBindByName(column = "SrNo")
    private String SrNo;

    @CsvBindByName(column = "StateName")
    private String StateName;

    @CsvBindByName(column = "StateCode")
    private String StateCode;

    @CsvBindByName(column = "TIN")
    private String TIN;

    @CsvBindByName(column = "Population")
    private int Population;

    @CsvBindByName(column = "AreaInSqKm")
    private int AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private int DensityPerSqKm;

    public String getSrNo() {

        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public int getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(int areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public int getDensityPerSqKm() {
        return DensityPerSqKm;
    }

    public void setDensityPerSqKm(int densityPerSqKm) {
        DensityPerSqKm = densityPerSqKm;
    }
}
