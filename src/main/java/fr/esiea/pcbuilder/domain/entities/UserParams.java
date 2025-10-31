package fr.esiea.pcbuilder.domain.entities;


import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;

public class UserParams {
    private Categories ActualCategory;
    private ArrayList<QueryParams> orders;
    private int limit;

    public UserParams(Categories firstCategory, int limit) {
        if (limit <0){
            throw new IllegalArgumentException("limit can't be less than 0");
        }
        this.ActualCategory = firstCategory;
        this.orders = new ArrayList<>();
        this.limit = limit;
    }

    public Categories getActualCategory() {
        return ActualCategory;
    }

    public void setActualCategory(Categories actualCategory) {
        ActualCategory = actualCategory;
    }

    public ArrayList<QueryParams> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<QueryParams> orders) {
        this.orders = orders;
    }

    public void addOrder(QueryParams order) {
        this.orders.add(order);
    }

    public void clearOrders() {
        this.orders.clear();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit <0){
            throw new IllegalArgumentException("limit can't be less than 0");
        }
        this.limit = limit;
    }


}
