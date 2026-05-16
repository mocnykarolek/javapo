package game.world;

import java.util.ArrayList;
import game.organisms.Organisms;
import game.gui.Window;
import game.organisms.animals.Human;
import game.organisms.animals.Wolf;
import game.organisms.plants.Grass;
import game.utils.Vector2d;
import java.util.Random;

public class World {
    public Random rand = new Random();
    private ArrayList<Organisms> organisms = new ArrayList<>();
    private Organisms[][] grid = new Organisms[20][20];
    private ArrayList<String> logs = new ArrayList<>();
    private Window window;
    private int round_number = 0;
    private int nextHumanMove;
    private boolean specialAbility=false;
    private Organisms human_reference;

    public World(){

        window = new Window(this);
        window.init();
        generateInitialWorld();
    }
    public void setSpacialAbility(boolean special){
        this.specialAbility = special;
    }
    public boolean getSpecialAbility(){
        return this.specialAbility;
    }

    private void generateInitialWorld(){

        Human human = new Human(10,10,this);
        human_reference = human;
        addOrganism(human);


        int wolf_number = 5;

        for (int i = 0; i < wolf_number; i++)
        {
            Vector2d freeCords = this.randomUnoccupiedCords();
            addOrganism(new Wolf(freeCords.x,freeCords.y, this));
        }

        int grass_number = 3;
        for (int i = 0; i < grass_number; i++)
        {
            Vector2d freeCords = this.randomUnoccupiedCords();
            addOrganism(new Grass(freeCords.x,freeCords.y, this));
        }



    }

    private Vector2d randomUnoccupiedCords(){

        Vector2d cords = new Vector2d();
//        int random_x = (std::rand() % WORLD_WIDTH) + 1;
            int random_x = rand.nextInt(20);
//        int random_y = (std::rand() % WORLD_HEIGHT) + 1;
            int random_y = rand.nextInt(20);
        while(grid[random_y][random_x] != null){
//            random_x = (std::rand() % WORLD_WIDTH) + 1;
                random_x = rand.nextInt(20);
//            random_y = (std::rand() % WORLD_HEIGHT) + 1;
                random_y = rand.nextInt(20);

        }
        cords.x = random_x;
        cords.y = random_y;

        return cords;

    }

    public boolean isHumanAlive(){
        return human_reference.isAlive();
    }

    public Organisms getCell(Vector2d vec){
        return grid[vec.y][vec.x];

    }

    public ArrayList<Vector2d> getFreeNeighbours(Vector2d vec){
            ArrayList<Vector2d> positions_array = new ArrayList<Vector2d>();
            if(vec.y < 19 && grid[vec.y+1][vec.x] == null){

                positions_array.add(new Vector2d(vec.x, vec.y+1));
            }
            if(vec.y > 0 && grid[vec.y-1][vec.x] == null){

                positions_array.add(new Vector2d(vec.x, vec.y-1));
            }
            if(vec.x < 19 && grid[vec.y][vec.x+1] == null){

                positions_array.add(new Vector2d(vec.x+1, vec.y));

            }
            if(vec.x > 0 && grid[vec.y][vec.x-1] == null){

                positions_array.add(new Vector2d(vec.x-1, vec.y));

            }


            return positions_array;

    }

    public boolean  isOccupied(int x, int y){
        if(grid[y][x] == null){
            return false;
        }
            return true;
    }

    public int getRoundNumber(){
        return this.round_number;


    }

    public int getHumanDir(){
        return nextHumanMove;
    }
    public void setHumanDir(int dir){
        this.nextHumanMove = dir;
    }

    public void round(){
        round_number++;
        window.setRoundLabel(round_number);
//        window.addLog("tset tsetstsetsdf");


        organisms.sort((o1, o2) -> {
            if(o1.getInitiative() == o2.getInitiative()){
                return Integer.compare(o2.getAge(),o1.getAge());
            }else return Integer.compare(o2.getInitiative(),o1.getInitiative());
        });
        int start_number = organisms.size();
        for (int i =0;i < start_number;i++){
            if(organisms.get(i).isAlive()){
                organisms.get(i).action();
                organisms.get(i).incrementAge();
            }
        }

        collisionHandling();



        window.draw_round(this.grid);
        specialAbility = false;

    }



    public ArrayList<String> getLogs(){

        return logs;

    }

    public void updateGrid(Organisms o, Vector2d oldCords, Vector2d newCords){
        grid[oldCords.y][oldCords.x] = null;
        grid[newCords.y][newCords.x] = o;

    }

    private void collisionHandling(){
        for (int i = 0;i< organisms.size();){
            if (organisms.get(i).isAlive() == false){
                this.removeOrganism(organisms.get(i));
            }else {
                i++;
            }
        }
    }

    private void removeOrganism(Organisms o) {
        if(grid[o.getY()][o.getX()] == o){
            grid[o.getY()][o.getX()] = null;
        }
        organisms.remove(o);


    }


    public void addLog(String log){

        logs.add(log);
        window.addLog(log);

    }

    public void addOrganism(Organisms o){

        organisms.add(o);
        grid[o.getY()][o.getX()] = o;

    }



    public void drawOrganism(Organisms organisms) {
//        window.drawOrganism();
    }


    public int humanSpecialAbilityActivated() {
        if(specialAbility){
            return round_number;
        }
        else return -1;
    }

    public int getCurrentRound() {
        return round_number;
    }
}
