import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 *
 * Algorithm from Midterm solution:
 *
 * First, insert all flights into the two PQs. Set the current tally to zero. Peek at the top of both PQs.
 * Remove the smaller one. If it came from the start PQ, then add the number of passengers to the tally. If
 * the tally is larger than it has ever been, record that as best. If it came from the end PQ, subtract from the
 * tally. Return best.
 */
public class FlightSolver {
    PriorityQueue<Flight> start, end;
    Comparator<Flight> startComparator = (i, j) -> i.startTime() - j.startTime();
    Comparator<Flight> endComparator = (i, j) -> i.endTime() - j.endTime();

    public FlightSolver(ArrayList<Flight> flights) {
        start = new PriorityQueue<>(flights.size(),startComparator);
        end = new PriorityQueue<>(flights.size(),endComparator);

        for (Flight f : flights) {
            start.add(f);
            end.add(f);
        }
    }

    public int solve() {
        Flight minStart, minEnd;
        int total = 0, best = 0;
        while(start.size() != 0 || end.size() != 0) {
            minStart = start.peek();
            minEnd = end.peek();
            if (minStart == null) {
                total -= minEnd.passengers();
                end.poll();
            }
            else if (minEnd == null) {
                total += minStart.passengers();
                start.poll();
            }
            else if (minStart.startTime() <= minEnd.endTime()) {
                total += minStart.passengers();
                start.poll();
            }
            else {
                total -= minEnd.passengers();
                end.poll();
            }

            if (total > best)
                best = total;
        }
        return best;
    }

}
