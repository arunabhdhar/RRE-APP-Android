package org.rehab.app.models.app;

import java.io.Serializable;
import java.util.List;

/**
 */
public class NDHoldingCostRehab implements Serializable{

    private int holdingCostsType;
    private int totalHoldingCosts;
    private List<CostItem> listCostItem;
    private int totalCostItems;

    public int getHoldingCostsType() {
        return holdingCostsType;
    }

    public void setHoldingCostsType(int holdingCostsType) {
        this.holdingCostsType = holdingCostsType;
    }

    public int getTotalHoldingCosts() {
        return totalHoldingCosts;
    }

    public void setTotalHoldingCosts(int totalHoldingCosts) {
        this.totalHoldingCosts = totalHoldingCosts;
    }

    public List<CostItem> getListCostItem() {
        return listCostItem;
    }

    public void setListCostItem(List<CostItem> listCostItem) {
        this.listCostItem = listCostItem;
    }

    public int getTotalCostItems() {
        return totalCostItems;
    }

    public void setTotalCostItems(int totalCostItems) {
        this.totalCostItems = totalCostItems;
    }


    public static class CostItem implements Serializable{
        private String title,value;
        private int viewType;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }
    }
}
