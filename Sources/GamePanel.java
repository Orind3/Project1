package Sources;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.JPanel;

import Sources.GameState.GameStateManager;
import Sources.Map.MapManager;
import Sources.Tool.KeyHandler;

public class GamePanel extends JPanel implements Runnable {

    private final int originaltilesize = 64;
    private int scale;
    private int tilesize;
    private int screencol;
    private int screenrow;
    private int screenheight;
    private int screenwidth;
    private Thread gamethread;
    private KeyHandler keyhandler;
    private GameStateManager gamestatemanager;
    private MapManager MapManager;
    private GameDataStore gameDataStore;
    private SaveAndLoad saveandload;




    public GamePanel(){
        this.scale = 1;
        this.tilesize = this.originaltilesize*this.scale;
        this.screencol = 12;
        this.screenrow = 9;
        this.screenheight = this.screenrow*tilesize;
        this.screenwidth = this.screencol*tilesize;
        this.keyhandler = new KeyHandler();
        this.setPreferredSize(new Dimension(this.screenwidth,this.screenheight));
        this.setBackground(new Color(137,136,136));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this.keyhandler);
        this.gamestatemanager = new GameStateManager(this);
        this.MapManager = new MapManager();
        this.gameDataStore = new GameDataStore();
        this.saveandload = new SaveAndLoad(this);
        this.saveandload.load();
        gamethread = new Thread(this);
        this.gamethread.start();
    }

    @Override
    public void run() {
        double timeperwindow = 1e9/60;
        double delta = 0;
        long lastime = System.nanoTime();
        long currentime = 0;
        while(gamethread!=null){
            currentime = System.nanoTime();
            delta = (currentime - lastime)/timeperwindow;
            if(delta >= 1){
                input();
                update();
                repaint();
                lastime = currentime;
                delta = 0;
            }
        }
    }
    public void update(){
        this.gamestatemanager.update();
    }
    public void input(){
        this.gamestatemanager.input(this.keyhandler);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.gamestatemanager.render(g2);
    }
    public KeyHandler getKeyHandler(){
        return this.keyhandler;
    }
    public GameStateManager getGamestatemanager() {
        return gamestatemanager;
    }

    public void setGamestatemanager(GameStateManager gamestatemanager) {
        this.gamestatemanager = gamestatemanager;
    }
    public MapManager getMapManager() {
        return MapManager;
    }

    public void setMapManager(MapManager mapManager) {
        MapManager = mapManager;
    }
    public Thread getGamethread() {
        return gamethread;
    }
    public void setGamethread(Thread gamethread) {
        this.gamethread = gamethread;
    }
    
    public GameDataStore getGameDataStore() {
        return gameDataStore;
    }
    public void setGameDataStore(GameDataStore gameDataStore) {
        this.gameDataStore = gameDataStore;
    }
    public SaveAndLoad getSaveandload() {
        return saveandload;
    }

    public void setSaveandload(SaveAndLoad saveandload) {
        this.saveandload = saveandload;
    }
}
    
