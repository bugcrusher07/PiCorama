package entities;

import java.awt.*;

public abstract class Entity {

    protected float x, y;
    protected int width , height ;

    public Rectangle hitbox;

    public Entity(float x, float y,int width , int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox();
    }

    public void initHitBox()
    {
        hitbox = new Rectangle((int)x,(int)y,width , height);
    }

    protected void updateHitBox()
    {
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }

    public Rectangle getHitBox()
    {
        return hitbox;
    }

    public void drawHitBox(Graphics g)
    {
        g.setColor(Color.black);
        g.drawRect(hitbox.x,hitbox.y,width,height);
    }
}
