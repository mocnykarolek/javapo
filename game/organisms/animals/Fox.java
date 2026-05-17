package game.organisms.animals;

import game.organisms.Organisms;
import game.utils.Vector2d;
import game.world.World;

import java.util.ArrayList;

public class Fox extends Animal{

    public Fox(int x, int y, World world){
        world.addLog("Fox created");

        super(x, y,world);
        this.Color = "orange";
        this.initiative = 7;
        this.strength = 3;
        this.age = 0;
        this.alive = true;

    }
    public Fox(int x, int y, World world, int age, int strength){
        world.addLog("Fox loaded");

        super(x, y,world);
        this.Color = "orange";
        this.initiative = 7;
        this.strength = strength;
        this.age = age;
        this.alive = true;

    }

    protected Vector2d randomMove(){
//        std::vector<Vector2d> available_moves;
        ArrayList<Vector2d> available_moves = new ArrayList<>();

//        Vector2d c = this->cords;
        Vector2d c = new Vector2d(x,y);
        if(c.x + 1 < 20){
            Organisms cell = world.getCell(new Vector2d(c.x+1, c.y));
            if(cell == null || cell.getStrength() <= this.getStrength()){
//                available_moves.push_back({c.x+1, c.y});
                available_moves.add(new Vector2d(c.x+1, c.y));
            }

        }
        if(c.x - 1 >= 0){
            Organisms cell = world.getCell(new Vector2d(c.x-1, c.y));
            if(cell == null || cell.getStrength() <= this.getStrength()){
//                available_moves.push_back({c.x-1, c.y});
                available_moves.add(new Vector2d(c.x-1, c.y));
            }

        }
        if(c.y + 1 < 20){
            Organisms cell = world.getCell(new Vector2d(c.x, c.y+1));
            if(cell == null || cell.getStrength() <= this.getStrength()){
//                available_moves.push_back({c.x, c.y+1});
                available_moves.add(new Vector2d(c.x, c.y+1));
            }

        }
        if(c.y - 1 >= 0){
            Organisms cell = world.getCell(new Vector2d(c.x, c.y-1));
            if(cell == null || cell.getStrength() <= this.getStrength()){
//                available_moves.push_back({c.x, c.y-1});
                available_moves.add(new Vector2d(c.x, c.y-1));
            }

        }

        if(available_moves.isEmpty()){
            return new Vector2d(this.x, this.y);

        }
//        world.rand.nextInt(available_moves.size());
        return available_moves.get(world.rand.nextInt(available_moves.size()));
    }


    public void newOrganism(int ch_x, int ch_y){
        world.addOrganism(new Fox(ch_x, ch_y, world));
        world.addLog("A new Fox born");
    }

}

