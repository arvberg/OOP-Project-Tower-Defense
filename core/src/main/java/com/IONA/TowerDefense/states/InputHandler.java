package com.IONA.TowerDefense.states;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {


    @Override
    public void keyTyped(KeyEvent e) {

        // Not necessary?

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        /*
        different things depending on the game state
         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        /*
        different depending on game state
         */
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
