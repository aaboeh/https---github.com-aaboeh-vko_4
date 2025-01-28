package main;

import java.io.Serializable;

public class Monster implements Serializable{
    private String type;
    private int health;

    public Monster(String type, int health) {
        this.type = type;
        this.health = health;
    }

    public String getMonsterType() {
        return type;
    }

    public int getMonsterHealth() {
        return health;
    }

    public void printInfo(int number) {
        System.out.println(number + ": " + type + " / " + health + "HP");
    }

    public boolean takeDamage(int dmg) {
        health -= dmg; 
        return health > 0;
    }
    
}
