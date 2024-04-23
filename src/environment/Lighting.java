package environment;

import Main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    public Lighting (GamePanel gp, int circleSize){
        //CREAT A BUffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
        Area screenArea = new Area(new Rectangle2D.Double(0,0,gp.screenWidth, gp.screenHeight));

        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        //CREAT A LIGHT CICRLE SHAPE
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        //CTREATE A LIGHT CIRCLE AREA
        Area lightArea = new Area(circleShape);
        //SUBTRACT THE LIGHT CIRCLE FROM THE SCREEN RECTAGLE
        screenArea.subtract(lightArea);
        //CREATE A GRADATION EFFECT WITHIN THE LIGHT CIRCLE
        Color color[] = new Color[12];
        float fraction[] = new float[12];

        color[0] = new Color(0,0,0,0.1f);
        color[1] = new Color(0,0,0,0.2f);
        color[2] = new Color(0,0,0,0.3f);
        color[3] = new Color(0,0,0,0.4f);
        color[4] = new Color(0,0,0,0.5f);
        color[5] = new Color(0,0,0,0.6f);
        color[6] = new Color(0,0,0,0.7f);
        color[7] = new Color(0,0,0,0.75f);
        color[8] = new Color(0,0,0,0.80f);
        color[9] = new Color(0,0,0,0.85f);
        color[10] = new Color(0,0,0,0.90f);
        color[11] = new Color(0,0,0,0.95f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;
        //create a gradation paint settings for the light circle
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);

        //Set the gradient data on g2
        g2.setPaint(gPaint);

        //Draw the light circle
        g2.fill(lightArea);

        //draw the screen rectangle without the light circle area
        g2.fill(screenArea);

        g2.dispose();
    }
    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter,0,0,null);
    }
}
