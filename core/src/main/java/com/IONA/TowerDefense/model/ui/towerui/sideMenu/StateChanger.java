package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

public class StateChanger {

    private TowerMenuToggleButton tmtb;
    private UpgradeMenuToggleButton umtb;

    public StateChanger(){
    }

    public void setButtons(UpgradeMenuToggleButton umtb, TowerMenuToggleButton tmtb){
        this.umtb = umtb;
        this.tmtb= tmtb;
    }

    public void changeState(){
        umtb.changeState();
        tmtb.changeState();
    }

    public void reset(){
        if(!umtb.isOpen){umtb.changeState();}
        if(tmtb.isOpen){tmtb.changeState();}
    }
}
