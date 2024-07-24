package entities;

import TrueLevel.Level;
import Utilz.LoadSave;

import static Main.Game.*;
import static Main.Game.TILE_SIZE;
import static Utilz.HelpMethods.shouldMove;
import static Utilz.HelpMethods.isSolid;

import java.awt.*;
import java.awt.image.BufferedImage;

import static TrueLevel.LevelManager.level;

import static Utilz.Constants.Direction.*;
import static Utilz.Constants.Direction.DOWN;
import static Utilz.Constants.Player_Constants.*;
import static Utilz.LoadSave.BLACK;
import static Utilz.HelpMethods.*;

public class Player extends Entity{


    private int aniTick,aniIndex, aniSpeed=50;
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false;
    private BufferedImage [][] idleAni;
    private boolean left , up , right , down ;
    private float playerSpeed = 1.5f;
    private int [][] levelData ;
    private BufferedImage img2;
    private Level level2;
    private float xDrawOffset = 21*SCALE;
    private float yDrawOffset = 4*SCALE;

    // Jumping/Gravity
    private float airSpeed = 0f;
    private boolean jump;
    private float gravity = 0.02f * SCALE;
    private float jumpSpeed = -2.25f * SCALE;
    private float fallSpeedAfterCollision = 0.5f *SCALE;
    private boolean inAir = false;


    public Player(float x, float y, int width , int height)
    {
        super(x, y,width , height);
        loadIdleAni();
        initHitBox(x,y,20*SCALE,28*SCALE);
    }

    public void update()
    {
        updatePOS();

        updateAni();
        setAni();

    }

    public void render (Graphics g)
    {
        g.drawImage( idleAni[playerAction][aniIndex], (int) (hitbox.x-xDrawOffset),(int) (hitbox.y-yDrawOffset),width,height, null);
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

    public BufferedImage getBlack()
    {
        img2 = LoadSave.GetSpriteAtlas(BLACK);
        return img2;
    }
    public void drawTempLevelData(Graphics g)
    {

        for ( int i = 0 ; i < TILE_HEIGHT; i++)
            for ( int j = 0 ; j < TILE_WIDTH ; j++)
            {
                int index = level2.getSpriteIndex(j,i);
                g.drawImage(level[index], TILE_SIZE*j , TILE_SIZE*i,TILE_SIZE,TILE_SIZE ,null);
            }

    }


    private void resetAniLoop() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void  updatePOS()
    {
        moving = false;

        if(jump)
        {
            jump();
        }
        if ( !left && !right && !inAir)
            return;

        float xSpeed = 0;


        if ( left)
        {  xSpeed -= playerSpeed;}

        if ( right )
        {xSpeed += playerSpeed;}

        if(!inAir)
            if(!IsEntityOnFloor(hitbox,levelData))
                inAir = true;

        if (inAir) {
            if(shouldMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height,levelData))
            {
                hitbox.y +=airSpeed;
                airSpeed +=gravity;
                updateXPos(xSpeed);
            }
            else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        } else{
           updateXPos(xSpeed);
        }
            moving = true;
    }

    private void jump() {
        if(inAir)
            return;

        inAir = true;
        airSpeed = jumpSpeed;

    }

    private void resetInAir() {
        inAir = false;
        airSpeed= 0;
    }

    private void updateXPos(float xSpeed) {

        if (shouldMove(hitbox.x + xSpeed,hitbox.y ,hitbox.width,hitbox.height,levelData) )
            hitbox.x += xSpeed;
        else{
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}