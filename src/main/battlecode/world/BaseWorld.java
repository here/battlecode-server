package battlecode.world;

import battlecode.common.Team;
import battlecode.engine.GenericRobot;
import battlecode.engine.signal.Signal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class BaseWorld<WorldObject extends BaseObject> {
	protected int currentRound;  // do we need this here?? -- yes
    protected boolean running = true;  // do we need this here?? -- yes
    protected boolean wasBreakpointHit = false;
	protected Team winner = null;
    protected final String teamAName;
    protected final String teamBName;
    protected final Random randGen;
	protected int nextID;
	protected final ArrayList<Signal> signals;
	protected final long[][] archonMemory;
    protected final long[][] oldArchonMemory;
	protected final Map<Integer, WorldObject> gameObjectsByID;

	public BaseWorld(int seed, String teamA, String teamB, long [][] oldArchonMemory) {
		currentRound = -1;
        teamAName = teamA;
        teamBName = teamB;
		gameObjectsByID = new LinkedHashMap<Integer, WorldObject>();
		signals = new ArrayList<Signal>();
		randGen = new Random(seed);
        nextID = 1;
		archonMemory = new long[2][oldArchonMemory[0].length];
		this.oldArchonMemory = oldArchonMemory;
	}

	public int nextID() {
        return nextID++;
    }

    public int getCurrentRound() {
        return currentRound;
    }

	public String getTeamName(Team t) {
        switch (t) {
            case A:
                return teamAName;
            case B:
                return teamBName;
            case NEUTRAL:
                return "neutralplayer";
            default:
                return null;
        }
    }

    public Team getWinner() {
        return winner;
    }

    public boolean isRunning() {
        return running;
    }

	public WorldObject getObjectByID(int id) {
		return gameObjectsByID.get(id);
	}

	public GenericRobot getRobotByID(int id) {
		return (GenericRobot)getObjectByID(id);
	}

	public void addSignal(Signal s) {
        signals.add(s);
    }

	public void clearAllSignals() {
        signals.clear();
    }

	public Random getRandGen() {
        return randGen;
    }

    public void notifyBreakpoint() {
        wasBreakpointHit = true;
    }

    public boolean wasBreakpointHit() {
        return wasBreakpointHit;
    }

	public long[][] getArchonMemory() {
        return archonMemory;
    }

    public long[][] getOldArchonMemory() {
        return oldArchonMemory;
    }

    public void setArchonMemory(Team t, int archonID, long state) {
		archonMemory[t.ordinal()][archonID] = state;
    }

}