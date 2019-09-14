import java.io.FileInputStream;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Instructions extends Application
{
	private enum UserAction {
		NONE, LEFT, RIGHT
	}

	// Create Applet size
	private static final int APP_W = 900;
	private static final int APP_H = 600;

	// Create ball size
	private static final int BALL_RADIUS = 10;

	// Create Block size
	private static final int BAT_W = 200;
	private static final int BAT_H = 100;

	private static final int enemyCount = 20;

	// Scenes
	Group gHealth;
	Scene sHealth;

	// Create Objects

	
	private boolean running = true;

	

	private Parent createContent()
	{
		
		  Pane root = new Pane(); root.setPrefSize(APP_W, APP_H);
		  root.setBackground(new Background(new BackgroundImage(new
		  Image("softambience.jpg"), REPEAT, NO_REPEAT, CENTER, DEFAULT)));
		 
		
		root.setPrefSize(APP_W, APP_H);

		Label ins = new Label("*Instructions*");
		ins.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 64));
		ins.setTranslateX(170);
		ins.setTranslateY(30);
		ins.setTextFill(Color.BLACK);
		
		Label story = new Label("You are a single ninja warrior who will face the onslaught of numerous rival \n \"polygonal\" ninja clan warriors. You will stand in the center of the screen and \n punch left and right at the right time to defeat the oncoming enemies. There \n\n\n\n\n\n\t\t\t\t\t\tHow far will you go..?");
		story.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
		story.setTranslateX(38);
		story.setTranslateY(120);
		story.setTextFill(Color.BLACK);
		
		Label cont = new Label("*Controls*");
		cont.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 64));
		cont.setTranslateX(240);
		cont.setTranslateY(380);
		cont.setTextFill(Color.BLACK);
		
		Label cont2 = new Label("A --------------- Attack left\nD --------------- Attack Right");
		cont2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
		cont2.setTranslateX(296);
		cont2.setTranslateY(480);
		cont2.setTextFill(Color.BLACK);
		
		Button but = new Button("Return to Menu");
		but.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.ITALIC, 14));
		but.setTranslateX(370);
		but.setTranslateY(550);
		but.setTextFill(Color.BLACK);
		

		root.getChildren().addAll(ins, story, cont, cont2, but);
		return root;
	}

		

		
		
		
			
			// Movement of enemy

			// Collision detection for left and right
			/*
			 * if (enemy.getTranslateX() == APP_W/2) ballMiddle = true;
			 * 
			 * else if (enemy.getTranslateY() + BALL_RADIUS == APP_H - BAT_ H &&
			 * enemy.getTranslateX() + BALL_RADIUS >= p.getGroup().getTranslateX() &&
			 * enemy.getTranslateX() - BALL_RADIUS <= p.getGroup().getTranslateX() + BAT_W)
			 * ballUp = true;
			 * 
			 * if (enemy.getTranslateY() + BALL_RADIUS == APP_H) restartGame();
			 */

		
	

	

	private void startGame()
	{
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{

		Scene scene = new Scene(createContent());
		/*
		 * a VBox parent = new VBox(); Label healthDisplay = new Label("Health: " +
		 * p.getHealth()); parent.getChildren().add(healthDisplay); Scene sHealth = new
		 * Scene(parent); primaryStage.setScene(sHealth);
		 */
		

		primaryStage.setTitle("FINGER SMASHER");
		primaryStage.setScene(scene);
		// primaryStage.setScene(sHealth);

		primaryStage.show();
		startGame();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}