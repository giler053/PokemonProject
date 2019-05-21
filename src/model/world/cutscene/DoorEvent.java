package model.world.cutscene;


import model.world.Door;

/**
 * @author hydrozoa
 */
public class DoorEvent extends CutsceneEvent {

	private boolean opening;
	private Door door;
	
	private boolean finished = false;
	
	public DoorEvent(Door door, boolean opening) {
		this.door = door;
		this.opening = opening;
		
	}
	
	@Override
	public void begin(CutscenePlayer player) {
		super.begin(player);
		if (door.getState() == Door.STATE.OPEN && !opening) {
			door.close();
		} else if (door.getState() == Door.STATE.CLOSED && opening) {
			door.open();
		}
	}

	@Override
	public void update(float delta) {
		if (opening && door.getState() == Door.STATE.OPEN) {
			finished = true;
		} else if (!opening && door.getState() == Door.STATE.CLOSED) {
			finished = true;
		}
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void screenShow() {}

}
