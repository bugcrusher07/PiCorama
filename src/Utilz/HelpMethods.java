package Utilz;

import static Main.Game.*;

public class HelpMethods {

    public static boolean shouldMove(float x, float y, int width, int height, int[][] lvlData)
    {
        if ( !isSolid(x,y,lvlData))
            if ( !isSolid(x+width , y+height, lvlData))
                if ( !isSolid(x+width,y, lvlData))
                    if(!isSolid(x,y+height, lvlData))
                        return true;
        return false;


    }

    public static boolean isSolid(float x , float y, int[][] lvlData)
    {
        if ( x >= GAME_WIDTH || x < 0)
            return true;
        if ( y >= GAME_HEIGHT || y <0)
            return true;

        float xIndex = x/TILE_SIZE;
        float yIndex = x/ TILE_SIZE;
        int value = lvlData[(int)yIndex][(int)xIndex];

        if ( value >= 48 || value < 0 || value!=11)
            return true;

        if ( value ==11)
            return false;

        return false;

    }
}
