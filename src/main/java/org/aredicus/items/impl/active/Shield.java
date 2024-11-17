package org.aredicus.items.impl.active;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aredicus.items.ActiveItem;

@AllArgsConstructor
@Getter
public class Shield extends ActiveItem {
    private final int armor;
}
