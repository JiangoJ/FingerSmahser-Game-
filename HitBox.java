import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HitBox {
	private Image imageBoxL;
	private Image imageBoxR;
	private Image imageBoxC;

	private ImageView viewBoxL;
	private ImageView viewBoxR;
	private ImageView viewBoxC;
	
	private Group groupL;
	private Group groupR;
	private Group groupC;

	public HitBox() {
		imageBoxL = new Image("stickmanattackleftBoxInvisible.png");
		imageBoxR = new Image("stickmanattackrightBoxInvisible.png");
		imageBoxC = new Image("HitBoxCenteredInvisible.png");

		viewBoxL = new ImageView(imageBoxL);
		viewBoxR = new ImageView(imageBoxR);
		viewBoxC = new ImageView(imageBoxC);
		
		groupL = new Group(viewBoxL);
		groupR = new Group(viewBoxR);
		groupC = new Group(viewBoxC);
		
		

	}

	public Group getImage(String direction) {
		if (direction.equals("l")) {
			return groupL;
		} else if (direction.equals("r")) {
			return groupR;
		} else if (direction.equals("c")) {
			return groupC;
		} else
			return null;

	}

}
