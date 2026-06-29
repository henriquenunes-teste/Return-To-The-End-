package com.code.utils;

import com.code.models.Pocao;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    /** Resultado da última batalha. */
    public static boolean victory = false;

    /** Estoque de poções — vive em memória durante a sessão. */
    public static List<Pocao> pocoes = new ArrayList<>();

    public static void adicionarPocao(Pocao p) {
        pocoes.add(p);
    }

    /**  a primeira poção não usada */
    public static Pocao pegarPocaoDisponivel() {
        return pocoes.stream().filter(p -> !p.isUsada()).findFirst().orElse(null);
    }

    public static int quantidadePocoes() {
        return (int) pocoes.stream().filter(p -> !p.isUsada()).count();
    }
}