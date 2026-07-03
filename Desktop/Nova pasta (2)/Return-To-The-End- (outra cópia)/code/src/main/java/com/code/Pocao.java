package com.code.models;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Vai alterando quanto as poções vão dar ja que vc que vai fazer gameplay
 */
public class Pocao {

    private static final int MINIMO          = 10;
    private static final int BONUS_POR_PONTO = 15;

    private final int cura;
    private boolean usada = false;

    public Pocao(int upgradeVida) {
        int maximo = MINIMO + (upgradeVida * BONUS_POR_PONTO);
        this.cura  = ThreadLocalRandom.current().nextInt(MINIMO, maximo + 1);
    }

    public int getCura()      { return cura;  }
    public boolean isUsada()  { return usada; }

    /**
     * Aplica a cura no player. Respeita o maxHealth.
     * @return true se a cura foi aplicada, false se já usada.
     */
    public boolean usar(Player player) {
        if (usada) return false;
        int novaVida = Math.min(player.getHealth() + cura, player.getMaxHealth());
        player.setHealth(novaVida);
        usada = true;
        return true;
    }

    @Override
    public String toString() {
        return usada
            ? "Poção (já usada)"
            : "Poção de Vida  [ +" + cura + " HP ]";
    }
}