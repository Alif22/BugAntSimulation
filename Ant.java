//Ant class
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.IOException;

// Ant class
class Ant extends Organism
{
   public static ImageIcon antImageIcon;
   private static final int periodBreed = 3; // the time until ant is allowed to breed
   
   public Ant() // Ant contructor //by Alif
   {
      super();
      id = 'a';

      try{
         antImageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("ant.png")));
      }
      catch(IOException ex){
         System.out.println("ant file not found");
      }
      catch(IllegalArgumentException ex){
         System.out.println("ant file not found");

      } 
   }
   
   public int getperiodBreed() //by Alif
   {
      return periodBreed;
   }
   
   public void move(Organism[][] world, int y, int x) // function for the ant to move //by Alif //by Ganesan
   {
      shuffle();
      super.move(world, y, x);
   }

   
   public boolean goBreed() // to check if the ant can breed or not
   //by Puteri
   {
      boolean bool = false;
      if (breedTime >= periodBreed)
      {
         bool = true;
      }
      return bool;
   }
   
   protected void breed(Organism[][] world, int y, int x)  //by Alif //by Ganesan //by Puteri
   {
     int direction = orgDirection();
     if (direction == 1)
     {
        //up
        if (y == 0 && gridcheck(world, MainOrg.length - 1, x))
        {
           world[MainOrg.length - 1][x] = new Ant();
           OrgBred = true;
        }
        else if (y != 0 && gridcheck(world, y - 1, x))
        { 
           world[y - 1][x] = new Ant();
           OrgBred = true;
        }
     }  
     else if (direction == 2)
     {//right
        if (x == MainOrg.width - 1 && gridcheck(world, y, 0))
        {
           world[y][0] = new Ant();
           OrgBred = true;
        }
        else if (x != MainOrg.width - 1 && gridcheck(world, y, x + 1))
        {
           world[y][x + 1] = new Ant();
           OrgBred = true;
        }
     }
       else if (direction == 3)
       {
          //down
          if (y == MainOrg.length - 1 && gridcheck(world, 0, x))
          {
             world[0][x] = new Ant();
             OrgBred = true;
          }
          else if (y != MainOrg.length - 1 && gridcheck(world, y + 1, x))
          {
             world[y + 1][x] = new Ant();
             OrgBred = true;
          }
       }
       else if (direction == 4)
       {  
          // left
          if (x == 0 && gridcheck(world, y, MainOrg.width - 1))
          {
             world[y][MainOrg.width - 1] = new Ant();
             OrgBred = true;
          }
          else if (x != 0 && gridcheck(world, y, x - 1))
          {
             world[y][x - 1]  = new Ant();
             OrgBred = true;
          }
      }
      if(OrgBred)
      {
         breedTime = 0;
      }
   }
}