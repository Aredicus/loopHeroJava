package org.aredicus.items.impl.active;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aredicus.items.ActiveItem;

@AllArgsConstructor
@Getter
public class Sword extends ActiveItem {
    private final int damage;
}
