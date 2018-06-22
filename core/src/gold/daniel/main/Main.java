package gold.daniel.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Main extends ApplicationAdapter
{

    GameStateManager engine;
    
    
    @Override
    public void create()
    {
        engine = new GameStateManager();
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0.1f, 0.05f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        engine.updateEngine();
        engine.updateGame(Gdx.graphics.getDeltaTime() * Globals.TIMESCALE);
    }
    
    @Override
    public void resize(int width, int height)
    {
        engine.resize(width, height);
    }

    @Override
    public void dispose()
    {
    }
}
