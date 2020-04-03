import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.IOException;

//Bug class
class Bug extends Organism
{
   public static ImageIcon bugImageIcon;
   private static final int periodBreed = 8; // the time until the bug is allowed to breed
   private static final int starveTime = 3; // the time in which the bug will starve if it did not eat
   private int hungry;
   
   public Bug() // Bug constructor //by Alif
   {
      super();
      hungry = 0;
      
      id = 'b'; 
      try{
         bugImageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("bug.png")));
      }
      catch(IOException ex){
         System.out.println("bug file not found");
      }
      catch(IllegalArgumentException ex){
         System.out.println("bug file not found");

      } 
   }
   
   public int getstarveTime() // by Puteri
   {
      return starveTime;
   }
   
   public int getperiodBreed() //by Ganesan
   {
      return periodBreed;
   }
   
   public void setBreedFreq(int num) //for debugging // by Puteri
   {
      breedTime = num;
   }
   
   public void move(Organism[][] world, int y, int x) //by Alif //by Ganesan 
   {//normal move
      shuffle();
      if (!Bugstarve())
      {
         Eat(world, y, x); // the move and eat method
         if (!OrgMoved)
         {
            super.move(world, y, x);
            hungry++;
         }
      }
      else
         die(world, y, x);
   }
      
   private void Eat(Organism[][] world, int y, int x) // for the Bug to eat  //by Alif  //by Puteri
   {
      shuffle();
      int i = 0;    
      while (!OrgMoved && i < directions.length)
      {
         gridchecks(directions[i], world, y, x);
         i++;
      }
   }
   
   private void gridchecks(int direction, Organism[][] world, int y, int x) // for bug to check the grid and see if there's ant to eat //by Puteri //by Alif //by Ganesan
   {
      if (direction == 1) 
      {//up
         if (y == 0 && (world[MainOrg.length - 1][x] instanceof Ant))
         {
            world[MainOrg.length - 1][x] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
         else if (y > 0 && (world[y - 1][x] instanceof Ant))
         {
            world[y - 1][x] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
      }
      else if (direction == 2)
      {//right
         if (x == MainOrg.width - 1 && (world[y][0] instanceof Ant))
         {
            world[y][0] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
         else if (x < (MainOrg.width - 1) && (world[y][x + 1] instanceof Ant))
         {
            world[y][x + 1] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
      }
      else if (direction == 3)
      {//down
         if (y == (MainOrg.length - 1) && (world[0][x] instanceof Ant))
         {
            world[0][x] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
         else if (y < MainOrg.length - 1 && (world[y + 1][x] instanceof Ant))
         {
            world[y + 1][x] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
      }
      else if (direction == 4)
      {//left
         if (x == 0 && (world[y][MainOrg.width - 1] instanceof Ant))
         {
            world[y][MainOrg.width - 1] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
         else if (x > 0 && (world[y][x - 1] instanceof Ant))
         {
            world[y][x - 1] = world[y][x];
            world[y][x] = null;
            resetHungry();
            OrgMoved = true;
            breedTime++;
         }
      }
   }
      
   public boolean goBreed() // to check if the bug can breed or not //by Ganesan
   {
      return (breedTime >= periodBreed);
   }
   
   protected void breed(Organism[][] world, int y, int x) // to let the bug breed  //by Alif //by Ganesan //by Puteri
   {
      int i = 0;
      while(i < 4 && !OrgBred)
      {
         int direction = orgDirection();
         if (direction == 1)
         {
            //up
            if (y == 0 && gridcheck(world, MainOrg.length - 1, x))
            {
              
               world[MainOrg.length - 1][x] = new Bug();
               OrgBred = true;
            }
            else if (y != 0 && gridcheck(world, y - 1, x))
            { 
               
               world[y - 1][x] = new Bug();
               OrgBred = true;
            }
         }  
         else if (direction == 2)
         {//right
            if (x == MainOrg.width - 1 && gridcheck(world, y, 0))
            {
               
               world[y][0] = new Bug();
               OrgBred = true;
            }
            else if (x != MainOrg.width - 1 && gridcheck(world, y, x + 1))
            {
               
               world[y][x + 1] = new Bug();
               OrgBred = true;
            }
         }
         else if (direction == 3)
         {
            //down
            if (y == MainOrg.length - 1 && gridcheck(world, 0, x))
            {
               
               world[0][x] = new Bug();
               OrgBred = true;
            }
            else if (y != MainOrg.length - 1 && gridcheck(world, y + 1, x))
            {
               
               world[y + 1][x] = new Bug();
               OrgBred = true;
            }
         }
         else if (direction == 4)
         {  
            // left
            if (x == 0 && gridcheck(world, y, MainOrg.width - 1))
            {
               
               world[y][MainOrg.width - 1] = new Bug();
               OrgBred = true;
            }
            else if (x != 0 && gridcheck(world, y, x - 1))
            {
               
               world[y][x - 1] = new Bug();
               OrgBred = true;
            }
         }
         i++;
      }
      if(OrgBred)
      {
         breedTime = 0;
      }
   }
      
   private void resetHungry() // to reset the bug hunger timer // by Alif
   {
      hungry = 0;
   }
   
   private boolean Bugstarve() 
   //by Puteri
   {
      boolean Bugstarve = false;
      if (hungry >= starveTime)
         Bugstarve = true;
      return Bugstarve;
   }
   
   public void die(Organism[][] world, int y, int x) //by Ganesan //by Alif
   {
      world[y][x] = null;
   }
}
