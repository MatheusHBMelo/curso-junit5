package dev.matheushbmelo.classes;

public class SaudacaoDiaria {
    public String saudacao(int hora) {
        if (hora >= 6 && hora < 13) {
            return "Bom dia";
        }
        if (hora >= 13 && hora < 18) {
            return "Boa tarde";
        }
        if (hora >= 18 && hora < 24) {
            return "Boa noite!";
        }
        throw new RuntimeException("Horário invalido");
    }
}
