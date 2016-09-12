package org.rehab.app.models.app;

import java.io.Serializable;
import java.util.List;

/**
 */
public class NDClosingCostRehab implements Serializable{

    private int closingCostsOption;
    private float totalClosingCosts;
    private float totalPercentage;
    private float perOfPurchase;
    private List<ClosingCostItem> listCostItem;
    private int totalDICosts;

    public List<ClosingCostItem> getListCostItem() {
        return listCostItem;
    }

    public void setListCostItem(List<ClosingCostItem> listCostItem) {
        this.listCostItem = listCostItem;
    }

    public int getClosingCostsOption() {
        return closingCostsOption;
    }

    public void setClosingCostsOption(int closingCostsOption) {
        this.closingCostsOption = closingCostsOption;
    }

    public float getTotalClosingCosts() {
        return totalClosingCosts;
    }

    public void setTotalClosingCosts(float totalClosingCosts) {
        this.totalClosingCosts = totalClosingCosts;
    }

    public float getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(float totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    public float getPerOfPurchase() {
        return perOfPurchase;
    }

    public void setPerOfPurchase(float perOfPurchase) {
        this.perOfPurchase = perOfPurchase;
    }

    public int getTotalDICosts() {
        return totalDICosts;
    }

    public void setTotalDICosts(int totalDICosts) {
        this.totalDICosts = totalDICosts;
    }


    public static class ClosingCostItem implements Serializable{
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
