/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author wrksttn
 */
public class Assets
{
    private static AssetManager manager = new AssetManager();

    public static Skin uiSkin;

    public static Texture texture;
    
    public static TextureRegion playerBullet0;
    public static TextureRegion playerBullet1;
    public static TextureRegion playerBullet2;
    public static TextureRegion playerBullet3;
    
    
    public static TextureRegion smallBulletPurple;
    public static TextureRegion smallBulletOrange;
    public static TextureRegion smallBulletBlue;
    
    public static Texture boss;
    public static Texture backgroundLoop;
    
    //PLAYER TEXTURES
    public static Array<Texture> playerIdleFrames = new Array<Texture>();
    public static Array<Texture> playerTransitionFrames = new Array<Texture>();
    public static Array<Texture> playerLeanFrames = new Array<Texture>();
    
    //BLUE PARTICLES
    public static Array<Texture> particleAdditional01 = new Array<Texture>();
    public static Array<Texture> particleAdditional02 = new Array<Texture>();
    public static Array<Texture> particleAdditional03 = new Array<Texture>();
    
    //ORANGE PARTICLES
    public static Array<Texture> particleAdditional04 = new Array<Texture>();
    public static Array<Texture> particleAdditional05 = new Array<Texture>();
    public static Array<Texture> particleAdditional06 = new Array<Texture>();
    
    //EXPLOSION PARTICLES
    public static Array<Texture> particleExplosionSmall = new Array<Texture>();
    public static Array<Texture> particleExplosionLarge = new Array<Texture>();
    
    public static Array<Texture> backgroundTextures = new Array<Texture>();
    
    public static Music backgroundMusic;
    
    public static Sound playerLaser;
    
    public static Sound enemySmallExplosion;
    public static Sound playerExplosion;
    public static Sound bossExplosion;
    
