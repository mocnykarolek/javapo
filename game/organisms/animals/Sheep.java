package game.organisms.animals;

import game.world.World;

public class Sheep extends Animal{

    public Sheep(int x, int y, World world){
        world.addLog("Sheep created");

        super(x, y,world);
        this.Color = "sheepy";
        this.initiative = 4;
        this.strength = 4;
        this.age = 0;
        this.alive = true;

    }
    public Sheep(int x, int y, World world, int age, int strength){
        world.addLog("Sheep loaded");

        super(x, y,world);
        this.Color = "sheepy";
        this.initiative = 4;
        this.strength = strength;
        this.age = age;
        this.alive = true;

    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Sheep(ch_x, ch_y, world));
        world.addLog("A new Sheep born");
    }

}

