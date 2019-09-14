import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy
{
	private static final int BAT_W = 100;
	private static final int BAT_H = 200;


	public int getWidth()
	{
		return BAT_W;
	}

	public int getHeight()
	{
		return BAT_H;
	}

	public Rectangle createRect()
	{
		Rectangle r = new Rectangle(BAT_W, BAT_H);
		int R = (int) (Math.random() * 256);
		int G = (int) (Math.random() * 256);
		int B = (int) (Math.random() * 256);
		
		r.setFill(Color.rgb(R,G,B));
		return r;

	}
}
