package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Cave implements Serializable{
    Player player;
    ArrayList<Monster> monsters = new ArrayList<>();

    public Cave(Player player) {
        this.player = player;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void listMonsters() {
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).printInfo(i + 1);
        }
    }
}
