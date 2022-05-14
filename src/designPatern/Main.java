package designPatern;


import java.util.Scanner;

import static java.lang.System.exit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Testing Abstract Factory,Singleton and Iterator Design Patterns
public class Main {
	


    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        
        System.out.print("Choose your option : ");
    }

    public static void main(String[] args) {
    	//String configs[];


        String[] options = {"----------------Abstract Factory-------------------------",
        					"\n",
        					"1- Test Abstract Factory PC ",
                            "2- Test Abstract Factory Server",
                            "\n",
                            "----------------Singleton-------------------------",
                            "\n",
                            "3- Singleton JDBC Connection + Random Value ",
                            "4- None Singleton JDBC Connection + Random Value ",
                            "\n",
                            "----------------Iterator-------------------------",
                            "\n",
                            "5- Iterator for random PC Config Array ",
                            "-------------------------------------------------",
                            "\n",
                            "6- Exit(0)"
        };
        try (Scanner scanner = new Scanner(System.in)) {
			int option = 1;
			while (option!=6){
			    printMenu(options);
			    try {
			        option = scanner.nextInt();
			        switch (option){
			            case 1: 
			            	System.out.print("------------------------\n");
			            	option1();
			            	System.out.print("------------------------\n");
			            	break;

			            case 2:
			            	System.out.print("------------------------\n");
			            	option2();
			            	System.out.print("------------------------\n");
			            	break;
			            	
			            case 3: 
			            	System.out.print("------------------------\n");
			            	option3();
			            	System.out.print("------------------------\n");
			            	break;
			            case 4:
			            	System.out.print("------------------------\n");
			            	option4();
			            	System.out.print("------------------------\n");
			            	break;
			            case 5: 
			            	System.out.print("------------------------\n");
			            	option5(); 
			            	System.out.print("------------------------\n");
			            	break;
			            	
			            case 6:exit(0);
			        }
			    }
			    catch (Exception ex){
			        System.out.println("Please enter an integer value between 1 and " + options.length);
			        scanner.next();
			    }
			}
		}
    }

// Options
    private static void option1() {
    	
    	//Generate Dummy PC Instance
    	Computer pc = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
    	System.out.println("Random AbstractFactory PC Config::"+pc +"|| getClass()  : "+pc.getClass());
    }
    private static void option2() {
    	//Generate Dummy Server Instance
    	Computer server = ComputerFactory.getComputer(new ServerFactory(randomGenerator.randomGB(32,512),randomGenerator.randomGB(1000,6000),randomGenerator.randomHZ(3,6)));
    	System.out.println("Random AbstractFactory Server Config::"+server+"|| getClass(): " +server.getClass());
       	System.out.println(server.getCPU());
    }
    private static void option3() {
    	//Generate Random Type + Stores into Database
    	int choice = randomGenerator.randomchoice(1,2);
    	if (choice==1) {
    		Computer pc = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
        	System.out.println("Randomize PC Config::"+pc +"|| getClass()  : "+pc.getClass());
        	try (
        			//Singleton JDBC Connection refer to SingletonConnection Class, Allows only one Instance of the connection to be made that closes after the first SQL Statement
        			Connection conn = SingletonConnection.getCon()
        			
        			) {
        		//SQL Query
        		String sql = "INSERT INTO Configz (CPU,RAM,DISK,TYPE) VALUES (?,?,?,?)";
        		PreparedStatement statement = conn.prepareStatement(sql);
        		statement.setString(1, pc.getCPU());
        		statement.setString(2, pc.getRAM());
        		statement.setString(3, pc.getHDD());
        		statement.setString(4, pc.getClass().toString());
        		
        		int rowsInserted = statement.executeUpdate();
        		if(rowsInserted>0) {
        			System.out.print("Done Inserting");
        		}
        	     
        	    
        	     
        	} catch (SQLException ex) {
        	    ex.printStackTrace();
        	}
        	
    		
    	}else {
    		Computer server = ComputerFactory.getComputer(new ServerFactory(randomGenerator.randomGB(32,512),randomGenerator.randomGB(1000,6000),randomGenerator.randomHZ(3,6)));
        	System.out.println("Randomize Server Config::"+server+"|| getClass(): " +server.getClass());	
        	try (
        			//Singleton JDBC Connection refer to SingletonConnection Class, Allows only one Instance of the connection to be made that closes after the first SQL Statement
        			Connection conn = SingletonConnection.getCon()
        			
        			
        			) {
        		//SQL Query 
        		String sql = "INSERT INTO Configz (CPU,RAM,DISK,TYPE) VALUES (?,?,?,?)";
        		PreparedStatement statement = conn.prepareStatement(sql);
        		statement.setString(1, server.getCPU());
        		statement.setString(2, server.getRAM());
        		statement.setString(3, server.getHDD());
        		statement.setString(4, server.getClass().toString());
        		
        		
        		int rowsInserted = statement.executeUpdate();
        		if(rowsInserted>0) {
        			System.out.print("Done Inserting");
        		}
        	     
        	    
        	     
        	} catch (SQLException ex) {
        	    ex.printStackTrace();
        	}
     
    	}
    	
    	
    }
    private static void option4() {
    	//Creates new JDBC Connection each time,
    	int choice = randomGenerator.randomchoice(1,2);
    	if (choice==1) {
    		Computer pc = ComputerFactory.getComputer(new PCFactory(randomGenerator.randomGB(1,7),randomGenerator.randomGB(64,500),randomGenerator.randomHZ(1,5)));
        	System.out.println("Randomize PC Config::"+pc +"|| getClass()  : "+pc.getClass());
        	Connection conn;
        	final String url = "jdbc:mysql://localhost:3306/test";
    		final String user = "root";
    		final String pass = "";
    		try {
    			conn = (Connection) DriverManager.getConnection(url,user,pass);
    			System.out.println(" New Database Connection Created : Logged to " + url + " as : " + user );
    			String sql = "INSERT INTO Configz (CPU,RAM,DISK,TYPE) VALUES (?,?,?,?)";
        		PreparedStatement statement = conn.prepareStatement(sql);
        		statement.setString(1, pc.getCPU());
        		statement.setString(2, pc.getRAM());
        		statement.setString(3, pc.getHDD());
        		statement.setString(4, pc.getClass().toString());
        		
        		int rowsInserted = statement.executeUpdate();
        		conn.close();
        		if(rowsInserted>0) {
        			System.out.print("Done Inserting");
        		}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		
    	}else {
    		
    	}
    	
    }
    private static void option5() {
    	
    	 ConfigRepository confRepository = new ConfigRepository();
    	 System.out.println("Available Configurations list : \n");
         System.out.println("-----------------------------------");
         for(Iterator iter = confRepository.getIterator(); iter.hasNext();){
            String configs = (String)iter.next();
            System.out.println("Config : " + configs);
            
         } 	
         System.out.println("-----------------------------------");
    	
    }
    

   

}