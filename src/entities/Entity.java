package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static Main.Game.SCALE;

public abstract class Entity {

    public float x, y;
    public int width , height ;

    public Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width ,int  height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void initHitBox(float x, float y, float width, float height)
    {
        hitbox = new Rectangle2D.Float( x, y, width , height);
    }

//    protected void updateHitBox()
//    {
//        hitbox.x = x;
//        hitbox.y = y;
//    }

    public Rectangle2D.Float getHitBox()
    {
        return  hitbox;
    }

    public void drawHitBox(Graphics g)
    {
        g.setColor(Color.black);
        g.drawRect((int) hitbox.x , (int) hitbox.y , (int) hitbox.width , (int) hitbox.height );
    }
}