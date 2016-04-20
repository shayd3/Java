
import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class YourMazeWithPath {

    private InputGraphicMaze maze;
    private int R, C;
    private int[][] V;

    public YourMazeWithPath() {
        // an R rows x C columns maze
        maze = new InputGraphicMaze();
        R = maze.Rows();
        C = maze.Cols();
        V = new int[R + 1][C + 1];
        V[1][1] = 1;
        // Path holds the cells of the path
        LinkedList<Point> Path = new LinkedList<Point>();
        // Create the path
        CreatePath(maze, 1, 1, R, C, Path);
        // show the path in the maze
        maze.showPath(Path);
    }

    // Creates the path through maze, starting at cell (srow, scol)
    // and ending at cell (erow and ecol),  in L
    public boolean CreatePath(InputGraphicMaze maze, int srow, int scol, int erow, int ecol, LinkedList<Point> L) {
        int r = srow, c = scol;
        Point u = new Point(r, c);
        boolean done = false;

        if ((r == erow) && (c == ecol)) {
            done = true;
        } else {
            if (srow > 1 && V[srow - 1][scol] != 1 && maze.can_go(srow, scol, 'U')) {
                V[srow - 1][scol] = 1;
                done = CreatePath(maze, srow - 1, scol, erow, ecol, L);
            }

            if (!done && scol < C && V[srow][scol + 1] != 1 && maze.can_go(srow, scol, 'R')) {
                V[r][scol + 1] = 1;
                done = CreatePath(maze, srow, scol + 1, erow, ecol, L);
            }

            if (!done && srow < R && V[srow + 1][scol] != 1 && maze.can_go(srow, scol, 'D')) {
                V[srow + 1][scol] = 1;
                done = CreatePath(maze, srow + 1, scol, erow, ecol, L);
            }

            if (!done && scol > 1 && V[srow][scol - 1] != 1 && maze.can_go(srow, scol, 'L')) {
                V[srow][scol - 1] = 1;
                done = CreatePath(maze, srow, scol - 1, erow, ecol, L);
            }

        }
        if (done) {
            L.addFirst(u);
        }
        return done;
    }

    public static void main(String[] args) {
        new YourMazeWithPath();
    }
}
