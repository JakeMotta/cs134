import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.jogamp.opengl.GL2;

public class Sprite {

	public int x, y, width, height, image;

	public Sprite(int myX, int myY, int[] spriteSize) {
		x = myX;
		y = myY;
		width = spriteSize[0];
		height = spriteSize[1];
	}

	public void setX(int a) {
		x = a;
	}

	public void setY(int b) {
		y = b;
	}

	public void setImage(int i) {
		image = i;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getImage() {
		return image;
	}

	public void moveX(long l) {
		x += l;
	}

	public void moveY(long l) {
		y += l;
	}
}
