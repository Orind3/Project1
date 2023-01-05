package Sources.Entity;

import java.awt.Rectangle;
import java.util.Vector;

public class Crystal extends Entity {
    public Crystal(Vector<Integer> position) {
        super(position);
        this.setGothrough(true);
        this.setshape(new Rectangle(position.elementAt(0),position.elementAt(1),10,10));
    }
    
    public boolean hit(Vector<Entity> entity){
        for(Entity check: entity){
            if(check instanceof Box){
                if(check.getshape().intersects(this.getshape())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void pullTheBox(Vector<Entity> entity){
        for(int i = 0; i < entity.size(); i++){
            Entity check = entity.get(i);
            if(check instanceof Box){
                int pullx = this.getPosition().elementAt(0) - check.getPosition().elementAt(0);
                int pully = this.getPosition().elementAt(1) - check.getPosition().elementAt(1);
                if(Math.abs(pullx)<=8&&Math.abs(pully)<=8){
                    if(!((Box) check).getIsPulled()){
                        check.setPosition(check.getPosition().elementAt(0)+pullx,check.getPosition().elementAt(1)+pully);
                        check.updatePosition();
                        ((Box) check).setIsPulled(true);
                    }
                }
                else if(Math.abs(pullx)<=16&Math.abs(pully)<=16){
                    ((Box) check).setIsPulled(false);
                }
            }
        }
    }
}
