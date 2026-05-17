package game.organisms.plants;

import game.organisms.Organisms;

import game.utils.Vector2d;
import game.world.World;

import java.util.ArrayList;

public class Sosnowski extends Plant {

    public Sosnowski(int x, int y, World world){
        world.addLog("Sosnowski created");
        super(x, y, world);
        this.Color = "lightGray";
        this.strength = 10;
        this.age = 0;
        this.alive = true;
    }

    public Sosnowski(int x, int y, World world, int age, int strength){
        world.addLog("lightGray created");
        super(x, y, world);
        this.Color = "lightGray";
        this.strength = strength;
        this.age = age;
        this.alive = true;
    }

    private ArrayList<Organisms> getNeighboringAnimals(){
        ArrayList<Organisms> positions_array = new ArrayList<>();
        Vector2d vec = new Vector2d(x,y);

        // std::vector<Vector2d> positions_array;
        if(vec.y < 19){
            Organisms org =  world.getCell(new Vector2d(x,y+1));
            if(org != null && !org.isPlant())
//                positions_array.push_back(org);
                positions_array.add(org);
        }
        if(vec.y > 0){
            Organisms org =  world.getCell(new Vector2d(x,y-1));

            if(org != null && !org.isPlant())
                positions_array.add(org);

        }
        if(vec.x < 19){
            Organisms org =  world.getCell(new Vector2d(x+1,y));
            if(org != null && !org.isPlant())
                positions_array.add(org);

        }
        if(vec.x > 0){
            Organisms org =  world.getCell(new Vector2d(x-1,y));
            if(org != null && !org.isPlant())
                positions_array.add(org);

        }
        return positions_array;
    }

    @Override
    public void action() {

        ArrayList<Organisms> neighboring_cells = getNeighboringAnimals();
        for (int i = 0; i < (int)neighboring_cells.size(); i++)
        {
            world.addLog("Barszcz killed animal");
            neighboring_cells.get(i).kill();
        }

        super.action();

    }

    public boolean isAttackBlocked(Organisms attacker){
        return false;
    }

    @Override
    protected void eatenPlant(Organisms attacker) {
        this.kill();
        world.addLog("Barszcz Sosnowskiego killed animal");
        attacker.kill();
    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Sosnowski(ch_x, ch_y, world));
        world.addLog("A Sosnowski just spread");
    }

}
