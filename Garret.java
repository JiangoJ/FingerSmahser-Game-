import java.io.FileInputStream;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Garret extends Application
{
	private enum UserAction {
		NONE, LEFT, RIGHT
	}

	// Create Applet size
	private static final int APP_W = 1920;
	private static final int APP_H = 1080;

	// Create ball size
	private static final int BALL_RADIUS = 10;

	// Create Block size
	private static final int BAT_W = 100;
	private static final int BAT_H = 200;
	
	
	private static final int enemyCount = 5;

	// Create Objects
	
	ArrayList<Rectangle> enemySpawn;
	

	
	

	
	
	private Image playerdefault = new Image("stickIdle.png");
	private Image playerLeft = new Image("stickmanLeft.png");
	private Image playerRight = new Image("stickmanRight.png");

	// default movement for ball
	private boolean ballUp = true, ballMiddle = false;

	// Default user input
	private UserAction action = UserAction.NONE;

	// animates the ball
	private Timeline timeline = new Timeline();

	// boolean variable that tells you if the game is running or not
	private boolean running = true;

	ImageView playerone = new ImageView(playerdefault);
	ImageView player2 = new ImageView(playerLeft);
	ImageView player3 = new ImageView(playerRight);
	
	private Group playerGroup = new Group(playerone);
	
	  private boolean checkCollisionAuto(Group a, Rectangle b) {
	        if (a.getBoundsInParent().intersects(b.getBoundsInParent())) {
	            System.out.println("Collision");
	            return true;
	        }
	        else {
	            System.out.println("");
	            return false;
	        }
	    }

	private Parent createContent()
	{
		Pane root = new HBox();
		root.setPrefSize(APP_W, APP_H);
		root.setBackground(new Background(new BackgroundImage(new Image("mainMenuBackground.png"), REPEAT, NO_REPEAT, CENTER, DEFAULT)));
		
		 enemySpawn= new ArrayList<Rectangle>();
		 for(int i = 0 ; i< enemyCount; i ++)
		 {
			 enemySpawn.add(new Rectangle(BAT_W, BAT_H)); 
		 }
	
		
		playerGroup.setTranslateX(APP_W/2-200);
		playerGroup.setTranslateY(500);
/*		
		enemy.setTranslateX(0);
		enemy.setTranslateY(0);*/
		
		 KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
			 if (!running)
	                return;
			 
			    switch (action) {
                case LEFT:
                    if (playerGroup.getTranslateX() - 5 >= 0)
                        playerGroup.getChildren().setAll(player2);
                    break;
                case RIGHT:
                    if (playerGroup.getTranslateX() + BAT_W + 5 <= APP_W)
                        playerGroup.getChildren().setAll(player3);
                    break;
                case NONE:
                    break;
                    
                 
            }
			    for(int i = 0; i<enemyCount; i++)
			    {
			    	if(i%2 == 0)
			    	{
			    		  enemySpawn.get(i).setTranslateX(enemySpawn.get(i).getTranslateX() + 5);
				            //enemy.setTranslateY(enemy.getTranslateY() + (ballUp ? -5 : 5));
						    if(checkCollisionAuto(playerGroup, enemySpawn.get(i)))
						    {
						    	enemySpawn.get(i).setTranslateX(enemySpawn.get(i).getTranslateX() - (int)(Math.random()*500+500));
						    }
			    	}
			    	else
			    	{
			    		 enemySpawn.get(i).setTranslateX(enemySpawn.get(i).getTranslateX() - 5);
				            //enemy.setTranslateY(enemy.getTranslateY() + (ballUp ? -5 : 5));
						    if(checkCollisionAuto(playerGroup, enemySpawn.get(i)))
						    {
						    	enemySpawn.get(i).setTranslateX(enemySpawn.get(i).getTranslateX() + (int)(Math.random()*500+500));
						    }
			    	}
			      
			    }
			    //Movement of enemy
			
			  
			   
			    //Collision detection for left and right
		/*	    if (enemy.getTranslateX() == APP_W/2)
	                ballMiddle = true;
			    
			    else if (enemy.getTranslateY() + BALL_RADIUS == APP_H - BAT_ H
	                    && enemy.getTranslateX() + BALL_RADIUS >= playerGroup.getTranslateX()
	                    && enemy.getTranslateX() - BALL_RADIUS <= playerGroup.getTranslateX() + BAT_W)
	                ballUp = true;

	            if (enemy.getTranslateY() + BALL_RADIUS == APP_H)
	                restartGame();
			    */
			    
	           
		 });
		 timeline.getKeyFrames().add(frame);
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        
	        root.getChildren().add(playerGroup);
	        
	        for(int i = 0 ; i<enemyCount; i ++)
	        {
	        	root.getChildren().add(enemySpawn.get(i));
	        }
	        
	        return root;
	    }

	    private void restartGame() {
	        stopGame();
	        startGame();
	    }

	    private void stopGame() {
	        running = false;
	        timeline.stop();
	    }

	    private void startGame() {
	    
	        for(int i = 0 ; i<enemyCount; i ++)
	        {
	        	if(i%2 == 0)
	        	{
	        	    enemySpawn.get(i).setTranslateX(0);
	        	    enemySpawn.get(i).setTranslateY(750);
	        	}
	        	else
	        	{
	        		  enemySpawn.get(i).setTranslateX(750);
		        	  enemySpawn.get(i).setTranslateY(750);	        	
	        	}
	        }
	   

	        timeline.play();
	        running = true;
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    	
	        Scene scene = new Scene(createContent());
	        
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
	        
	        
	        primaryStage.setTitle("Tutorial");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        startGame();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	
	}

