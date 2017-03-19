import com.jogamp.opengl.GL2;

public class Background {
	
	// background sprite
    private int bgTexture;
    private int bgTexture2;
    
    private int x;
    private int y;
    private int width;
    private int height;
    private int dimension;
    
    private int startXTile = 0;
    private int startYTile = 0;
    private int endXTile = 0;
    private int endYTile = 0;

	public Background(int[] spriteSize, GL2 gl) {
		bgTexture = Main.glTexImageTGAFile(gl, "background.tga", spriteSize);
		bgTexture2 = Main.glTexImageTGAFile(gl, "background2.tga", spriteSize);
		
		width = height = dimension = 64;
		x = y = 0;
	}
	
	public void setX(int a) { x = a; }
	public void setY(int b) { y = b; }
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void moveX(long l) { x += l; }
	public void moveY(long l) { y += l; }
	
	public void draw(GL2 gl) {
		
		startXTile = (int) (Math.floor((-1*x)/width));
		startYTile = (int) Math.abs(Math.floor((-1*y)/height));
		
		for (int i = startYTile; i < startYTile + 11; i++) {
        	for(int j = startXTile; j < startXTile + 14; j++) {
        		if(i%2 == 0) {
        			Main.glDrawSprite(gl, bgTexture, (x + j * width), (y + i * height), width, height);
        		}
        		else {
        			Main.glDrawSprite(gl, bgTexture2, (x + j * width), (y + i * height), width, height);
        		}
        	}
        }
	}
	
}
