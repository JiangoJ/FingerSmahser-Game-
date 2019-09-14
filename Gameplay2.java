import java.io.FileInputStream;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.util.Pair;

public class Gameplay2 extends Application
{
	private enum UserAction {
		NONE, LEFT, RIGHT
	}
	
	Stage mMenu = new Stage();
	
	

	// Create Applet size
	private static final int APP_W = 1920;
	private static final int APP_H = 1080;

	// Create ball size
	private static final int BALL_RADIUS = 10;

	// Create Block size
	private static final int BAT_W = 200;
	private static final int BAT_H = 100;

	private static final int enemyCount = 50;

	// Scenes
	Group gHealth;
	Scene sHealth;

	Button toMenu;
	Button quit;



	// Create Objects

	ArrayList<Rectangle> enemySpawn;
	Player p = new Player();
	AudioClip audio = new AudioClip(getClass().getResource("peritune-rapid4.mp3").toExternalForm());
	HitBox box = new HitBox();

	// Default user input
	private UserAction action = UserAction.NONE;

	// animates the ball
	private Timeline timeline = new Timeline();

	// boolean variable that tells you if the game is running or not
	private boolean running = true;

	private boolean checkCollisionAuto(Group a, Rectangle b)
	{
		if (a.getBoundsInParent().intersects(b.getBoundsInParent()))
		{
			System.out.println("Collision");
			return true;
		} else
		{
			System.out.println("");
			return false;
		}
	}
	
