/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import gold.daniel.main.Assets;
import gold.daniel.main.GameStateManager;

/**
 *
 * @author wrksttn
 */
public abstract class GameScreen implements Screen
{
    protected GameStateManager engine;
    protected SpriteBatch batch;
    
    protected Stage uiStage;
    protected Skin uiSkin;
    
    protected static final float BUTTON_WIDTH = 300f;
    protected static final float BUTTON_HEIGHT = 75f;
    
    public GameScreen(final GameStateManager engine)
    {
        this.engine = engine;
        batch = new SpriteBatch();
        batch.setProjectionMatrix(engine.getCamera().combined);
        batch.enableBlending();
        uiStage = new Stage(engine.getHUDViewport(), engine.getHUDSpriteBatch());
        uiSkin = Assets.uiSkin;
    }


    @Override
    public final void render(float delta)
    {
        Gdx.input.setInputProcessor(uiStage);
        update(delta);
        draw();
    }

    @Override
    public void show()
    {
        
    }
    
    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void dispose()
    {
    }
    
    protected abstract void update(float deltaTime);
    protected abstract void draw();
}
