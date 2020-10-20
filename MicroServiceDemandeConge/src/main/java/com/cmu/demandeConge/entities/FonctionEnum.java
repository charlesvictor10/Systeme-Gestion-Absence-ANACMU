package com.cmu.demandeConge.entities;

public enum FonctionEnum {
    DirecteurGeneral("DG"),
    SecretaireGeneral("SG"),
    DAF("DAF"),
    ResponsableRH("RH"),
    AdministrateurSystem("RED");

    private String code;

    private FonctionEnum(String code) {
        this.code = code;
    }

    public String getNiveau() {
        return this.code;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getCode() {
        return code;
    }
}
