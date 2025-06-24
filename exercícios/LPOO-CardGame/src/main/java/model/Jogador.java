/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Igor
 */

import java.util.List;

public class Jogador {
    private String id;
    private String nickname;
    private int levelJogador;
    private List<Carta> baralho;

    
    public Jogador(String id, String nickname, int levelJogador, List<Carta> baralho) {
        this.id = id;
        this.nickname = nickname;
        this.levelJogador = levelJogador;
        this.baralho = baralho;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevelJogador() {
        return levelJogador;
    }

    public void setLevelJogador(int levelJogador) {
        this.levelJogador = levelJogador;
    }

    public List<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(List<Carta> baralho) {
        this.baralho = baralho;
    }
}
