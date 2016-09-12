package org.rehab.app.models.app;

import java.io.Serializable;
import java.util.List;

/**
 */
public class RadiantBudgetSelOptionCPy implements Serializable{


    //1 - One Time
    // 0- Monthlywise
    //How do you want to enter budget?
    private int enterBudget=0;


    //0 - Quick Lump Sum
    // 1 - Detailed input!
    private int overrideOption=1;
    private int totalAmount;
    private DetailInputData detailInput;


    public void setOverrideOption(int option){
        this.overrideOption=option;
    }

    public int getEnterBudget() {
        return enterBudget;
    }

    public void setEnterBudget(int fundRehabClosing) {
        this.enterBudget = fundRehabClosing;
    }

    public int getOverrideOption() {
        return overrideOption;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DetailInputData getDetailInput() {
        return detailInput;
    }

    public void setDetailInput(DetailInputData detailInput) {
        this.detailInput = detailInput;
    }


    public static class DetailInputData implements Serializable{

        private float totalBid1,totalBid2,totalBid3,totalBudget;
        private GroupInfo groupInfo;
        private boolean isShowBid;


        public float getTotalBid1() {
            return totalBid1;
        }

        public void setTotalBid1(float totalBid1) {
            this.totalBid1 = totalBid1;
        }

        public float getTotalBid2() {
            return totalBid2;
        }

        public void setTotalBid2(float totalBid2) {
            this.totalBid2 = totalBid2;
        }

        public float getTotalBid3() {
            return totalBid3;
        }

        public void setTotalBid3(float totalBid3) {
            this.totalBid3 = totalBid3;
        }

        public float getTotalBudget() {
            return totalBudget;
        }

        public void setTotalBudget(float totalBudget) {
            this.totalBudget = totalBudget;
        }

        public GroupInfo getGroupInfo() {
            return groupInfo;
        }

        public void setGroupInfo(GroupInfo groupInfo) {
            this.groupInfo = groupInfo;
        }

        public boolean isShowBid() {
            return isShowBid;
        }

        public void setShowBid(boolean showBid) {
            isShowBid = showBid;
        }
    }

    public static class GroupInfo implements Serializable{
        private String groupName;
        private List<ItemValue> itemValues;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<ItemValue> getItemValues() {
            return itemValues;
        }

        public void setItemValues(List<ItemValue> itemValues) {
            this.itemValues = itemValues;
        }
    }

    public static class ItemValue implements Serializable{
        private float qty,rate,bid1,bid2,bid3,budget;
        private String monthToPay,itemName,details;
        //ViewType Related to header(Group Name)/item/footer(Add Item)
        private int viewType;


        public float getQty() {
            return qty;
        }

        public void setQty(float qty) {
            this.qty = qty;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public float getBid1() {
            return bid1;
        }

        public void setBid1(float bid1) {
            this.bid1 = bid1;
        }

        public float getBid2() {
            return bid2;
        }

        public void setBid2(float bid2) {
            this.bid2 = bid2;
        }

        public float getBid3() {
            return bid3;
        }

        public void setBid3(float bid3) {
            this.bid3 = bid3;
        }

        public float getBudget() {
            return budget;
        }

        public void setBudget(float budget) {
            this.budget = budget;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getMonthToPay() {
            return monthToPay;
        }

        public void setMonthToPay(String monthToPay) {
            this.monthToPay = monthToPay;
        }

        /**
         * ViewType Related to header(Group Name)/item/footer(Add Item)
         * @return
         */
        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }
    }

}
