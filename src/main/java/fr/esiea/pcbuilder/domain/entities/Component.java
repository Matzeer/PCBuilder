package fr.esiea.pcbuilder.domain.entities;

public abstract class Component {
    private int id;
    private String name;
    private String type;
    private double price;
    private double grade;

    public Component(int id, String name, String type, double price, double grade) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
