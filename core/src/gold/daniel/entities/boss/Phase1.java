
package gold.daniel.entities.boss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import gold.daniel.entities.Boss;
import gold.daniel.entities.Bullet;
import gold.daniel.entities.BulletSpawner;
import gold.daniel.main.World;

/**
 *
 * @author wrksttn
 */
public class Phase1
{
    
    static float bossCircleAngle = 90f;
    static boolean rotateClockwise = true;
    static final float START_ANGLE = -135f;
    
    static BulletSpawner follower = new BulletSpawner(
            0, 0, 1, 360,
            0, 0, 35f, 150, 
            Bullet.BulletType.SMALL_PURPLE);
    
    static BulletSpawner containment = new BulletSpawner(
            0, 0, 2, 90,
            0, 60, 1, 400f/3f, 
            Bullet.BulletType.SMALL_ORANGE);
    
    public static void update(Boss boss, World world, float delta)
    {
        if(boss.getHealth() < 300)
        {
            boss.setPhase(Boss.Phase.PHASE_2);
            boss.inTransition = true;
            return;
        }
        
        bossCircleAngle += 60f * delta;
        //cos/sin flipped on purpose
        boss.x += MathUtils.sinDeg(bossCircleAngle) * 105f * delta;
        boss.y += MathUtils.cosDeg(bossCircleAngle) * 45f * delta;
        
        
        containment.x = boss.x + boss.getWidth() / 2f;
        containment.y = boss.y + boss.getHeight() / 2f;
        
        if(containment.rotateAngle > -89f)
        {
            containment.rotateAngle = -90f;
            containment.spinSpeed = -containment.spinSpeed;
        }
        if(containment.rotateAngle < -181f)
        {
            containment.rotateAngle = -180f;
            containment.spinSpeed = -containment.spinSpeed;
        }
        
        follower.x = boss.x + boss.getWidth() / 2f;
        follower.y = boss.y + boss.getHeight() / 2f;
        
        Vector2 bossPos = new Vector2(follower.x, follower.y);
        
        Vector2 playerPos = world.getPlayer().getPosition();
        playerPos.x += world.getPlayer().getWidth() / 2f;
        playerPos.y += world.getPlayer().getHeight() / 2f;
        
        follower.rotateAngle = MathUtils.atan2(
                playerPos.y - bossPos.y, 
                playerPos.x - bossPos.x) * MathUtils.radDeg;
        
                
        containment.update(world, delta);
        follower.update(world, delta);
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
    