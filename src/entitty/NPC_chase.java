package entitty;
import Main.GamePanel;
import Main.UI;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_chase extends Entity {
    public NPC_chase(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 3;
        getImage();
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC_right_3.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        super.update();
        //Check player collision
        int playerIndex = gp.cChecker.checkEntity(this, new Player[]{gp.player});
        interactPlayer(playerIndex);

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 6){

            int i = new Random().nextInt(100)+1;
            if(i < 50){
                onPath = true;
                speed = 5;
            }
        }

    }
    public void interactPlayer(int i) {
        if(i != 999) {
            gp.gameState = gp.gameOverState;
        }
    }
    public void setAction() {
        if (onPath == true) {
//            int goalCol=12;
//            int goalRow=9;
            int goalCol = (gp.player.worldX + gp.player.soliArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.soliArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);
        } else {
            actionLockCounter++;

            if (actionLockCounter == 120) {

                Random random = new Random();

                int i = random.nextInt(100 + 1);

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }
    public void searchPath(int goalCol, int goalRow){
            int startCol = (worldX + soliArea.x)/ gp.tileSize;
            int startRow = (worldY + soliArea.y)/ gp.tileSize;

            gp.pfinder.setNodes(startCol, startRow, goalCol, goalRow, this);
            if(gp.pfinder.search() == true){

                int nextX = gp.pfinder.pathList.get(0).col * gp.tileSize;
                int nextY = gp.pfinder.pathList.get(0).row * gp.tileSize;
                // entity solidArea position
                int enLeftX = worldX + soliArea.x;
                int enRightX = worldX + soliArea.x + soliArea.width;
                int enTopY = worldY + soliArea.y;
                int enBottomY = worldY + soliArea.y + soliArea.height;

                if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                    direction = "up";
                }
                else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                    direction = "down";
                }
                else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                    if (enLeftX > nextX) {
                        direction = "left";
                    }
                    if (enLeftX < nextX) {
                        direction = "right";
                    }
                }
                else if (enTopY > nextY && enLeftX > nextX) {
                    // up or left
                    direction = "up";
                    checkCollision();
                    if(collisionOn == true) {
                        direction = "left";
                    }
                }
                else if (enTopY > nextY && enLeftX < nextX) {
                    // up or right
                    direction = "up";
                    checkCollision();
                    if (collisionOn == true) {
                        direction = "right";
                    }
                }
                else if (enTopY < nextY && enLeftX > nextX) {
                    // down or left
                    direction = "down";
                    checkCollision();
                    if (collisionOn == true) {
                        direction = "left";
                    }
                }
                else if (enTopY < nextY && enLeftX < nextX) {
                    // down or right
                    direction = "down";
                    checkCollision();
                    if (collisionOn == true) {
                        direction = "right";
                    }
                }

//                int nextCol = gp.pfinder.pathList.get(0).col;
//                int nextRow = gp.pfinder.pathList.get(0).row;
//                if(nextCol == goalCol && nextRow == goalRow){
//                    onPath = false;
//                }
            }
    }
}

