import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(120,100,90));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(50,50,50));
        textField.setForeground(new Color(25,140,0));
        textField.setFont(new Font("Ink Free", Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(80,80,170));

        for (int i=0;i<9;i++){
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField);
        frame.add(buttonPanel);
        frame.add(titlePanel, BorderLayout.NORTH);

        firstTurnDecider();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1Turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(180, 20, 20));
                        buttons[i].setText("X");
                        textField.setText("O turn");
                        checkWinConditions();
                        player1Turn = false;
                    }
                }else {
                    if (buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(20, 20, 180));
                        buttons[i].setText("O");
                        textField.setText("X turn");
                        checkWinConditions();
                        player1Turn = true;
                    }
                }
            }
        }

    }

    public void firstTurnDecider(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (random.nextInt(2)==0){
            player1Turn = true;
            textField.setText("X turn");
        }
        else{
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    public void checkSectionForX(int a, int b, int c){
        if(
                (buttons[a].getText()=="X") &&
                (buttons[b].getText()=="X") &&
                (buttons[c].getText()=="X")
        )
            xWins(a,b,c);
    }
    public void checkSectionForO(int a, int b, int c){
        if(
                (buttons[a].getText()=="O") &&
                (buttons[b].getText()=="O") &&
                (buttons[c].getText()=="O")
        )
            oWins(a,b,c);
    }
    public void checkWinConditions(){

        if(player1Turn){
            checkSectionForX(0,1,2);
            checkSectionForX(3,4,5);
            checkSectionForX(6,7,8);

            checkSectionForX(0,3,6);
            checkSectionForX(1,4,7);
            checkSectionForX(2,5,8);

            checkSectionForX(0,4,8);
            checkSectionForX(2,4,6);
        }
        else{
            checkSectionForO(0,1,2);
            checkSectionForO(3,4,5);
            checkSectionForO(6,7,8);

            checkSectionForO(0,3,6);
            checkSectionForO(1,4,7);
            checkSectionForO(2,5,8);

            checkSectionForO(0,4,8);
            checkSectionForO(2,4,6);
        }


    }

    public void xWins(int a, int b, int c){
        textField.setForeground(new Color(200,80,200));
        textField.setText("X Wins");
        buttons[a].setBackground(new Color(200,80,200));
        buttons[b].setBackground(new Color(200,80,200));
        buttons[c].setBackground(new Color(200,80,200));

        for(int i = 0; i<9 ; i++){
            buttons[i].setEnabled(false);
        }
    }

    public void oWins(int a, int b, int c){
        textField.setForeground(new Color(200,80,200));
        textField.setText("O Wins");
        buttons[a].setBackground(new Color(200,80,200));
        buttons[b].setBackground(new Color(200,80,200));
        buttons[c].setBackground(new Color(200,80,200));


        for(int i = 0; i<9 ; i++){
            buttons[i].setEnabled(false);
        }
    }

}