    public static String level01;
    
    
    public static void load()
    {
        manager.load("ui/neon-ui.json", Skin.class);
        
        manager.load("misc/texture.png", Texture.class);
        
        manager.load("enemies/boss/Boss_Merged.png", Texture.class);
        
        manager.load("enemies/medium/Medium_Ship_Merged.png", Texture.class);
        
        manager.load("background/level/Stage_1_Loop.png", Texture.class);
        
        manager.load("player/Ship_Bank_00_Frame_1.png", Texture.class);
        manager.load("player/Ship_Bank_00_Frame_2.png", Texture.class);
        manager.load("player/Ship_Bank_00_Frame_3.png", Texture.class);
        manager.load("player/Ship_Bank_00_Frame_4.png", Texture.class);
        
        manager.load("player/Ship_Bank_15_Frame_1.png", Texture.class);
        manager.load("player/Ship_Bank_30_Frame_1.png", Texture.class);
        
                
        manager.load("player/Ship_Bank_45_Frame_1.png", Texture.class);
        manager.load("player/Ship_Bank_45_Frame_2.png", Texture.class);
        manager.load("player/Ship_Bank_45_Frame_3.png", Texture.class);
        manager.load("player/Ship_Bank_45_Frame_4.png", Texture.class);
        
        manager.load("particles/Add_Hitspark_01_Frame_0.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_1.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_2.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_3.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_4.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_5.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_6.png", Texture.class);
        manager.load("particles/Add_Hitspark_01_Frame_7.png", Texture.class);
        
        manager.load("particles/Add_Hitspark_02_Frame_0.png", Texture.class);
        manager.load("particles/Add_Hitspark_02_Frame_1.png", Texture.class);
        manager.load("particles/Add_Hitspark_02_Frame_2.png", Texture.class);
        manager.load("particles/Add_Hitspark_02_Frame_3.png", Texture.class);
        manager.load("particles/Add_Hitspark_02_Frame_4.png", Texture.class);
        manager.load("particles/Add_Hitspark_02_Frame_5.png", Texture.class);
        manager.load("particles/Add_Hitspark_02_Frame_6.png", Texture.class);
        
        manager.load("particles/Add_Hitspark_03_Frame_0.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_1.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_2.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_3.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_4.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_5.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_6.png", Texture.class);
        manager.load("particles/Add_Hitspark_03_Frame_7.png", Texture.class);
        
        manager.load("particles/Add_Hitspark_04_Frame_0.png", Texture.class);
        manager.load("particles/Add_Hitspark_04_Frame_1.png", Texture.class);
        manager.load("particles/Add_Hitspark_04_Frame_2.png", Texture.class);
        manager.load("particles/Add_Hitspark_04_Frame_3.png", Texture.class);
        manager.load("particles/Add_Hitspark_04_Frame_4.png", Texture.class);
        manager.load("particles/Add_Hitspark_04_Frame_5.png", Texture.class);
        manager.load("particles/Add_Hitspark_04_Frame_6.png", Texture.class);
        
        manager.load("particles/Add_Hitspark_05_Frame_0.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_1.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_2.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_3.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_4.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_5.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_6.png", Texture.class);
        manager.load("particles/Add_Hitspark_05_Frame_7.png", Texture.class);
        
        manager.load("particles/Add_Hitspark_06_Frame_0.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_1.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_2.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_3.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_4.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_5.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_6.png", Texture.class);
        manager.load("particles/Add_Hitspark_06_Frame_7.png", Texture.class);
     
        manager.load("particles/Explosion_100x100_Frame_00.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_01.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_02.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_03.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_04.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_05.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_06.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_07.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_08.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_09.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_10.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_11.png", Texture.class);
        manager.load("particles/Explosion_100x100_Frame_12.png", Texture.class);

        manager.load("particles/Explosion_250x250_Frame_01.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_02.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_03.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_04.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_05.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_06.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_07.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_08.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_09.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_10.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_11.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_12.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_13.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_14.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_15.png", Texture.class);
        manager.load("particles/Explosion_250x250_Frame_16.png", Texture.class);
        
        manager.load("player/bullets/Bullet_Level_0.png", Texture.class);
        manager.load("player/bullets/Bullet_Level_1.png", Texture.class);
        manager.load("player/bullets/Bullet_Level_2.png", Texture.class);
        manager.load("player/bullets/Bullet_Level_3.png", Texture.class);
        
        manager.load("player/bullets/Tear_Shot_Blue_Frame_1.png", Texture.class);
        
        manager.load("enemies/small/Small_Ship_000_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_030_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_060_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_090_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_120_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_150_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_180_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_210_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_240_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_270_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_300_Degrees.png", Texture.class);
        manager.load("enemies/small/Small_Ship_330_Degrees.png", Texture.class);
        
        manager.load("background/level/Stage_1_Page_1.png", Texture.class);
        manager.load("background/level/Stage_1_Page_2.png", Texture.class);
        manager.load("background/level/Stage_1_Page_3.png", Texture.class);
        manager.load("background/level/Stage_1_Page_4.png", Texture.class);
        manager.load("background/level/Stage_1_Page_5.png", Texture.class);
        
        manager.load("audio/gameBackgroundMusic.mp3", Music.class);
        
        manager.load("audio/playerLaser.wav", Sound.class);
        
        manager.load("enemies/bullets/Small_Shot_Purple.png", Texture.class);
        manager.load("enemies/bullets/Small_Shot_Orange.png", Texture.class);
        manager.load("enemies/bullets/Small_Shot_Blue.png", Texture.class);
        
        manager.load("audio/enemySmallExplosion.wav", Sound.class);
        manager.load("audio/enemyMediumExplosion.wav", Sound.class);
        manager.load("audio/bossExplosion.wav", Sound.class);
    }

