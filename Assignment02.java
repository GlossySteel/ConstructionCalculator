/**
* CSE 110     : Class 77686 / iCourse
* Assignment  : Assignment 2
* Author      : Joseph Kharzo & 1218555299
* Description : A program that helps a road construction company determine the number
* of materials they need and the total cost. The program accomplishes this by having
* the user enter the length of the road, the number of lanes, the depth of the
* asphalt, and the days to complete the project. The program then outputs the
* truckloads of asphalt, the number of stoplights, the number of conduit pipes,
* the number of crew members, and their respective costs.
*/

import java.util.Scanner;

public class Assignment02 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // declare and instantiate a Scanner
        Scanner userInput = new Scanner(System.in);

        // declare and initialize variables
        
       
        // Formats for the printf methods
        
        // Format for the prompts
        String format = "%-27s%s%n";
        // Formats for displaying the results
        String format1 = "%-22s%s%n";
        String format2 = "%-22s%s$%,.2f%n";
        
        // Input variables
        
        // Length of road is in miles
        double lengthOfRoad;
        
        // Depth of asphalt is in inches
        int numberOfLanes, depthOfAsphalt, daysToCompleteProject;
        
        // Output variables
        int truckloadsOfAsphalt, stoplights, conduitPipe, crewMembers;
        
        double costOfAsphalt, costOfStoplights, costOfConduitPipes;
        double costOfLabor, totalCostOfProject;
        
        // Intermediate variable for calculating the weight and cost of asphalt
        double totalAsphaltWeight;
        
        // Constants
        final int LANE_LENGTH = 12; // In feet
        final int ASPHALT_WEIGHT_TO_NUMBER_OF_TRUCKS = 10000; // Converts the weight to number of trucks
        final int STOPLIGHTS_PER_INTERSECTION = 2;
        final int CONDUIT_PIPE_LENGTH = 24;
        final int ASPHALT_COST_PER_TON = 200;
        final int COST_PER_STOPLIGHT = 25000;
        final int COST_PER_LENGTH_OF_PIPES = 500;
        final int WORKER_WAGE = 25;
        final int WORK_DAY_HOURS = 8;
        final double ASPHALT_WEIGHT = 150.0; // Per cubic foot
        final double MILES_TO_FEET = 5280.0; // Converts miles to feet
        final double INCHES_TO_FEET = 12.0; // Converts inches to feet
        final double POUNDS_TO_US_TONS = 2000; // Converts lbs to US tons
        
        // collect inputs
        System.out.printf(format, "Length of the road in miles", ":");
        lengthOfRoad = userInput.nextDouble();
        
        System.out.printf(format, "Number of lanes", ":");
        numberOfLanes = userInput.nextInt();
        
        System.out.printf(format, "Depth of asphalt in inches", ":");
        depthOfAsphalt = userInput.nextInt();
        
        System.out.printf(format, "Days to complete project", ":");
        daysToCompleteProject = userInput.nextInt();
        
        // compute required values
        
        // This calculates the total wight of the asphalt required in lbs
        totalAsphaltWeight = (ASPHALT_WEIGHT * LANE_LENGTH * numberOfLanes * (depthOfAsphalt / INCHES_TO_FEET) * (lengthOfRoad * MILES_TO_FEET));
        
        // Calculates the number of trucks rquired to haul the asphalt
        // The value is rounded up using the ceil method and a cast to an int.
        truckloadsOfAsphalt = (int)Math.ceil(totalAsphaltWeight / ASPHALT_WEIGHT_TO_NUMBER_OF_TRUCKS);
        
        // The number of stoplights required
        /*
        * Uses the information that there are 2 stoplights per intersection and
        * the number of lanes determines the extra stoplights required.
        * The length of the road rounded down determines the number of intersections
        */
        stoplights = (STOPLIGHTS_PER_INTERSECTION + numberOfLanes) *(int)lengthOfRoad;
        
        // The amount of conduit pipe required in lengths of 24 feet
        // Rounded up using the same method with the asphalt
        conduitPipe = (int)Math.ceil((lengthOfRoad * MILES_TO_FEET) / CONDUIT_PIPE_LENGTH);
        
        // The number of crew members required rounded up
        crewMembers = (int)Math.ceil((50 * lengthOfRoad * numberOfLanes) / daysToCompleteProject);
        
        // The total cost of the asphalt is found by converting from lbs to US tons.
        /*
        * The cost uses 4 significant figures, but it must be formatted as a
        * dollar amount, so it is divided by 1000, rounded up, and multiplied by
        * 1000.
        */
        costOfAsphalt = Math.ceil(((totalAsphaltWeight / POUNDS_TO_US_TONS) * ASPHALT_COST_PER_TON) / 1000)*1000;
        
        // The total cost of the stoplights
        costOfStoplights = stoplights * COST_PER_STOPLIGHT;
        
        // The toal cost of the conduit pipes
        costOfConduitPipes = conduitPipe * COST_PER_LENGTH_OF_PIPES;
        
        // The total cost of labor found using the given constants
        costOfLabor = WORKER_WAGE * WORK_DAY_HOURS * daysToCompleteProject * crewMembers;
        
        // The total cost of the project is found by adding the previous costs
        totalCostOfProject = costOfLabor + costOfConduitPipes + costOfStoplights + costOfAsphalt;
        
        // display results
        System.out.println("=== Amount of materials needed ===");
        System.out.printf(format1,"Truckloads of asphalt",": " + truckloadsOfAsphalt);
        System.out.printf(format1,"Stoplights",": " + stoplights);
        System.out.printf(format1,"Conduit pipes",": " + conduitPipe);
        System.out.printf(format1,"Crew members needed",": " + crewMembers);
        System.out.println("=== Cost of Materials ============");
        System.out.printf(format2,"Cost of asphalt",": ", costOfAsphalt);
        System.out.printf(format2,"Cost of stoplights",": ", costOfStoplights);
        System.out.printf(format2,"Cost of conduit pipes",": ", costOfConduitPipes);
        System.out.printf(format2,"Cost of labor",": ", costOfLabor);
        System.out.println("=== Total Cost of Project ========");
        System.out.printf(format2,"Total cost of project", ": ", totalCostOfProject);
    }
}
