package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.Resource;
import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.IONA.TowerDefense.model.units.decorations.Core;
import com.IONA.TowerDefense.model.units.decorations.Decoration;
import com.IONA.TowerDefense.view.ui.ResourceDrawer;
import com.IONA.TowerDefense.view.units.decorations.CoreDrawer;
import com.IONA.TowerDefense.view.units.decorations.DrawableDecoration;
import com.IONA.TowerDefense.view.units.decorations.DrawableDecorationFactory;

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
