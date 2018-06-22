/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import gold.daniel.main.World;

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

    public boolean invulnerable = false;
    
    public final float START_TIME;
    
    private static final Rectangle RECT_1 = new Rectangle();
    private static final Rectangle RECT_2 = new Rectangle();
    
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

    /**
     * Returns side entity is colliding with relative to this.
     * 
     *  1 for top
     * -1 for bottom
     * -2 for left
     * 2 for right
     * 
     * @param obj
     * @return
     */
    public int getCollisionSide(Entity obj)
    {
        int result = 0;
        //decide what side the object is on relative to the tile
        if (isBoundingBoxColliding(obj))
        {
            //horizontal side
            boolean left = x < obj.x;
            //vertical side
            boolean above = y > obj.y;

            //holds how deep the object is inside the tile on each axis
            float horizontalDif;
            float verticalDif;

            //determine the differences for depth
            if (left)
            {
                horizontalDif = x + width - obj.x;
            }
            else
            {
                horizontalDif = obj.x + obj.width - x;
            }

            if (above)
            {
                verticalDif = obj.y + obj.height - y;
            }
            else
            {
                verticalDif = y + height - obj.y;
            }

            if (horizontalDif < verticalDif)
            {
                if (left)
                {
                    result = -2;
                }
                else
                {
                    result = 2;
                }
            }
            else if (above)
            {
                result = 1;
            }
            else
            {
                result = -1;
            }
        }
        return result;
    }

    /**
     * do collision for horizontal axis.
     * 
     * @param obj
     * @return side entity is colliding with. -1 for left, 1 for right.
     */
    public int handleHorizontalCollisionResponse(Entity obj)
    {
        int result = 0;

        if (isBoundingBoxColliding(obj))
        {
            //horizontal side
            boolean left = x <= obj.x;

            //determine the differences for depth
            if (left)
            {
                result = -1;
                setX(obj.x - width);
            }
            else
            {
                result = 1;
                setX(obj.x + obj.width);
            }
        }
        return result;
    }

    /**
     * Handles collisions for vertical axis
     *
     *
     * @param obj
     * @return 0 if not colliding, 1 if above, -1 if below.
     */
    public int handleVerticalCollisionResponse(Entity obj)
    {
        int result = 0;

        if (isBoundingBoxColliding(obj))
        {
            boolean above = y >= obj.y;

            if (above)
            {
                result = 1;
                setY(obj.y + obj.height);
            }
            else
            {
                result = -1;
                setY(obj.y - height);
            }
        }
        return result;
    }

    /**
     *
     * ONLY USE FOR TOP-DOWN STYLE MOVING TYPES.
     *
     * makes entity go to proper position after colliding with an object. maybe
     * move this method the the World class?
     *
     * @param obj
     * @return if collision happened
     */
    public boolean handleMoveCollisionResponse(Entity obj)
    {
        boolean result = false;

        //broad check if colliding
        if (isBoundingBoxColliding(obj))
        {
            result = true;
            //decide what side the object is on relative to the tile

            //horizontal side
            boolean left = x < obj.x;
            //vertical side
            boolean above = y > obj.y;

            //holds how deep the object is inside the tile on each axis
            float horizontalDif;
            float verticalDif;

            //determine the differences for depth
            if (left)
            {
                horizontalDif = x + width - obj.x;
            }
            else
            {
                horizontalDif = obj.x + obj.width - x;
            }

            if (above)
            {
                verticalDif = obj.y + obj.height - y;
            }
            else
            {
                verticalDif = y + height - obj.y;
            }

            //DO HORIZONTAL
            if (horizontalDif < verticalDif)
            {
                if (left)
                {
                    setX(obj.x - width - 1);
                }
                else
                {
                    setX(obj.x + obj.width + 1);
                }
            }
            //DO VERTICAL
            else if (above)
            {
                setY(obj.y + obj.height + 1);
            }
            else
            {
                setY(obj.y - height - 1);
            }
        }
        return result;
    }

    /**
     * checks collision with gameObject
     *
     * @param obj
     * @return
     */
    public boolean isBoundingBoxColliding(Entity obj)
    {
        RECT_2.x = obj.x;
        RECT_2.y = obj.y;
        RECT_2.width = obj.width;
        RECT_2.height = obj.height;

        return isBoundingBoxColliding(RECT_2);
    }

    /**
     * checks collision with rectangle.
     *
     * @param rect
     * @return
     */
    public boolean isBoundingBoxColliding(Rectangle rect)
    {
        RECT_1.x = this.x;
        RECT_1.y = this.y;
        RECT_1.width = this.width;
        RECT_1.height = this.height;

        return RECT_1.overlaps(rect) || rect.overlaps(RECT_1);
    }
    
    public boolean isEntityAbove(Entity e)
    {
        return y + height > e.y;
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
