import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;

public class Hero extends Sprite implements Character {

	public static int currentImage;
	
	public static int idleLeft[] = new int[8];
    public static int idleRight[] = new int[8];
    public static int walkLeft[] = new int[8];
    public static int walkRight[] = new int[8];
    public static int lPunch[] = new int[6];
    public static int rPunch[] = new int[6];
    
    // Character Specifics
    int fps = 6;
    long speed = 1;
    
    // Strings
	String direction = "right";
	String lastDirection = "right";
	
	// Booleans
    boolean shouldMove = false; 
    
    // Counters
    int walkCounter = 0;
    int idleCounter = 0;
    int imgCounter = 0;
    int punchCounter = 0;
   
    
    Background background;

	public Hero(int myX, int myY, int[] spriteSize, GL2 gl) {
		super(myX, myY, spriteSize);
	    
		idleLeft[0] = Main.glTexImageTGAFile(gl, "l_idle1.tga", spriteSize);
		idleLeft[1] = Main.glTexImageTGAFile(gl, "l_idle2.tga", spriteSize);
		idleLeft[2] = Main.glTexImageTGAFile(gl, "l_idle3.tga", spriteSize);
		idleLeft[3] = Main.glTexImageTGAFile(gl, "l_idle4.tga", spriteSize);
		idleLeft[4] = Main.glTexImageTGAFile(gl, "l_idle5.tga", spriteSize);
		idleLeft[5] = Main.glTexImageTGAFile(gl, "l_idle6.tga", spriteSize);
		idleLeft[6] = Main.glTexImageTGAFile(gl, "l_idle7.tga", spriteSize);
		idleLeft[7] = Main.glTexImageTGAFile(gl, "l_idle8.tga", spriteSize);
		
		idleRight[0] = Main.glTexImageTGAFile(gl, "r_idle1.tga", spriteSize);
		idleRight[1] = Main.glTexImageTGAFile(gl, "r_idle2.tga", spriteSize);
		idleRight[2] = Main.glTexImageTGAFile(gl, "r_idle3.tga", spriteSize);
		idleRight[3] = Main.glTexImageTGAFile(gl, "r_idle4.tga", spriteSize);
		idleRight[4] = Main.glTexImageTGAFile(gl, "r_idle5.tga", spriteSize);
		idleRight[5] = Main.glTexImageTGAFile(gl, "r_idle6.tga", spriteSize);
		idleRight[6] = Main.glTexImageTGAFile(gl, "r_idle7.tga", spriteSize);
		idleRight[7] = Main.glTexImageTGAFile(gl, "r_idle8.tga", spriteSize);
		
		walkLeft[0] = Main.glTexImageTGAFile(gl, "lrun1.tga", spriteSize);
	    walkLeft[1] = Main.glTexImageTGAFile(gl, "lrun2.tga", spriteSize);
	    walkLeft[2] = Main.glTexImageTGAFile(gl, "lrun3.tga", spriteSize);
		walkLeft[3] = Main.glTexImageTGAFile(gl, "lrun4.tga", spriteSize);
	    walkLeft[4] = Main.glTexImageTGAFile(gl, "lrun5.tga", spriteSize);
	    walkLeft[5] = Main.glTexImageTGAFile(gl, "lrun6.tga", spriteSize);
		walkLeft[6] = Main.glTexImageTGAFile(gl, "lrun7.tga", spriteSize);
	    walkLeft[7] = Main.glTexImageTGAFile(gl, "lrun8.tga", spriteSize);
	    
	
	    walkRight[0] = Main.glTexImageTGAFile(gl, "rrun1.tga", spriteSize);
	    walkRight[1] = Main.glTexImageTGAFile(gl, "rrun2.tga", spriteSize);
	    walkRight[2] = Main.glTexImageTGAFile(gl, "rrun3.tga", spriteSize);
	    walkRight[3] = Main.glTexImageTGAFile(gl, "rrun4.tga", spriteSize);
	    walkRight[4] = Main.glTexImageTGAFile(gl, "rrun5.tga", spriteSize);
	    walkRight[5] = Main.glTexImageTGAFile(gl, "rrun6.tga", spriteSize);
	    walkRight[6] = Main.glTexImageTGAFile(gl, "rrun7.tga", spriteSize);
	    walkRight[7] = Main.glTexImageTGAFile(gl, "rrun8.tga", spriteSize);
	    
	    lPunch[0] = Main.glTexImageTGAFile(gl, "lpunch1.tga", spriteSize);
	    lPunch[1] = Main.glTexImageTGAFile(gl, "lpunch2.tga", spriteSize);
	    lPunch[2] = Main.glTexImageTGAFile(gl, "lpunch3.tga", spriteSize);
	    lPunch[3] = Main.glTexImageTGAFile(gl, "lpunch4.tga", spriteSize);
	    lPunch[4] = Main.glTexImageTGAFile(gl, "lpunch5.tga", spriteSize);
	    lPunch[5] = Main.glTexImageTGAFile(gl, "lpunch6.tga", spriteSize);
	    
	    rPunch[0] = Main.glTexImageTGAFile(gl, "rpunch1.tga", spriteSize);
	    rPunch[1] = Main.glTexImageTGAFile(gl, "rpunch2.tga", spriteSize);
	    rPunch[2] = Main.glTexImageTGAFile(gl, "rpunch3.tga", spriteSize);
	    rPunch[3] = Main.glTexImageTGAFile(gl, "rpunch4.tga", spriteSize);
	    rPunch[4] = Main.glTexImageTGAFile(gl, "rpunch5.tga", spriteSize);
	    rPunch[5] = Main.glTexImageTGAFile(gl, "rpunch6.tga", spriteSize);
	    
	    width = spriteSize[0];
	    height = spriteSize[1];
	    
		setImage(idleRight[0]);
	}
	
