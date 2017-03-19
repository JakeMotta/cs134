import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.opengl.*;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class delete {
	
	// The previous frame's keyboard state.
    public static boolean kbPrevState[] = new boolean[256];

    // The current frame's keyboard state.
    public static boolean kbState[] = new boolean[256];
	
    // Set this to true to make the game loop exit.
    private static boolean shouldExit;
    
    // Position of the sprite.
    private static int[] spritePos = new int[] { 10, 50 };

    // Texture for the sprite.
    private static int spriteTex;
    
    // Size of the sprite.
    private static int[] spriteSize = new int[2];
    
    // background sprite
    private static int bgTexture;
    private static int bgTexture2;
    
    private static int currentImage;

    private static int walkUp[] = new int[3];
    private static int walkDown[] = new int[3];
    private static int walkLeft[] = new int[3];
    private static int walkRight[] = new int[3];
        
    public static void main(String[] args) {
        
    	GLProfile gl2Profile;
        
    	try {
	        // Make sure we have a recent version of OpenGL
	        gl2Profile = GLProfile.get(GLProfile.GL2);
	    }
	    catch (GLException ex) {
	        System.out.println("OpenGL max supported version is too low.");
	        System.exit(1);
	        return;
	    }
    	
    	// Create the window and OpenGL context.
	    GLWindow window = GLWindow.create(new GLCapabilities(gl2Profile));
	    window.setSize(800, 600);
	    window.setTitle("Magicarp Used Fly");
	    window.setVisible(true);
	    window.setDefaultCloseOperation(
	            WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
	    window.addKeyListener(new KeyListener() {
	        @Override
	        public void keyPressed(KeyEvent keyEvent) {
	            if (keyEvent.isAutoRepeat()) {
	                return;
	            }
	            kbState[keyEvent.getKeyCode()] = true;
	        }
	
	        @Override
	        public void keyReleased(KeyEvent keyEvent) {
	            if (keyEvent.isAutoRepeat()) {
	                return;
	            }
	            kbState[keyEvent.getKeyCode()] = false;
	        }
	    });
	    
	    // Setup OpenGL state.
	    window.getContext().makeCurrent();
	    GL2 gl = window.getGL().getGL2();
	    gl.glViewport(0, 0, 800, 600);
	    gl.glMatrixMode(GL2.GL_PROJECTION);
	    gl.glOrtho(0, 800, 600, 0, 0, 100);
	    gl.glEnable(GL2.GL_TEXTURE_2D);
	    gl.glEnable(GL2.GL_BLEND);
	    gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        
		// Game initialization goes here.
		spriteTex = glTexImageTGAFile(gl, "wr1.tga", spriteSize);
		
		bgTexture = glTexImageTGAFile(gl, "background.tga", spriteSize);
		bgTexture2 = glTexImageTGAFile(gl, "background2.tga", spriteSize);
		
		walkUp[0] = glTexImageTGAFile(gl, "wu1.tga", spriteSize);
        walkUp[1] = glTexImageTGAFile(gl, "wu2.tga", spriteSize);
        walkUp[2] = glTexImageTGAFile(gl, "wu3.tga", spriteSize);
       
        walkDown[0] = glTexImageTGAFile(gl, "wd1.tga", spriteSize);
        walkDown[1] = glTexImageTGAFile(gl, "wd2.tga", spriteSize);
        walkDown[2] = glTexImageTGAFile(gl, "wd3.tga", spriteSize);
        
        walkLeft[0] = glTexImageTGAFile(gl, "wl1.tga", spriteSize);
        walkLeft[1] = glTexImageTGAFile(gl, "wl2.tga", spriteSize);
        walkLeft[2] = glTexImageTGAFile(gl, "wl3.tga", spriteSize);
        
        walkRight[0] = glTexImageTGAFile(gl, "wr1.tga", spriteSize);
        walkRight[1] = glTexImageTGAFile(gl, "wr2.tga", spriteSize);
        walkRight[2] = glTexImageTGAFile(gl, "wr3.tga", spriteSize);
			
        // The game loop
        long lastFrameNS;
        long curFrameNS = System.nanoTime();
        
        String direction = "right";
        boolean shouldMove = false; 
        int walkCounter = 0;
        
        currentImage = walkRight[0];
        
        int bgX = 0;
        int bgY = 0;
        
        Hero hero = new Hero(100, 100, spriteSize, 0);
        
        System.out.println("X: " + hero.getX());
        System.out.println("Y: " + hero.getY());
        hero.setX(spritePos[0]);
        hero.setY(spritePos[1]);
        
        System.out.println("X: " + hero.getX());
        System.out.println("Y: " + hero.getY());
        
        while (!shouldExit) {
        	
            System.arraycopy(kbState, 0, kbPrevState, 0, kbState.length);
            lastFrameNS = curFrameNS;
            curFrameNS = System.nanoTime();
            long deltaTimeMS = (curFrameNS - lastFrameNS) / 1000000;
            
            long speed = (1 * deltaTimeMS)/2;

            // Actually, this runs the entire OS message pump.
            window.display();
            
            if (!window.isVisible()) {
                shouldExit = true;
                break;
            }
            
            shouldMove = false;

            // Game logic goes here.
            if (kbState[KeyEvent.VK_ESCAPE]) {
                shouldExit = true;
            }
            
            if (kbState[KeyEvent.VK_UP]) {   
            		direction = "up";
            		shouldMove = true;
            		if (hero.getY() > 300) {
            			hero.moveY(-speed);
            		}
            		else {
	            		if(bgY < 0)
	            			bgY += ((speed * deltaTimeMS)/2);
	            		else {
	            			bgY = 0;
	            			
	            			if(hero.getY() > 0)
	            				hero.moveY(-speed);
	            		}
            		}
            }

            if (kbState[KeyEvent.VK_DOWN]) {
            		direction = "down";
            		shouldMove = true;
            		
            		if (hero.getY() < 300) {
            			hero.moveY(speed);
            		}
            		else {
	            		if(bgY >= -679)
	            			bgY -= ((speed * deltaTimeMS)/2);
	            		else {
	            			bgY = -680;   
	            			
	            			if(hero.getY() < 571)
	            				hero.moveY(speed);
	            		}
            		}
            }
            

            if (kbState[KeyEvent.VK_LEFT]) {
	            	direction = "left";
	        		shouldMove = true;
        		
	        		if (hero.getX() > 400) {
            			hero.moveX(-speed);
            		}
	        		else {
	            		if(bgX < 0)
	            			bgX += ((speed * deltaTimeMS)/2);
	            		else {
	            			bgX = 0;
	            			
	            			if(hero.getX() > 0)
	            				hero.moveX(-speed);
	            		}
	        		}
            }
            
            if (kbState[KeyEvent.VK_RIGHT]) {
            		direction = "right";
            		shouldMove = true;
            		
            		if (hero.getX() < 400) {
            			hero.moveX(speed);
            		}
            		else {
	            		if(bgX >= -359)
	            			bgX -= ((speed * deltaTimeMS)/2);
	            		else {
	            			bgX = -360;
	            			
	            			if(hero.getX() < 768)
	            				hero.moveX(speed);
	            		}
            		}
            }
            
            
            // increase or reset walk counter
            if (walkCounter >= 60)
            	walkCounter = 0;
            else
            	walkCounter++;

            if((walkCounter % 30 == 0)) { 
	            if (shouldMove == true) {
	            	getCurrentImage(direction, walkCounter/30);
	            }
	            else {
	            	getCurrentImage(direction, 0);
	            }
            }
            
            //if(AABBintersect(camera,sprite)){
            //	drawsprite(sprite);
            //else
            //	do nothing
              
            //spritePos[0] = hero.getX();
            //spritePos[1] = hero.getY();
                        
            gl.glClearColor(0, 0, 0, 1);
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
            
            for (int i = 0; i < 40; i++) {
            	for(int j = 0; j < 40; j++) {
            		if(i%2 == 0) {
            			glDrawSprite(gl, bgTexture, (bgX + j * spriteSize[0]), (bgY + i * spriteSize[1]), spriteSize[0], spriteSize[1]);
            		}
            		else {
            			glDrawSprite(gl, bgTexture2, (bgX + j * spriteSize[0]), (bgY + i * spriteSize[1]), spriteSize[0], spriteSize[1]);
            		}
            	}
            }
            
            // Draw the sprite
            glDrawSprite(gl, currentImage, hero.getX(), hero.getY(), spriteSize[0], spriteSize[1]);
        }
    }
    
    // axis aligned bounding box
    /**
    public static boolean AABBIntersect(GL2 gl, int bgTexture3) {
	     // box1 to the right
	     if (gl.getX() > bgTexture3 + bgTexture3.getWidth()) {
	    	 return false;
	     }
	     
	     // box1 to the left
	     if (gl.getX() + gl.getWidth() < bgTexture3.getX()) {
	    	 return false;
	     }
	     
	     // box1 below
	     if (gl.getY() > bgTexture3.getY() + bgTexture3.getHeight()) {
	    	 return false;
	     }
	     
	     // box1 above
	     if (gl.getY() + gl.getHeight() < bgTexture3.getY()) {
	    	 return false;
	     }
	     
	     return true;
    }
    **/
    public static void getCurrentImage(String direction, int walkCounter) {
    	switch (direction) {
		case "up":
			currentImage = walkUp[walkCounter];
			break;
		case "down":
			currentImage = walkDown[walkCounter];
			break;
		case "left":
			currentImage = walkLeft[walkCounter];
			break;
		case "right":
			currentImage = walkRight[walkCounter];
			break;
    	}
    }

    // Load a file into an OpenGL texture and return that texture.
    public static int glTexImageTGAFile(GL2 gl, String filename, int[] out_size) {
        final int BPP = 4;

        DataInputStream file = null;
        try {
            // Open the file.
            file = new DataInputStream(new FileInputStream(filename));
        } catch (FileNotFoundException ex) {
            System.err.format("File: %s -- Could not open for reading.", filename);
            return 0;
        }

        try {
            // Skip first two bytes of data we don't need.
            file.skipBytes(2);

            // Read in the image type.  For our purposes the image type
            // should be either a 2 or a 3.
            int imageTypeCode = file.readByte();
            if (imageTypeCode != 2 && imageTypeCode != 3) {
                file.close();
                System.err.format("File: %s -- Unsupported TGA type: %d", filename, imageTypeCode);
                return 0;
            }

            // Skip 9 bytes of data we don't need.
            file.skipBytes(9);

            int imageWidth = Short.reverseBytes(file.readShort());
            int imageHeight = Short.reverseBytes(file.readShort());
            int bitCount = file.readByte();
            file.skipBytes(1);

            // Allocate space for the image data and read it in.
            byte[] bytes = new byte[imageWidth * imageHeight * BPP];

            // Read in data.
            if (bitCount == 32) {
                for (int it = 0; it < imageWidth * imageHeight; ++it) {
                    bytes[it * BPP + 0] = file.readByte();
                    bytes[it * BPP + 1] = file.readByte();
                    bytes[it * BPP + 2] = file.readByte();
                    bytes[it * BPP + 3] = file.readByte();
                }
            } else {
                for (int it = 0; it < imageWidth * imageHeight; ++it) {
                    bytes[it * BPP + 0] = file.readByte();
                    bytes[it * BPP + 1] = file.readByte();
                    bytes[it * BPP + 2] = file.readByte();
                    bytes[it * BPP + 3] = -1;
                }
            }

            file.close();

            // Load into OpenGL
            int[] texArray = new int[1];
            gl.glGenTextures(1, texArray, 0);
            int tex = texArray[0];
            gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
            gl.glTexImage2D(
                    GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, imageWidth, imageHeight, 0,
                    GL2.GL_BGRA, GL2.GL_UNSIGNED_BYTE, ByteBuffer.wrap(bytes));
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

            out_size[0] = imageWidth;
            out_size[1] = imageHeight;
            return tex;
        }
        catch (IOException ex) {
            System.err.format("File: %s -- Unexpected end of file.", filename);
            return 0;
        }
    }

    public static void glDrawSprite(GL2 gl, int tex, int x, int y, int w, int h) {
        gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
        gl.glBegin(GL2.GL_QUADS);
        {
            gl.glColor3ub((byte)-1, (byte)-1, (byte)-1);
            gl.glTexCoord2f(0, 1);
            gl.glVertex2i(x, y);
            gl.glTexCoord2f(1, 1);
            gl.glVertex2i(x + w, y);
            gl.glTexCoord2f(1, 0);
            gl.glVertex2i(x + w, y + h);
            gl.glTexCoord2f(0, 0);
            gl.glVertex2i(x, y + h);
        }
        gl.glEnd();
    }
}
