package fr.esiea.pcbuilder.shared.enums;

import java.util.EnumSet;
import java.util.Set;

public enum Categories {

    CPU(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.CORECOUNT, QueryParams.R_CORECOUNT,
            QueryParams.CORECLOCK, QueryParams.R_CORECLOCK,
            QueryParams.BOOSTCLOCK, QueryParams.R_BOOSTCLOCK,
            QueryParams.TDP, QueryParams.R_TDP,
            QueryParams.GRAPHICS, QueryParams.R_GRAPHICS,
            QueryParams.SMT, QueryParams.R_SMT
    )),

    MOTHERBOARD(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.SOCKET, QueryParams.R_SOCKET,
            QueryParams.FORMFACTOR, QueryParams.R_FORMFACTOR,
            QueryParams.MAXMEMORY, QueryParams.R_MAXMEMORY,
            QueryParams.MEMORYSLOTS, QueryParams.R_MEMORYSLOTS,
            QueryParams.COLOR, QueryParams.R_COLOR
    )),

    MEMORY(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.SPEED0, QueryParams.R_SPEED0,
            QueryParams.SPEED1, QueryParams.R_SPEED1,
            QueryParams.MODULE0, QueryParams.R_MODULE0,
            QueryParams.MODULE1, QueryParams.R_MODULE1,
            QueryParams.PRICEPERGB, QueryParams.R_PRICEPERGB,
            QueryParams.COLOR, QueryParams.R_COLOR,
            QueryParams.FIRSTWORDLATENCY, QueryParams.R_FIRSTWORDLATENCY,
            QueryParams.CASLATENCY, QueryParams.R_CASLATENCY
    )),

    INTERNAL_HARD_DRIVE(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.CAPACITY, QueryParams.R_CAPACITY,
            QueryParams.PRICEPERGB, QueryParams.R_PRICEPERGB,
            QueryParams.STORAGETYPE, QueryParams.R_STORAGETYPE,
            QueryParams.CACHE, QueryParams.R_CACHE,
            QueryParams.FORMFACTOR, QueryParams.R_FORMFACTOR,
            QueryParams.STORAGEINTERFACE, QueryParams.R_STORAGEINTERFACE
    )),

    VIDEO_CARD(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.CHIPSET, QueryParams.R_CHIPSET,
            QueryParams.MEMORY, QueryParams.R_MEMORY,
            QueryParams.CORECLOCK, QueryParams.R_CORECLOCK,
            QueryParams.BOOSTCLOCK, QueryParams.R_BOOSTCLOCK,
            QueryParams.COLOR, QueryParams.R_COLOR,
            QueryParams.LENGTH, QueryParams.R_LENGTH
    )),

    CASE(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.COLOR, QueryParams.R_COLOR,
            QueryParams.PSU, QueryParams.R_PSU,
            QueryParams.SIDEPANEL, QueryParams.R_SIDEPANEL,
            QueryParams.EXTERNAL525BAYS, QueryParams.R_EXTERNAL525BAYS,
            QueryParams.INTERNAL35BAYS, QueryParams.R_INTERNAL35BAYS
    )),

    POWER_SUPPLY(EnumSet.of(
            QueryParams.ID, QueryParams.R_ID,
            QueryParams.NAME, QueryParams.R_NAME,
            QueryParams.PRICE, QueryParams.R_PRICE,
            QueryParams.GRADE, QueryParams.R_GRADE,
            QueryParams.EFFICIENCY, QueryParams.R_EFFICIENCY,
            QueryParams.WATTAGE, QueryParams.R_WATTAGE,
            QueryParams.MODULAR, QueryParams.R_MODULAR,
            QueryParams.COLOR, QueryParams.R_COLOR
    ));

    private final Set<QueryParams> allowedParams;

    Categories(Set<QueryParams> allowedParams) {
        this.allowedParams = allowedParams;
    }

    public boolean allows(QueryParams param) {
        return allowedParams.contains(param);
    }

    public Set<QueryParams> getAllowedParams() {
        return allowedParams;
    }
}
