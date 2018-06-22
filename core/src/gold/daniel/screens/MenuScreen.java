
package gold.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import gold.daniel.main.Assets;
import gold.daniel.main.GameStateManager;
import gold.daniel.main.Globals;

/**
 *
 * @author wrksttn
 */
public class MenuScreen extends GameScreen
{
    private Texture background;
    
    float diff;
    
    public MenuScreen(final GameStateManager engine)
    {
        super(engine);
        
        background = Assets.backgroundLoop;
        
        
        final TextButton startButton = new TextButton("START GAME", uiSkin);
        startButton.setWidth(BUTTON_WIDTH);
        startButton.setHeight(BUTTON_HEIGHT);
        startButton.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - startButton.getWidth() / 2,
                engine.getHUDViewport().getWorldHeight() / 2 + 50f);
        
        startButton.addListener(new ClickListener()
        {
            @Override 
            public void clicked(InputEvent event, float x, float y)
            {
                engine.setScreen(GameStateManager.Screens.PLAY);
            }
        });
        uiStage.addActor(startButton);

        final TextButton howToPlayButton = new TextButton("CONTROLS", uiSkin);
        howToPlayButton.setWidth(BUTTON_WIDTH);
        howToPlayButton.setHeight(BUTTON_HEIGHT);
        howToPlayButton.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - howToPlayButton.getWidth() / 2,
                engine.getHUDViewport().getWorldHeight() / 2 - 50f);
        howToPlayButton.addListener(new ClickListener() 
        {
            @Override 
            public void clicked(InputEvent event, float x, float y)
            {
                engine.setScreen(GameStateManager.Screens.HOW_TO_PLAY);
            }
        });
        uiStage.addActor(howToPlayButton);
        
        final TextButton exitButton = new TextButton("EXIT", uiSkin);
        exitButton.setWidth(BUTTON_WIDTH);
        exitButton.setHeight(BUTTON_HEIGHT);
        exitButton.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - exitButton.getWidth() / 2,
                engine.getHUDViewport().getWorldHeight() / 2 - 150f);       
        exitButton.addListener(new ClickListener() 
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });
        uiStage.addActor(exitButton);
        
        
        final Label title = new Label("SPACE SHOOT EM UP", uiSkin);
        title.setPosition(
                engine.getHUDViewport().getWorldWidth() / 2 - title.getWidth() / 2, 
                engine.getHUDViewport().getWorldHeight() -40f);
        uiStage.addActor(title);
        
    }

    @Override
    protected void update(float deltaTime)
    {
        diff -= 1f;
    }

    @Override
    protected void draw()
    {
        batch.setProjectionMatrix(engine.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0 + diff, Globals.GAME_WIDTH, Globals.GAME_HEIGHT);
        batch.draw(background, 0, Globals.GAME_HEIGHT + diff, Globals.GAME_WIDTH, Globals.GAME_HEIGHT);
        if(diff <= -Globals.GAME_HEIGHT)
        {
            diff = 0;
        }
        batch.end();
        
        uiStage.draw();
    }
}
