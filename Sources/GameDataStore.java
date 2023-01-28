package Sources;
import java.io.Serializable;


public class GameDataStore implements Serializable {
    public int mapunlock = 1;
    public int getMapunlock() {
        return mapunlock;
    }
public void setMapunlock(int mapunlock) {
    this.mapunlock = mapunlock;
}
}
