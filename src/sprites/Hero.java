package sprites;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;

import background.Background;
import main.Main;

public class Hero extends Sprite implements Actor {

	public static int currentImage;
	
	public static int idleLeft[] = new int[8];
    public static int idleRight[] = new int[8];
    public static int walkLeft[] = new int[8];
    public static int walkRight[] = new int[8];
    public static int lPunch[] = new int[6];
    public static int rPunch[] = new int[6];
    
    // Character Specifics
    private int fps = 6;
    
    // Strings
	String shape = "rect";
	String keyDown = null;
	
	// Booleans
    boolean attacking = false;
    
    // Counters
    int walkCounter = 0;
    int idleCounter = 0;
    int imgCounter = 0;
    public int punchCounter = 0;
    public int jumpCounter = 0;
    
    // hero offsets
    int bottomOffset; //Offset to make player look like he's on the ground
    int bottomOffsetTimer = 0; //Timer to allow Y offset
    int bottomBG = 0;
    
    int leftOffset = 60;
    int leftOffsetTimer = 0;
    int leftBG = 0;
    
	public Hero(int myX, int myY, int[] spriteSize, GL2 gl) {
		super(myX, myY, spriteSize, gl);

		idleLeft[0] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle1.tga", spriteSize);
		idleLeft[1] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle2.tga", spriteSize);
		idleLeft[2] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle3.tga", spriteSize);
		idleLeft[3] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle4.tga", spriteSize);
		idleLeft[4] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle5.tga", spriteSize);
		idleLeft[5] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle6.tga", spriteSize);
		idleLeft[6] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle7.tga", spriteSize);
		idleLeft[7] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/l_idle8.tga", spriteSize);
		
		idleRight[0] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle1.tga", spriteSize);
		idleRight[1] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle2.tga", spriteSize);
		idleRight[2] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle3.tga", spriteSize);
		idleRight[3] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle4.tga", spriteSize);
		idleRight[4] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle5.tga", spriteSize);
		idleRight[5] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle6.tga", spriteSize);
		idleRight[6] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle7.tga", spriteSize);
		idleRight[7] = Main.glTexImageTGAFile(gl, "Sprites/Hero/Idle/r_idle8.tga", spriteSize);
		
		walkLeft[0] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun1.tga", spriteSize);
	    walkLeft[1] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun2.tga", spriteSize);
	    walkLeft[2] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun3.tga", spriteSize);
		walkLeft[3] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun4.tga", spriteSize);
	    walkLeft[4] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun5.tga", spriteSize);
	    walkLeft[5] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun6.tga", spriteSize);
		walkLeft[6] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun7.tga", spriteSize);
	    walkLeft[7] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunLeft/lrun8.tga", spriteSize);
	
	    walkRight[0] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun1.tga", spriteSize);
	    walkRight[1] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun2.tga", spriteSize);
	    walkRight[2] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun3.tga", spriteSize);
	    walkRight[3] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun4.tga", spriteSize);
	    walkRight[4] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun5.tga", spriteSize);
	    walkRight[5] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun6.tga", spriteSize);
	    walkRight[6] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun7.tga", spriteSize);
	    walkRight[7] = Main.glTexImageTGAFile(gl, "Sprites/Hero/RunRight/rrun8.tga", spriteSize);
	    
	    lPunch[0] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchLeft/lpunch1.tga", spriteSize);
	    lPunch[1] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchLeft/lpunch2.tga", spriteSize);
	    lPunch[2] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchLeft/lpunch3.tga", spriteSize);
	    lPunch[3] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchLeft/lpunch4.tga", spriteSize);
	    lPunch[4] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchLeft/lpunch5.tga", spriteSize);
	    lPunch[5] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchLeft/lpunch6.tga", spriteSize);
	    
	    rPunch[0] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchRight/rpunch1.tga", spriteSize);
	    rPunch[1] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchRight/rpunch2.tga", spriteSize);
	    rPunch[2] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchRight/rpunch3.tga", spriteSize);
	    rPunch[3] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchRight/rpunch4.tga", spriteSize);
	    rPunch[4] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchRight/rpunch5.tga", spriteSize);
	    rPunch[5] = Main.glTexImageTGAFile(gl, "Sprites/Hero/PunchRight/rpunch6.tga", spriteSize);
	    
	    vsp = hsp = speed = 3;
		direction = "right";
	    width = spriteSize[0];
	    height = spriteSize[1];
	    keyDown = null;
	    shape = "rect";
	    isGrounded = false;
	    
		currentImage = idleRight[0];
	}
	
	// Set hero's speed
	public void setSpeed(long bgSpeed){
		speed = bgSpeed;
	}

