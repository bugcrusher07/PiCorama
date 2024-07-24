package TrueLevel;

import Main.Game;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Game.*;


public class LevelManager {

    private Game game;
    public static BufferedImage[] level ;
    private Level levelOne;

    public LevelManager (Game game)
    {
        getLevelData();
        this.game = game;
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void getLevelData()
    {

        level = new BufferedImage[48] ;
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        for ( int i = 0 ; i < 4 ; i++)
        {
            for (int j = 0 ; j < 12 ; j++)
            {
                int index = j+(i*12);
                level[index] = img.getSubimage(32*j, 32*i ,32, 32);
            }
        }

    }

    public void update()
    {

    }
    public Level getLevel ()
    {
        return levelOne;
    }

    public void render (Graphics g)
    {
        for ( int i = 0 ; i < TILE_HEIGHT; i++)
            for ( int j = 0 ; j < TILE_WIDTH ; j++)
            {
                int index = levelOne.getSpriteIndex(j,i);
                g.drawImage(level[index], TILE_SIZE*j , TILE_SIZE*i,TILE_SIZE,TILE_SIZE ,null);
            }

    }

}
