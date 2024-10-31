import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Node implements Comparable<Node> {
    int[] puzzle;
    String action;
    int cost;

    public Node(int[] puzzle, int cost){
        this.puzzle = puzzle.clone();
        this.cost = cost;
    }

public int findzero(){
        for(int i=0;i< puzzle.length;i++){
            if(this.puzzle[i]==0) return i;
        }
        return -1;
    }
    
    public int[] swap(int[] s, int i, int j){
    int[] newS = s.clone();
     int temp = newS[i];
     newS[i] = newS[j];
     newS[j] = temp;
     return newS;
    }

//Calculate Nilsons sequence for a heuristic
public int Nilsons(int[] puzzle, int[] goal){
    int[] goalper = perimeter(goal);
    int[] puzzleper = perimeter(puzzle);
    int score = 0;

    for(int i = 0; i<9;i++){
        if(goalper[i] != puzzleper[i]){
            score++;
        }
    }
    return score;
}
//Gets the sequence for the perimeter
public int[] perimeter(int[] puzzle){
    int[] perimeter = new int[9];

    perimeter[0] = puzzle[0];
    perimeter[1] = puzzle[1];
    perimeter[2] = puzzle[2];
    perimeter[3] = puzzle[5];
    perimeter[4] = puzzle[8];
    perimeter[5] = puzzle[7];
    perimeter[6] = puzzle[6];
    perimeter[7]= puzzle[3];
    perimeter[8] = puzzle[4];
    
    return perimeter;
}


//Calculates the manhattan distance and comes up with a score for the current state
public int manhattan(int[] puzzle, int[] goal){
    int distance = 0;
    int goalnum = 0;

    for(int i =0;i<9;i++){
        if(puzzle[i] != goal[i]){
            for(int j = 0;j<9;j++){
                if(puzzle[i]==goal[j]){
                    goalnum = j;
                    break;
                }
            }
        distance += Math.abs(goalnum-puzzle[i]);
        }
    }
    return distance;
}


    


    //Move with the cost simply being added for every move.
    public ArrayList<Node> move(int[] goal){
       ArrayList<Node> possiblePuzzles = new ArrayList<>();
       int zero = this.findzero();
       int cost = this.cost;
       //If 0 is not in the bottom row it can move down
       if(zero!=6 && zero!=7 && zero!=8){
        int[] newPuz = this.puzzle.clone();
        newPuz = swap(newPuz,zero, zero+3);
        Node newNode = new Node(newPuz,cost+1);
        possiblePuzzles.add(newNode);
       }
       //if 0 is not in the top row it can move up
       if(zero!=0 && zero!=1 && zero!=2){
        int[] newPuz = this.puzzle.clone();
        newPuz = swap(newPuz,zero, zero-3);
        Node newNode = new Node(newPuz,cost+1);
        possiblePuzzles.add(newNode);
       }
       //if 0 is not on the left we can move left
       if(zero!=0 && zero!=3 && zero!=6){
        int[] newPuz = this.puzzle.clone();
        newPuz = swap(newPuz,zero, zero-1);
        Node newNode = new Node(newPuz,cost+1);
        possiblePuzzles.add(newNode);
       }
       //if 0 is not on the right we can move right
       if(zero!=2 && zero!=5 && zero!=8){
        int[] newPuz = this.puzzle.clone();
        newPuz = swap(newPuz,zero, zero+1);
        Node newNode = new Node(newPuz,cost+1);
        possiblePuzzles.add(newNode);
       }

       
        return possiblePuzzles;
    } 
   

//Moves with the cost being calculated with Manhattan distance heuristic 
    public ArrayList<Node> moveManhattan(int[] goal){
        ArrayList<Node> possiblePuzzles = new ArrayList<>();
        int zero = this.findzero();
        int cost = this.cost;
        //If 0 is not in the bottom row it can move down
        if(zero!=6 && zero!=7 && zero!=8){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero+3);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
        //if 0 is not in the top row it can move up
        if(zero!=0 && zero!=1 && zero!=2){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero-3);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
        //if 0 is not on the left we can move left
        if(zero!=0 && zero!=3 && zero!=6){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero-1);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
        //if 0 is not on the right we can move right
        if(zero!=2 && zero!=5 && zero!=8){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero+1);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
 
        
         return possiblePuzzles;
     } 



     //Gives possible moves where the cost is calculated with Nilsons sequence heuristic
     public ArrayList<Node> moveNilsons(int[] goal){
        ArrayList<Node> possiblePuzzles = new ArrayList<>();
        int zero = this.findzero();
        int cost = this.cost;
        //If 0 is not in the bottom row it can move down
        if(zero!=6 && zero!=7 && zero!=8){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero+3);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal) + Nilsons(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
        //if 0 is not in the top row it can move up
        if(zero!=0 && zero!=1 && zero!=2){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero-3);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal) + Nilsons(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
        //if 0 is not on the left we can move left
        if(zero!=0 && zero!=3 && zero!=6){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero-1);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal)+Nilsons(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
        //if 0 is not on the right we can move right
        if(zero!=2 && zero!=5 && zero!=8){
         int[] newPuz = this.puzzle.clone();
         newPuz = swap(newPuz,zero, zero+1);
         Node newNode = new Node(newPuz,cost+1+manhattan(newPuz, goal)+Nilsons(newPuz, goal));
         possiblePuzzles.add(newNode);
        }
 
        
         return possiblePuzzles;
     } 

    public int compareTo(Node y){
        return Integer.compare(this.cost, y.cost);
    }
}
