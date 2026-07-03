package com.code.utils;

import com.code.models.Pocao;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static boolean victory = false;

    /** 0 = Aranha, 1 = Jacaré, 2 = Cerberus, 3 = Pirata */
    public static int bossAtual = 0;

    public static final int TOTAL_BOSSES = 4;

    /**
     * Coins acumuladas durante as batalhas mas ainda bloqueadas.
     * Só são transferidas para o player ao morrer.
     */
    public static int coinsBloqueadas = 0;

    public static List<Pocao> pocoes = new ArrayList<>();

    public static void adicionarPocao(Pocao p) {
        pocoes.add(p);
    }

    public static Pocao pegarPocaoDisponivel() {
        for (Pocao p : pocoes) {
            if (!p.isUsada()) return p;
        }
        return null;
    }

    public static int quantidadePocoes() {
        int count = 0;
        for (Pocao p : pocoes) {
            if (!p.isUsada()) count++;
        }
        return count;
    }

    public static boolean avancarBoss() {
        if (bossAtual < TOTAL_BOSSES - 1) {
            bossAtual++;
            return true;
        }
        return false;
    }

    public static boolean jogoCompleto() {
        return bossAtual >= TOTAL_BOSSES;
    }
}