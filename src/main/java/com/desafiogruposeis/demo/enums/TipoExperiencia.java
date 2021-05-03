package com.desafiogruposeis.demo.enums;

public enum TipoExperiencia {
    SIN_EXPERIENCIA("Sin Experiencia", 405),
    CON_EXPERIENCIA("Con Experiencia", 101),
    CON_EXPERIENCIA5A("Con Experiencia", 101);

    private final String value;
    private final int code;

    TipoExperiencia(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
