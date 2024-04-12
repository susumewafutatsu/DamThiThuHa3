package entitty;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;

/**
 *
 * @author arima
 */
public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        //Size box character
        soliArea = new Rectangle();
        soliArea.x = (gp.tileSize/2)-14;
        soliArea.y = (gp.tileSize/16*10);
        soliAreaDefaultX = soliArea.x;
        soliAreaDefaultY = soliArea.y;
        soliArea.width = gp.tileSize - soliArea.x * 2;
        soliArea.height = gp.tileSize - soliArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        //let's start
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
    }
    public  void getPlayerImage(){
            up1 = setup("main_up_1");
            up2 = setup("main_up_2");
            up3 = setup("main_up_3");
            down1 = setup("main_down_1");
            down2 = setup("main_down_2");
            down3 = setup("main_down_3");
            left1 = setup("main_left_1");
            left2 = setup("main_left_2");
            left3 = setup("main_left_3");
            right1 = setup("main_right_1");
            right2 = setup("main_right_2");
            right3 = setup("main_right_3");
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image =ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }return  image;
    }
    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);


            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false){
                switch (direction){
                    case "up":   worldY -= speed;break;
                    case "down": worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }

            spriteCounter++;
            if (spriteCounter >12){
                if (spriteNum == 1){
                    spriteNum = 2;
                }else if (spriteNum == 2){
                    spriteNum = 3;
                }
                else if (spriteNum == 3){
                    spriteNum=1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void pickUpObject(int i){
        if (i!=999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a body!");
                    break;
                case "coffin":
                    if (hasKey == 5){
                        gp.ui.showMessage("Finish");
                        gp.ui.gameFinished = true;
                        break;

                    }else {gp.ui.showMessage("You need most all body parts");}
                    System.out.println("Head"+hasKey);
                    break;
            }

        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                if (spriteNum == 3){
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                if (spriteNum == 3){
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                if (spriteNum == 3){
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                if (spriteNum == 3){
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,null);

    }
}