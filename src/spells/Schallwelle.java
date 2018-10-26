package spells;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import core.ParUtils;
import core.Spell;
import net.minecraft.server.v1_13_R2.Blocks;
import net.minecraft.server.v1_13_R2.Items;

public class Schallwelle extends Spell{
	
	public Schallwelle() {
		cooldown = 2;
		name = "§eSchallwelle";
		speed = 1;
		steprange =30;
		hitPlayer = true;
		
	}
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		//ParUtils.createFlyingParticle(Particle.BUBBLE_POP, loc,0, 0, 0, 1, 2, loc.getDirection().multiply(-1));
		
		
	}

	@Override
	public void onPlayerHit(Player p) {
		
		
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
		Bukkit.broadcastMessage("HitBLock");
		
		//bounce();
		
	}


	@Override
	public void onDeath() {
		Bukkit.broadcastMessage("Ded");
	}


	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

}
