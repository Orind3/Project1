package GameState;
import java.awt.Color;
import java.awt.Graphics2D;

import Sources.GamePanel;
import Sources.Tool.KeyHandler;
import java.awt.event.*;

import Map.Map;
public class GamePause extends GameState {
    private int counter;
    private int choice;
    public GamePause(GamePanel gamepanel) {
        super(gamepanel);
        counter = 0;
        choice = 1;
    }

    @Override
    public void input(KeyHandler keyHandler) {
        if(keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]){
            keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE] = false;
            counter++;
            if(counter<2){
                this.getGamepanel().getGamestatemanager().popState();
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    this.getGamepanel().getGamestatemanager().popState();
                }
            }
        }
        else if(keyHandler.getkeypresses()[(int) 'S']){
            counter++;
            if(counter<2){
                choice++;
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    choice++;
                }
            }
            if(choice>4){
                choice = 4;
            }
        }
        else if(keyHandler.getkeypresses()[(int) 'W']){
            counter++;
            if(counter<2){
                choice--;
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    choice--;
                }
            }
            if(choice>4){
                choice = 4;
            }
            if(choice<1){
                choice = 1;
            }
        }
        else if(keyHandler.getkeypresses()[10]){
            keyHandler.getkeypresses()[10] = false;
            if(counter<2){
                if(this.choice==1){
                    this.getGamepanel().getGamestatemanager().popState();
                }
                else if(this.choice==2){
                    this.getGamepanel().getGamestatemanager().popState();
                    Map inputpath = ((PlayState) this.getGamepanel().getGamestatemanager().states.lastElement()).getMap();
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState( new PlayState(this.getGamepanel(),inputpath));
                }
                else if(this.choice==3){
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState(new MapSelection(this.getGamepanel()));
                }
                if(this.choice==4){
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
                }
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    if(this.choice==1){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                    }
                    if(this.choice==2){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                    }
                    if(this.choice==3){
        
                    }
                }
            }
            
        }   
        else{
            counter = 0;
        }
    }
    public int getStringLenth(Graphics2D g, String input){
        int txtLength = (int) g.getFontMetrics().getStringBounds(input, g).getWidth();
        return txtLength;
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(255,255,255,100));
        g.fillRect(0, 0, 2000, 2000);
        g.setFont(GameStateManager.font_bong);
        g.setColor(Color.red);
        g.setFont(g.getFont().deriveFont(30F));
        g.drawString("GamePause",700-getStringLenth(g, "GamePause")/2,200);
        g.setColor(Color.black);
        g.drawString("Continue",700-getStringLenth(g, "Continue")/2,300);
        g.drawString("Restart",700-getStringLenth(g, "Restart")/2,400);
        g.drawString("Map Seclection",700-getStringLenth(g, "Map Selection")/2,500);
        g.drawString("Quit", 700-getStringLenth(g, "Quit")/2, 600);
        g.drawString("=>",400,(choice+2)*100);
        
    }

    @Override
    public void update() {
        
    }
    
}