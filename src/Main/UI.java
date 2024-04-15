package Main;

import object.OBJ_Head;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message ="";
    int messageCounter = 0;
    public boolean gameFinished = false;


    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80 = new Font("Arial",Font.PLAIN,80);
        OBJ_Head head = new OBJ_Head(gp);
        keyImage = head.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        this. g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState){
            //DO playState stuff later
            if (gameFinished == true){
                g2.setFont(arial_80);
                g2.setColor(Color.white);

                String text;
                int textLength;
                int x; int y;

                text = "Congratulations";
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            }
            else {
                g2.setFont(arial_40);
                g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize,null);
            g2.drawString("x"+gp.player.hasKey, 125, 100);

                // MESSAGE
                if(messageOn == true){

                    g2.setFont(g2.getFont().deriveFont(30F));
                    g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                    messageCounter++;

                    if (messageCounter>120){
                        messageCounter = 0;
                        messageOn = false;
                    }
                }
            }
        }
        // Pause state
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //Game over state
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont (Font.BOLD, 80f));
        text = "Game Over";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString (text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);
        // Retry
        g2.setFont(g2.getFont().deriveFont(30f));
        text = "Retry";
        x= getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString (text, x, y);
        // Back to the title screen text = "Quit";
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString (text, x, y);
    }
    public  void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));

        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y= gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public  int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x= gp.screenWidth - lenght*14/5;
        return  x;
    }
}
