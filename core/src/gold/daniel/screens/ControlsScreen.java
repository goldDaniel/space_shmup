/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import gold.daniel.main.GameStateManager;
import gold.daniel.main.Particle;

/**
 *
 * @author wrksttn
 */
public class ControlsScreen extends GameScreen
{
    
    Array<Particle.ParticleType> pt;
    Array<Particle> particles;
    int index = 0;
    
    public ControlsScreen(final GameStateManager engine)
    {
        super(engine);
        
        
        
        
        final Label MOVE_CONTROLS = new Label("MOVE: W A S D", uiSkin);
        final Label SLOW_CONTROLS = new Label("SLOW MOTION: SHIFT", uiSkin);
        final Label SHOO_CONTROLS = new Label("SHOOT: SPACE", uiSkin);
        
        
        MOVE_CONTROLS.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - MOVE_CONTROLS.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() * 10f / 12f);
        uiStage.addActor(MOVE_CONTROLS);
        SLOW_CONTROLS.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - SLOW_CONTROLS.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() * 8f / 12f);
        uiStage.addActor(SLOW_CONTROLS);
        SHOO_CONTROLS.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - SHOO_CONTROLS.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() * 6f / 12f);
        uiStage.addActor(SHOO_CONTROLS);
        
        final TextButton BACK_BUTTON = new TextButton("BACK", uiSkin);
        BACK_BUTTON.setWidth(BUTTON_WIDTH);
        BACK_BUTTON.setHeight(BUTTON_HEIGHT);
        BACK_BUTTON.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - BACK_BUTTON.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() / 8f);
        
        BACK_BUTTON.addListener(new ClickListener() 
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                engine.setScreen(GameStateManager.Screens.MAIN_MENU);
            }
        });
        uiStage.addActor(BACK_BUTTON);
        
        particles = new Array<Particle>();
        
        pt = new Array<Particle.ParticleType>();
        pt.add(Particle.ParticleType.EXPLOSION_LARGE);
        pt.add(Particle.ParticleType.EXPLOSION_SMALL);
        pt.add(Particle.ParticleType.ADDITIONAL_BLUE_01);
        pt.add(Particle.ParticleType.ADDITIONAL_BLUE_02);
        pt.add(Particle.ParticleType.ADDITIONAL_BLUE_03);
        pt.add(Particle.ParticleType.ADDITIONAL_ORANGE_01);
        pt.add(Particle.ParticleType.ADDITIONAL_ORANGE_02);
        pt.add(Particle.ParticleType.ADDITIONAL_ORANGE_03);
    }

    @Override
    protected void update(float deltaTime)
    {
        Gdx.input.setInputProcessor(uiStage);
        if(engine.isKeyJustPressed(Keys.P))
        {
            particles.add(new Particle(engine.getMouse().x, engine.getMouse().y, 
                    pt.get(index)));
            index = (index + 1) % pt.size;
        }
        for(Particle particle : particles)
        {
            particle.update(deltaTime);
        }
    }

    @Override
    protected void draw()
    {
        uiStage.draw();
        
        batch.begin();
        for(Particle particle : particles)
        {
            particle.draw(batch);
        }
        batch.end();
    }
    
}
