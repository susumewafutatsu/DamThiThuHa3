package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Body_1 extends SuperObject{

    GamePanel gp;
    public OBJ_Body_1(GamePanel gp){
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/body_1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
