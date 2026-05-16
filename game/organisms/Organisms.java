package game.organisms;

import game.world.World;
import game.utils.Vector2d;

import java.util.ArrayList;
import java.util.Random;
public abstract class Organisms {
    private Random rand = new Random();
    protected World world;
    protected int x;
    protected int y;
    protected boolean can_organism_move;
    protected int initiative;
    protected int x_prev;
    protected int y_prev;
    protected int age;
    protected boolean alive;
    protected int shift_range;
    protected String Color;
    protected int strength;



    public Organisms(int x, int y, World world){
        this.x= x;
        this.y =y;
        this.world = world;
        this.can_organism_move = true;

    }

    public void incrementAge(){
        this.age++;
    }
    public boolean isAlive(){
        return this.alive;
    }
    public void kill(){
        this.alive = false;
    }
    public boolean isPlant(){
        return false;
    }

    public Vector2d randomMove(){

        int side;
        int dir;
        Vector2d vec = new Vector2d();

        boolean condition_x;
        boolean condition_y;

        do{
            int ver=0;
            int hor=0;
            side = rand.nextInt(2);
            dir = rand.nextInt(2);
            if(dir == 0) dir = -1;

            if(side == 1) ver = dir;
            else hor = dir;


            vec.x = this.x + hor*shift_range;
            vec.y = this.y + ver*shift_range;

            condition_x = vec.x >= 0 && vec.x < 20;

            condition_y = vec.y >= 0 && vec.y < 20;

        }while(!(condition_x && condition_y));


        return vec;
    }


    protected Vector2d actionModifier(){
        return randomMove();
    }

    public abstract void action();

    public int getX(){
        return this.x;
    }

    public int getY(){
        return y;
    }

    public int getAge(){

        return this.age;
    }

    public void setInitialCords(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getInitiative(){
        return this.initiative;
    }
    public void increaseStrength(int points){
        this.strength = this.strength + points;
    }
    public int getStrength(){
        return this.strength;
    }

    public void draw(){
        this.world.drawOrganism(this);

    }
    public String getColor(){

        return this.Color;
    }
    protected abstract void newOrganism(int ch_x, int ch_y);
    protected boolean isAttackBlocked(Organisms attacker){
        return false;
    }
    protected void eatenPlant(Organisms attacker){}
    
    public void collision(Organisms other){
            world.addLog("Collision");
        if(this.getColor().equals(other.getColor())){ // reporduction

            // new_organism(this->cords);
            can_organism_move = false;


            ArrayList<Vector2d> child_cords = world.getFreeNeighbours(new Vector2d(this.x, this.y));
            if(!child_cords.isEmpty()){

                newOrganism(child_cords.get(0).x, child_cords.get(0).y);



            }else{


                world.addLog("Not enough space for reproduction");
            }

        }
        else{ // combat


            if (other.isAttackBlocked(this)) {
                can_organism_move = false;
                world.addLog("Dodged");

            } else if (this.getStrength() >= other.getStrength()){
                other.eatenPlant(this);
                other.kill();
                // return true;

            }else{
                if (other.isPlant())
                    other.eatenPlant(this);
                this.kill();
            }


        }


    }

//    private boolean isPlant() {
//        return false;
//    }


}
