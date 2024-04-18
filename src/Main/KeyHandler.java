package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author arima
 */
public class KeyHandler implements KeyListener{

    GamePanel gp;
    UI ui;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    boolean checkDrawTime = false;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed=true;
        }
        if(code == KeyEvent.VK_S){
            downPressed=true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed=true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

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
                    gp.playMusic(0);
                }
                if(gp.ui.commanNum == 1){

                }
                if(gp.ui.commanNum == 2){
                    //
                }
                if(gp.ui.commanNum == 3){
                    System.exit(0);
                }
            }
        }

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
        if(code == KeyEvent.VK_P){
            if (gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
    }

}