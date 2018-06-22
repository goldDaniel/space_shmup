package gold.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import gold.daniel.entities.Boss;
import gold.daniel.entities.Bullet;
import gold.daniel.entities.Player;
import gold.daniel.main.Assets;
import gold.daniel.main.Background;
import gold.daniel.main.GameStateManager;
import gold.daniel.main.Globals;
import gold.daniel.main.Particle;
import gold.daniel.main.World;

/**
 *
 * @author wrksttn
 */
public class PlayScreen extends GameScreen
{
    final float SECONDS_UNTIL_START = 5f;
    
    //seconds past 
    float stateTime;
    
    World world;
    
    Player player;
    
    Background background;
        
    boolean paused = false;
    
    final Label startLabel;
    final Label winLabel;
    final Label loseLabel;
    
    public PlayScreen(GameStateManager engine)
    {
        super(engine);
        
        background = new Background();
        
        player = new Player(engine.getNewInput());
        player.setX(Globals.GAME_WIDTH / 2 - player.getWidth() / 2);
        player.setY(-player.getHeight());
        
        
        world = createBossTest();
        //level = createTestLevel();
        
        world.addEntity(player); 
        
        startLabel = new Label("GAME START", uiSkin);
        startLabel.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - startLabel.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() / 2 - startLabel.getHeight() / 2);
        
        uiStage.addActor(startLabel);
        
        winLabel = new Label("YOU WIN!", uiSkin);
        winLabel.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - winLabel.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() / 2 - winLabel.getHeight() / 2);
        
        loseLabel = new Label("YOU LOSE!", uiSkin);
        loseLabel.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - loseLabel.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() / 2 - loseLabel.getHeight() / 2);
        
        //play causes the audio to be buffered.the game will stutter
        //when the music begins otherwise
        Assets.backgroundMusic.play();
        Assets.backgroundMusic.pause();
    }
    
    private World createBossTest()
    {
        World result = new World();
        
        result.addEntity(new Boss(0));
        
        
        return result;
    }

    @Override
    protected void update(float deltaTime)
    {  
        stateTime+= deltaTime;
        Gdx.input.setInputProcessor(uiStage);

        
        Assets.backgroundMusic.play();
        

        uiStage.addActor(startLabel);
        
        if(stateTime > 5)
        {
            uiStage.getActors().removeValue(startLabel, true);
        }
        
        if(world.getEntityType(Boss.class).size == 0)
        {
            if(!uiStage.getActors().contains(winLabel, true))
            {
                uiStage.addActor(winLabel);
            }
            Array<Particle> p = world.getParticles();
            for(Particle particle : p)
            {
                particle.update(deltaTime);
            }
            return;
        }
        else if(world.getEntityType(Player.class).size == 0)
        {
            if(!uiStage.getActors().contains(loseLabel, true))
            {
                uiStage.addActor(loseLabel);
            }
        }
        background.update(deltaTime);
        world.update(deltaTime);
        
       
        
        if(engine.isKeyJustPressed(Keys.F1))
        {
            player.invulnerable = !player.invulnerable;
            
        }
        if(engine.isKeyJustPressed(Keys.NUM_1))
        {
            world.getPlayer().bulletType = Bullet.BulletType.PLAYER_00;
        }
        else if(engine.isKeyJustPressed(Keys.NUM_2))
        {
            world.getPlayer().bulletType = Bullet.BulletType.PLAYER_01;
        }
        else if(engine.isKeyJustPressed(Keys.NUM_3))
        {
            world.getPlayer().bulletType = Bullet.BulletType.PLAYER_02;
        }
        else if(engine.isKeyJustPressed(Keys.NUM_4))
        {
            world.getPlayer().bulletType = Bullet.BulletType.PLAYER_03;
        }

    }

    @Override
    protected void draw()
    {
        batch.begin();
        
        background.draw(batch);
        
        world.draw(batch);
        batch.end();
        
        uiStage.draw();
    }
}