	private void exit()
	{
		
		MainMenu mm = new MainMenu();
		
		try
		{
			mm.start(mMenu);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.exit();
		
	}

	private Parent createContent()
	{

		Pane root = new Pane();
		root.setPrefSize(APP_W, APP_H);
		root.setBackground(new Background(
				new BackgroundImage(new Image("mainBackground.png"), REPEAT, NO_REPEAT, CENTER, DEFAULT)));

		root.setPrefSize(APP_W, APP_H);

		Rectangle box1 = new Rectangle();
		box1.setHeight(50);
		box1.setWidth(175);
		box1.setTranslateX(90);
		box1.setTranslateY(100);
		box1.setFill(Color.LIGHTGRAY);

		root.getChildren().add(box1);

		Rectangle box2 = new Rectangle();
		box2.setHeight(50);
		box2.setWidth(140);
		box2.setTranslateX(1690);
		box2.setTranslateY(100);
		box2.setFill(Color.LIGHTGRAY);

		root.getChildren().add(box2);

		Label health = new Label("Health: " + p.getHealth());
		health.setFont(Font.font("Sans serif", FontWeight.BOLD, FontPosture.ITALIC, 32));
		health.setTranslateX(100);
		health.setTranslateY(100);
		health.setTextFill(Color.GREEN);

		root.getChildren().add(health);

		Label kills = new Label("Kills: " + p.getKills());
		kills.setFont(Font.font("Sans serif", FontWeight.BOLD, FontPosture.ITALIC, 32));
		kills.setTranslateX(1700);
		kills.setTranslateY(100);
		kills.setTextFill(Color.RED);

		root.getChildren().add(kills);

		enemySpawn = new ArrayList<Rectangle>();
		for (int i = 0; i < enemyCount; i++)
		{

			Enemy e = new Enemy();

			enemySpawn.add(e.createRect());

		}

		p.getGroup().setTranslateX(APP_W / 2 - 400);
		p.getGroup().setTranslateY(530);

		box.getImage("l").setTranslateX(APP_W / 2 - 335);
		box.getImage("l").setTranslateY(500);

		box.getImage("r").setTranslateX(APP_W / 2 - 170);
		box.getImage("r").setTranslateY(500);

		box.getImage("c").setTranslateX(APP_W / 2 - 96.5);
		box.getImage("c").setTranslateY(500);

		/*
		 * enemy.setTranslateX(0); enemy.setTranslateY(0);
		 */

		KeyFrame frame = new KeyFrame(Duration.seconds(0.006), event -> {
			if (!running)
				return;

			switch (action) {
			case LEFT:
				p.drawLeft();
				break;
			case RIGHT:
				p.drawRight();
				break;
			case NONE:
				p.draw();
				break;

			}

			if (p.getHealth() > 0 && (10 - p.getHealth() + p.getKills()) < 50)
			{
				for (int i = 0; i < enemyCount; i++)
				{
					if (i % 2 == 0)
					{
						enemySpawn.get(i).setTranslateX(enemySpawn.get(i).getTranslateX() + 5);

						System.out.println("Left: " + p.getDirection().equals("left"));
						System.out.println("Right: " + p.getDirection().equals("right"));

						// enemy.setTranslateY(enemy.getTranslateY() + (ballUp ? -5 : 5));
						if (checkCollisionAuto(box.getImage("l"), enemySpawn.get(i)) && p.getDirection().equals("left"))
						{

							p.killUp();
							kills.setText("Kills: " + p.getKills());
							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 350);

							TranslateTransition transL = new TranslateTransition();
							transL.setDuration(Duration.seconds(2));
							transL.setToX(APP_W / 2);
							transL.setToY(0);
							transL.setNode(enemySpawn.get(i));
							transL.play();

							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 5000);
						}
						if (checkCollisionAuto(box.getImage("c"), enemySpawn.get(i))
								&& !p.getDirection().equals("left"))
						{
							p.healthDown();
							health.setText("Health: " + p.getHealth());
							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 350);

							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 5000);
						}

					} else
					{
						enemySpawn.get(i).setTranslateX(enemySpawn.get(i).getTranslateX() - 5);
						// enemy.setTranslateY(enemy.getTranslateY() + (ballUp ? -5 : 5));
						if (checkCollisionAuto(box.getImage("r"), enemySpawn.get(i))
								&& p.getDirection().equals("right"))
						{
							p.killUp();
							kills.setText("Kills: " + p.getKills());

							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 350);

							TranslateTransition transL = new TranslateTransition();
							transL.setDuration(Duration.seconds(2));
							transL.setToX(APP_W / 2);
							transL.setToY(0);
							transL.setNode(enemySpawn.get(i));
							transL.play();

							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 5000);
						}
						if (checkCollisionAuto(box.getImage("c"), enemySpawn.get(i))
								&& !p.getDirection().equals("right"))
						{
							p.healthDown();
							health.setText("Health: " + p.getHealth());
							enemySpawn.get(i).setTranslateY(enemySpawn.get(i).getTranslateY() - 5000);
						}
					}
				}
			} 
			
			else
			{
				p.die();
				audio.stop();
				enemySpawn = null;

				Rectangle end = new Rectangle(APP_W, APP_H);

				Button toMenu = new Button("Return to Menu");
				toMenu.setFont(Font.font("Sans Serif", FontWeight.NORMAL, FontPosture.REGULAR, 50));
				toMenu.setTranslateX(440);
				toMenu.setTranslateY(580);

				Button quit = new Button("Quit");
				quit.setFont(Font.font("Sans Serif", FontWeight.NORMAL, FontPosture.REGULAR, 50));
				quit.setTranslateX(1240);
				quit.setTranslateY(580);

				toMenu.setOnAction((ActionEvent e) -> exit());

				quit.setOnAction((ActionEvent e) -> exit());

				if (p.getHealth() <= 0)
				{
					end.setFill(Color.BLACK);

					Label gameOver = new Label("GAME OVER");
					gameOver.setFont(Font.font("Chiller", FontWeight.NORMAL, FontPosture.REGULAR, 150));
					gameOver.setTextFill(Color.RED);
					gameOver.setTranslateX(665);
					gameOver.setTranslateY(280);

					root.getChildren().addAll(end, gameOver, toMenu, quit);
				} 
				
				else if (10 - p.getHealth() + p.getKills() >= 50)
				{
					end.setFill(Color.GREEN);

					Label win = new Label("YOU WIN");
					win.setFont(Font.font("Broadway", FontWeight.NORMAL, FontPosture.REGULAR, 150));
					win.setTextFill(Color.YELLOW);
					win.setTranslateX(640);
					win.setTranslateY(280);

					root.getChildren().addAll(end, win, toMenu, quit);
				}

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

		});
		timeline.getKeyFrames().add(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		audio.setVolume(0.5f);
		audio.setCycleCount(3);
		audio.play();

		root.getChildren().add(p.getGroup());

		for (int i = 0; i < enemyCount; i++)
		{
			root.getChildren().add(enemySpawn.get(i));
		}

		root.getChildren().add(box.getImage("l"));
		root.getChildren().add(box.getImage("r"));
		root.getChildren().add(box.getImage("c"));

		return root;
	}

	private void restartGame()
	{
		stopGame();
		startGame();
	}

	private void stopGame()
	{
		Platform.exit();
		running = false;
		audio.stop();
		timeline.stop();
	}

	private void startGame()
	{

		int pLPos = 0;
		int pRPos = 0;
		int randAdd = 400;
		int distance = 500;
		int distance2 = 1980;

		int i = 0;

		while (i < enemyCount)
		{
			int cLPos = 0 - (int) (Math.random() * 1000 + randAdd);
			int cRPos = 1980 + (int) (Math.random() * 1000 + randAdd);

			if (i % 2 == 0)
			{

				if (cLPos - pLPos < distance && Math.abs(cLPos - cRPos) > distance2)
				{
					enemySpawn.get(i).setTranslateX(cLPos);
					enemySpawn.get(i).setTranslateY(600);
					i++;
					pLPos = cLPos;
					randAdd += distance;
					distance2 += distance;
				}

			} else
			{

				if (cRPos - pRPos > distance && Math.abs(cLPos - cRPos) > distance2)
				{
					enemySpawn.get(i).setTranslateX(cRPos);
					enemySpawn.get(i).setTranslateY(600);
					i++;
					pRPos = cRPos;
					randAdd += distance;
					distance2 += distance;

				}

			}

		}

		timeline.play();
		running = true;
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
		scene.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case A:
				action = UserAction.LEFT;
				break;
			case D:
				action = UserAction.RIGHT;
				break;
			}
		});

		scene.setOnKeyReleased(event -> {
			switch (event.getCode()) {
			case A:
				action = UserAction.NONE;
				break;
			case D:
				action = UserAction.NONE;
				break;
			}
		});

		primaryStage.setTitle("FINGER SMASHER");
		primaryStage.setScene(scene);
		// primaryStage.setScene(sHealth);

		primaryStage.show();

		primaryStage.setOnCloseRequest(e -> Platform.exit());

		startGame();

	}

	public static void main(String[] args)
	{
		launch(args);
	}

}