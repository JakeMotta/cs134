package sprites;

import com.jogamp.opengl.GL2;

import main.Main;

public class Block extends Sprite implements Actor {

	private static int blockImg;
	
	private int hp = 100;
        
	public Block(int myX, int myY, int[] spriteSize, GL2 gl) {
		super(myX, myY, spriteSize, gl);

		width = height = 64;
		blockImg = Main.glTexImageTGAFile(gl, "Sprites/Block/block.tga", spriteSize);
		
		setImage(blockImg);
	}

	@Override
	public void update(GL2 gl) {
		
		if(hp <= 0)
			isAlive = false;
			
		setImage(blockImg);
		draw(gl);

	}
	
	public void setHP(int dmg) {
		hp -= dmg;
	}
	
	public int getHP() {
		return hp;
	}

	public String getShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
