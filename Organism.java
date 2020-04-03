import java.util.*;

//Organism class
public abstract class Organism
{
   protected int breedTime; // the time since an Organism has bred
   protected int d; //the day the organism are alive
   protected Random ran = new Random(); 
   protected boolean OrgMoved; //bool of whether the organism has moved
   protected boolean OrgBred;///bool of whether the organism has moved
   protected char id; 
   
   protected static int[] directions = {1, 2, 3, 4};
   
   public Organism() //organism constructor to initialize the organism condition
   // by Puteri
   {
      d = 0;
      breedTime = 0;
      OrgBred = false;
      OrgMoved = false;
   }
   
   public static boolean gridcheck(Organism[][] o, int x, int y) //checking the grid //by Ganesan
   {
      return (o[x][y] == null);
   }
   
   public boolean buggridcheck(Organism[][] o, int x, int y)// checking the grid for bug //by Ganesan
   {
      boolean isEmpty = true;
      if (o[x][y] instanceof Bug)
         isEmpty = false;
      return isEmpty;
   }
   
   public boolean getOrgMoved()//by Ganesan
   {
      return OrgMoved;
   }
   
   public char getId() //get the id that differentiate the two organism//by Puteri
   {
      return id;
   }
   
   public void setOrgMoved(boolean bool) // to set the organism to move action //by Ganesan
   {
      OrgMoved = bool;
   }
   
   public void resetBreeding() // to reset the breeding time to zero
   //by Puteri
   {
      breedTime = 0;
   }
   
   protected void North(Organism[][] world, int y, int x)// for organism to move up //by Ganesan //by Puteri //by Alif
   {
      world[y - 1][x] = world[y][x];
      world[y][x] = null;
   }
   
   protected void East(Organism[][] world, int y, int x) // for organism to move right //by Ganesan //by Puteri //by Alif
   {
      world[y][x + 1] = world[y][x];
      world[y][x] = null;
   }
   
   protected void South(Organism[][] world, int y, int x) // for organism to move down //by Ganesan //by Puteri //by Alif
   {
      world[y + 1][x] = world[y][x];
      world[y][x] = null;
   }
   
   protected void West(Organism[][] world, int y, int x) // for organism to move left //by Ganesan //by Puteri //by Alif
   {
      world[y][x - 1] = world[y][x];
      world[y][x] = null;
   }
   
    
   public int orgDirection() //determine the direction the organism wil go //by Ganesan //by Puteri
   {
      shuffle();
      int direction = 0;
      int check = directions[ran.nextInt(directions.length)];
      if (check == 1)
      {
         //up
         direction = 1;
      }  
      else if (check == 2)
      {  
         //right
         direction = 2;
      }
      else if (check == 3)
      {
         //down
         direction = 3;
      }
      else if (check == 4)
      {  
         //left
         direction = 4;
      }  
      return direction;
   }
   
   public void setOrgBred(boolean bool) //by Ganesan
   {
      OrgBred = bool;
   }
   public void shuffle() // to shuffle the direction so it would be random //by Puteri
   {
      int index1;
      int index2;
      int temp;
      for (int i = 0; i < 500; i++)
      {
         index2 = index1 = ran.nextInt(directions.length);   
         while (index2 == index1)
            index2 = ran.nextInt(directions.length);
         temp = directions[index1];
         directions[index1] = directions[index2];
         directions[index2] = temp;
      }
   }
   
   public void move(Organism[][] world, int y, int x) // function to make the organism move //by Puteri //by Ganesan //by Alif
   {
      int i = 0;
      while(i < 4 && !OrgMoved)
      {
         int direction = ran.nextInt(4) + 1;
         if(direction == 1)
         {//up
            if (y != 0 && gridcheck(world, y - 1, x))
            {
               North(world, y, x);
            }
		 }
			
			
         
		else if (direction == 2) 
			{//right
            if (x != MainOrg.width - 1 && gridcheck(world, y, x + 1))
            {
               East(world, y, x);
            }
			}
			else if (direction == 3)
			{//down
            if (y != MainOrg.length - 1 && gridcheck(world, y + 1, x))
            {
               South(world, y, x);
            }
			}
         
			else if(direction == 4)
			{//left
            if(x != 0 && gridcheck(world, y, x - 1))
            {
               West(world, y, x);
            }
			}
            i++;
         }
         if (world[y][x] == null)
            OrgMoved = true;
      breedTime++;
	}
   protected abstract void breed(Organism[][] world, int y, int x);
   
   public abstract int getperiodBreed();
   
   public abstract boolean goBreed();
}
/*exception for when the organism become extinct */
class ExtinctExcept extends Exception //by Puteri
{
   public ExtinctExcept (String Org)
   {
      super(Org + " is now extinct.");
   }
}