package spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import core.Spell;

public class Feuerwerksbombardement extends Spell{

	int count = 12;
	public Feuerwerksbombardement() {
		steprange = 200;
		cooldown = 2;
		name = "§eFeuerball";
		speed = 1;
		
		hitPlayer = true;
		hitBlock = true;
		
		
		
	}
	 Firework f;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
	 f = (Firework) loc.getWorld().spawn(caster.getEyeLocation(), Firework.class);
		
		FireworkMeta fm = f.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder()
				.flicker(false)
				.trail(false)				
				.with(Type.BALL)
				.withColor(Color.fromBGR(randInt(0,255), randInt(0,255), randInt(0,255)))
				.withFade(Color.fromBGR(randInt(0,255), randInt(0,255), randInt(0,255)))
				.build());
		fm.setPower(3);
		
		f.setFireworkMeta(fm);
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityHit(Entity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
