package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.application.dto.CaseDTO;
import fr.esiea.pcbuilder.shared.enums.Categories;

public class Case extends Component {
    private String color;
    private String psu;
    private String sidePanel;
    private int external525Bays;
    private int internal35Bays;

    public Case(int id, String name, double price, double grade,
                String color, String psu, String sidePanel,
                int external525Bays, int internal35Bays) {
        super(id, name, Categories.CASE, price, grade);
        this.color = color;
        this.psu = psu;
        this.sidePanel = sidePanel;
        this.external525Bays = external525Bays;
        this.internal35Bays = internal35Bays;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPsu() {
        return psu;
    }

    public void setPsu(String psu) {
        this.psu = psu;
    }

    public String getSidePanel() {
        return sidePanel;
    }

    public void setSidePanel(String sidePanel) {
        this.sidePanel = sidePanel;
    }

    public int getExternal525Bays() {
        return external525Bays;
    }

    public void setExternal525Bays(int external525Bays) {
        this.external525Bays = external525Bays;
    }

    public int getInternal35Bays() {
        return internal35Bays;
    }

    public void setInternal35Bays(int internal35Bays) {
        this.internal35Bays = internal35Bays;
    }

    public CaseDTO toDTO() {
        return new CaseDTO(
                this.getId(),
                this.getName(),
                this.getPrice(),
                this.getGrade(),
                this.color,
                this.psu,
                this.sidePanel,
                this.external525Bays,
                this.internal35Bays
        );
    }
}
