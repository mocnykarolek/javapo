package game.organisms.animals;

import game.organisms.Organisms;
import game.utils.Vector2d;
import game.world.World;

import java.util.ArrayList;

public class Antelope extends Animal{

    public Antelope(int x, int y, World world){
        world.addLog("Antelope created");

        super(x, y,world);
        this.Color = "magenta";
        this.initiative = 4;
        this.strength = 4;
        this.age = 0;
        this.alive = true;
        this.shift_range = 2;

    }
    public Antelope(int x, int y, World world, int age, int strength){
        world.addLog("Antelope created");

        super(x, y,world);
        this.Color = "magenta";
        this.initiative = 4;
        this.strength = strength;
        this.age = age;
        this.alive = true;
        this.shift_range = 2;

    }

    protected boolean isAttackBlocked(Organisms attacker){

        int chance = world.rand.nextInt(2);
        if(chance == 1){
            ArrayList<Vector2d> available_cells = world.getFreeNeighbours(new Vector2d(x,y));

            if(!available_cells.isEmpty()){



                world.updateGrid(this, new Vector2d(x,y), available_cells.get(0));
                this.x = available_cells.get(0).x;
                this.y = available_cells.get(0).y;
//                this->cords = available_cells[0];
                world.addLog("Antelope escaped");
                return true;
            }





        }


        return false;

    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Antelope(ch_x, ch_y, world));
        world.addLog("A new Antelope born");
    }

}
