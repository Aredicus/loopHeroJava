package org.aredicus.functional_interface;

import org.aredicus.units.AliveUnit;

@FunctionalInterface
public interface CanAttack {
    void attack(AliveUnit enemy);
}
