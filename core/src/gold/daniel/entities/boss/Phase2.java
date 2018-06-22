
package gold.daniel.entities.boss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import gold.daniel.entities.Boss;
import gold.daniel.entities.Bullet;
import gold.daniel.entities.BulletSpawner;
import gold.daniel.main.World;
/**
 *
 * @author wrksttn
 */
public class Phase2
{   
    static float cycle = 90f;
    
    static BulletSpawner crossSpawner = new BulletSpawner(
            0, 0, 12, 360,
            0, 40f, 16, 125, 
            Bullet.BulletType.SMALL_BLUE);
    
    
    static BulletSpawner leftSpawner = new BulletSpawner(
            0, 0, 1, 45, 
            90, 125, 6, 90, 
            Bullet.BulletType.SMALL_ORANGE);
    
    static BulletSpawner rightSpawner = new BulletSpawner(
            0, 0, 1, 45, 
            90, -125, 6, 90, 
            Bullet.BulletType.SMALL_PURPLE);
    
    
    public static void update(Boss boss, World world, float delta)
    {
        if(boss.getHealth() < 200)
        {
            boss.setPhase(Boss.Phase.PHASE_3);
            boss.inTransition = true;
            return;
        }
        
        cycle += 60f * delta;
        //cos/sin flipped on purpose
        boss.x += MathUtils.sinDeg(cycle) * 90f * delta;
        boss.y += MathUtils.cosDeg(cycle) * 30f * delta;
        crossSpawner.x = boss.x + boss.width / 2f;
        crossSpawner.y = boss.y + boss.height / 2f;
        
        leftSpawner.x = boss.x + boss.width / 4f;
        leftSpawner.y = boss.y + boss.height / 2f;
       
        rightSpawner.x = boss.x + boss.width * 3f / 4f;
        rightSpawner.y = boss.y + boss.height / 2f;
        
        
        crossSpawner.update(world, delta);
        leftSpawner.update(world, delta);
        rightSpawner.update(world, delta);
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
