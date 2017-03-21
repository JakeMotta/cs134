import com.jogamp.opengl.GL2;

public class Background {
	
	// background sprite
    //private int bgTexture;
    //private int bgTexture2;
    private int grass_top_left_corner;
    private int grass_top_right_corner;
    private int grass_top;
    private int grass_left;
    private int grass_right;
    private int grass_earth_block;
    private int sky;
	
    private int x;
    private int y;
    private int width;
    private int height;
    private int dimension;
    
    private int startXTile = 0;
    private int startYTile = 0;
    private int endXTile = 0;
    private int endYTile = 0;
    
    int level1[][];

	public Background(int[] spriteSize, GL2 gl) {
		//bgTexture = Main.glTexImageTGAFile(gl, "background.tga", spriteSize);
		//bgTexture2 = Main.glTexImageTGAFile(gl, "background2.tga", spriteSize);
		grass_top_left_corner = Main.glTexImageTGAFile(gl, "0.tga", spriteSize);
		grass_top = Main.glTexImageTGAFile(gl, "1.tga", spriteSize);
		grass_left = Main.glTexImageTGAFile(gl, "2.tga", spriteSize);
		grass_top_right_corner = Main.glTexImageTGAFile(gl, "3.tga", spriteSize);
		grass_right = Main.glTexImageTGAFile(gl, "4.tga", spriteSize);
		grass_earth_block = Main.glTexImageTGAFile(gl, "5.tga", spriteSize);
		sky = Main.glTexImageTGAFile(gl, "8.tga", spriteSize);
				
				
		width = height = dimension = 64;
		x = y = 0;
		
		level1 = new int[][] {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
			{ 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
			{ 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
			{ 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }
		};

};
	
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
        		switch(level1[i][j]) {
        			case 0:
        				Main.glDrawSprite(gl, grass_top_left_corner, (x + j * width), (y + i * height), width, height);
        				break;
        			case 1:
        				Main.glDrawSprite(gl, grass_top, (x + j * width), (y + i * height), width, height);
        				break;
        			case 2:
        				Main.glDrawSprite(gl, grass_left, (x + j * width), (y + i * height), width, height);
        				break;
        			case 3:
        				Main.glDrawSprite(gl, grass_top_right_corner, (x + j * width), (y + i * height), width, height);
        				break;
        			case 4:
        				Main.glDrawSprite(gl, grass_right, (x + j * width), (y + i * height), width, height);
        				break;
        			case 5:
        				Main.glDrawSprite(gl, grass_earth_block, (x + j * width), (y + i * height), width, height);
        				break;
        			case 8:
        				Main.glDrawSprite(gl, sky, (x + j * width), (y + i * height), width, height);
        				break;
        			default:
        				System.out.println("tile " + level1[i][j] + " not printed");
        				break;
        		}
        	}
        }
		
		/**	
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
        **/
		
		
	}
	
}
