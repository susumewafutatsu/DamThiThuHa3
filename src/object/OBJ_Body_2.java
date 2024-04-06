package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Body_2 extends SuperObject{

    GamePanel gp;
    public OBJ_Body_2(GamePanel gp){
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/body_2.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
