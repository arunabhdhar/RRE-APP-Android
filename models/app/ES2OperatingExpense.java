package org.rehab.app.models.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ES2OperatingExpense implements Serializable {

    private String toeMonthlyExpense,toeAnuualExpense,toePercentageExpense,toeRestIncome;


    private List<ItemExpenseValue> itemValueList=new ArrayList<>();

    public void addItemValue(ItemExpenseValue itemValue){
        itemValueList.add(itemValue);
    }
    public void addItemValueAtPos(int pos,ItemExpenseValue itemValue){
        itemValueList.add(pos,itemValue);
    }
    public void removeItemValue(int pos){
        if(itemValueList.size()>pos+1){
            itemValueList.remove(pos);
        }
    }
    public ItemExpenseValue getItemByValue(int pos){
        if(itemValueList.size()>pos+1){
            return itemValueList.get(pos);
        }
        return null;
    }
    public int getItemValueSize(){
        return itemValueList.size();
    }
    public List<ItemExpenseValue> getItemValueList(){
        return itemValueList;
    }

    public String getToeMonthlyExpense() {
        return toeMonthlyExpense;
    }

    public void setToeMonthlyExpense(String toeMonthlyExpense) {
        this.toeMonthlyExpense = toeMonthlyExpense;
    }

    public String getToeAnuualExpense() {
        return toeAnuualExpense;
    }

    public void setToeAnuualExpense(String toeAnuualExpense) {
        this.toeAnuualExpense = toeAnuualExpense;
    }

    public String getToePercentageExpense() {
        return toePercentageExpense;
    }

    public void setToePercentageExpense(String toePercentageExpense) {
        this.toePercentageExpense = toePercentageExpense;
    }

    public String getToeRestIncome() {
        return toeRestIncome;
    }

    public void setToeRestIncome(String toeRestIncome) {
        this.toeRestIncome = toeRestIncome;
    }


    public static class ItemExpenseValue implements Serializable{
        private String noOfUnits,title,monthlyRent,annualRent,percent;
        private boolean isEditable;
        public String getNoOfUnits() {
            return noOfUnits;
        }

        public void setNoOfUnits(String noOfUnits) {
            this.noOfUnits = noOfUnits;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isEditable() {
            return isEditable;
        }

        public void setEditable(boolean editable) {
            isEditable = editable;
        }
    }
}
