package game;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tower {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Enter your Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().toUpperCase();
        scanner.close();

        TimeUnit.SECONDS.sleep(2);

        System.out.println(name + " finds a tall tower...");

        int nFloor = 1;
        TimeUnit.SECONDS.sleep(4);

        int damMin = 50;
        int damMax = 100;
        int damBound = 50;
        int damBoundMin = 0;

        for (; ;){

        System.out.println("You enter Floor Number " + nFloor + "...");
        TimeUnit.SECONDS.sleep(3);

        String[] enemy = {"Snake", "Spider", "Scorpion", "Salamander"};
        Random dice = new Random();
        int nEnemy = dice.nextInt(4);

        System.out.println("You encounter a giant enemy " + enemy[nEnemy] + ", you attack it...");
        TimeUnit.SECONDS.sleep(3);


        int nCritmiss = dice.nextInt(8);

        if (nCritmiss == 1) {
            damMax = damMax - (nFloor * 10);
            System.out.println("You stumble and hurt yourself...");
            TimeUnit.SECONDS.sleep(2);
        }

        int nDamage = dice.nextInt(damMin, damMax);

        if (nDamage >= (nDamage * 0.33)) {
            System.out.println("You deal " + nDamage + " damage to the " + enemy[nEnemy] + "...");
            TimeUnit.SECONDS.sleep(2);
        }
            else {
            System.out.println("You do " + nDamage + " damage to the " + enemy[nEnemy] + ", you fear for your life...");
            TimeUnit.SECONDS.sleep(2);
        }

        if (nDamage >= (damMax * 0.9)) {
             System.out.println("Critical Hit...");
             TimeUnit.SECONDS.sleep(2);
        }

        int nDamageRoll = dice.nextInt(damBoundMin, damBound);
        TimeUnit.SECONDS.sleep(2);

        if (nDamage <= (damBound * 0.9)) {
            System.out.println("You sense a bad omen...");
            TimeUnit.SECONDS.sleep(2);
        }

        int enemyScale = dice.nextInt(1, 3);

        if (nDamage > nDamageRoll) {
            System.out.println("You have slayed the " + enemy[nEnemy] + ", Well done!");
            nFloor = nFloor + 1;
            damMin = damMin + 25;
            damMax = damMax + 50;
            damBound = damBound + 50 * enemyScale;
            damBoundMin = damBoundMin + 30;
            TimeUnit.SECONDS.sleep(2);

            if (enemyScale >= 2) {
                System.out.println("You have a bad feeling about the next floor...");
                TimeUnit.SECONDS.sleep(2);
            }

            TimeUnit.SECONDS.sleep(2);

        } else {
            System.out.println(name + " has fumbled their swing on Floor " + nFloor + " and the " + enemy[nEnemy] + " ate them, Game Over...");
            TimeUnit.SECONDS.sleep(2);
            return;
        }
        }
    }
}
