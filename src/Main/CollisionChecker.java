package Main;

import entitty.Entity;
import entitty.Player;

public class CollisionChecker {
    GamePanel gp;
    public  CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public  void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.soliArea.x;
        int entityRightWorldX = entity.worldX + entity.soliArea.x + entity.soliArea.width;
        int entityTopWorldY = entity.worldY + entity.soliArea.y;
        int entityBottomWorldY = entity.worldY + entity.soliArea.y + entity.soliArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public  int checkObject(Entity entity, boolean player){

        int index = 999;
        for (int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                entity.soliArea.x = entity.worldX + entity.soliArea.x;
                entity.soliArea.y = entity.worldY + entity.soliArea.y;
                gp.obj[i].soliArea.x = gp.obj[i].worldX + gp.obj[i].soliArea.x;
                gp.obj[i].soliArea.y = gp.obj[i].worldY + gp.obj[i].soliArea.y;

                switch (entity.direction){
                    case "up":
                        entity.soliArea.y -= entity.speed;
                        if (entity.soliArea.intersects(gp.obj[i].soliArea)){
                            if (gp.obj[i].collison == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.soliArea.y += entity.speed;
                        if (entity.soliArea.intersects(gp.obj[i].soliArea)){
                            if (gp.obj[i].collison == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.soliArea.x -= entity.speed;
                        if (entity.soliArea.intersects(gp.obj[i].soliArea)){
                            if (gp.obj[i].collison == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.soliArea.x += entity.speed;
                        if (entity.soliArea.intersects(gp.obj[i].soliArea)){
                            if (gp.obj[i].collison == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.soliArea.x = entity.soliAreaDefaultX;
                entity.soliArea.y = entity.soliAreaDefaultY;
                gp.obj[i].soliArea.x = gp.obj[i].soliAreaDefaultX;
                gp.obj[i].soliArea.y = gp.obj[i].soliAreaDefaultY;

            }
        }
        return  index;
    }
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for(int i = 0; i < target.length; i++) {

            if(target[i] != null) {

                entity.soliArea.x = entity.worldX + entity.soliArea.x;
                entity.soliArea.y = entity.worldY + entity.soliArea.y;

                target[i].soliArea.x = target[i].worldX + target[i].soliArea.x;
                target[i].soliArea.y = target[i].worldY + target[i].soliArea.y;
                switch(entity.direction) {
                    case "up":
                        entity.soliArea.y -= entity.speed;
                        if(entity.soliArea.intersects(target[i].soliArea)) {

                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.soliArea.y += entity.speed;
                        if(entity.soliArea.intersects(target[i].soliArea)) {

                            entity.collisionOn = true;
                            index = i;

                        }
                        break;
                    case "left":
                        entity.soliArea.x -= entity.speed;
                        if(entity.soliArea.intersects(target[i].soliArea)) {

                            entity.collisionOn = true;
                            index = i;

                        }
                        break;
                    case "right":
                        entity.soliArea.x += entity.speed;
                        if(entity.soliArea.intersects(target[i].soliArea)) {

                            entity.collisionOn = true;
                            index = i;

                            break;
                        }
                }
                entity.soliArea.x = entity.soliAreaDefaultX;
                entity.soliArea.y = entity.soliAreaDefaultY;
                target[i].soliArea.x = target[i].soliAreaDefaultX;
                target[i].soliArea.y = target[i].soliAreaDefaultY;
            }
        }

        return index;

    }
    public void checkPlayer(Entity entity) {

        entity.soliArea.x = entity.worldX + entity.soliArea.x;
        entity.soliArea.y = entity.worldY + entity.soliArea.y;

        gp.player.soliArea.x = gp.player.worldX + gp.player.soliArea.x;
        gp.player.soliArea.y = gp.player.worldY + gp.player.soliArea.y;

        switch(entity.direction) {
            case "up":
                entity.soliArea.y -= entity.speed;
                if(entity.soliArea.intersects(gp.player.soliArea)) {

                    entity.collisionOn = true;

                }
                break;
            case "down":
                entity.soliArea.y += entity.speed;
                if(entity.soliArea.intersects(gp.player.soliArea)) {

                    entity.collisionOn = true;

                }
                break;
            case "left":
                entity.soliArea.x -= entity.speed;
                if(entity.soliArea.intersects(gp.player.soliArea)) {

                    entity.collisionOn = true;

                }
                break;
            case "right":
                entity.soliArea.x += entity.speed;
                if(entity.soliArea.intersects(gp.player.soliArea)) {

                    entity.collisionOn = true;

                    break;
                }
        }
        entity.soliArea.x = entity.soliAreaDefaultX;
        entity.soliArea.y = entity.soliAreaDefaultY;
        gp.player.soliArea.x = gp.player.soliAreaDefaultX;
        gp.player.soliArea.y = gp.player.soliAreaDefaultY;
    }
}
