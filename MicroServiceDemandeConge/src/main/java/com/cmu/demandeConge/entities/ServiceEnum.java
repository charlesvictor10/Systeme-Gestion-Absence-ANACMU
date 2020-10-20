package com.cmu.demandeConge.entities;

public enum ServiceEnum {
    DirectionGeneral("DG"),
    DSI("DSI");

    private String code;

    private ServiceEnum(String code) {
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
