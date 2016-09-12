package org.rehab.app.models.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ES2OperatingIncome implements Serializable {

    private String gsiMonthlyRent,gsiAnuualRent,gsiPercentage,vlSqFt,vlMonthlyRent,vlAnnumalERnt,
            oiMontlyRent,oiAnnualRent,goiMontlyRent,goiAnnualRent,oiNoOfUnit="1",oiSqFeet="1400";
    private List<ItemValue> itemValueList=new ArrayList<>();

    public void addItemValue(ItemValue itemValue){
        itemValueList.add(itemValue);
    }
    public void removeItemValue(int pos){
        if(itemValueList.size()>pos+1){
            itemValueList.remove(pos);
        }
    }
    public ItemValue getItemByValue(int pos){
        if(itemValueList.size()>pos+1){
            return itemValueList.get(pos);
        }
        return null;
    }
    public int getItemValueSize(){
        return itemValueList.size();
    }
    public List<ItemValue> getItemValueList(){
        return itemValueList;
    }

    public String getGsiMonthlyRent() {
        return gsiMonthlyRent;
    }

    public void setGsiMonthlyRent(String gsiMonthlyRent) {
        this.gsiMonthlyRent = gsiMonthlyRent;
    }

    public String getGsiAnuualRent() {
        return gsiAnuualRent;
    }

    public void setGsiAnuualRent(String gsiAnuualRent) {
        this.gsiAnuualRent = gsiAnuualRent;
    }

    public String getGsiPercentage() {
        return gsiPercentage;
    }

    public void setGsiPercentage(String gsiPercentage) {
        this.gsiPercentage = gsiPercentage;
    }

    public String getVlSqFt() {
        return vlSqFt;
    }

    public void setVlSqFt(String vlSqFt) {
        this.vlSqFt = vlSqFt;
    }

    public String getVlMonthlyRent() {
        return vlMonthlyRent;
    }

    public void setVlMonthlyRent(String vlMonthlyRent) {
        this.vlMonthlyRent = vlMonthlyRent;
    }

    public String getVlAnnumalERnt() {
        return vlAnnumalERnt;
    }

    public void setVlAnnumalERnt(String vlAnnumalERnt) {
        this.vlAnnumalERnt = vlAnnumalERnt;
    }

    public String getOiMontlyRent() {
        return oiMontlyRent;
    }

    public void setOiMontlyRent(String oiMontlyRent) {
        this.oiMontlyRent = oiMontlyRent;
    }

    public String getOiAnnualRent() {
        return oiAnnualRent;
    }

    public void setOiAnnualRent(String oiAnnualRent) {
        this.oiAnnualRent = oiAnnualRent;
    }

    public String getGoiMontlyRent() {
        return goiMontlyRent;
    }

    public void setGoiMontlyRent(String goiMontlyRent) {
        this.goiMontlyRent = goiMontlyRent;
    }

    public String getGoiAnnualRent() {
        return goiAnnualRent;
    }

    public void setGoiAnnualRent(String goiAnnualRent) {
        this.goiAnnualRent = goiAnnualRent;
    }

    public String getOiNoOfUnit() {
        return oiNoOfUnit;
    }

    public void setOiNoOfUnit(String oiNoOfUnit) {
        this.oiNoOfUnit = oiNoOfUnit;
    }

    public String getOiSqFeet() {
        return oiSqFeet;
    }

    public void setOiSqFeet(String oiSqFeet) {
        this.oiSqFeet = oiSqFeet;
    }


    public static class ItemValue implements Serializable{
        private String noOfUnits,unityType,squareFt,monthlyRent,annualRent,percent;
        public String getNoOfUnits() {
            return noOfUnits;
        }

        public void setNoOfUnits(String noOfUnits) {
            this.noOfUnits = noOfUnits;
        }

        public String getUnityType() {
            return unityType;
        }

        public void setUnityType(String unityType) {
            this.unityType = unityType;
        }

        public String getSquareFt() {
            return squareFt;
        }

        public void setSquareFt(String squareFt) {
            this.squareFt = squareFt;
        }

        public String getMonthlyRent() {
            return monthlyRent;
        }

        public void setMonthlyRent(String monthlyRent) {
            this.monthlyRent = monthlyRent;
        }

        public String getAnnualRent() {
            return annualRent;
        }

        public void setAnnualRent(String annualRent) {
            this.annualRent = annualRent;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }
    }
}
