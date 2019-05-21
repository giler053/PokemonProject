package model.actor;

import model.world.World;
import model.world.cutscene.CutscenePlayer;
import util.AnimationSet;

/**
 * @author hydrozoa
 */
public class PlayerActor extends Actor {
	
	private CutscenePlayer cutscenePlayer;

	public PlayerActor(World world, int x, int y, AnimationSet animations, CutscenePlayer cutscenePlayer) {
		super(world, x, y, animations);
		this.cutscenePlayer = cutscenePlayer;
	}
	
	public CutscenePlayer getCutscenePlayer() {
		return cutscenePlayer;
	}
}