    public static void setAssetReferences()
    {
        texture = manager.get("misc/texture.png", Texture.class);
        
        uiSkin     = manager.get("ui/neon-ui.json", Skin.class);
        
        playerBullet0 = new TextureRegion(manager.get("player/bullets/Bullet_Level_0.png", Texture.class));
        playerBullet1 = new TextureRegion(manager.get("player/bullets/Bullet_Level_1.png", Texture.class));
        playerBullet2 = new TextureRegion(manager.get("player/bullets/Bullet_Level_2.png", Texture.class));
        playerBullet3 = new TextureRegion(manager.get("player/bullets/Bullet_Level_3.png", Texture.class));
        
        boss = manager.get("enemies/boss/Boss_Merged.png", Texture.class);
        
        backgroundLoop = manager.get("background/level/Stage_1_Loop.png", Texture.class);
        
        playerIdleFrames.add(manager.get("player/Ship_Bank_00_Frame_1.png", Texture.class));
        playerIdleFrames.add(manager.get("player/Ship_Bank_00_Frame_2.png", Texture.class));
        playerIdleFrames.add(manager.get("player/Ship_Bank_00_Frame_3.png", Texture.class));
        playerIdleFrames.add(manager.get("player/Ship_Bank_00_Frame_4.png", Texture.class));
        
        playerTransitionFrames.add(manager.get("player/Ship_Bank_15_Frame_1.png", Texture.class));
        playerTransitionFrames.add(manager.get("player/Ship_Bank_30_Frame_1.png", Texture.class));

        playerLeanFrames.add(manager.get("player/Ship_Bank_45_Frame_1.png", Texture.class));
        playerLeanFrames.add(manager.get("player/Ship_Bank_45_Frame_2.png", Texture.class));
        playerLeanFrames.add(manager.get("player/Ship_Bank_45_Frame_3.png", Texture.class));
        
        
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_0.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_1.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_2.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_3.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_4.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_5.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_6.png", Texture.class));
        particleAdditional01.add(manager.get("particles/Add_Hitspark_01_Frame_7.png", Texture.class));
        
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_0.png", Texture.class));
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_1.png", Texture.class));
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_2.png", Texture.class));
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_3.png", Texture.class));
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_4.png", Texture.class));
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_5.png", Texture.class));
        particleAdditional02.add(manager.get("particles/Add_Hitspark_02_Frame_6.png", Texture.class));
        
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_0.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_1.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_2.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_3.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_4.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_5.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_6.png", Texture.class));
        particleAdditional03.add(manager.get("particles/Add_Hitspark_03_Frame_7.png", Texture.class));
        
        
        ////////////////////////////////////////////////////////////////////////////////
        
        
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_0.png", Texture.class));
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_1.png", Texture.class));
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_2.png", Texture.class));
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_3.png", Texture.class));
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_4.png", Texture.class));
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_5.png", Texture.class));
        particleAdditional04.add(manager.get("particles/Add_Hitspark_04_Frame_6.png", Texture.class));
        
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_0.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_1.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_2.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_3.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_4.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_5.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_6.png", Texture.class));
        particleAdditional05.add(manager.get("particles/Add_Hitspark_05_Frame_7.png", Texture.class));
        
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_0.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_1.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_2.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_3.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_4.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_5.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_6.png", Texture.class));
        particleAdditional06.add(manager.get("particles/Add_Hitspark_06_Frame_7.png", Texture.class));
        
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_00.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_01.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_02.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_03.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_04.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_05.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_06.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_07.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_08.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_09.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_10.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_11.png", Texture.class));
        particleExplosionSmall.add(manager.get("particles/Explosion_100x100_Frame_12.png", Texture.class));
        
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_01.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_02.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_03.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_04.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_05.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_06.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_07.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_08.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_09.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_10.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_11.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_12.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_13.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_14.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_15.png", Texture.class));
        particleExplosionLarge.add(manager.get("particles/Explosion_250x250_Frame_16.png", Texture.class));
        
        backgroundTextures.add(manager.get("background/level/Stage_1_Page_1.png", Texture.class));
        backgroundTextures.add(manager.get("background/level/Stage_1_Page_2.png", Texture.class));
        backgroundTextures.add(manager.get("background/level/Stage_1_Page_3.png", Texture.class));
        backgroundTextures.add(manager.get("background/level/Stage_1_Page_4.png", Texture.class));
        backgroundTextures.add(manager.get("background/level/Stage_1_Page_5.png", Texture.class));
        
        backgroundMusic = manager.get("audio/gameBackgroundMusic.mp3", Music.class);
        backgroundMusic.setVolume(0.2f);
        
        playerLaser = manager.get("audio/playerLaser.wav", Sound.class);
        
        enemySmallExplosion = manager.get("audio/enemySmallExplosion.wav", Sound.class);
        playerExplosion = manager.get("audio/enemyMediumExplosion.wav", Sound.class);
        bossExplosion = manager.get("audio/bossExplosion.wav", Sound.class);
        
        smallBulletPurple = new TextureRegion(manager.get("enemies/bullets/Small_Shot_Purple.png", Texture.class));
        smallBulletOrange = new TextureRegion(manager.get("enemies/bullets/Small_Shot_Orange.png", Texture.class));
        smallBulletBlue = new TextureRegion(manager.get("enemies/bullets/Small_Shot_Blue.png", Texture.class));
        
        level01 = Gdx.files.internal("levels/level01.txt").readString();
    }

    public static AssetManager getManager()
    {
        return manager;
    }
}
