package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.shared.enums.Categories;

public class Storage extends Component {
    private int capacity;
    private double pricePerGb;
    private String storageType;
    private int cache;
    private String formFactor;
    private String storageInterface;

    public Storage(int id, String name, double price, double grade,
                   int capacity, double pricePerGb, String storageType,
                   int cache, String formFactor, String storageInterface) {
        super(id, name, Categories.INTERNAL_HARD_DRIVE, price, grade);
        this.capacity = capacity;
        this.pricePerGb = pricePerGb;
        this.storageType = storageType;
        this.cache = cache;
        this.formFactor = formFactor;
        this.storageInterface = storageInterface;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPricePerGb() {
        return pricePerGb;
    }

    public void setPricePerGb(double pricePerGb) {
        this.pricePerGb = pricePerGb;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getStorageInterface() {
        return storageInterface;
    }

    public void setStorageInterface(String storageInterface) {
        this.storageInterface = storageInterface;
    }
}