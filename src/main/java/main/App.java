package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Syötä pelaajan nimi: ");
        String playerName = sc.nextLine();
        Player newPlayer = new Player(playerName);
        Cave newCave = new Cave(newPlayer);

        boolean exit = false;
        while(!exit) {
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");
            if (sc.hasNext()) {
                int i = 0;
                String stringInput = sc.nextLine();
                i = Integer.parseInt(stringInput);

                switch(i) {
                    case 1:
                        System.out.println("Anna hirviön tyyppi:");
                        String monsterType = sc.nextLine();
                        System.out.println("Anna hirviön elämän määrä numerona:");
                        int monsterHealth = Integer.parseInt(sc.nextLine());
                        newCave.addMonster(new Monster(monsterType, monsterHealth));
                        break;
                    case 2:
                        newCave.listMonsters();
                        break;
                    case 3:
                        System.out.println("Valitse hirviö, johon hyökätä:");
                        newCave.listMonsters();
                        int attackedMonsterIndex = Integer.parseInt(sc.nextLine()) - 1;
                        newCave.player.attack(newCave.monsters.get(attackedMonsterIndex));
                        if (newCave.monsters.get(attackedMonsterIndex).getMonsterHealth() <= 0) {
                            newCave.monsters.remove(attackedMonsterIndex);
                        }
                        break;
                    case 4:
                        System.out.println("Anna tiedoston nimi, johon peli tallentaa:");
                        String saveFile = sc.nextLine();
                        try {
                            ObjectOutputStream gameWriter = new ObjectOutputStream(new FileOutputStream(saveFile));
                            gameWriter.writeObject(newCave);
                            gameWriter.close();
                            System.out.println("Peli tallennettiin tiedostoon " + saveFile + ".");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                        String loadFile = sc.nextLine();
                        try {
                            ObjectInputStream gameLoader = new ObjectInputStream(new FileInputStream(loadFile));
                            newCave = (Cave) gameLoader.readObject();
                            gameLoader.close();
                            System.out.println("Peli ladattu tiedostosta " + loadFile + ". Tervetuloa takaisin, " + newCave.player.getPlayerName() + ".");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 0:
                        System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Syöte oli väärä");
                }
            }
        }
        sc.close();
    }
}
