package Model;
import java.util.ArrayList;
import java.util.Observable;
import Helper.*;
import View.PigeonWindow;


import static java.lang.Math.*;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Environnement extends Observable {

	 int pigeonNumber = 5;
	 private ArrayList<Food> foods;
	 private ArrayList<Pigeon> pigeons;
	 private Random random = new Random();
	 public static final ReadWriteLock foodLock = new ReentrantReadWriteLock(true); 
	
		
	 
     public Environnement()
     {
         Init();
         
     }
     private void Init()
     {               
    	 this.foods = new ArrayList<>();
    	 this.pigeons =  new ArrayList<>();
         for (int i = 0; i < pigeonNumber; i++)
         {             
        	 addPigeon(new Pigeon(new Position(Random(PigeonWindow.getWidth_p()),Random(PigeonWindow.getHeight_p()))));            
         }
         
     }
     public void addFood(Food food)
     {
    	 foods.add(food);
     }
     
     public synchronized void drawFood(Position p)
     {
         Food new_food = new Food(p);
         foods.add(new_food);        
     }
		
	public void addPigeon(Pigeon pigeon)
	{
		pigeons.add(pigeon);
	}
	
	public ArrayList<Food> getFood() {
		return foods;
	}
	
	public ArrayList<Pigeon> getPigeon() {
		return pigeons;
	}
	
    public int Random(int n) { return random.nextInt(n); }
    
      
    
    public void eatFood(Food f) {
		foodLock.writeLock().lock();
		try {
			this.foods.remove(f);
		}
		finally {
			foodLock.writeLock().unlock();
		}
	}
    
 		
		
	

	
	
}
