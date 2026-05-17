package game.world;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import game.organisms.Organisms;
import game.gui.Window;
import game.organisms.animals.*;
import game.organisms.plants.*;
import game.utils.Vector2d;

import java.util.List;
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
        window.draw_round(grid);
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

        int sheep_number = 6;
        for (int i = 0; i < sheep_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Sheep(freeCords.x,freeCords.y, this));
        }

        int fox_number = 4;
        for (int i = 0; i < fox_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Fox(freeCords.x,freeCords.y, this));
        }
        int turtle_number = 3;
        for (int i = 0; i < turtle_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Turtle(freeCords.x,freeCords.y, this));
        }

        int antelope_number = 4;
        for (int i = 0; i < antelope_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Antelope(freeCords.x,freeCords.y, this));
        }

        int grass_number = 3;
        for (int i = 0; i < grass_number; i++)
        {
            Vector2d freeCords = this.randomUnoccupiedCords();
            addOrganism(new Grass(freeCords.x,freeCords.y, this));
        }

        int dandelion_number = 3;
        for (int i = 0; i < dandelion_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Dandelion(freeCords.x,freeCords.y, this));
        }

        int guarana_number = 5;
        for (int i = 0; i < guarana_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Guarana(freeCords.x,freeCords.y, this));
        }

        int belladonna_number = 3;
        for (int i = 0; i < belladonna_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Belladonna(freeCords.x,freeCords.y, this));
        }

        int barszcz_number = 3;
        for (int i = 0; i < barszcz_number; i++)
        {
            Vector2d freeCords = randomUnoccupiedCords();
            addOrganism(new Sosnowski(freeCords.x,freeCords.y, this));
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
    public void SaveToFile(){
        Path p = Paths.get("gamestate.txt");

        List<String> toSave = new ArrayList<>();
        toSave.add("ROUND " + this.round_number);

//        toSave.add(name + " " + x + " " + y + " " + grid[y][x].getAge() + " " + grid[y][x].getStrength());

        for (int i = 0;i<organisms.size();i++){
            if( !(organisms.get(i) instanceof Human)) {

                String name = switch (organisms.get(i).getColor()) {
                    case "pink" -> "HUMAN";
                    case "grey" -> "WOLF";
                    case "sheepy" -> "SHEEP";
                    case "orange" -> "FOX";
                    case "blue" -> "TURTLE";
                    case "magenta" -> "ANTELOPE";
                    case "" -> "";
                    case "green" -> "GRASS";
                    case "yellow" -> "DANDELION";
                    case "cyan" -> "GUARANA";
                    case "darkRed" -> "BELLADONNA";
                    case "lightGray" -> "SOSNOWSKI";
                    default -> "NULL";
                };
                toSave.add(name + " " + organisms.get(i).getX() + " " + organisms.get(i).getY() + " " + organisms.get(i).getAge() + " " + organisms.get(i).getStrength());
            }else{

                toSave.add("HUMAN" + " " + organisms.get(i).getX() + " " + organisms.get(i).getY() + " " + organisms.get(i).getAge() + " " + organisms.get(i).getStrength() + " " + ((Human) organisms.get(i)).saveHuman());
            }
        }


        try{
            Files.write(p, toSave);
        }catch (IOException e){
            System.out.println("Error");

        }


    }
    public void LoadFromFile(){
        Path p = Paths.get("gamestate.txt");
        this.organisms = new ArrayList<>();
        this.grid = new Organisms[20][20];

        try{
            List<String> loadedLines = Files.readAllLines(p);
            int size = loadedLines.size();
            for (String loadedLine : loadedLines) {
                String[] line = loadedLine.split(" ");
                int x=0;
                int y=0;
                int age=0;
                int strength=0;
                if(!line[0].equals("ROUND")){
                x = Integer.parseInt(line[1]);
                y = Integer.parseInt(line[2]);
                age = Integer.parseInt(line[3]);
                strength = Integer.parseInt(line[4]);
                }

                switch (line[0]){
                    case "ROUND" -> {
                        round_number = Integer.parseInt(line[1]);
                    }
                    case "HUMAN" -> {
                        boolean elixirActive = Boolean.parseBoolean(line[5]);
                        int elixirBonus = Integer.parseInt(line[6]);
                        int roundOfActivation = Integer.parseInt(line[7]);
                        int cooldownLeft = Integer.parseInt(line[8]);
                        Human human = new Human(x,y,this, age,strength, elixirActive, elixirBonus, roundOfActivation, cooldownLeft);
                        human_reference = human;
                        addOrganism(human);

                    }
                    case "WOLF" -> {
                        addOrganism(new Wolf(x,y,this, age,strength));
                    }
                    case "SHEEP" -> {
                        addOrganism(new Sheep(x,y,this, age,strength));
                    }

                    case "FOX" -> {
                        addOrganism(new Fox(x,y,this, age,strength));
                    }
                    case "TURTLE" -> {
                        addOrganism(new Turtle(x,y,this, age,strength));
                    }
                    case "ANTELOPE" -> {
                        addOrganism(new Antelope(x,y,this, age,strength));
                    }
                    case "GRASS" -> {
                        addOrganism(new Grass(x,y,this, age,strength));
                    }
                    case "DANDELION" -> {
                        addOrganism(new Dandelion(x,y,this, age,strength));
                    }
                    case "GUARANA" -> {
                        addOrganism(new Guarana(x,y,this, age,strength));
                    }
                    case "BELLADONNA" -> {
                        addOrganism(new Belladonna(x,y,this, age,strength));
                    }

                    case "SOSNOWSKI" -> {
                        addOrganism(new Sosnowski(x,y,this, age,strength));
                    }


                }


            }



        } catch (IOException e){
            System.out.println("Error");
        }
        window.setRoundLabel(round_number);
        window.draw_round(grid);
    }
}
