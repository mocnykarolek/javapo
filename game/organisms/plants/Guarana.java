package game.organisms.plants;

import game.organisms.Organisms;

import game.world.World;

public class Guarana extends Plant {

    public Guarana(int x, int y, World world){
        world.addLog("Guarana created");
        super(x, y, world);
        this.Color = "cyan";
        this.strength = 0;
        this.age = 0;
        this.alive = true;
    }

    public Guarana(int x, int y, World world, int age, int strength){
        world.addLog("Guarana created");
        super(x, y, world);
        this.Color = "cyan";
        this.strength = strength;
        this.age = age;
        this.alive = true;
    }

    public boolean isAttackBlocked(Organisms attacker){
        return false;
    }

    protected void eatenPlant(Organisms attacker){
        attacker.increaseStrength(3);
        world.addLog("Guarana added 3 points of strength");

    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Guarana(ch_x, ch_y, world));
        world.addLog("A Guarana just spread");
    }

}
