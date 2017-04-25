package sprites;

import com.jogamp.opengl.GL2;

import main.Main;

public class Ramona extends Sprite implements Actor {

	private static int runLeft[] = new int[8];
	private static int runRight[] = new int[8];
	private static int jumpLeft[] = new int[7];
	private static int jumpRight[] = new int[7];
	private static int idleL;
	private static int idleR;
	
	private static int currentImage;
        
    private int runCounter;
    private int jumpCounter;
    private int fps;
    
	public Ramona(int myX, int myY, int[] spriteSize, GL2 gl) {
		super(myX, myY, spriteSize, gl);
		
		runLeft[0] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun1.tga", spriteSize);
		runLeft[1] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun2.tga", spriteSize);
		runLeft[2] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun3.tga", spriteSize);
		runLeft[3] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun4.tga", spriteSize);
		runLeft[4] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun5.tga", spriteSize);
		runLeft[5] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun6.tga", spriteSize);
		runLeft[6] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun7.tga", spriteSize);
		runLeft[7] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunLeft/lrun8.tga", spriteSize);
		
		runRight[0] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun1.tga", spriteSize);
		runRight[1] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun2.tga", spriteSize);
		runRight[2] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun3.tga", spriteSize);
		runRight[3] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun4.tga", spriteSize);
		runRight[4] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun5.tga", spriteSize);
		runRight[5] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun6.tga", spriteSize);
		runRight[6] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun7.tga", spriteSize);
		runRight[7] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/RunRight/rrun8.tga", spriteSize);
		
		jumpLeft[0] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL1.tga", spriteSize);
		jumpLeft[1] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL2.tga", spriteSize);
		jumpLeft[2] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL3.tga", spriteSize);
		jumpLeft[3] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL4.tga", spriteSize);
		jumpLeft[4] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL5.tga", spriteSize);
		jumpLeft[5] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL6.tga", spriteSize);
		jumpLeft[6] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpLeft/jumpL7.tga", spriteSize);
		
		jumpRight[0] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR1.tga", spriteSize);
		jumpRight[1] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR2.tga", spriteSize);
		jumpRight[2] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR3.tga", spriteSize);
		jumpRight[3] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR4.tga", spriteSize);
		jumpRight[4] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR5.tga", spriteSize);
		jumpRight[5] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR6.tga", spriteSize);
		jumpRight[6] = Main.glTexImageTGAFile(gl, "Sprites/Ramona/JumpRight/jumpR7.tga", spriteSize);
		
		idleL = Main.glTexImageTGAFile(gl, "Sprites/Ramona/Idle/idleL.tga", spriteSize);
		idleR = Main.glTexImageTGAFile(gl, "Sprites/Ramona/Idle/idleR.tga", spriteSize);
		
		setImage(runRight[0]);
		
		direction = "right";
		runCounter = 0;
		jumpCounter = 0;
		fps = 6;
		speed = 3;
	}

	@Override
	public void update(GL2 gl) {
		
		if(Main.hero.getX() > this.getX() + 100) {
			shouldMove = true;
			direction = "right";
			moveX(speed);
		}
		if(Main.hero.getX() < this.getX() - 100) {
			shouldMove = true;
			direction = "left";
			moveX(-speed);
		}
		
		if (shouldMove) {
			// Play walking animation
		    if (runCounter >= (fps*runRight.length)-2)
		    	runCounter = -1;
		    else
		    	runCounter++;
		
		    if(runCounter % fps == 0)
		    	getRunImage(direction, runCounter/fps);
		} else {

			if(Main.hero.attacking) {
				if (jumpCounter >= (fps*jumpRight.length)-2)
					jumpCounter = -1;
			    else
			    	jumpCounter++;
			
			    if(jumpCounter % fps == 0)
			    	getJumpImage(direction, jumpCounter/fps);
			} else {
				getIdleImage(direction);
			}
		}
			
		
		shouldMove = false;
		
		setImage(currentImage);
		draw(gl);

	}
	
	private void getRunImage(String dir, int count) {
		switch (dir) {
		case "left":
			currentImage = runLeft[count];
			break;
		case "right":
			currentImage = runRight[count];
			break;
    	}
	}
	
	private void getIdleImage(String dir) {
		if(dir == "left")
			currentImage = idleL;
		else
			currentImage = idleR;
	}
	
	private void getJumpImage(String dir, int count) {
		switch (dir) {
		case "left":
			currentImage = jumpLeft[count];
			break;
		case "right":
			currentImage = jumpRight[count];
			break;
    	}
	}

	@Override
	public String getShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
