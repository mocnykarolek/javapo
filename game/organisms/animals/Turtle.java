package game.organisms.animals;

import game.organisms.Organisms;
import game.utils.Vector2d;
import game.world.World;

public class Turtle extends Animal{

    public Turtle(int x, int y, World world){
        world.addLog("Turtle created");

        super(x, y,world);
        this.Color = "blue";
        this.initiative = 1;
        this.strength = 2;
        this.age = 0;
        this.alive = true;

    }
    public Turtle(int x, int y, World world, int age, int strength){
        world.addLog("Wolf created");

        super(x, y,world);
        this.Color = "blue";
        this.initiative = 1;
        this.strength = strength;
        this.age = age;
        this.alive = true;

    }

    protected Vector2d actionModifier(){

        int random = world.rand.nextInt(4);
        if(random == 3){
            return randomMove();
        }
        else{
            return new Vector2d(x,y);
        }
    }

    protected boolean isAttackBlocked(Organisms attacker){
        if(attacker.getStrength() < 5){
            // attacker->can_organism_move =false;
            return true;
        }
        else{

            return super.isAttackBlocked(attacker);
        }
    }


    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Turtle(ch_x, ch_y, world));
        world.addLog("A new Turtle born");
    }

}
