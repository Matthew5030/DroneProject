package dsa_assignment2;

import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * A Drone class to simulate the decisions and information collected by a drone
 * on exploring an underground maze.
 */
public class Drone implements DroneInterface {
    private static final Logger logger = Logger.getLogger(Drone.class);
    /**
     * The Maze that the Drone is in
     */
    private Maze maze;
    /**
     * The stack containing the portals to backtrack through when all other
     * doorways of the current chamber have been explored (see assignment
     * handout). Note that in Java, the standard collection class for both
     * Stacks and Queues are Deques
     */
    private Deque<Portal> visitStack = new ArrayDeque<>();
    /**
     * The set of portals that have been explored so far.
     */
    private Set<Portal> visited = new HashSet<>();
    /**
     * The Queue that contains the sequence of portals that the Drone has
     * followed from the start
     */
    private Deque<Portal> visitQueue = new ArrayDeque<>();

    /**
     * This constructor should never be used. It is private to make it
     * uncallable by any other class and has the assert(false) to ensure that if
     * it is ever called it will throw an exception.
     */
    @SuppressWarnings("unused")
    private Drone() {
        assert (false);
    }

    /**
     * Create a new Drone object and place it in chamber 0 of the given Maze
     *
     * @param maze the maze to put the Drone in.
     */
    public Drone(Maze maze) {
        this.maze = maze;
    }

    public String getStudentID() {
        //change this return value to return your student id number
        return "1884401";
    }

    public String getStudentName() {
        //change this return value to return your name
        return "MATTHEW BILELLA";
    }

    /*
     * @see dsa_assignment2.DroneInterface#searchStep()
     */
    /*


     */
    @Override
    public Portal searchStep() {
        /* WRITE YOUR CODE HERE */

        Portal testPortal = null;

        //visited.add(new Portal(this.maze.getCurrentChamber(), 0));
        //visited.add(new Portal(this.maze.getCurrentChamber(), 1));

        boolean newNodeFound = false;

        for (int doorCounter = 0; doorCounter < this.maze.getNumDoors(); doorCounter++) {


            testPortal = new Portal(this.maze.getCurrentChamber(), doorCounter);
            //System.out.println("Current node: "+testPortal.getChamber());

            //System.out.println("door " + doorCounter);

            if (visited.contains(testPortal)) {

                // System.out.println("Already visited!");
                //testPortal=null;

            } else {

                newNodeFound = true;


                visited.add(testPortal);
                visitQueue.addLast(testPortal);

                testPortal = this.maze.traverse(doorCounter);//new node

                visited.add(testPortal);// add the nodes to visits
                visitStack.addLast(testPortal);//add the portal used to enter as a return

                doorCounter = this.maze.getNumDoors();//escape the for loop

                //System.out.println("moved to a new portal "+testPortal.getChamber());
            }

        }

        if (!newNodeFound) {

            for (int doorCounter = 0; doorCounter < this.maze.getNumDoors(); doorCounter++) {


                testPortal = new Portal(this.maze.getCurrentChamber(), doorCounter);
                //System.out.println("Current node: "+testPortal.getChamber());

                //System.out.println("door " + doorCounter);

                if (visitQueue.contains(testPortal)) {

                    //System.out.println("Already used path!");

                } else {

                    visitQueue.addLast(testPortal);

                    testPortal = this.maze.traverse(doorCounter);//new node


                    visitStack.addLast(testPortal);//add the portal used to enter as a return

                    doorCounter = this.maze.getNumDoors();//escape the for loop

                    //System.out.println("moved on new path! "+testPortal.getChamber());
                    return testPortal;
                }

            }
            testPortal = null;
            return testPortal;
        } else {
            return testPortal;
        }

    }






        /*
        1. visit stack shows the way back
        2. visit queue i need to add the portal as i go
        3. (0)-1 -------2-(3)
        4. where you kleave in the back of the queue .add last
        5. add the place you arrive to the stack
        6. search step and single search
        7. if it is not in the visit queue (gone through) but it is in visited
        then go explore it if no unexplored one

         */


    /*
     * @see dsa_assignment2.DroneInterface#getVisitOrder()
     */
    @Override
    public Portal[] getVisitOrder() {
        /* WRITE YOUR CODE HERE */

        Portal[] PortalSequence = new Portal[visitQueue.size() * 2];
        Portal[] visitStackArray = new Portal[visitStack.size()];
        Portal[] visitQueueArray = new Portal[visitQueue.size()];

        visitStackArray = visitStack.toArray(visitStackArray);
        visitQueueArray = visitQueue.toArray(visitQueueArray);

        for (int i = 0; i < visitQueue.size() * 2; i++) {

            if ((i % 2) == 0) {//even
                PortalSequence[i] = visitQueueArray[i / 2];
            } else {//odd
                PortalSequence[i] = visitStackArray[i / 2];
            }


        }
        return PortalSequence;
    }

    /*
     * @see dsa_assignment2.DroneInterface#findPathBack()
     */
    @Override
    public Portal[] findPathBack() {
        /* WRITE YOUR CODE HERE */
        return null;
    }

}
