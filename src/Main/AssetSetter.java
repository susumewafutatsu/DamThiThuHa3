package Main;

import entitty.NPC_chase;
import object.*;
import tile.TileManager;

public class AssetSetter {

    GamePanel gp;
    public  AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public  void setObject(){
        int[][] randomNumber = new int[2][5];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 5; j++){
                double randomDouble = Math.random() * 52; // Số ngẫu nhiên từ 0.0 đến 49.999...
                randomNumber[i][j] = (int) randomDouble; // Chuyển đổi thành số nguyên
            }
        }

        gp.obj[0] = new OBJ_Head(gp);
        gp.obj[0].worldX = 4*gp.tileSize;//randomNumber[0][0] * gp.tileSize;
        gp.obj[0].worldY = 56*gp.tileSize;//randomNumber[1][0] * gp.tileSize;
        gp.obj[1] = new OBJ_Body_1(gp);
        gp.obj[1].worldX = 5*gp.tileSize; //randomNumber[0][1] * gp.tileSize;
        gp.obj[1].worldY = 56*gp.tileSize;//randomNumber[1][1] * gp.tileSize;
        gp.obj[2] = new OBJ_Body_2(gp);
        gp.obj[2].worldX = 6*gp.tileSize;//randomNumber[0][2] * gp.tileSize;
        gp.obj[2].worldY = 56*gp.tileSize;//randomNumber[1][2] * gp.tileSize;
        gp.obj[3] = new OBJ_Leg_1(gp);
        gp.obj[3].worldX = 7*gp.tileSize;//randomNumber[0][3] * gp.tileSize;
        gp.obj[3].worldY = 56*gp.tileSize;//randomNumber[1][3] * gp.tileSize;
        gp.obj[4] = new OBJ_Leg_2(gp);
        gp.obj[4].worldX = 8*gp.tileSize;//randomNumber[0][4] * gp.tileSize;
        gp.obj[4].worldY = 56*gp.tileSize;//randomNumber[1][4] * gp.tileSize;
        gp.obj[5] = new OBJ_Coffin(gp);
        gp.obj[5].worldX =6 * gp.tileSize;
        gp.obj[5].worldY =6 * gp.tileSize;
    }
    public void setNPC() {
        gp.npc[0] = new NPC_chase(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 10;
    }
}
