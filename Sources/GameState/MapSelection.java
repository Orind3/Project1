package Sources.GameState;
import Sources.Tool.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import Sources.GamePanel;
import Sources.Map.Map;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class MapSelection extends GameState {
    private int choice;
    private int counter;
    private BufferedImage background;
    public MapSelection(GamePanel gamepanel) {
        super(gamepanel);
        this.choice = 1;
        this.counter = 0;
        try {
            this.background = ImageIO.read(getClass().getResourceAsStream("/Image/background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void input(KeyHandler keyHandler) {
        if(keyHandler.getkeypresses()[(int) 'S']){
            counter++;
            if(counter<2){
                choice+=3;
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    choice+=3;
                }
            }
        }
        else if(keyHandler.getkeypresses()[(int) 'W']){
            counter++;
            if(counter<2){
                choice-=3;
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    choice-=3;
                }
            }
        }
        else if(keyHandler.getkeypresses()[(int) 'D']){
            counter++;
            if(counter<2){
                choice++;
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    choice+=1;
                }
            }
        }
        else if(keyHandler.getkeypresses()[(int) 'A']){
            counter++;
            if(counter<2){
                choice--;
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    choice-=1;
                }
            }
        }
        else if(keyHandler.getkeypresses()[10]){
            this.getGamepanel().getGamestatemanager().popState();
            this.getGamepanel().getGamestatemanager().addState( new PlayState(this.getGamepanel(),this.getGamepanel().getMapManager().getVectormap().elementAt(choice-1)));
        }
        else if(keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]){
            counter++;
            if(counter<2){
                this.getGamepanel().getGamestatemanager().popState();
                this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
                }
            }
        }
        else{
            this.counter = 0;
        }
        if(this.choice>9){
            this.choice=1;
        }
        if(this.choice<1){
            this.choice=9;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.background,0,0,null);
        g.setFont(GameStateManager.font_bong);
        g.setColor(new Color(105,76,57,255));
        g.setFont(g.getFont().deriveFont(20F));
        for(Map input: this.getGamepanel().getMapManager().getVectormap()){
            g.setStroke(new BasicStroke(1));
            g.drawString("Map"+"0"+(input.getX()+input.getY()*3+1),input.getX()*450+200,input.getY()*250+250);
            g.drawRect(input.getX()*450+30, input.getY()*250+20, 400, 200);
            g.drawImage(input.getMinimap(), input.getX()*450+30, input.getY()*250+20, 400,200,null);
            g.setStroke(new BasicStroke(10));
            g.drawRect(((this.choice-1)%3)*450+30, ((this.choice-1)/3)*250+20, 400, 200);
            g.setColor(new Color(105,76,57,25));
            g.fillRect(((this.choice-1)%3)*450+30, ((this.choice-1)/3)*250+20, 400, 200);
        }
    }

    @Override
    public void update() {
        
        
        
    }
}
