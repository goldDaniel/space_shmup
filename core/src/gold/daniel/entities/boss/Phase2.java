
package gold.daniel.entities.boss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import gold.daniel.entities.Boss;
import gold.daniel.entities.Bullet;
import gold.daniel.entities.BulletSpawner;
import gold.daniel.main.Assets;
import gold.daniel.main.Particle;
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
            90, 165, 4, 90,
            Bullet.BulletType.SMALL_ORANGE);
    
    static BulletSpawner rightSpawner = new BulletSpawner(
            0, 0, 1, 45, 
            90, -165, 4, 90,
            Bullet.BulletType.SMALL_ORANGE);

    static BulletSpawner follower = new BulletSpawner(
            0, 0, 1, 360,
            0, 0, 24f, 150,
            Bullet.BulletType.SMALL_PURPLE);
    
    public static void update(Boss boss, World world, float delta)
    {
        if(boss.getHealth() < boss.HEALTH_MAX / 2)
        {
            boss.setPhase(Boss.Phase.PHASE_3);
            boss.inTransition = true;
            Assets.bossExplosion.play();
            world.addParticle(new Particle(boss.x + boss.width / 2, boss.y + boss.height / 2,
                    Particle.ParticleType.EXPLOSION_LARGE));
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

        follower.x = boss.x + boss.getWidth() / 2f;
        follower.y = boss.y + boss.getHeight() / 2f;

        Vector2 bossPos = new Vector2(follower.x, follower.y);

        Vector2 playerPos = world.getPlayer().getPosition();
        playerPos.x += world.getPlayer().getWidth() / 2f;
        playerPos.y += world.getPlayer().getHeight() / 2f;

        follower.rotateAngle = MathUtils.atan2(
                playerPos.y - bossPos.y,
                playerPos.x - bossPos.x) * MathUtils.radDeg;
        
        
        crossSpawner.update(world, delta);
        leftSpawner.update(world, delta);
        rightSpawner.update(world, delta);
        follower.update(world, delta);
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
