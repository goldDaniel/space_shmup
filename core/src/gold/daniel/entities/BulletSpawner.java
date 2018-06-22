
package gold.daniel.entities;

import gold.daniel.entities.Bullet.BulletType;
import gold.daniel.main.World;

/**
 *
 * @author wrksttn
 */
public class BulletSpawner
{
    
    public float x;
    public float y;
    
    //"Streams" of bullets coming out of spawner
    //or How many bullets shoot at once 
    public int bulletStreams;
    
    //angle that the "streams" are locked between
    //
    public float streamAngleBounds;
    
    //takes the inistal stream angles and rotates them
    public float rotateAngle;
    public float spinSpeed;
    
    //how many frames in between shots
    public float fireRate;
    public float fireRateCounter;
    
    
    public float bulletSpeed;
    
    public BulletType type;
    
    /**
     * 
     * @param x X spawn location
     * @param y Y spawn location
     * @param bs Amount of bullet streams 
     * @param sab Streams will be bound between 0 and this angle
     * 
     * //break here for easy to read parameters when defining(Boss phases)
     *
     * @param ra angle to rotate streams by
     * @param ss speed to adjust rotate angle 
     * @param fr how many frames between spawning bullets
     * @param bss speed bullets will travel at 
     *
     * @param type 
     */
    public BulletSpawner(float x, float y, int bs, float sab, float ra, float ss, float fr, float bss, BulletType type)
    {
        this.x = x;
        this.y = y;
        bulletStreams = bs;
        streamAngleBounds = sab;
        
        rotateAngle = ra;
        spinSpeed = ss;
        fireRate = fr;
        bulletSpeed = bss;
        
        this.type = type;
    }
    
    public void update(World level, float delta)
    {
        if(bulletStreams == 0 || fireRate == 0) return;
        
        rotateAngle += spinSpeed * delta;
        
        if(fireRateCounter >= fireRate)
        {
            fireRateCounter = 0;
            
            level.createBullet(x, y, rotateAngle, bulletSpeed, type);
            for (int i = 1; i < bulletStreams; i++)
            {
                float angle = ((float)i / (float)(bulletStreams - 1f)) * streamAngleBounds + rotateAngle;

                level.createBullet(x, y, angle, bulletSpeed, type);
            }
        }
        else
        {
            // 60*delta is aproximately 1. allows for adjusting timescales
            fireRateCounter += 60 * delta;
        }
    }
}
