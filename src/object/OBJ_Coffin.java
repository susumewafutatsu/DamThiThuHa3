package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coffin extends SuperObject{

    GamePanel gp;
    public OBJ_Coffin(GamePanel gp){
        name = "cofin";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coffin.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collison = true;
    }
}
