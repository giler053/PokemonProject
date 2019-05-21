package battle.event;

import battle.BATTLE_PARTY;
import battle.animation.BattleAnimation;
import com.badlogic.gdx.graphics.Texture;

import aurelienribon.tweenengine.TweenManager;
import ui.DialogueBox;
import ui.StatusBox;

/**
 * @author hydrozoa
 */
public interface BattleEventPlayer {
	
	public void playBattleAnimation(BattleAnimation animation, BATTLE_PARTY party);
	
	public void setPokemonSprite(Texture region, BATTLE_PARTY party);
	
	public DialogueBox getDialogueBox();
	
	public StatusBox getStatusBox(BATTLE_PARTY party);
	
	public BattleAnimation getBattleAnimation();
	
	public TweenManager getTweenManager();
	
	public void queueEvent(BattleEvent event);
}
