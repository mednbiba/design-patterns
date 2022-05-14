package designPatern;

public class ConfigRepository implements Container{
	Computer pc1 = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
	Computer pc2 = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
	Computer pc3 = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
	Computer pc4 = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
	Computer pc5 = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
	
	
	public String names[] = {formatPC(pc1),formatPC(pc2),formatPC(pc3),formatPC(pc4),formatPC(pc5)};

	   @Override
	   public Iterator getIterator() {
	      return new configIterator();
	   }

	   private class configIterator implements Iterator {

	      int index;

	      @Override
	      public boolean hasNext() {
	      
	         if(index < names.length){
	            return true;
	         }
	         return false;
	      }

	      @Override
	      public Object next() {
	      
	         if(this.hasNext()){
	            return names[index++];
	         }
	         return null;
	      }		
	   }
	   public String formatPC(Computer c) {
		String config ="CPU : "+ c.getCPU()+" | RAM : "+c.getRAM()+" | HDD :"+c.getHDD() + "\n";
		return config;
		   
	   }
	}