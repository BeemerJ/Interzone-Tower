package game.Tower;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class tower {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Please enter a Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next().toUpperCase();
        scanner.close();

        //TIME INIT

        int timeSlow = 2;
        int timeCinematic = timeSlow * 2;

        TimeUnit.SECONDS.sleep(timeSlow);

        //TITLE SPLASH

        System.out.println("-----------------------------------\nI N T E R Z Ø  N E    T Ø W E R\n-----------------------------------");

        System.out.println("You find a tall tower...\n-----------------------------------");

        int nFloor = 1;
        TimeUnit.SECONDS.sleep(timeCinematic);

        //INIT DICE

        Random dice = new Random();

        //INIT ROLLS

        int damMin = dice.nextInt(40, 60);
        int damMax = 100;
        int damBound = 50;
        int damBoundMin = 5;


        //CHEATS

        if (name.equals("DANTE")) {
            damMin = 99;
            damMax = 150;
            System.out.println("You whip out Ebony and Ivory...");
            TimeUnit.SECONDS.sleep(timeSlow);
            timeSlow = timeSlow / 2;
        } else if (name.equals("TONBERRY")) {
            timeSlow = timeSlow * 2;
            damMax = damMax * 2;
            damMin = damMin * 2;
            System.out.println("You smell a bit fishy...");
            TimeUnit.SECONDS.sleep(timeSlow);
        }

        boolean playerAlive = true;

        //START MAIN LOOP

        for (int i = 0; i < 9; i++) {

            System.out.println("You enter Floor Number " + nFloor + "...");
            TimeUnit.SECONDS.sleep(timeCinematic);

            //ENEMY TABLE

            String[] enemy = {"Snake", "Spider", "Scorpion", "Salamander", "Slug", "Scarab"};
            int nEnemy = dice.nextInt(6);

            //EVOLVED ROLL

            boolean isEvolved = false;
            isEvolved = dice.nextBoolean();
            String enemyEvolved = " ";
            int evolveRoll = dice.nextInt(8);

            if (evolveRoll > 1) {
                enemyEvolved = " ";
            } else {
                enemyEvolved = " EVOLVED ";
                damBoundMin = damBoundMin + (nFloor * 15);
            }

            //POTION

            int potionRoll = dice.nextInt(12);
            int potionBuff = (dice.nextInt(4, 8) * dice.nextInt(2, 8));

            if (potionRoll >= 8) {
                damMin = damMin + potionBuff;
                damMax = damMax + potionBuff;
                double buffPercent = ((double) potionBuff / damMax) * 100;
                System.out.println("You found a Potion!\n-----------------------------------\nYou feel a " + (int) buffPercent + "% boost in strength...\n-----------------------------------");
                TimeUnit.SECONDS.sleep(timeSlow);
            }

            //ENCOUNTER

            System.out.println("You encounter an" + enemyEvolved + "enemy " + enemy[nEnemy] + ", you attack it...");
            TimeUnit.SECONDS.sleep(timeCinematic);

            //CRITMISS

            int nCritmiss = dice.nextInt(5);
            int nFallDam = nFloor * dice.nextInt(8, 12);
            if (nCritmiss == 1) {
                damMax = damMax - (nFallDam);
                System.out.println("You stumble and hurt yourself for " + nFallDam + "HP...");
                TimeUnit.SECONDS.sleep(timeSlow);
            }

            //PLAYER DAMAGE

            int nDamage = dice.nextInt(damMin, damMax);

            if (nDamage >= (nDamage * 0.33)) {
                System.out.println("You deal " + nDamage + " damage to the " + enemy[nEnemy] + "...");
                TimeUnit.SECONDS.sleep(timeCinematic);
            } else {
                System.out.println("You do " + nDamage + " damage to the " + enemy[nEnemy] + ", you fear for your life...");
                TimeUnit.SECONDS.sleep(timeCinematic);
            }

            //CRITICAL

            if (nDamage >= (damMax * 0.8)) {
                System.out.println("A CRITICAL HIT...");
                TimeUnit.SECONDS.sleep(timeSlow);
            } else if (nDamage <= (damMax * 0.33)) {
                System.out.println("You barely hit the " + enemy[nEnemy] + "...");
                TimeUnit.SECONDS.sleep(timeSlow);
            }

            //ENEMY HEALTH

            int nDamageRoll = dice.nextInt(damBoundMin, damBound);
            TimeUnit.SECONDS.sleep(timeSlow);

            if (nDamage <= (damBound * 0.75)) {
                System.out.println("You sense a bad omen...");
                TimeUnit.SECONDS.sleep(timeSlow);
            }

            //LEVEL UP

            double enemyScale = dice.nextInt(8);

            if (nDamage > nDamageRoll) {
                System.out.println("You have slayed the " + enemy[nEnemy] + ", Well done!");
                nFloor = nFloor + 1;
                damMin = damMin + 25;
                damMax = damMax + 50;
                damBound = (int) (damBound + 50 + ((enemyScale * nFloor) / 2));
                if (isEvolved) {
                    damBoundMin = (damBoundMin + 30) - (nFloor * 20);
                } else {
                    damBoundMin = damBoundMin + 30;
                }
                TimeUnit.SECONDS.sleep(timeSlow);

                if (enemyScale >= 4 && enemyScale < 6) {
                    System.out.println("You get an uneasy feeling...");
                    TimeUnit.SECONDS.sleep(timeSlow);
                } else if (enemyScale > 6) {
                    System.out.println("You get a terrible feeling...");
                    TimeUnit.SECONDS.sleep(timeSlow);
                }

                System.out.println("-----------------------------------");
                TimeUnit.SECONDS.sleep(timeSlow);

                //GAME OVER

            } else {
                System.out.println("-----------------------------------\nYou have fumbled your swing on Floor " + nFloor + " and got eaten by a " + enemy[nEnemy] + ", RIP " + name + "...\n-----------------------------------\n☠   ☠   ☠   ☠   ☠   ☠   ☠   ☠\n-----------------------------------");
                TimeUnit.SECONDS.sleep(timeSlow);
                System.out.println("Your final Max Damage was " + damMax + "...\nYou lost to a " + enemy[nEnemy] + " with " + nDamageRoll + "HP...");
                TimeUnit.SECONDS.sleep(timeSlow);
                System.out.println("Game Over... Goodbye ☺\n-----------------------------------");
                TimeUnit.SECONDS.sleep(timeCinematic);
                return;
            }
        }

        //BOSS ENCOUNTER

        String[] enemyBoss = {"Spectre", "Spriggan", "Sphinx"};
        int nBoss = dice.nextInt(3);
        int bossHealth = dice.nextInt(444, 555);

        System.out.println("You find yourself at the top of the tower facing off against a " + enemyBoss[nBoss] + "...\nYou bravely attack it...");
        TimeUnit.SECONDS.sleep(timeCinematic);

        //BOSS FIGHT

        int nDamage = dice.nextInt(damMin, damMax);

        if (nDamage >= (nDamage * 0.5)) {
            System.out.println("You deal " + nDamage + " damage to the " + enemyBoss[nBoss] + "...");
            TimeUnit.SECONDS.sleep(timeCinematic);
        } else {
            System.out.println("You do " + nDamage + " damage to the " + enemyBoss[nBoss] + ", you fear your journey is coming to an end...");
            TimeUnit.SECONDS.sleep(timeCinematic);
        }

        if (nDamage > bossHealth) {
            System.out.println("You have slayed the " + enemyBoss[nBoss] + " and have conquered the Tower!");
            TimeUnit.SECONDS.sleep(timeCinematic);
        } else {
            System.out.println("-----------------------------------\nYou have fumbled your swing on Floor " + nFloor + " and got annihilated by a " + enemyBoss[nBoss] + ", RIP " + name + "...\n-----------------------------------\n☠   ☠   ☠   ☠   ☠   ☠   ☠   ☠\n-----------------------------------");
            TimeUnit.SECONDS.sleep(timeSlow);
            System.out.println("Your final Max Damage was " + damMax + "...\nYou lost to a " + enemyBoss[nBoss] + " with " + bossHealth + "HP...");
            TimeUnit.SECONDS.sleep(timeSlow);
            System.out.println("Game Over... Goodbye ☺\n-----------------------------------");
            TimeUnit.SECONDS.sleep(timeCinematic);
        }
    }
}
