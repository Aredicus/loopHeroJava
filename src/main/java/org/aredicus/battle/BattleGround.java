package org.aredicus.battle;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aredicus.units.AliveUnit;
import org.aredicus.units.impl.alive.Hero;
import org.aredicus.units.impl.alive.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleGround {

    @AllArgsConstructor
    @Getter
    private static class Pair {
        private Thread attack;
        private Thread isDie;
    }

    public static boolean battle(Hero hero, List<Monster> monsters) {
        HashMap<Monster, Pair> monstersActive = new HashMap<>();
        for (Monster monster : monsters) {
            Thread attack = new Thread(unitAttack(monster, hero));
            Thread thread = new Thread(isDie(monster, attack));
            monstersActive.put(monster, new Pair(attack, thread));
        }

        for (Map.Entry<Monster, Pair> entry : monstersActive.entrySet()) {
            entry.getValue().isDie.start();
            entry.getValue().attack.start();
        }
        return !hero.isDie();
    }

    private static Runnable isDie(AliveUnit object, Thread attack) {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (object.isDie()) {
                    attack.interrupt();
                    return;
                }
            }
        };
    }

    private static Runnable unitAttack(AliveUnit attacker, AliveUnit defender) {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (attacker.getSword().getIsCooldown()) {
                    try {
                        attacker.getSword().getThreadCooldown().join();
                    } catch (InterruptedException e) {
                        attacker.getSword().getThreadCooldown().interrupt();
                        return;
                    }
                }
                attacker.attack(defender);
            }
        };
    }
}
