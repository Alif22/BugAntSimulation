/*The GUI that will be displayed to the user everytime main hits
the end of the requested time steps*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

//GUI class
public class GUI extends JFrame
{   
   public static Random rand = new Random();
   private static int NewStepNum = 1;
   private boolean SimEnd = false;
   private JPanel gridPanel;
   private JPanel buttonPanel;
   private JPanel textPanel;
   private JLabel displayText;
   private JLabel OrgNum;
   
   public GUI(int length, int width) //constructor for GUI to set the game up //by Alif 
   {
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      setTitle("Bugslife");

      buttonPanel = new JPanel();
      gridPanel = new JPanel();
      OrgNum = new JLabel();
      displayText = new JLabel();
      textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,7));
      this.addKeyListener(new EnterKeyListener());
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 7));
      gridPanel.setLayout(new GridLayout(width, length));
      displayText.setText("Welcome to Ant and Bug simulation. Press Enter to start the simulation");
	 
      buttonPanel.add(OrgNum);
      textPanel.add(displayText);

      add(textPanel,BorderLayout.SOUTH);
      add(gridPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.NORTH);
      setVisible(true);
   }
   
   public String getOrgs(Organism org) //by Alif
   {
      String orgLabel = "";
      if (org instanceof Ant)
         orgLabel = "a";
      else if (org instanceof Bug)
         orgLabel = "b";
      return orgLabel;
   }
   
   public int getSteps() //by Puteri
   {
      return NewStepNum;
   }

   public boolean getEnd()//by Ganesan
   {
      return SimEnd;
   }
   //update the gui every step
   public void update(int timeSteps, Organism[][] world, int ants, int Bugs) //by Puteri //by Alif //by Ganesan
   {
      SimEnd = false;
      remove(gridPanel);
      OrgNum.setText("Ants: " + ants + " Bugs: " + Bugs);
      if(timeSteps>0)  //for time step 0 welcome the user and do not change key enter prompt
         displayText.setText("Time step "+ timeSteps+ " done. Press Enter to continue");
      
      JPanel newGridPanel = new JPanel();
      newGridPanel.setLayout(new GridLayout(20, 20));
      
      String label;
      for (int i = 0; i < MainOrg.width; i++)
      {
         for (int j = 0; j < MainOrg.length; j++)
         {
         if(world[i][j] != null)
            label = String.valueOf(world[i][j].getId());
         else
            label = "";
         JLabel gridLabel = new JLabel();
         switch (label) {
            case "a": // ant
                gridLabel.setIcon(Ant.antImageIcon);
                gridLabel.setBackground( Color.white );
                break;
            case "b": // bug
                gridLabel.setIcon(Bug.bugImageIcon);
                gridLabel .setBackground( Color.white );
                break;
            default:
                gridLabel.setBackground(Color.white);
                break;
        }
         gridLabel.setHorizontalAlignment(SwingConstants.CENTER);
         gridLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
         newGridPanel.add(gridLabel);
         gridLabel.setOpaque(true);
         } 
      }
      gridPanel = newGridPanel;
      add(gridPanel, BorderLayout.CENTER);
      setVisible(true);
   }
   private class EnterKeyListener implements KeyListener{ //keylistener fo enter to fire the step // by Alif
      @Override
      public void keyTyped(KeyEvent e){
         char c = e.getKeyChar();
         if(c == '\n'){
            SimEnd = true;
         }
      }
      public void keyReleased(KeyEvent e){}
      public void keyPressed(KeyEvent e){}
   }
}
   
