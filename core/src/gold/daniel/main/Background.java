package gold.daniel.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author wrksttn
 */
public class Background
{
    Array<Texture> backgroundTextures;
    
    float speed;
    
    float y;
    
    public Background()
    {
        backgroundTextures = Assets.backgroundTextures;

        //takes 177 seconds to scroll through the entire background
        speed = Assets.backgroundTextures.first().getHeight() * 
        backgroundTextures.size / 100f;
    }
    
    public void update(float delta)
    {
        y -= speed * delta;
    }
    
    public void draw(SpriteBatch s)
    {
        
        //this took a little fuckery to get going
        //every texture gets drawn twice, as each half is a section to be drawn
        //draws the left half, once the background reaches the top of the 
        //image, switch to the right-hand side and draw that half on top
        //once this half reaches the top, draw the next image.
        for(int i = 0; i < backgroundTextures.size; i++)
        {
            Texture tex = backgroundTextures.get(i);
            for (int j = 0; j < 2; j++)
            {
                float x = (j % 2 == 0 ? 0 : -Globals.GAME_WIDTH);
          
                s.draw(tex, x, y + tex.getHeight() * (j + (i*2)),
                        Globals.GAME_WIDTH * 2, backgroundTextures.get(0).getHeight());
            }
        }
    }
}
