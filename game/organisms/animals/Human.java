package game.organisms.animals;

import game.utils.Vector2d;
import game.world.World;

public class Human extends Animal{
    private final int EAST = 2;
    private final int NORTH = 1;
    private final int SOUTH = 3;
    private final int WEST = 4;
//    boolean specialAbilityActivated;
    int roundOfActivation;
    int currentRound;
    int elixirBonus;
    int cooldownLeft;
    public Human(int x, int y, World world){
        world.addLog("Human created");

        super(x, y,world);
        this.Color = "pink";
        this.initiative = 4;
        this.age = 0;
        this.alive = true;
        world.setSpacialAbility(false);
        this.elixirBonus = 0;
        this.cooldownLeft = 0;
        this.strength = 5+ elixirBonus;

    }

    public void newOrganism(int ch_x, int ch_y){}

    public void handleSpecialAbility(){
        if(world.getSpecialAbility() && this.elixirBonus > 0){
            elixirBonus--;
        }

        if(world.getSpecialAbility() && currentRound - roundOfActivation >= 5){
            world.setSpacialAbility(false);

            cooldownLeft = 5;
            world.addLog("No elixir left");
        }
    }

    public int getSila(){
        return this.strength + elixirBonus;
    }

    public void action(){


        if(world.humanSpecialAbilityActivated() != -1 && world.getSpecialAbility() != true && cooldownLeft == 0){
            world.setSpacialAbility(true);
            elixirBonus = 5;
            world.addLog("Czlowiek wypil magiczny eliksir");
            roundOfActivation = world.humanSpecialAbilityActivated();
        }
        if(cooldownLeft >0) cooldownLeft--;
        currentRound = world.getCurrentRound();

        System.out.println("" + cooldownLeft + "\n");
        System.out.println("" + roundOfActivation + "\n");
        System.out.println("" + elixirBonus + "\n");
        handleSpecialAbility();
        // world->add_log(std::to_string(getSila()));


//        prevCords = this->cords;
        this.x_prev = this.x;
        this.y_prev = this.y;


//        Vector2d new_cords = this->cords;
        Vector2d new_cords = new Vector2d(this.x, this.y);
        int nextMove = world.getHumanDir();
        if(nextMove == NORTH && this.y > 1){
            new_cords.y--;
            world.addLog("Czlowiek idzie do gory");
        }else if(nextMove == EAST && this.x < 20){
            new_cords.x++;
            world.addLog("Czlowiek idzie w prawo");
        }else if(nextMove == SOUTH && this.y < 20){
            world.addLog("Czlowiek idzie w dol");
            new_cords.y++;
        } else if(nextMove == WEST && this.x > 1){
            world.addLog("Czlowiek idzie w lewo");
            new_cords.x--;
        }



        can_organism_move =true;
        if(world.isOccupied(new_cords.x,new_cords.y)){

            if(world.getCell(new_cords) != this){
                collision(world.getCell(new_cords));

                if(this.isAlive()){
                    if(can_organism_move){
//                        this.cords = new_cords;
                        this.x = new_cords.x;
                        this.y = new_cords.y;
                        world.updateGrid(this, new Vector2d(x_prev, y_prev), new_cords);
                    }
                }

            }

        }else{

//            this.cords = new_cords;
            this.x = new_cords.x;
            this.y = new_cords.y;
            world.updateGrid(this, new Vector2d(x_prev, y_prev), new_cords);
        }



    }


}
