package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.shared.enums.Categories;

public abstract class Component {
    private int id;
    private String name;
    private Categories category;
    private double price;
    private double grade;

    public Component(int id, String name, Categories category, double price, double grade) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
