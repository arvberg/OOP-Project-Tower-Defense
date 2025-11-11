package com.IONA.TowerDefense.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{

    public final static int TILESIZE = 48;
    public final static int TILECOUNTx = 21;
    public final static int TILECOUNTy = 14;
    public final static int GAMEWIDTH = TILESIZE * TILECOUNTx;
    public final static int GAMEHEIGHT = TILESIZE * TILECOUNTy;
    public final static int BORDERSIZE = 16;

//	private JSplitPane split = new JSplitPane();

    public GameFrame(){
        setTitle("Pew Pew Defense");
        setExtendedState(MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
