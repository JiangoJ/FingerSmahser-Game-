import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Player
{
	private Image imageI;
	private Image imageL;
	private Image imageR;

	private ImageView viewI;
	private ImageView viewL;
	private ImageView viewR;
	
	private Group viewGroup;
	
	private boolean running;
	private String direction;
	
	private int score;
	private int health;
	private int kills;
	
	public Player()
	{
		imageI = new Image("StickIdleCentered.png");
		imageL = new Image("StickManAttackLeftCentered.png");
		imageR = new Image("StickManAttackRightCentered.png");
		
		viewI = new ImageView(imageI);
		viewL = new ImageView(imageL);
		viewR = new ImageView(imageR);
		
		
		
	
		
		viewGroup = new Group(viewI);
		
		running = true;
		
		score = 0;
		health = 10;
		kills = 0;
	}
	
	private enum UserAction
	{
		NONE, LEFT, RIGHT
	}
	
	
	private UserAction action = UserAction.NONE;
	
	public Group getGroup()
	{
		return viewGroup;
		
	}
	
	public void draw()
	{
		viewGroup.getChildren().setAll(viewI);
		direction = "none";
	}
	
	public void drawLeft()
	{
		viewGroup.getChildren().setAll(viewL);
		direction = "left";
	}
	
	public void drawRight()
	{
		viewGroup.getChildren().setAll(viewR);
		direction = "right";
	}
	
	public void attack(TheStage stage, Enemy enemy)
	{
		
		if (!running)
			return;

		switch (action) {
		case LEFT:
			viewGroup.getChildren().setAll(viewL);
			break;
		case RIGHT:
			viewGroup.getChildren().setAll(viewR);
			break;
		case NONE:
			viewGroup.getChildren().setAll(viewI);
			break;
		}
	}
	
	
	public int getHealth()
	{
		return health;
	}
	

	public int getKills()
	{
		return kills;
	}
	
	
	public void killUp()
	{
		kills ++;
	}
	
	public void healthDown()
	{
		health --;
	}
	
	public String getDirection()
	{
		if(direction.equals("left"))
			return "left";
		else if(direction.equals("right"))
			return "right";
		else
			return "none";
	}
	
	public void die()
	{
		
		viewGroup.getChildren().setAll();
		
		
	}
	public void setHealth2()
	{
		health = 8;
	}
	public void setHealth3()
	{
		health = 5;
	}
	public void setHealth4()
	{
		health = 50;
	}
	public void setHealth5()
	{
		health = 1;
	}
}
