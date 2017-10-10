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
	 private ArrayList<Human> humans;
	 private Random random = new Random();
	 public static final ReadWriteLock foodLock = new ReentrantReadWriteLock(true); 
	 public static final ReadWriteLock HumanLock = new ReentrantReadWriteLock(true); 
	
		
	 
     public Environnement()
     {
         Init();
         
     }
     private void Init()
     {               
    	 this.foods = new ArrayList<>();
    	 this.pigeons =  new ArrayList<>();
    	 this.humans =  new ArrayList<>();
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
     
     public void addHuman(Human human)
     {
 		this.humans.add(human);
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
	public ArrayList<Human> getHuman() {
		return humans;
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
				double dist = Coord.computeDistance(p.getPosition(), f.getPosition());
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
    

    public Human getNearestHuman(Pigeon p) {
    	Human human = null;
	    double min = Double.POSITIVE_INFINITY;
	    HumanLock.readLock().lock();
	    try {
			for (Human h : humans) {
				double dist = Coord.computeDistance(p.getPosition(), h.getPosition());
				if (dist < min) {
					min = dist;
					human = h;
				}
			}
	    }
	    finally {
	    	HumanLock.readLock().unlock();
		}     
	    
        return human;  
    }
    
    public void clean() {
    	
    	//Nourriture
    	ArrayList<Food> foodToRemove = new ArrayList<Food>();
    	 foodLock.readLock().lock();
 		try {
	    	for(Food f : foods) {
	    		if(f.needsToBeDeleted()) {
	    			foodToRemove.add(f); 
	    		}
	    	}
 		}
 		 finally {
 			foodLock.readLock().unlock();
 		}      
 		
 		//Suppression de toutes les nourriture pourries
 		
 		 foodLock.writeLock().lock();
  		try {
 	    	foods.removeAll(foodToRemove);
  		}
  		 finally {
  			foodLock.writeLock().unlock();
  		}      
  		
  		//Human
  		
  		ArrayList<Human> HumanToRemove = new ArrayList<Human>();
  		 HumanLock.readLock().lock();
  		 try {
	    	for(Human h : humans) {
	    		if((!h.isAlive())) {
	    			HumanToRemove.add(h); 
	    		}
	    	}
  		 }
  		 finally {
 	    	HumanLock.readLock().unlock();
 		}     
  		HumanLock.writeLock().lock();
   		try {
   			humans.removeAll(HumanToRemove);
   		}
   		 finally {
   			HumanLock.writeLock().unlock();
   		}     
		 
    	


    }	

	
    public void generateHuman() {
    	//Créer un nombre aléatoire    	
    	int occurence = Random(1000);
    	int probability = Random(5);
    	//System.out.println(occurence + " " + probability);	
    	 if(occurence<probability)    	
    		humans.add(
    				new Human(
    						new Position(
    								Random(PigeonWindow.getWidth_p()),
    								Random(PigeonWindow.getHeight_p())
    								),
    						new Position(
									Random(PigeonWindow.getWidth_p()),
									Random(PigeonWindow.getHeight_p())
									)
    						)
    				);
    	
    	
    	
    	//Thread.sleep(10000 / speed); //10s/speed to avoid afraid bool to change to frequently
    	                   
    }
    
    public boolean isThereHuman() {
    	return humans.size() != 0;    	                   
    }
    
    public boolean closeToHuman(Pigeon p) {  
    	
    	 HumanLock.readLock().lock();
    	 try {
	    	 for(Human human : humans)
	    	 {
	    		 double dist = Coord.computeDistance(p.getPosition(), human.getPosition());
					if (dist < 300) {
						return true;
					}
	 	     } 		  		  
    	 } finally {
  	    	HumanLock.readLock().unlock();
  		}  
    	
    	return false;                 
    }
    
	
}
