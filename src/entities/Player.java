package entities;

import Utilz.LoadSave;
import org.w3c.dom.ls.LSOutput;

import static Utilz.HelpMethods.shouldMove;
import static Utilz.HelpMethods.isSolid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utilz.Constants.Direction.*;
import static Utilz.Constants.Direction.DOWN;
import static Utilz.Constants.Player_Constants.*;

public class Player extends Entity{


    private int aniTick,aniIndex, aniSpeed=50;
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false;
    private BufferedImage [][] idleAni;
    private boolean left , up , right , down ;
    private float playerSpeed = 1.0f;
    private int [][] levelData ;


    public Player(float x, float y, int width , int height)
    {
        super(x, y,width , height);
        loadIdleAni();
        initHitBox();
    }

    public void update()
    {
        updatePOS();
        updateHitBox();
        updateAni();
        setAni();

    }

    public void render (Graphics g)
    {
        g.drawImage(idleAni[playerAction][aniIndex], (int)x,(int) y ,width,height, null);
        drawHitBox(g);
    }

    public void loadLvlData (int [][] lvlData)
    {
        this.levelData = lvlData;
    }

    public void setAttacking (boolean attacking)
    {
        this.attacking = attacking;
    }

    public void setAni ()
    {
        int startAni = playerAction;
        if (moving == true)
        {
            playerAction=RUNNING;
        }
        else {
            playerAction=IDLE;
        }

        if ( attacking )
        {
            playerAction = SWORD_ATTACK_1;
        }

        if ( startAni != playerAction)
        {
            resetAniLoop();
        }
    }

    private void resetAniLoop() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void  updatePOS()
    {
        moving = false;
        if ( !left && !right && !up && !down)
            return;

        float xSpeed = 0;
        float ySpeed = 0;

        if ( left &&  (!right))
        {  xSpeed = -playerSpeed;}
          // moving = true;
           //x += xSpeed;}
        else if ( right && !left)
        {xSpeed= playerSpeed;}
         //   moving = true;
        //x+= xSpeed;}

        if ( up && !down)
        {ySpeed = -playerSpeed;}
           // moving = true;
       // y +=ySpeed;}
        else if ( down && !up)
        {ySpeed =playerSpeed;}
           // moving = true;
            //y +=ySpeed;}


        if (shouldMove(x + xSpeed,y + ySpeed,width,height,levelData) )
        {
            this.x += xSpeed;
            this.y += ySpeed;
            moving = true;
            System.out.println("levelData");
        }
        else {
            System.out.println("noleveldata");
        }

    }



    public void updateAni()
    {
        aniTick++;
        if ( aniTick >= aniSpeed)
        {
            aniIndex++;
            aniTick=0;
            if ( aniIndex >= animationIndex(playerAction) )
            {
                aniIndex=0;
                attacking= false;
            }
        }
    }


    private void loadIdleAni()
    {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        idleAni = new BufferedImage[9][6];

            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 6; j++)
                {
                    idleAni[i][j] = img.getSubimage(64 * j, 40 * i, 64, 40);
                }
            }
    }

    public void resetDirBooleans() {
        up = false ;
        right = false ;
        down = false;
        left = false;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }




}
