package gold.daniel.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gold.daniel.main.Globals;
import gold.daniel.main.Main;

public class DesktopLauncher
{

    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        config.width = Globals.WINDOW_WIDTH;
        config.height = Globals.WINDOW_HEIGHT;
        
        config.vSyncEnabled = true;
        
        config.resizable = true;
        
        
        new LwjglApplication(new Main(), config);
    }
}
