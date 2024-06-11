package Main;

import TrueLevel.LevelManager;
import entities.Player;

import java.awt.*;

public class Game implements Runnable{
    public static final int TILE_DEFAULT_SIZE = 32 ;
    public static final float TILE_SCALE = 1.5f;
    public static final int TILE_HEIGHT = 14;
    public static final int TILE_WIDTH = 26;
    public static final int TILE_SIZE = (int) (TILE_DEFAULT_SIZE* TILE_SCALE);
    public static final int GAME_HEIGHT = TILE_HEIGHT*TILE_SIZE ;
    public static final int GAME_WIDTH = TILE_WIDTH * TILE_SIZE;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread thread;
    private long lastCheck;
    private int FPS_SET = 160;
    private final int FPS_LIMIT =140 ;
    private final int UPS_LIMIT = 400;
    private double deltaU = 0;
    private int update = 0;
    private double timePerUpdate = 1000000000/UPS_LIMIT;
    private double timePerFrame = 1000000000/FPS_LIMIT;
    private Player player;
    private LevelManager levelManager;

    public Game()
    {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gameLoop();
    }

    private void initClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200,200, 256, 128);
        player.loadLvlData(levelManager.getLevel().getLevelData());

    }


    private void gameLoop()
    {
        thread = new Thread(this);
        thread.start();
    }

    private void updateLogic()
    {
        levelManager.update();
        player.update();

    }

    public void render (Graphics g)
    {
        levelManager.render(g);
        player.render(g);
    }


    @Override
    public void run()
    {
      double deltaU ;
      double deltaF ;
      long initialNanoTime = System.nanoTime();
      long initialNanoTimeFrames = System.nanoTime();
      long initialMiliTime = System.currentTimeMillis();
      int update=0 ;
      int frame=0 ;

      while ( true )
      {
          long currentNanoTime = System.nanoTime();
          long currentNanoTimeFrames = System.nanoTime();
          deltaU = (currentNanoTime - initialNanoTime) / timePerUpdate;
          if ( deltaU >= 1)
          {
              updateLogic();
              update++;
              deltaU--;
              initialNanoTime = currentNanoTime;
          }

          deltaF = (currentNanoTimeFrames - initialNanoTimeFrames) / timePerFrame;
          if ( deltaF >=1)
          {
              gamePanel.repaint();
              frame++;
              deltaF--;
              initialNanoTimeFrames = currentNanoTimeFrames;
          }

          long currentMiliTime = System.currentTimeMillis();
          if ( currentMiliTime - initialMiliTime >= 1000)
          {
              System.out.println("Frames = " + frame + "  |  Updates = " + update);
              frame = 0;
              update=0;
              initialMiliTime = currentMiliTime;

          }
      }


    }

    public Player getPlayer()
    {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
