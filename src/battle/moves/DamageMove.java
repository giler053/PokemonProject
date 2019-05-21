package battle.moves;

import java.lang.reflect.InvocationTargetException;

import battle.BATTLE_PARTY;
import battle.BattleMechanics;
import battle.STAT;
import battle.animation.BattleAnimation;
import battle.animation.BlinkingAnimation;
import battle.event.AnimationBattleEvent;
import battle.event.BattleEventQueuer;
import battle.event.HPAnimationEvent;
import battle.event.TextEvent;
import com.badlogic.gdx.Gdx;
import model.Pokemon;

/**
 * We're going to do some real important shit around here Morty.
 * 
 * @author hydrozoa
 */
public class DamageMove extends Move {

	public DamageMove(MoveSpecification spec, Class<? extends BattleAnimation> clazz) {
		super(spec, clazz);
	}

	@Override
	public BattleAnimation animation() {
		try {
			return animationClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException	| NoSuchMethodException | SecurityException e) {
			System.err.println(animationClass.getName()+" does not seem to have a constructor");
			e.printStackTrace();
			Gdx.app.exit();
		}
		return null;
	}

	@Override
	public String message() {
		return null;
	}

	@Override
	public boolean isDamaging() {
		return true;
	}
	
	@Override
	public int useMove(BattleMechanics mechanics, Pokemon user, Pokemon target, BATTLE_PARTY party, BattleEventQueuer broadcaster) {
		int hpBefore = target.getCurrentHitpoints();
		int damage = super.useMove(mechanics, user, target, party, broadcaster);
		
		/* Broadcast animations */
		broadcaster.queueEvent(new AnimationBattleEvent(party, animation()));
		
		/* Broadcast blinking */
		broadcaster.queueEvent(new AnimationBattleEvent(BATTLE_PARTY.getOpposite(party), new BlinkingAnimation(1f, 5)));
		
		//float hpPercentage = ((float)target.getCurrentHitpoints())/(float)target.getStat(STAT.HITPOINTS);
		
		/* Broadcast HP change */
		broadcaster.queueEvent(
				new HPAnimationEvent(
						BATTLE_PARTY.getOpposite(party), 
						hpBefore,
						target.getCurrentHitpoints(), 
						target.getStat(STAT.HITPOINTS),
						0.5f));
		
		if (mechanics.hasMessage()) {
			broadcaster.queueEvent(new TextEvent(mechanics.getMessage(), 0.5f));
		}
		return damage;
	}

	@Override
	public Move clone() {
		return new DamageMove(spec, animationClass);
	}
}
