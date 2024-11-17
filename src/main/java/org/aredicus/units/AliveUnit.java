package org.aredicus.units;

import lombok.Builder;
import lombok.Getter;
import org.aredicus.functional_interface.*;
import org.aredicus.items.impl.active.Shield;
import org.aredicus.items.impl.active.Sword;

import static java.util.Objects.nonNull;

@Builder
@Getter
public abstract class AliveUnit implements CanAttack, CanDie, CanHaveDamage, CanDefend {

    protected int health;
    protected int armor;
    protected int level;
    protected Sword sword;
    protected Shield shield;
    protected String name;


    @Override
    public void haveDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public void attack(AliveUnit enemy) {
        if (nonNull(sword) && !sword.getIsCooldown()) {
            var realDamage = sword.getDamage() - enemy.getArmor();
            if (realDamage <= 0) realDamage = 0;
            enemy.haveDamage(realDamage);
            sword.cooldown();
        }
    }

    @Override
    public boolean isDie() {
        return this.health <= 0;
    }

    @Override
    public void defend() {
        if (nonNull(shield) && !shield.getIsCooldown()) {
            armor += shield.getArmor();
            shield.cooldown();
        }
    }

}
