package com.cmu.demandeConge.entities;

public enum NiveauValidationDemandeEnum {
    ValidationDG(new Long(1),"VALIDATION_DG"),
    ValidationRH(new Long(2),"VALIDATION_RH"),
    ValidationSH(new Long(3),"VALIDATION_SH");

    private Long id;
    private String code;

    private NiveauValidationDemandeEnum(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
