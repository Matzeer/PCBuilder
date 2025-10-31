package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.MotherBoard;

public final class MotherBoardFactory {

    private MotherBoardFactory() {}

    public static MotherBoard createExample() {
        return new MotherBoard(
                1,
                "ASUS TUF Gaming B550-PLUS",
                149.99,
                4.6,
                "AM4",
                "ATX",
                128,
                4,
                "Black/Gray"
        );
    }
}
