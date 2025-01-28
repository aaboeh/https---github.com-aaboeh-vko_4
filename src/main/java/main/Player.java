package main;

import java.io.Serializable;

public class Player implements Serializable{
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public void attack(Monster target) {
        System.out.println(name + " hyökkää " + target.getMonsterType() + " hirviöön!");
        if (!target.takeDamage(10)) {
            System.out.println(target.getMonsterType() + " on kuollut!");
        } else {
            System.out.println("Hirviöllä on " + target.getMonsterHealth() + " elämää jäljellä.");
        }
    }
}
