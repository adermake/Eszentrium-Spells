package spells.spells;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.util.Vector;

import esze.utils.Matrix;
import spells.spellcore.Spell;

public class TridentHover extends Spell {

	ArrayList<Trident> tridents = new ArrayList<Trident>();
	Location tloc;
	@Override
	public void setUp() {
	
		
		double x = 0;
		double y = 1;
		double z = 0;
		loc = caster.getLocation();
		Vector v = new Vector(x,y,z);
		Vector vec = Matrix.rotateMatrixVectorFunktion(v, loc.clone());
		Bukkit.broadcastMessage(""+vec);
		
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		
		
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
	public void onEntityHit(LivingEntity ent) {
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
