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

	 int pigeonNumber = 6;
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
        	 addPigeon(new Pigeon(new Position(Random(PigeonWindow.getWidth_p()),Random(PigeonWindow.getHeight_p())), this, i));            
         }
        
     }
     public void addFood(Food food)
     {
    	 Environnement.foodLock.writeLock().lock();
 		try {
 			this.foods.add(food);
 		} finally {
 			Environnement.foodLock.writeLock().unlock();
 		}

    	
     }     
     
     public void StartPigeonThread() {
    		for(Pigeon p : pigeons) 
    		{		
    			Thread t = new Thread(p);				
    			t.start();
    		}
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
    
    
    public Food getNearestFood(Pigeon p) {
		
    	Food food = null;
		 
    double min = Double.POSITIVE_INFINITY;
         
      foodLock.readLock().lock();
		try {
			for (Food f : foods) {
				double dist = Maths.computeDistance(p.getPosition(), f.getPosition());
				if (dist < min && f.Fresh()) {
					min = dist;
					food = f;
				}
			}
		} finally {
			foodLock.readLock().unlock();
		}          
         
         return food;   
		
		
	}
    
   
		
	

	
	
}
