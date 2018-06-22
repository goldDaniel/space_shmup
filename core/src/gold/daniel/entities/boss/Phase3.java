/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Phase3
{   
    
    private static float cycle = MathUtils.PI/2f;
    
    static BulletSpawner leftContainment = new BulletSpawner(
            0, 0, 1, 360, 
            -90, 0, 1, 250, 
            Bullet.BulletType.SMALL_ORANGE);
    
    static BulletSpawner rightContainment = new BulletSpawner(
            0, 0, 1, 360,
            -90, 0, 1, 250, 
            Bullet.BulletType.SMALL_ORANGE);
    
    static BulletSpawner follower = new BulletSpawner(
            0, 0, 3, 45,
            0, 0, 35f, 90f, 
            Bullet.BulletType.SMALL_BLUE);
    
    static BulletSpawner spinSpawner = new BulletSpawner(
            0, 0, 2, 360,
            0, 270f, 3, 75, 
            Bullet.BulletType.SMALL_PURPLE);
    
    public static void update(Boss boss, World world, float delta)
    {
        if(boss.getHealth() < 100)
        {
            boss.setPhase(Boss.Phase.PHASE_4);
            boss.inTransition = true;
            return;
        }
        boss.x += MathUtils.cosDeg(cycle) * 105f * delta;
        
        cycle += delta*60f;
        
        leftContainment.x = boss.x + boss.getWidth() * 1f / 10f;
        leftContainment.y = boss.y + boss.getHeight() / 2f;
        
        rightContainment.x = boss.x + boss.getWidth() * 9f / 10f;
        rightContainment.y = boss.y + boss.getHeight() / 2f;
        
        follower.x = spinSpawner.x = boss.x + boss.getWidth()/2f;
        follower.y = spinSpawner.y = boss.y + boss.getHeight()/2f;
        
        Vector2 bossPos = new Vector2(follower.x, follower.y);
        
        Vector2 playerPos = world.getPlayer().getPosition();
        playerPos.x += world.getPlayer().getWidth() / 2f;
        playerPos.y += world.getPlayer().getHeight() / 2f;
        
        follower.rotateAngle = MathUtils.atan2(
                playerPos.y - bossPos.y, 
                playerPos.x - bossPos.x) * MathUtils.radDeg;
        follower.rotateAngle -= follower.streamAngleBounds/2f;
        
        leftContainment.update(world, delta);
        rightContainment.update(world, delta);
        
        follower.update(world, delta);
        
        spinSpawner.update(world, delta);
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
