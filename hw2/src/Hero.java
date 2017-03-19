import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;

public class Hero extends Sprite implements Character {

	public static int currentImage;
	
	public static int idleLeft[] = new int[8];
    public static int idleRight[] = new int[8];
    public static int walkLeft[] = new int[8];
    public static int walkRight[] = new int[8];
    	
    // Character Specifics
    int fps = 6;
    long speed = 1;
    
    // Strings
	String direction = "right";
	String lastDirection = "right";
	
	// Booleans
    boolean shouldMove = false; 
    boolean keyDown = false;
    
    // Counters
    int walkCounter = 0;
    int idleCounter = 0;
    int imgCounter = 0;
   
    
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
	    
	    width = spriteSize[0];
	    height = spriteSize[1];
	    
		setImage(idleRight[0]);
	}
	
	public void setSpeed(long bgSpeed){
		speed = bgSpeed;
	}
	
	public void draw(GL2 gl) {
		Main.glDrawSprite(gl, getImage(), x, y, width, height);
	}

	@Override
	public void update(GL2 gl) {
		
		shouldMove = checkKeyDown();
		keyDown = false;
				
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
	
	
	public void checkBackground(Background bg) {
		background = bg;
	}
	
	private boolean checkKeyDown() {
		if(keyDown == true)
			return true;
		return false;
	}

	public void keyDown(String key) {
		direction = key;
		keyDown = true;
		
		/**
		if(direction == "up") {
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
		
		
		if(direction == "down") {
			if (getY() < 300) 
    			moveY(speed);
    		else {
        		if(background.getY() >= -1959)
        			background.moveY(-speed);
        		else {
        			background.setY(-1960);
        			
        			if(getY() < 571)
        				moveY(speed);
        		}
    		}
		}
		**/
		
		if(direction == "left") {
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
		
		if(direction == "right") {
			if (getX() < 400)
    			moveX(speed);
    		else {
        		if(background.getX() >= -1759)
        			background.moveX(-speed);
        		else {
        			background.setX(-1760);
        			
        			if(getX() < 768)
        				moveX(speed);
        		}
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


}
