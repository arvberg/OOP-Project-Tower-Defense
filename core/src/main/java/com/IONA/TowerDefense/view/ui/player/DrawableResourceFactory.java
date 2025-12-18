package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;

public final class DrawableResourceFactory {

    private DrawableResourceFactory(){}

    public static DrawableResource create(Resource resource){
        return switch (resource){
            case ResourceMoney r -> new MoneyDrawer(r);
            case ResourceHP r -> new HpDrawer(r);
            //case EnemyFast e   -> new EnemyFastDrawer(e);
            //case EnemyTanky e   -> new EnemyTankDrawer(e);
            default -> throw new IllegalStateException("Unexpected value: " + resource);
        };
    }
}
