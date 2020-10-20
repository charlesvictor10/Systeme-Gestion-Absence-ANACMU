package com.cmu.parametrage.entities;

public enum RoleEnum {
    ADMIN(new Long(1), "ADMIN"),
    DG(new Long(2), "DG"),
    SH(new Long(3), "SH"),
    RH(new Long(4), "RH"),
    AG(new Long(5), "AG");

    private Long id;
    private String code;

    RoleEnum(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
