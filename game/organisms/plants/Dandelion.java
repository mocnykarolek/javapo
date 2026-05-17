package game.organisms.plants;

import game.organisms.Organisms;

import game.world.World;

public class Dandelion extends Plant {

    public Dandelion(int x, int y, World world){
        world.addLog("Dandelion created");
        super(x, y, world);
        this.Color = "yellow";
        this.strength = 0;
        this.age = 0;
        this.alive = true;
    }

    public Dandelion(int x, int y, World world, int age, int strength){
        world.addLog("Dandelion created");
        super(x, y, world);
        this.Color = "yellow";
        this.strength = strength;
        this.age = age;
        this.alive = true;
    }

    public boolean isAttackBlocked(Organisms attacker){
        return false;
    }

    public void action(){
        int attempts = 3;

        for (int i = 0; i < attempts; i++)
        {
            double probality = (world.rand.nextInt(44)) ;

            if(probality == 3)
            {
//                reporoduction_attempt(this);
                reproduction_attempt(this);

            }


        }
    }


    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Dandelion(ch_x, ch_y, world));
        world.addLog("A Dandelion just spread");
    }

}
