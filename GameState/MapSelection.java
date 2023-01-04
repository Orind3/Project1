package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import Map.Map;

import java.awt.BasicStroke;
import Sources.GamePanel;
import Sources.Tool.KeyHandler;

public class MapSelection extends GameState {
    private int choice;
    private Vector<Map> vectormap;
    private int counter;
    public MapSelection(GamePanel gamepanel) {
        super(gamepanel);
        this.choice = 1;
        this.counter = 0;
        vectormap = new Vector<Map>();
        setUp();
    }

    public void setUp(){
        for(int i = 0; i <= 8; i++){
            Map inputmap = new Map("/Map/map0"+(i+1)+".txt",i%3,i/3);
            this.vectormap.add(inputmap);
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
            this.getGamepanel().getGamestatemanager().addState( new PlayState(this.getGamepanel(),this.vectormap.elementAt(choice-1)));
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
        g.setFont(GameStateManager.font_bong);
        g.setColor(new Color(102,0,204,255));
        g.setFont(g.getFont().deriveFont(20F));
        for(Map input: this.vectormap){
            g.setStroke(new BasicStroke(1));
            g.drawString("Map"+"0"+(input.getX()+input.getY()+1),input.getX()*450+200,input.getY()*250+230);
            g.drawRect(input.getX()*450+30, input.getY()*250, 400, 200);
            g.setStroke(new BasicStroke(5));
            g.drawRect(((this.choice-1)%3)*450+30, ((this.choice-1)/3)*250, 400, 200);
        }
        
    }

    @Override
    public void update() {
        
        
        
    }
    
}
