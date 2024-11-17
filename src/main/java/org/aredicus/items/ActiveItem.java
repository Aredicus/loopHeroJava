package org.aredicus.items;

import lombok.Getter;
import org.aredicus.functional_interface.CanCooldown;

@Getter
public abstract class ActiveItem extends Item implements CanCooldown {
    protected long cooldown;
    protected Boolean isCooldown;
    protected final Thread threadCooldown = new Thread(() -> {
        try {
            this.isCooldown = true;
            this.wait(this.cooldown);
            this.isCooldown = false;
        } catch (InterruptedException ignored) {
        }
    });

    @Override
    public void cooldown() {
        threadCooldown.start();
    }
}
