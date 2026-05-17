package game.organisms.plants;

import game.organisms.Organisms;

import game.world.World;

public class Belladonna extends Plant {

    public Belladonna(int x, int y, World world){
        world.addLog("Belladonna created");
        super(x, y, world);
        this.Color = "darkRed";
        this.strength = 99;
        this.age = 0;
        this.alive = true;
    }

    public Belladonna(int x, int y, World world, int age, int strength){
        world.addLog("Belladonna created");
        super(x, y, world);
        this.Color = "darkRed";
        this.strength = strength;
        this.age = age;
        this.alive = true;
    }

    public boolean isAttackBlocked(Organisms attacker){
        return false;
    }

    @Override
    protected void eatenPlant(Organisms attacker) {
        this.kill();
        attacker.kill();
        world.addLog("Belladonna killed a animal");

    }

    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Belladonna(ch_x, ch_y, world));
        world.addLog("A Belladonna just spread");
    }

}
