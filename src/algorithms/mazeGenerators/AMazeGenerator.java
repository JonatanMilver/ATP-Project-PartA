package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract class that implements IMazeGenerator.
 * Other classes can extend this class and make various types of mazes.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    ArrayList<Position> totalyRandomStartGoal(int rows, int columns) {
        ArrayList<Position> startNgoal = new ArrayList<>();
        Random randgen = new Random();

        Position start;
        Position end;
        int start_edge = randgen.nextInt(4); // 0 - up , 1 - right , 2 - down , 3 - left
        int start_pos, end_edge , end_pos;
        if (start_edge == 0 || start_edge == 2){
            start_pos = randgen.nextInt(columns);

            // start position in (0,?)
            if (start_edge == 0){

                //start position in (0,0)
                if (start_pos == 0){
                    start = new Position(0,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,columns-1);
                    }
                    else { //end_edge == 1
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1 , end_pos);
                    }
                }

                //start position in (0,columns-1)
                else if (start_pos == columns-1) {
                    start = new Position(0,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,0);
                    }
                    else{ //end_edge == 1
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(rows-1 , end_pos);
                    }
                }

                else{
                    start = new Position(0,start_pos);
                    end_edge = randgen.nextInt(3); // 0 - left , 1 - down , 2 - right
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,0);
                    }
                    else if (end_edge == 2){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,columns-1);
                    }
                    else{ // end_pos == 1
                        end_pos = randgen.nextInt(columns);
                        end = new Position(rows-1 , end_pos);
                    }
                }
            }

            // start position in (rows-1,?)
            else{ //start_edge == 2

                //start position in (rows-1,0)
                if (start_pos == 0){
                    start = new Position(rows-1,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,columns-1);
                    }
                    else { //end_edge == 1
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(0 , end_pos);
                    }
                }


                //start position in (rows-1,columns-1)
                else if (start_pos == columns-1){
                    start = new Position(rows-1,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,0);
                    }
                    else{ //end_edge == 1
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(0 , end_pos);
                    }
                }

                else{
                    start = new Position(rows-1,start_pos);
                    end_edge = randgen.nextInt(3); // 0 - left , 1 - up , 2 - right
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,0);
                    }
                    else if (end_edge == 2){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,columns-1);
                    }
                    else{ // end_pos == 1
                        end_pos = randgen.nextInt(columns);
                        end = new Position(0 , end_pos);
                    }
                }
            }

        }

        else{ //start_edge == 1 || start_edge == 3
            start_pos = randgen.nextInt(rows);

            // start position in right-most column (?,columns-1)
            if (start_edge == 1){

                //start position in (0,columns-1)
                if (start_pos==0){
                    start = new Position(start_pos,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,0);
                    }
                    else {
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1,end_pos);
                    }
                }

                //start position in (rows-1,columns-1)
                else if (start_pos == rows-1){
                    start = new Position(start_pos,columns-1);
                    end_edge = randgen.nextInt(2); // 0 - left , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,0);
                    }
                    else{
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(0 , end_pos);
                    }
                }

                else{
                    start = new Position(start_pos,columns-1);
                    end_edge = randgen.nextInt(3); // 0 - left , 1 - up , 2 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows);
                        end = new Position(end_pos,0);
                    }
                    else if (end_edge == 1){
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(0,end_pos);
                    }
                    else{
                        end_pos = randgen.nextInt(columns-1);
                        end = new Position(rows-1,end_pos);
                    }
                }
            }

            // start position in left-most column (?,0)
            else{ //start_edge == 3

                //start position in (0,0)
                if (start_pos == 0){
                    start = new Position(0,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1)+1;
                        end = new Position(end_pos,columns-1);
                    }
                    else {
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1,end_pos);
                    }
                }

                //start position in (rows-1,0)
                else if (start_pos == rows-1){
                    start = new Position(start_pos,0);
                    end_edge = randgen.nextInt(2); // 0 - right , 1 - up
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(rows-1);
                        end = new Position(end_pos,columns-1);
                    }
                    else{
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(0 , end_pos);
                    }
                }

                else{
                    start = new Position(start_pos , 0);
                    end_edge = randgen.nextInt(3); // 0 - up , 1 - right , 2 - down
                    if (end_edge == 0){
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(0 , end_pos);
                    }
                    else if (end_edge == 2){
                        end_pos = randgen.nextInt(columns-1)+1;
                        end = new Position(rows-1,end_pos);
                    }
                    else{
                        end_pos = randgen.nextInt(rows);
                        end = new Position(end_pos,columns-1);
                    }
                }

            }

        }

        startNgoal.add(start);
        startNgoal.add(end);
        return startNgoal;
    }
}
