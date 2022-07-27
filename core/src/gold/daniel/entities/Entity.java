/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import gold.daniel.main.World;
import jdk.nashorn.internal.objects.annotations.Where;

/**
 * @author wrksttnpc
 */
public abstract class Entity
{
    public float x;
    public float y;
    public float width;
    public float height;
    protected boolean isAlive;

    public float collisionRadius;

    public boolean invulnerable = false;
    
    public final float START_TIME;

    
    public Entity(float startTime)
    {
        START_TIME = startTime;
    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public float getWidth()
    {
        return width;
    }
    
    public boolean isAlive()
    {
        return isAlive;
    }
    
    public float getHeight()
    {
        return height;
    }
    
    public abstract void update(World level, float delta);
    public abstract void draw(SpriteBatch batch, float parentAlpha);
    public void draw(ShapeRenderer sh)
    {
        sh.setColor(Color.WHITE);
        Circle c = getCollisionCircle();
        sh.circle(c.x, c.y, c.radius);
        sh.circle(c.x, c.y, c.radius);
    }
    public abstract void kill(World level);
    public abstract void dispose();
    
    public Vector2 getPosition()
    {
        return new Vector2(x, y);
    }

    /**
     * calculates angle from center of character to a point X,Y
     *
     * @param x
     * @param y
     * @return
     */
    protected float calculateAngleToPoint(float x, float y)
    {
        Vector2 temp = new Vector2(this.x + width / 2, this.y + height / 2);
        return temp.sub(x, y).angle();
    }

    public Circle getCollisionCircle()
    {
        return new Circle(x + width / 2, y + height / 2, collisionRadius);
    }


    public boolean isColliding(Entity entity)
    {
        Circle c1 = entity.getCollisionCircle();
        return isColliding(c1);
    }

    public boolean isColliding(Circle c1)
    {
        Circle c0 = this.getCollisionCircle();
        return c0.overlaps(c1);
    }
    
    public void setX(float x)
    {
        this.x = x;
    }
    
    public void setY(float y)
    {
        this.y = y;
    }
    
    public void setWidth(float width)
    {
        this.width = width;
    }
    
    public void setHeight(float height)
    {
        this.height = height;
    }
    

}
