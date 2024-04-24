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
                    gp.ui.commanNum = 3;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commanNum++;
                if(gp.ui.commanNum>3){
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if(gp.ui.commanNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commanNum == 1){
                    gp.gameState = gp.gameInstruction;
                }
                if(gp.ui.commanNum == 2){
                    gp.gameState = gp.optionState;
                }
                if(gp.ui.commanNum == 3){
                    System.exit(0);
                }
            }
        }
        //gameover state
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        //win state
        else if (gp.gameState == gp.gameWinState) {
            gameWinState(code);
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.titleState;
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
        if(gp.gameState == gp.optionState){
            optionState(code);
        }
    }
    public void gameOverState(int code){

        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            if (gp.ui.commanNum < 0) {
                gp.ui.commanNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            if (gp.ui.commanNum > 1) {
                gp.ui.commanNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commanNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
                gp.ui.dayUpdated = false;
            }
            else if (gp.ui.commanNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void gameWinState(int code){
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.titleState;
            gp.restart();
        }
    }
    public void optionState(int code){
        if (code == KeyEvent.VK_ENTER){
            enterPressed =true;
        }
        int maxCommanNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommanNum = 5;
        }
        if(code == KeyEvent.VK_W){
            gp.ui.commanNum--;
            if(gp.ui.commanNum < 0){
                gp.ui.commanNum = maxCommanNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commanNum++;
            if(gp.ui.commanNum > maxCommanNum){
                gp.ui.commanNum = 0;
            }
        }
    }
}