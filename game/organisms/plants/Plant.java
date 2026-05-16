package game.organisms.plants;

import game.organisms.Organisms;
import game.utils.Vector2d;
import game.world.World;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Plant extends Organisms {


    public Plant(int x, int y, World world){
        super(x, y,world);
        initiative = 0;
        shift_range = 0;
    }



    @Override
    protected void newOrganism(int ch_x, int ch_y) {

    }
    public void action(){

        double probaility = world.rand.nextInt(50);

        if(probaility == 3){
            reproduction_attempt(this);
        }


    }

    public boolean isPlant(){
        return true;
    }

    private void reproduction_attempt(Organisms o) {

        ArrayList<Vector2d> freeCells = world.getFreeNeighbours(new Vector2d(o.getX(), o.getY()));

        if(!freeCells.isEmpty()){
            newOrganism(freeCells.getFirst().x, freeCells.getFirst().y);

        }else{
            world.addLog("Not enough space for reproduction");
        }


    }


}
