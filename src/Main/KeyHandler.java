package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author arima
 */
public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed;

    boolean checkDrawTime = false;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed=false;
        }
        if(code == KeyEvent.VK_S){
            downPressed=false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        //title state
        if(gp.gameState == gp.titleState){
            if (code == KeyEvent.VK_W){
                gp.ui.commanNum--;
                if(gp.ui.commanNum<0){
                    gp.ui.commanNum = 2;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commanNum++;
                if(gp.ui.commanNum>2){
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if(gp.ui.commanNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commanNum == 1){
                    //
                }
                if(gp.ui.commanNum == 2){
                    System.exit(0);
                }
            }
        }//gameover state
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        //Play state
        if(gp.gameState == gp.playState) {

            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }

            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }

            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;

            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;

            }
            //debug
            if(code == KeyEvent.VK_T) {
                if(checkDrawTime == false) {
                    checkDrawTime = true;
                }
                else if(checkDrawTime == true){
                    checkDrawTime = false;
                }
            }
        }
        if(code == KeyEvent.VK_P){
            if (gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }

    }
    public void gameOverState(int code){

        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            if (gp.ui.commanNum < 0) {
                gp.ui.commanNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            if (gp.ui.commanNum > 1) {
                gp.ui.commanNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commanNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if (gp.ui.commanNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

}