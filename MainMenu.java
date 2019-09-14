
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Application
{

	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;

	Gameplay game = new Gameplay();
	Gameplay2 game2 = new Gameplay2();
	Gameplay3 game3 = new Gameplay3();
	Gameplay4 game4 = new Gameplay4();
	Gameplay5 game5 = new Gameplay5();
	Instructions ins = new Instructions();
	Stage stge = new Stage();
	Stage stge2 = new Stage();
	Stage stge3 = new Stage();
	Stage stge4 = new Stage();
	Stage stge5 = new Stage();
	Stage inst = new Stage();
	String[] a = new String[1];
	AudioClip audio = new AudioClip(getClass().getResource("serge-narcissoff-orion.mp3").toExternalForm());

	private List<Pair<String, Runnable>> menuData = Arrays.asList(new Pair<String, Runnable>("Easy", () -> {
		try
		{
			
			game.start(stge);
		
			
			audio.stop();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}),

			new Pair<String, Runnable>("Medium", () -> {
				try
				{
					game2.start(stge2);
					audio.stop();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}),

			new Pair<String, Runnable>("Hard", () -> {
				try
				{
					game3.start(stge3);
					audio.stop();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}),

			new Pair<String, Runnable>("Impossible", () -> {
				try
				{
					game4.start(stge4);
					audio.stop();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}),

			new Pair<String, Runnable>("Challenge", () -> {
				try
				{
					game5.start(stge5);
					audio.stop();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}),

			new Pair<String, Runnable>("Instructions", () -> {
				try
				{
					ins.start(inst);
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}),

			new Pair<String, Runnable>("Exit to Desktop", Platform::exit));

	private Pane root = new Pane();
	private VBox menuBox = new VBox(-5);
	private Line line;

	private Parent createContent()
	{
		addBackground();
		addTitle();

		audio.setVolume(0.5f);
		audio.setCycleCount(3);
		audio.play();

		double lineX = WIDTH / 2 - 100;
		double lineY = HEIGHT / 3 + 50;

		addLine(lineX, lineY);
		addMenu(lineX + 5, lineY + 5);

		startAnimation();

		return root;
	}

	private void addBackground()
	{
		ImageView imageView = new ImageView(
				new Image(getClass().getResource("res/mainMenuBackground.png").toExternalForm()));
		imageView.setFitWidth(WIDTH);
		imageView.setFitHeight(HEIGHT);

		root.getChildren().add(imageView);
	}

	private void addTitle()
	{
		MainMenuTitle title = new MainMenuTitle("FINGER SMASHER");
		title.setTranslateX(WIDTH / 2 - title.getTitleWidth() + 325);
		title.setTranslateY(HEIGHT / 3);

		root.getChildren().add(title);
	}

	private void addLine(double x, double y)
	{
		line = new Line(x, y, x, y + 260);
		line.setStrokeWidth(3);
		line.setStroke(Color.color(1, 1, 1, 0.75));
		line.setEffect(new DropShadow(5, Color.BLACK));
		line.setScaleY(0);

		root.getChildren().add(line);
	}

	private void startAnimation()
	{
		ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
		st.setToY(1);
		st.setOnFinished(e -> {

			for (int i = 0; i < menuBox.getChildren().size(); i++)
			{
				Node n = menuBox.getChildren().get(i);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
				tt.setToX(0);
				tt.setOnFinished(e2 -> n.setClip(null));
				tt.play();
			}
		});
		st.play();
	}

	private void addMenu(double x, double y)
	{
		menuBox.setTranslateX(x);
		menuBox.setTranslateY(y);
		menuData.forEach(data -> {
			MainMenuItem item = new MainMenuItem(data.getKey());
			item.setOnAction(data.getValue());
			item.setTranslateX(-300);

			Rectangle clip = new Rectangle(300, 30);
			clip.translateXProperty().bind(item.translateXProperty().negate());

			item.setClip(clip);

			menuBox.getChildren().addAll(item);
		});

		root.getChildren().add(menuBox);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Finger Smasher: by Justin Jiang, Garrett Hlavinka, Danny Choi, and Vincent Dang");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
