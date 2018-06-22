/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import gold.daniel.main.Assets;
import gold.daniel.main.GameStateManager;
import gold.daniel.main.Globals;

/**
 * Used as a loading screen for the game. 
 * 
 * Shows a loading bar, while loading all other assets not used on this screen
 * nice and simple. 
 * @author wrksttn
 */
public class SplashScreen extends GameScreen
{
    Skin skin;
    
    ProgressBar bar;
    
    public SplashScreen(GameStateManager engine)
    {
        super(engine);
        skin = new Skin(Gdx.files.internal("ui/neon-ui.json"));
        
        Label text = new Label("LOADING", skin);
        text.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() / 2);
        uiStage.addActor(text);
        
        bar = new ProgressBar(0f, 1f, 0.01f, false, skin);
        bar.setWidth(engine.getHUDViewport().getWorldWidth());
        bar.setHeight(20f);
        bar.setPosition(
                0, 
                engine.getHUDViewport().getWorldWidth() / 2 - 50f);
        uiStage.addActor(bar);

        Assets.load();
    }

    @Override
    protected void update(float deltaTime)
    {
        if(Assets.getManager().update())
        {
            bar.setValue(1f);
            
            Assets.setAssetReferences();
            engine.initScreens();
            
            uiStage.dispose();
            skin.dispose();
            
            engine.setScreen(GameStateManager.Screens.MAIN_MENU);
        }
        else
        {
            bar.setValue(Assets.getManager().getProgress());
        }
    }

    @Override
    protected void draw()
    {
        engine.sh.setProjectionMatrix(engine.getCamera().combined);
        batch.setProjectionMatrix(engine.getCamera().combined);
        
        uiStage.draw();
    }
}
