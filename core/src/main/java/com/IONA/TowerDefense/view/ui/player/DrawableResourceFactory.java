package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
/**
 * Factory class for creating drawable representations of player resources.
 *
 * Converts a Resource instance into its corresponding DrawableResource implementation.
 * Supports ResourceMoney and ResourceHP. Throws an exception for unknown types.
 */
public final class DrawableResourceFactory {

    private DrawableResourceFactory() {
    }

    public static DrawableResource create(Resource resource) {
        return switch (resource) {
            case ResourceMoney r -> new MoneyDrawer(r);
            case ResourceHP r -> new HpDrawer(r);
            default -> throw new IllegalStateException("Unexpected value: " + resource);
        };
    }
}
