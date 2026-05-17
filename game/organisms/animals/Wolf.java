package game.organisms.animals;

import game.world.World;

public class Wolf extends Animal{

    public Wolf(int x, int y, World world){
        world.addLog("Wolf created");

        super(x, y,world);
        this.Color = "grey";
        this.initiative = 5;
        this.strength = 9;
        this.age = 0;
        this.alive = true;

    }
    public Wolf(int x, int y, World world, int age, int strength){
        world.addLog("Wolf created");

        super(x, y,world);
        this.Color = "grey";
        this.initiative = 5;
        this.strength = strength;
        this.age = age;
        this.alive = true;

    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Wolf(ch_x, ch_y, world));
        world.addLog("A new Wolf born");
    }

}
