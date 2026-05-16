package game.gui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.util.ArrayList;
import game.organisms.Organisms;
import game.world.World;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.desktop.SystemEventListener;

public class Window {
    private final int ROWS = 20;
    private final int COLUMNS = 20;
    private JPanel board;
    private JTextArea logs_list;
//    private int round_number = 0;
    private JLabel round_label;
    private JLabel nextMoveLabel;
    private World world;
    private JPanel[][] gridGUI = new JPanel[COLUMNS][ROWS];
    private String humanMove = "null";
    public Window(World world){
        this.world = world;



    }

    private void renderLogs(ArrayList<String> logs){

        for (int i = 0; i< logs.size(); i++){
            continue;

        }

    }
    public void addLog(String log){

        logs_list.append(log + "\n");

    }


    public void draw_round(Organisms[][] gridOrganisms){



        board.removeAll();

//        for (int i = 0; i < COLUMNS*ROWS; i++)
//        {
//            JPanel cell = new JPanel();
//            cell.setBorder(BorderFactory.createLineBorder(Color.black));
//            cell.setBackground(Color.white);
//            board.add(cell);
//            board.revalidate();
//            board.repaint();
//        }

        for (int y = 0;y<ROWS;y++){
            for (int x = 0; x<COLUMNS;x++){
                gridGUI[y][x] = new JPanel();
                gridGUI[y][x].setBorder(BorderFactory.createLineBorder(Color.black));
                if(gridOrganisms[y][x] != null){
                    Color colorToDraw = switch (gridOrganisms[y][x].getColor()){
                        case "pink" -> Color.pink;
                        case "grey" -> Color.darkGray;
                        case "orange" -> Color.orange;
                        case "blue" -> Color.BLUE;
                        case "magenta" -> Color.magenta;
                        case "green" -> Color.GREEN;
                        case "yellow" -> Color.yellow;
                        case "cyan" -> Color.CYAN;
                        case "darkRed" -> Color.red;
                        case "lightGray" -> Color.lightGray;
                        default -> Color.black;
                    };

                    gridGUI[y][x].setBackground(colorToDraw);

                }else{
                    gridGUI[y][x].setBackground(Color.white);
                }

                board.add(gridGUI[y][x]);
//                board.revalidate();
//                board.repaint();
            }
        }
//        gridGUI[1][1].setBackground(Color.gray);
        setHumanNextMove("null");
    }

    public void init(){



        JFrame frame = new JFrame("Zabista kurwa gra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton button = new JButton("Nowa tura");

        board = new JPanel();
        board.setLayout(new GridLayout(ROWS, COLUMNS));
        board.setPreferredSize(new Dimension(600,600));
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP -> setHumanNextMove("Up");
                    case KeyEvent.VK_DOWN -> setHumanNextMove("Down");
                    case KeyEvent.VK_RIGHT -> setHumanNextMove("Right");
                    case KeyEvent.VK_LEFT -> setHumanNextMove("Left");
                    case KeyEvent.VK_F -> setHumanNextMove("special");
                }
            }
        });

        JPanel log_panel = new JPanel();

        log_panel.setLayout(new GridLayout(1, 4));

        log_panel.setPreferredSize(new Dimension(400, 600));



        logs_list = new JTextArea(20,30);
        DefaultCaret caret = (DefaultCaret) logs_list.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        logs_list.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logs_list);
        scrollPane.setPreferredSize(new Dimension(250,600));
        logs_list.append("test\n");


        log_panel.add(scrollPane);

        frame.add(log_panel, BorderLayout.EAST);


        // x axis, y axis, width, height
        button.setBounds(150, 200, 220, 50);

        // adding button in JFrame
        JPanel southPanel = new JPanel();
        southPanel.add(button);
        JLabel txt = new JLabel();
        txt.setText("Karol Oledzki 208226");
        southPanel.add(txt);

        round_label = new JLabel();
        round_label.setText("Round: " + world.getRoundNumber());
        southPanel.add(round_label);

        nextMoveLabel = new JLabel();
        nextMoveLabel.setText("Human next: ");
        southPanel.add(nextMoveLabel);

        frame.add(southPanel, BorderLayout.SOUTH);

        // 400 width and 500 height
        frame.setSize(800, 600);


        button.addActionListener(e -> {
            if((humanMove != "null" && humanMove != "special" ) || !world.isHumanAlive()){
                world.round();
                logs_list.append("New Round\n");

            }
            frame.setFocusable(true);
            frame.requestFocusInWindow();


        });

        board.setSize(400,400);



//        this.draw_round(new Organisms[20][20]);


        frame.add(board, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    private void setHumanNextMove(String dir){
        this.humanMove = dir;
        if(this.humanMove == "null"){
            nextMoveLabel.setText("Human next: ");
            world.setHumanDir(-1);
        }
        else if (this.humanMove == "special"){
            world.setSpacialAbility(true);
        }
        else{
            nextMoveLabel.setText("Human next: " + dir);
            int to_set = 0;
            switch (dir) {
                case "Up" -> to_set=1;
                case "Down" -> to_set=3;
                case "Left" -> to_set=4;
                case "Right" -> to_set=2;
            }
            world.setHumanDir(to_set);
        }



    }
    public void setRoundLabel(int number){

        round_label.setText("Round: " + String.valueOf(number));





    }
    public void drawOrganism(Organisms o){



    }

}
