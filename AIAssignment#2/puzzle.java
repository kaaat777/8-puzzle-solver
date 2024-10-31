import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.*;


public class puzzle{
   /*  public static int[] shuffle(int[] n){
        
    }
   */
public static void main (String[] args) {
int[] goal = {1,2,3,8,0,4,7,6,5};
int[] random = shuffledPuzzle();

Node initial = new Node(random, 0);




//Checks if puzzle is solvable and if so runs the method. 
if(!Solvable(random, goal)){
    System.out.println("This puzzle is not solvable");
    return;
}
else {
    System.out.println("This puzzle is solvable!");
    

    solve(initial, goal);
}


}




//Solving based on what method the user wants to use

public static void solve(Node initial, int[] goal){
int ans = 1;

Scanner scanner = new Scanner(System.in);
System.out.println("Which solve method would you like to use?\n 1. UCS\n 2. A*Search with Manhattan heuristic\n 3.A*Search with Nilsons sequence");
ans = scanner.nextInt();


//Start a priority queue for UCS
PriorityQueue<Node> pq = new PriorityQueue<>();

//hashset to keep track of which nodes were visited
Set<String> visited = new HashSet<>();


//add the initial state to the priority queue
pq.add(initial);

//add initial to visited set
visited.add(Arrays.toString(initial.puzzle));

int nodesExpanded = 0;

while(!pq.isEmpty()){


    System.out.print("Its working, ");

    //Takes the puzzle node that has the lowest count in the priority queue
    Node current =  pq.poll();
    //keeps track of nodes that are being expanded
    nodesExpanded++;

//Prints out the current state to show users 
    System.out.print("Current state: ");
    for (int i = 0; i < 9; i++) {
        System.out.print(current.puzzle[i] + " ");
    }
    System.out.println();



    if(goalReached(current.puzzle,goal)){
        System.out.println("Goal state reached!");
        System.out.println("Nodes expanded: " + nodesExpanded);

        for(int i =0;i<9;i++){
            System.out.print(current.puzzle[i]);
        }

        System.out.print("\nGoal: ");
        for(int i =0;i<9;i++){
            System.out.print(goal[i]);
        }
        return;
    }
    else{
        System.out.println("Still searching...");
    }

    //Makes an arraylist based on what moves were possible from the move method in Node
    ArrayList<Node> nextMoves = current.move(goal);

    if(ans==1){
    nextMoves = current.move(goal);
    }
    if(ans ==2){
        nextMoves = current.moveManhattan(goal);
    }
    if(ans==3){
        nextMoves = current.moveNilsons(goal);
    }
    for(Node move : nextMoves){
        String moveString = Arrays.toString(move.puzzle);

        if(!visited.contains(moveString)){
        visited.add(moveString);
        pq.add(move);
        }
    }
}
System.out.println("No solution found.");
   
}








//checks if the goal state has been reached
public static boolean goalReached(int[] puzzle, int[] goa){
    return Arrays.equals(puzzle,goa);
}

//Checks if the puzzle is solvable by comparing the inversion count from the initial state and goal state
public static boolean Solvable(int[] puzzle, int[] goal){
    int inversion = 0;


    //Gets the inversion count for the initial puzzle state 
    for(int i =0;i<9;i++){
        for(int j=i+1;j<9;j++){
            if(puzzle[i] != 0 && puzzle[j] != 0 && puzzle[i] > puzzle[j]){
                inversion++;
            }
        }
    }

    //gets the inversion count for the goal state
    int goalinversion = 0;
    for(int i =0;i<9;i++){
        for(int j=i+1;j<9;j++){
            if(goal[i] != 0 && goal[j] != 0 && goal[i] > goal[j]){
            goalinversion++;
            }
        }
    }
    //returns whether the inversion counts are both odd or even meaning the puzzle is solvable
   return (inversion % 2) == (goalinversion%2);
    }



    //Shuffles the puzzle so everytime the code is ran there is a new puzzle 
    public static int[] shuffledPuzzle(){
        int[] shuffled = new int[9];

        ArrayList<Integer> shuffle = new ArrayList<>();

        for(int i=0;i<9;i++){
        shuffle.add(i);
        }

        Random rand = new Random();
        //loops through and grabs a random index from the array list and puts it into the new puzzle
        for(int i = 0; i<9;i++){
            int y = rand.nextInt(shuffle.size());
            shuffled[i] = shuffle.get(y);
            shuffle.remove(y);
        }
        return shuffled;
    }

}
