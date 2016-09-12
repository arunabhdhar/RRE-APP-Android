package org.rehab.app.models.app;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 */
public class ERParentListItem implements ParentListItem {

    private String title;
    private List<ERChildListItem> erChildListItems;

    public ERParentListItem(String title, List<ERChildListItem> erChildListItems) {
        this.title = title;
        this.erChildListItems = erChildListItems;
    }

    @Override
    public List<?> getChildItemList() {
        return null;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ERChildListItem> getErChildListItems() {
        return erChildListItems;
    }

    public void setErChildListItems(List<ERChildListItem> erChildListItems) {
        this.erChildListItems = erChildListItems;
    }
}
