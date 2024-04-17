package Main;

import ai.Pathfinder;
import entitty.Entity;
import entitty.Player;
import environment.EnvironmentManager;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author arima
 */
public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 32;
    final int scale =3;

    public final int tileSize = originalTileSize * scale;//96x96 title
    public final int maxScreenCol =16;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol;// 768 pixels
    public final int screenHeight= tileSize * maxScreenRow;//576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //FPS
    int  FPS = 60;
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public  AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Pathfinder pfinder = new Pathfinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 6;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        //playMusic(0);
        gameState = titleState;
        eManager.setup();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000/FPS; // 0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
//                System.out.println("FPS:"+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){

        if (gameState == playState){
            player.update();
            //NPC
            for(int i = 0;i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState){}
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //TILE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        //OTHERS
        else{
            //TILE
            tileM.draw(g2);
            //OBJ
            for (int i = 0; i< obj.length; i++){
                if (obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            //NPC
            for(int i = 0;i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(g2);
                }
            }
            //PLAYER
            player.draw(g2);
//        eManager.draw(g2);

            //UI
            ui.draw(g2);
            g2.dispose();
        }
    }
    public void retry(){
        player.setDefaultPositions();
        aSetter.setNPC();
    }
    public void restart(){
        player.setDefaultPositions();
        aSetter.setObject();
        aSetter.setNPC();
    }
    public void  playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}