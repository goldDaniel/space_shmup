/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.entities.boss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gold.daniel.entities.Boss;
import gold.daniel.main.Globals;
import gold.daniel.main.World;

/**
 *
 * @author wrksttn
 */
public class PhaseEntrance
{
    public static void update(Boss boss, World world, float delta)
    {
        //this number is to time next phase with music, requires manual tuning
        boss.y -= 32.5f * delta;
        if(boss.y <= Globals.GAME_HEIGHT * 3f / 4f - 32f)
        {
            boss.y = Globals.GAME_HEIGHT * 3f / 4f - 32f;
            boss.setPhase(Boss.Phase.PHASE_1);
        }
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
