/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.code;

import com.code.models.Player;
import com.code.models.Pocao;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

 
class PocaoTest {
 
  
    @Test
    void usar_deveAplicarCuraNoPlayer_quandoPocaoAindaNaoFoiUsada() {
        Pocao pocao = new Pocao(0); // 
        Player player = new Player("Herói");
        player.setHealth(50); // 
 
        boolean resultado = pocao.usar(player);
 
        assertTrue(resultado);
        assertTrue(pocao.isUsada());
        assertEquals(60, player.getHealth()); 
    }
 
    @Test
    void usar_naoDeveUltrapassarMaxHealth() {
        Pocao pocao = new Pocao(0); 
        Player player = new Player("Herói");
        player.setHealth(95); 
 
        boolean resultado = pocao.usar(player);
 
        assertTrue(resultado);
        assertEquals(100, player.getHealth());
    }
 
    @Test
    void usar_deveRetornarFalse_quandoPocaoJaFoiUsada() {
        Pocao pocao = new Pocao(0);
        Player player = new Player("Herói");
        player.setHealth(50);
 
        pocao.usar(player); 
        boolean segundaVez = pocao.usar(player); 
 
        assertFalse(segundaVez);
        assertEquals(60, player.getHealth()); 
    }
}
   
 