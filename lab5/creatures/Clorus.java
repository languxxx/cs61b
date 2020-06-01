package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
        if (energy < 0) energy = 0;
    }

    public void stay() {
        energy -= 0.01;
        if (energy < 0) energy = 0;
    }

    public Clorus replicate() {
        Clorus offspring = new Clorus(this.energy/2);
        this.energy = this.energy/2;
        return offspring;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> anyPlip = new ArrayDeque<>();
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction dir : neighbors.keySet()) {
            if (neighbors.get(dir).name() == "empty") {
                emptyNeighbors.addLast(dir);
            }
            else if (neighbors.get(dir).name() == "plip") {
                anyPlip.addLast(dir);
            }
        }

        if (emptyNeighbors.isEmpty()) { // FIXME
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (!anyPlip.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(anyPlip));
        }

        // Rule 3
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
        // Rule 4
        return new Action(Action.ActionType.MOVE,HugLifeUtils.randomEntry(emptyNeighbors));
    }
}
