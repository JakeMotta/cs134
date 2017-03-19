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

public class delete2 {
	
    // Set this to true to make the game loop exit.
    private static boolean shouldExit;
    
    // Position of the sprite.
    private static int[] spritePos = new int[] { 10, 50 };
    
    // Size of the sprite.
    private static int[] spriteSize = new int[2];
        
    //static GL2 gl;
       
    public static void main(String[] args) {
          
		// Game initialization goes here.
    	
    	Window window = new Window();
    	//gl = window.gl;
			
        // The game loop
        long lastFrameNS;
        long curFrameNS = System.nanoTime();
        
        int bgX = 0;
        int bgY = 0;
        
        Hero hero = new Hero(100, 100, spriteSize, 0);
		Sprite background[][] = null;
		
        hero.setX(spritePos[0]);
        hero.setY(spritePos[1]);
        
        background = GenerateMap();
        
        while (!shouldExit) {
        	
            System.arraycopy(Window.kbState, 0, Window.kbPrevState, 0, Window.kbState.length);
            lastFrameNS = curFrameNS;
            curFrameNS = System.nanoTime();
            long deltaTimeMS = (curFrameNS - lastFrameNS) / 1000000;
            
            long bgSpeed = (1 * deltaTimeMS)/2;
            
            // Actually, this runs the entire OS message pump.
            window.myWindow.display();
            
            if (!window.myWindow.isVisible()) {
                shouldExit = true;
                break;
            }

            // Game logic goes here.
            if (window.kbState[KeyEvent.VK_ESCAPE]) {
                shouldExit = true;
            }
            
            if (window.kbState[KeyEvent.VK_UP]) {   
            	hero.keyDown("up");
            		
	            if(bgY < 0)
	            	bgY += bgSpeed;
	            else
	            	bgY = 0;
            }

            if (window.kbState[KeyEvent.VK_DOWN]) {
            	hero.keyDown("down");
            		
	            if(bgY >= -679)
	            	bgY -= bgSpeed;
	            else 
	            	bgY = -680;   
            }
            
            if (window.kbState[KeyEvent.VK_LEFT]) {
            	hero.keyDown("left");

	        	if(bgX < 0)
	            	bgX += bgSpeed;
	        	else 
	            	bgX = 0; 	
            }
            
            if (window.kbState[KeyEvent.VK_RIGHT]) {
            	hero.keyDown("right");
            	
	            if(bgX >= -359)
	            	bgX -= bgSpeed;
	            else 
	            	bgX = -360;
            }
            
            hero.update();
            
            //if(AABBintersect(camera,sprite)){
            //	drawsprite(sprite);
            //else
            //	do nothing
              
            //spritePos[0] = hero.getX();
            //spritePos[1] = hero.getY();
            
            window.gl.glClearColor(0, 0, 0, 1);
            window.gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
                
            
            for (int i = 0; i < 40; i++) {
            	for(int j = 0; j < 40; j++) {
            		if(i%2 == 0) {
            			//glDrawSprite(gl, bgTexture, (bgX + j * spriteSize[0]), (bgY + i * spriteSize[1]), spriteSize[0], spriteSize[1]);
            			background[i][j].setX(bgX + j * spriteSize[0]);
            			background[i][j].setY(bgY + i * spriteSize[1]);
            			background[i][j].draw();
            		}
            		else {
            			//glDrawSprite(gl, bgTexture2, (bgX + j * spriteSize[0]), (bgY + i * spriteSize[1]), spriteSize[0], spriteSize[1]);
            			background[i][j].setX(bgX + j * spriteSize[0]);
            			background[i][j].setY(bgY + i * spriteSize[1]);
            			background[i][j].draw();
            		}
            	}
            }
            
            // Draw the sprite
            hero.draw();
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
    
    public static Sprite[][] GenerateMap() {
    	
		Sprite background[][] = new Sprite[40][40];
    	
    	for (int i = 0; i < 40; i++) {
        	for(int j = 0; j < 40; j++) {
        		if(i%2 == 0) {
        			background[i][j] = new Sprite(j * spriteSize[0], i * spriteSize[1], spriteSize, 0);
        			background[i][j].draw();
        		}
        		else {
        			background[i][j] = new Sprite(j * spriteSize[0], i * spriteSize[1], spriteSize, 1);
        			background[i][j].draw();
        		}
        	}
        }
    	
		return background;
    }

    /**
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
    **/
    
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
}
