package Sources.GameState;

import Sources.GamePanel;
import Sources.Tool.KeyHandler;
import java.awt.*;
public abstract class GameState {
    private GamePanel gamepanel;
    protected int counter;
    public GameState(GamePanel gamepanel){
        this.gamepanel = gamepanel;
    }
    public abstract void input(KeyHandler keyHandler);
    public abstract void render(Graphics2D g);
    public abstract void update();
    public GamePanel getGamepanel() {
        return gamepanel;
    }
    public void setGamepanel(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }
}