	@Override
	public void update(GL2 gl) {

		checkCollision();
		
		if(isGrounded == false) {
			gravity();
			// update yOffset with regards to current gravity speed
			bottomOffset = (int) (11 + gravity);
		}
		
		//System.out.println("bOS: " + bottomBG + ", hY: " + getY());
		
		
		if (shouldMove) {
			// Play walking animation
		    if (walkCounter >= (fps*walkRight.length)-2)
		        walkCounter = -1;
		    else
		        walkCounter++;
		
		    if(walkCounter % fps == 0)
		    	getWalkingImage(direction, walkCounter/fps);
		} else {
			if(!attacking) {
				walkCounter = -1;
				
				// Reset attacks
				punchCounter = -1;
	
				// Play Idle animation
				if(idleCounter >= (fps*idleRight.length)-2)
					idleCounter = -1;
				else
					idleCounter++;
					 
				if(idleCounter % fps == 0)
			    	getIdleImage(direction, idleCounter/fps);
			}
		}
	        
	    setImage(currentImage);
	    
	    // Reset booleans
	    shouldMove = false;
	    attacking = false;
	    keyDown = null;
	    
	    draw(gl);
	}
	
	/**
	 * 
	 * Priority List:
	 * 1. Movement
	 * 2. Collision Detection
	 * 3. Collision Resolution
	 * 4. Hi Mom
	 * 
	 */
	
	public void checkCollision() {
		
		
		// Collidable tile below
        if(Main.background.levelCollision[sY()+1][sX()] != 0) {
        	
        	// Offset resolution
        	if(bottomOffsetTimer == 0)
        		bottomBG = getY()+bottomOffset;
        	bottomOffsetTimer++;
        	
        	// What happens when the hero is on the ground
        	if(getY() >= bottomBG) {
        		isGrounded = true;
        		gravityTimer = 0;
        		gravity = 1; // reset gravity back to 1
        		canJump = true;
        	}
        } else { // when hero is not on the ground
        	bottomOffsetTimer = 0;
        	isGrounded = false;
        	gravityTimer++;
        	canJump = false;
        }
        
        // Collidable tile to the right
        if(Main.background.levelCollision[sY()][sX()+1] != 0) {
        	canMoveRight = false;
        	System.out.println("collided right!");
        } else { 
        	canMoveRight = true;
        }
        
     // Collidable tile to the left
        if(Main.background.levelCollision[sY()][sX()] != 0) {
        	canMoveLeft = false;
        	System.out.println("collided left!");
        } else { 
        	canMoveLeft = true;
        }

        /**
        // Collidable tile to the left
        if(Main.background.levelCollision[sY()][sX()-1] != 0) {
        	
        	// Offset resolution
        	if(leftOffsetTimer == 0)
        		leftBG = getX()-leftOffset;
        	leftOffsetTimer++;
        	
        	// What happens the block is to the left
        	if(getX() <= leftBG) {
        		canMoveLeft = false;
        	}
        } else { 
        	canMoveLeft = true;
        	leftOffsetTimer = 0;
        }**/
        
	}
	
	public void gravity() {
		moveY(gravity);
		
		if(gravityTimer % 10 == 0)
			gravity++;
	}
		
	public void keyDown(String key) {
		keyDown = key;
		
		//System.out.println("shouldMove: " + shouldMove);

		// Direction keys
		if((keyDown == "up" || keyDown == "down" || keyDown == "left" || keyDown == "right")) {
			direction = keyDown;
			shouldMove = true;
		}
		else
			shouldMove = false;
	
		// Attack keys
		if(keyDown == "z") {
			attacking = true;
			shouldMove = false;
		}
		else
			attacking = false;
		
		// If movement keys are pressed		
			if(keyDown == "up" && canJump == true) {
				jumpCounter++;
				
				if(jumpCounter <= 1)
					if(getY() > 0)
						setY(getY()-64-bottomOffset);				
			} 

			if(keyDown == "left" && canMoveLeft == true)
				if (getX() > 0)
	    			moveX(-hsp);
			
			
			if(keyDown == "right" && canMoveRight == true) 
				if(getX() < Main.worldWidth-getWidth())
					moveX(hsp);			

		if(keyDown == "z") {
			// Play punch animation
			if (punchCounter >= (fps*rPunch.length)-2)
				punchCounter = -1;
			else
			   	punchCounter++;
			
			if(punchCounter % fps == 0) 
			   	getPunchImage(direction, punchCounter/fps);
		}
	}
	
	public static void getWalkingImage(String dir, int count) {
    	switch (dir) {
		case "left":
			currentImage = walkLeft[count];
			break;
		case "right":
			currentImage = walkRight[count];
			break;
    	}
    }    
	
	private void getIdleImage(String dir, int count) {
		switch (dir) {
		case "left":
			currentImage = idleLeft[count];
			break;
		case "right":
			currentImage = idleRight[count];
			break;
    	}
	}
	
	private void getPunchImage(String dir, int count) {
		switch (dir) {
		case "left":
			currentImage = lPunch[count];
			break;
		case "right":
			currentImage = rPunch[count];
			break;
    	}
	}
		
	public String getShape() {
		return shape;
	}
}
