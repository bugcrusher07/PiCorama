package Utilz;

import Main.Game;

import java.awt.geom.Rectangle2D;

import static Main.Game.*;

public class HelpMethods {

    public static boolean shouldMove(float x, float y, float width, float height, int[][] lvlData)
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
        float yIndex = y/TILE_SIZE;
        int value = lvlData[(int)yIndex][(int)xIndex];

        if ( value >= 48 || value < 0 || value!=11)
            return true;


        return false;

    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int) (hitbox.x / TILE_SIZE);
        if (xSpeed > 0)
        { //Right
            int tileXPos = currentTile * TILE_SIZE;
            int xOffset = (int) (TILE_SIZE-hitbox.width);
            return tileXPos + xOffset -1;
        }else {
            return currentTile * TILE_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed)
    {
        int currentTile = (int) (hitbox.y / TILE_SIZE);
        if(airSpeed> 0)
        { //Falling -Touching Floor
            int tileYPos = currentTile*TILE_SIZE;
            int yOffset = (int) (TILE_SIZE-hitbox.height);
            return yOffset + tileYPos -1;
        }
        else
        { //Jumping
            return currentTile*TILE_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][]levelData)
    { //Check the pixel below bottom left and bottom right
        if(!isSolid(hitbox.x, hitbox.y+hitbox.height+1, levelData))
            if(!isSolid(hitbox.x+hitbox.width, hitbox.y+hitbox.height+1, levelData))
                return false;

        return true;

    }
}