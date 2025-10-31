package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.Case;

public final class CaseFactory {

    private CaseFactory() {}

    public static Case createExample() {
        return new Case(
                1,
                "NZXT H510",
                89.99,
                4.5,
                "Black",
                "None",
                "Tempered Glass",
                2,
                2
        );
    }
}
