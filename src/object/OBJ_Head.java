package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Head extends SuperObject{

    GamePanel gp;
    public OBJ_Head(GamePanel gp){
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/head-1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
