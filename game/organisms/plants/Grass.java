package game.organisms.plants;

import game.organisms.Organisms;

import game.world.World;

public class Grass extends Plant {

    public Grass(int x, int y, World world){
        world.addLog("Grass created");
        super(x, y, world);
        this.Color = "green";
        this.strength = 0;
        this.age = 0;
        this.alive = true;
    }

    public Grass(int x, int y, World world, int age, int strength){
        world.addLog("Grass created");
        super(x, y, world);
        this.Color = "green";
        this.strength = strength;
        this.age = age;
        this.alive = true;
    }

    public boolean isAttackBlocked(Organisms attacker){
        return false;
    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Grass(ch_x, ch_y, world));
        world.addLog("A Grass just spread");
    }

}
