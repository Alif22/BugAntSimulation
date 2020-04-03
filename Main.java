
import java.util.*;
import javax.swing.*;

//the class that have the number of the organism
class MainOrg ////by Puteri //by Alif //by Ganesan
{
   public static int length = 20;
   public static int width = 20;
   
   public MainOrg(int wid, int len)
   {
      length = len;
      width = wid;
   }
}

// Main class
public class Main
{
   public static final int AntIn = 100; // no of initial Ant in the game
   public static final int BugIn = 5; // no of initial Bug in the gane
   public static final int length = 20;
   public static final int width = 20; 
   public static int AntNum = 0; //counter for num of Ant
   public static int BugNum = 0; // counter for num of Bug
   public static int StepNum = 0; // counter for num of step
   public static MainOrg master = new MainOrg(length, width);
   public static Organism[][] world = new Organism[MainOrg.width][MainOrg.length];
   
   public static void main(String[] args) throws ExtinctExcept,InterruptedException //by Puteri //by Alif //by Ganesan
   {
      CountBug BugCounter = new CountBug(); 
      CountAnt antCounter = new CountAnt();
      GUI gui = new GUI(MainOrg.length, MainOrg.width);
      boolean Extinct = false;
      int timeSteps = 0;
      
      createAnt();
      createBug();
      
      //moving
      int b;
      while(!Extinct)
      {
         b = 0;
         while(b < StepNum)
         {
            try
            {
              //move bug
               for (int i = 0; i < MainOrg.width; i++)
               {
                  for (int j = 0; j < MainOrg.length; j++)
                  {
                     if (world[i][j] != null && !world[i][j].getOrgMoved() && world[i][j].getId() == 'b')
                     {
                        world[i][j].move(world, i, j);
                     }
                  }
               }
               //breed bug
               reset();
               for (int i = 0; i < MainOrg.width; i++)
               {
                  for (int j = 0; j < MainOrg.length; j++)
                  {
                     if (world[i][j] != null && world[i][j].goBreed() && world[i][j].getId() == 'b')
                         world[i][j].breed(world, i, j);
                  }
               }
               BugCounter.run();
               gui.update(timeSteps, world, AntNum, BugNum);
               Thread.sleep(300);
               //move ant
               for (int i = 0; i < MainOrg.width; i++)
               {
                  for (int j = 0; j < MainOrg.length; j++)
                  {
                     if (world[i][j] != null && !world[i][j].getOrgMoved() && world[i][j].getId() == 'a')
                     {
                        world[i][j].move(world, i, j);
                     }
                  }
               }
               //breed ant
               reset();
               for (int i = 0; i < MainOrg.width; i++)
               {
                  for (int j = 0; j < MainOrg.length; j++)
                  {
                     if (world[i][j] != null && world[i][j].goBreed() && world[i][j].getId() == 'a')
                         world[i][j].breed(world, i, j);
                  }
               }
               antCounter.run();
               gui.update(timeSteps, world, AntNum, BugNum);
               
               if (AntNum <= 0)
               {
                  throw new ExtinctExcept("Ants");
               }
               else if (BugNum <= 0)
               {
                  throw new ExtinctExcept("Bugs");
               }
            }
            catch(ExtinctExcept e)
            {
               JOptionPane.showMessageDialog(null, e.getMessage());
               Extinct = true;
               System.exit(0);
            }
            b++;
            timeSteps++;
         }
         gui.update(timeSteps, world, AntNum, BugNum);
         //gui running until end
         while(!gui.getEnd())
         {
            Thread.sleep(200);
         }
         StepNum = gui.getSteps();
      }
   }
   
   public static void reset()  //by Alif 
   {
      for(int i = 0; i < MainOrg.width; i++)
      {
         for (int j = 0; j < MainOrg.length; j++)
         {
            if (world[i][j] != null)
            {
               world[i][j].setOrgMoved(false);
               world[i][j].setOrgBred(false);
            }
         }
      }
   }
   
   public static void createBug() //by Puteri //by Alif //by Ganesan
   {
      Random rand = new Random();
      int xcord, ycord;
      for (int j = 0; j < BugIn; j++)
      {
         xcord = rand.nextInt(MainOrg.width);
         ycord = rand.nextInt(MainOrg.length);
         while(!Organism.gridcheck(world, ycord, xcord))
         {
            xcord = rand.nextInt(MainOrg.width);
            ycord = rand.nextInt(MainOrg.length);
         }
         world[xcord][ycord] = new Bug();
         BugNum++;
      }
   }
   
   public static void createAnt()//by Puteri //by Alif //by Ganesan
   {
      Random rand = new Random();
      int xcord, ycord;
      for (int i = 0; i < AntIn; i++)
      {
         xcord = rand.nextInt(MainOrg.width);
         ycord = rand.nextInt(MainOrg.length);
         while(!Organism.gridcheck(world, ycord, xcord))
         {
            xcord = rand.nextInt(MainOrg.width);
            ycord = rand.nextInt(MainOrg.length);
         }
         world[xcord][ycord] = new Ant();
         AntNum++;
      }
   }
   /*A seperate thread to count the number of ants*/
   static class CountAnt extends Thread //by Puteri //by Alif //by Ganesan
   {
      public void run()
      {
         AntNum = 0;
         for (int i = 0; i < MainOrg.width; i++)
         {
            for (int j = 0; j < MainOrg.length; j++)
            {
               if (world[i][j] instanceof Ant)
                  AntNum++;
            }
         }
      }
   }
  
   static class CountBug extends Thread //by Puteri //by Alif //by Ganesan
   {
      public void run()
      {
         BugNum = 0;
         for (int i = 0; i < MainOrg.width; i++)
         {
            for (int j = 0; j < MainOrg.length; j++)
            {
               if (world[i][j] instanceof Bug)
                  BugNum++;
            }
         }
      }
   }
}