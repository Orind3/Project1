package Map;

import Sources.Tool.LoadMap;

public class Map {
    private LoadMap loadMap;
    private String source;
    private int x;
    private int y;
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Map(String name,int x,int y){
        this.source = name;
        this.x = x;
        this.y = y;
    }
    public LoadMap getLoadAndLoadMap() {
        this.loadMap = new LoadMap(this.source);
        return loadMap;
    }
    public LoadMap getLoadMap(){
        return this.loadMap;
    }
    public void setLoadMap(LoadMap loadMap) {
        this.loadMap = loadMap;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
