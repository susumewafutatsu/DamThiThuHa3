package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public  Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[60];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/map/world01.txt");
    }
    public  void  getTileImage(){
            //floor
            setup(0,"floor_class",false);

            //wall
            setup(1,"wall_left_1",true);
            setup(3,"wall_shool_2",true);
            setup(5,"grass",false);
            setup(9,"floor_class-1",false);
            setup(6,"floor_class_3",false);
            setup(7,"podium_1",true);
//basketball
            setup(8,"basketball_court",false);
            setup(14,"basketball_court_1",false);
            setup(10,"basketball_court_2",false);
            setup(11,"basketball_court_3",false);
            setup(4,"basketball_court_4",false);
            setup(2,"basketball_court_5",false);
            setup(12,"basketball_court_10",false);
            setup(13,"basketball_court_11",false);
            setup(15,"basketball_court_10-1",false);
            setup(16,"basketball_court_10-2",false);
            setup(17,"basketball_court_10-3",false);
            setup(18,"basketball_court_11-1",false);
            setup(19,"basketball_court_11-2",false);
            setup(20,"basketball_court_11-3",false);
            setup(21,"basketball_court_8",false);
            setup(22,"basketball_court_9",false);
            setup(23,"basketball_court_6",false);
            setup(24,"basketball_court_12",false);
            setup(31,"basketball_court_7",false);
            setup(25,"basketball_court_10-4",false);
            setup(26,"basketball_court_10-5",false);
            setup(27,"basketball_court_11-4",false);
            setup(28,"basketball_court_11-5",false);
            setup(29,"basketball_court_13-1",false);
            setup(30,"basketball_court_13",false);
//basket ball
            setup(32,"sand",false);
            setup(33,"sand_1",false);
            setup(34,"sand_2",false);
            setup(35,"sand_3",false);
            setup(36,"sand_4",false);
            setup(37,"sand_5",false);
            setup(38,"sand_6",false);
            setup(39,"sand_7",false);
            setup(40,"sand_8",false);
            setup(41,"sand_9",false);
            setup(42,"sand_10",false);
            setup(43,"sand_11",false);
            setup(44,"sand_12",false);
            setup(45,"table_b_1",true);
            setup(46,"table_b_2",true);
            setup(47,"table_b_3",true);
            setup(48,"table_b_4",true);
            setup(49,"table_b_5",true);
            setup(50,"table_b_6",true);
            setup(51,"table_b_7",true);
        setup(52,"floor_shool",false);
        setup(53,"flower-1",false);
        setup(54,"flower-2",false);
        setup(55,"flower-3",true);
        setup(56,"flower-4",true);
        setup(57,"flower-5",true);
        setup(58,"wall_left_8",true);
    }
    public void  setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e){
            e.printStackTrace();}
    }
    public void  loadMap( String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row< gp.maxWorldRow){
                String line = br.readLine();
                while (col < gp.maxWorldCol){

                    String nubers[] = line.split(" ");

                    int num = Integer.parseInt(nubers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){


        }
    }
    public  void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY)
            {
            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
