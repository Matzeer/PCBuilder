package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.application.dto.CpuDTO;
import fr.esiea.pcbuilder.shared.enums.Categories;

public class Cpu extends Component {
    private int coreCount;
    private double coreClock;
    private double boostClock;
    private int tdp;
    private String graphics;
    private boolean smt;

    public Cpu(int id, String name, double price, double grade,
               int coreCount, double coreClock, double boostClock,
               int tdp, String graphics, boolean smt) {
        super(id, name, Categories.CPU, price, grade);
        this.coreCount = coreCount;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.graphics = graphics;
        this.smt = smt;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public double getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(double coreClock) {
        this.coreClock = coreClock;
    }

    public double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(double boostClock) {
        this.boostClock = boostClock;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public boolean isSmt() {
        return smt;
    }

    public void setSmt(boolean smt) {
        this.smt = smt;
    }

    public CpuDTO toDTO() {
        return new CpuDTO(
                this.getId(),
                this.getName(),
                this.getPrice(),
                this.getGrade(),
                this.coreCount,
                this.coreClock,
                this.boostClock,
                this.tdp,
                this.graphics,
                this.smt
        );
    }
}