	// Set hero's speed
	public void setSpeed(long bgSpeed){
		speed = bgSpeed;
	}
	
	// Quick draw reference
	public void draw(GL2 gl) {
		Main.glDrawSprite(gl, getImage(), x, y, width, height);
	}

	@Override
	public void update(GL2 gl) {
		System.out.println("ShouldMove: " + shouldMove);
		shouldMove = false;
		
		if (shouldMove == true) {
			
			// Play walking animation
		    if (walkCounter >= (fps*walkRight.length)-2)
		        walkCounter = -1;
		    else
		        walkCounter++;
		
		    if(walkCounter % fps == 0)
		    	getWalkingImage(direction, walkCounter/fps);
		} else {
			walkCounter = -1;

			// Play Idle animation
			if(idleCounter >= (fps*idleRight.length)-2)
				idleCounter = -1;
			else
				idleCounter++;
				 
			if(idleCounter % fps == 0)
		    	getIdleImage(direction, idleCounter/fps);
		}
	        
	    setImage(currentImage);
	    
	    draw(gl);
	}
	
	
	// Gets background info from Main
	public void checkBackground(Background bg) {
		background = bg;
	}
	
	public void keyDown(String key) {
		
		if(key == "up" || key == "down" || key == "left" || key == "right") {
			direction = key;
			shouldMove = true;
		}
		else
			shouldMove = false;
		
		if(shouldMove == true) {
			if(key == "up") {
				if (getY() > 300) 
	    			moveY(-speed);
	    		else {
	        		if(background.getY() < 0)
	        			background.moveY(speed);
	        		else {
	        			background.setY(0);
	        			
	        			if(getY() > 0)
	        				moveY(-speed);
	        		}
	    		}
			}
			
			
			if(key == "down") {
				if (getY() < 300) 
	    			moveY(speed);
	    		else {
	        		if(background.getY() >= -615)
	        			background.moveY(-speed);
	        		else {
	        			background.setY(-616);
	        			
	        			if(getY() < 571)
	        				moveY(speed);
	        		}
	    		}
			}
			
			
			if(key == "left") {
				if (getX() > 400)
	    			moveX(-speed);
	    		else {
	        		if(background.getX() < 0)
	        			background.moveX(speed);
	        		else {
	        			background.setX(0);
	        			
	        			if(getX() > 0)
	        				moveX(-speed);
	        		}
	    		}
			}
			
			if(key == "right") {
				if (getX() < 400)
	    			moveX(speed);
	    		else {
	        		if(background.getX() >= -1695)
	        			background.moveX(-speed);
	        		else {
	        			background.setX(-1696);
	        			
	        			if(getX() < 768)
	        				moveX(speed);
	        		}
	    		}
			}
		}
		else {
			if(key == "z") {
				// Play punch animation
			    if (punchCounter >= (fps*rPunch.length)-2)
			        punchCounter = -1;
			    else
			    	punchCounter++;
			
			    if(punchCounter % fps == 0)
			    	getPunchImage(direction, punchCounter/fps);
			}
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


}
