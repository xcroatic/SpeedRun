import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.animation.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.io.FileInputStream;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RaceCar extends Application {

   private static final double width = 900, height = 675, DEFAULT_CAR_WIDTH = 60, DEFAULT_CAR_HEIGHT = 60;

   private static final String IMAGE = "carTop.png";

   private Image carImage;
   private ImageView  car;
   private int raceROT = 0; 
   private double speed = 0, speedMultiplier;
   private Image track;

   boolean goUp, goDown, goRight, goLeft;
   
   public static void main(String[] args) { 
      launch(args);
   }
   
   @Override
   public void start(Stage stage) throws Exception {
      carImage = new Image(IMAGE);
      car = new ImageView(carImage);
      car.setFitWidth(DEFAULT_CAR_WIDTH);
      car.setFitHeight(DEFAULT_CAR_HEIGHT);
      car.setTranslateX(250);
      car.setTranslateY(410);
      track = new Image("track.png");
      Pane root = new Pane();
      root.getChildren().addAll(car);
      
      Scene scene = new Scene(root, width, height);
   
      scene.setOnKeyPressed(
         new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
               switch (event.getCode()) {
                  case UP:    
                     goUp = true; 
                     break;
                  case DOWN:  
                     goDown = true; 
                     break;
                  case LEFT:  
                     goLeft  = true; 
                     break;
                  case RIGHT: 
                     goRight  = true; 
                     break;
                  case W:    
                     goUp = true; 
                     break;
                  case S:  
                     goDown = true; 
                     break;
                  case A:  
                     goLeft  = true; 
                     break;
                  case D: 
                     goRight  = true; 
                     break;
                  
               }
            }
         });
   
      scene.setOnKeyReleased(
         new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
               switch (event.getCode()) {
                  case UP:    
                     goUp = false;
                     break;
                  case DOWN:  
                     goDown = false; 
                     break;
                  case LEFT:  
                     goLeft  = false; 
                     break;
                  case RIGHT: 
                     goRight  = false; 
                     break;
                  case W:    
                     goUp = false;
                     break;
                  case S:  
                     goDown = false; 
                     break;
                  case A:  
                     goLeft  = false; 
                     break;
                  case D: 
                     goRight  = false; 
                     break;
               
               }
            }
         });
      
      scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
      stage.setScene(scene);
      stage.show(); 
      root.setId("root");
      stage.setResizable (false);
      stage.widthProperty().addListener(
         (obs, oldVal, newVal) -> {
            double x = (double) newVal / width;
            car.setPreserveRatio(true);
            car.setFitWidth(DEFAULT_CAR_WIDTH * x);
            speedMultiplier = x;
            System.out.println(speedMultiplier);
         });
   
      AnimationTimer timer = 
         new AnimationTimer() {
            @Override
            public void handle(long now) {
               if (goLeft){ 
                  if(speed == 0) 
                     return; 
                  else rotateLeft();
               }
               if (goUp){
                  if(speed > 5){
                     speed += 0;
                  }else{ speed += 0.1; }
               }
               if (goDown){
                  if(speed > 0){
                     speed -= 0.3;
                  }else{ speed -= 0.1; }
               }
               if (goRight){
                  if(speed == 0) 
                     return; 
                  else rotateRight();
               }
               if(speed != 0 || !goUp || !goDown){
                  if(speed > 0) speed -= 0.05;
                  if(speed < 0) speed += 0.05; 
                  
               }
               moveCar();
               ifOutOfTrack();
            }
            
         };
      timer.start();
   }

   private void moveCar() {
   
      double x = Math.cos(Math.toRadians(car.getRotate()));
      double y = Math.sin(Math.toRadians(car.getRotate()));
      
      car.setTranslateX(car.getTranslateX() + speed * x);
      car.setTranslateY(car.getTranslateY() + speed * y);
      
   }
      
   public void rotateLeft(){
      car.setRotate(raceROT);
      raceROT -= 2;
   }
   
   public void rotateRight(){
      car.setRotate(raceROT);     
      raceROT += 2;
   }
    
   public void ifOutOfTrack(){
      String color = String.valueOf(track.getPixelReader().getColor((int)car.getTranslateX(), (int)car.getTranslateY()));
      if(car.getTranslateX() < 3.0 || car.getTranslateX() > 875.0 || car.getTranslateY() < 3.0 || car.getTranslateY() > 665.0 ){
         car.setTranslateY(450);
         car.setTranslateX(270);
         car.setRotate(360); 
         raceROT = 360;
         speed = 0;
         System.out.println("You can't go out of the map!");
      }
      if(track.getPixelReader().getColor((int)car.getTranslateX(), (int)car.getTranslateY()).equals(Color.rgb(81,177,35))){
          car.setOpacity(0.3);
          speed = 1;
      }else{
         car.setOpacity(1);
      }
   }  
   
}