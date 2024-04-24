package Main;

import object.OBJ_Head;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;


public class UI  extends JPanel{

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message ="";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commanNum = 0;
    public int titleScreenState = 0;
    public int day = 0;

    BufferedImage image1 = null;
    int subState = 0;

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

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
//        //PLAY STATE
        if (gp.gameState == gp.playState){
            //DO playState stuff later
            if (gameFinished == true){
                g2.setFont(arial_80);
                g2.setColor(Color.white);
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
        //Win state
        if(gp.gameState == gp.gameWinState){
            drawGameWinScreen();
        }
        //Option state
        if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }
    }
    public void drawGameOverScreen(){
        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/tiles/Untitled.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g2.drawImage(image1, 0, 0, gp.screenWidth,gp.screenHeight, null);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.red);
        day=day+1;
        text = "Day";
        x = gp.tileSize*12;
        y = gp.tileSize*5;
        g2.drawString(text+day, x, y);
        // Retry
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.red);
        text = "Continue";
        x = gp.tileSize*12;
        y = gp.tileSize*6;
        g2.drawString(text, x, y);
        if (commanNum == 0){
            g2.drawString(">",x-40,y);
        }
        // Back to the title screen text = "Quit";
        text = "Quit";
        x = gp.tileSize*12;
        y = gp.tileSize/12*80;
        g2.drawString(text, x, y);
        if (commanNum == 1){
            g2.drawString(">",x-40,y);
        }
    }
    public void drawGameWinScreen(){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/tiles/1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g2.drawImage(image1, 0, 0, null);
        g2.setFont(g2.getFont().deriveFont (Font.BOLD, 70f));
        text = "THE END...?";
        // Shadow
        g2.setColor(Color.black);
        x = gp.tileSize*10;
        y = gp.tileSize*7;
        g2.drawString (text,x,y);
        //Main
        g2.setColor(Color.red);
        g2.drawString(text,x-4,y-4);
        if (commanNum == 0){
            g2.drawString(">",x-40,y);
        }
    }
//    public void drawInstructions(){
//        try{
//            image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/pngtree-3d-rendered-classroom-with-blood-a-terrifying-and-eerie.png"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        g2.drawImage(image2, 0, 0, gp.screenWidth,gp.screenHeight, null);
//    }
    public void drawTitleScreen(){
        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/tiles/116183.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g2.drawImage(image1, 0, 0, gp.screenWidth,gp.screenHeight, null);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        int x,y;String text;
        text = "NEW GAME";
        x = gp.tileSize*2;
        y = gp.tileSize/12*60;
        if (commanNum == 0){
            g2.setColor(Color.black);
            g2.drawString(text, x, y);
            x = gp.tileSize*2;
            y = gp.tileSize/12*59;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            g2.drawString(">",x-gp.tileSize/2,y);
        }

        g2.drawString(text, x, y);
        text = "INSTRUCTIONS";
        x = gp.tileSize*2;
        y = gp.tileSize/12*80;
        if (commanNum == 1){
            g2.setColor(Color.black);
            g2.drawString(text, x, y);
            x = gp.tileSize*2;
            y = gp.tileSize/12*79;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            g2.drawString(">",x-gp.tileSize/2,y);
        }

        g2.drawString(text, x, y);
        text = "SETTING";
        x = gp.tileSize*12;
        y = gp.tileSize/12*60;
        g2.drawString(text, x, y);
        if (commanNum == 2){
            g2.setColor(Color.black);
            g2.drawString(text, x, y);
            x = gp.tileSize*12;
            y = gp.tileSize/12*59;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            g2.drawString(">",x-gp.tileSize/2,y);
        }

        g2.drawString(text, x, y);
        text = "QUIT";
        x = gp.tileSize*12;
        y = gp.tileSize/12*80;
        g2.drawString(text, x, y);
        if (commanNum == 3){
            g2.setColor(Color.black);
            g2.drawString(text, x, y);
            x = gp.tileSize*12;
            y = gp.tileSize/12*79;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            g2.drawString(">",x-gp.tileSize/2,y);
        }
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
    public void drawOptionScreen(){
        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/tiles/116183.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g2.drawImage(image1, 0, 0, gp.screenWidth,gp.screenHeight, null);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gp.tileSize*5;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*5;
        int frameHeight = gp.tileSize*6;
        drawSubWinow(frameX,frameY,frameWidth, frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1: break;
            case 2: break;
        }
    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(40F));

        //TILE
        String text = "Setting";
        textX = frameX + gp.tileSize/12*22;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);


        g2.setFont(g2.getFont().deriveFont(32F));
        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize/2;
        g2.drawString("Full Screen", textX, textY);
        if (commanNum == 0){
            g2.drawString(">",textX-gp.tileSize/2,textY);
        }

        //MUSIC
        textY += gp.tileSize/2;
        g2.drawString("Music", textX, textY);
        if (commanNum == 1){
            g2.drawString(">",textX-gp.tileSize/2,textY);
        }

        //SE
        textY += gp.tileSize/2;
        g2.drawString("SE", textX, textY);
        if (commanNum == 2){
            g2.drawString(">",textX-gp.tileSize/2,textY);
        }

        //CONTROL
        textY += gp.tileSize/2;
        g2.drawString("Control", textX, textY);
        if (commanNum == 3){
            g2.drawString(">",textX-gp.tileSize/2,textY);
        }

        //END GAME
        textY += gp.tileSize/2;
        g2.drawString("End Game", textX, textY);
        if (commanNum == 4){
            g2.drawString(">",textX-gp.tileSize/2,textY);
        }

        //BACK
        textX = frameX + gp.tileSize/12*25;
        textY += gp.tileSize/12*26;
        g2.drawString("Back", textX, textY);
        if (commanNum == 5){
            g2.drawString(">",textX-gp.tileSize/2,textY);
        }
    }
    public void drawSubWinow(int x, int y, int width, int height){
        Color c = new Color(255,255,255);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35,35);

        c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x+5, y+5, width-10, height-10, 25,25);
    }
}
