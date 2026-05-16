package game.organisms.animals;

import game.organisms.Organisms;
import game.utils.Vector2d;
import game.world.World;

public abstract class Animal extends Organisms {

    public Animal(int x, int y, World world){
        super(x, y,world);
        shift_range = 1;


    }
    protected abstract void newOrganism(int ch_x, int ch_y);

    public void action(){

//        prev_cords = this.cords;
//
        x_prev = this.x;
        y_prev = this.y;
        Vector2d prev_cords = new Vector2d(this.x, this.y);
        Vector2d new_cords = actionModifier();


        can_organism_move =true;
        if(world.isOccupied(new_cords.x,new_cords.y)){

            if(world.getCell(new_cords) != this){
                collision(world.getCell(new_cords));

                if(this.isAlive()){
                    if(can_organism_move){

                        this.x = new_cords.x;
                        this.y = new_cords.y;
                        world.updateGrid(this, prev_cords, new_cords);
                    }
                }

            }

        }else{
            this.x = new_cords.x;
            this.y = new_cords.y;
            world.updateGrid(this, prev_cords, new_cords);
        }
    }



}
