package Sources.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.*;
import Sources.GamePanel;
import Sources.Tool.KeyHandler;

public class GameMenu extends GameState {
    public BufferedImage background;
    public BufferedImage playerFace;
    private int choice;
    private int counter;
    public GameMenu(GamePanel gamepanel) {
        super(gamepanel);
        this.choice = 1;
        this.counter = 0;
        try {
            this.background = ImageIO.read(getClass().getResourceAsStream("/Image/background.png"));
            this.playerFace = ImageIO.read(getClass().getResourceAsStream("/Image/playerFace.png")); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void input(KeyHandler keyHandler) {
        if(keyHandler.getkeypresses()[(int) 'S']){
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
            if(choice>3){
                choice = 3;
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
            if(choice>3){
                choice = 3;
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
                    this.getGamepanel().getGameDataStore().setMapunlock(1);
                    this.getGamepanel().getSaveandload().save();
                    this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                }
                if(this.choice==2){
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                }
                if(this.choice==3){
                    this.getGamepanel().setGamethread(null);
                    System.exit(1);
                }
            }
            else{
                if(counter>30){
                    if(counter%10==0)
                    if(this.choice==1){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGameDataStore().setMapunlock(1);
                        this.getGamepanel().getSaveandload().save();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                    }
                    if(this.choice==2){
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState( new MapSelection(this.getGamepanel()));
                    }
                    if(this.choice==3){
                        this.getGamepanel().setGamethread(null);
                        System.exit(1);
                    }
                }
            }
            
        }
        else{
            counter = 0;
        }
    }

    @Override
    //ScreenWidth = 1400;
    public void render(Graphics2D g) {
        g.drawImage(this.background,0,0,null);
        g.drawImage(this.playerFace, 650, 100,null);
        g.setFont(GameStateManager.font_bong);
        g.setColor(new Color(40,177,98,255));
        g.setFont(g.getFont().deriveFont(80F));
        g.drawString("Push'n Push",700-getStringLenth(g, "Push'n Push")/2,300);
        g.setColor(new Color(105,76,57,255));
        g.setFont(g.getFont().deriveFont(40F));
        g.drawString("New Game",700-getStringLenth(g, "New Game")/2,400);
        g.drawString("Continue",700-getStringLenth(g, "Continue")/2,500);
        g.drawString("Exit",700-getStringLenth(g, "Exit")/2,600);
        g.setColor(new Color(192,160,124,255));
        g.drawString("=>",300,(choice+3)*100);
    }
    public int getStringLenth(Graphics2D g, String input){
        int txtLength = (int) g.getFontMetrics().getStringBounds(input, g).getWidth();
        return txtLength;
    }
    @Override

    public void update() {
    }
    
}
