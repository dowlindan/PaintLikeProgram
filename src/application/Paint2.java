package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;


public class Paint2 extends Application 
{
	private Button clear;
	private ColorPicker colorPicker;
	private HBox tools;
	private Group root;
	private Polyline polyline;
	private ArrayList<Polyline> lines;
	
	public void start(Stage primaryStage) 
	{
		clear = new Button("Clear");
		colorPicker = new ColorPicker(Color.BLACK);
		
		lines = new ArrayList<Polyline>();
		tools = new HBox(clear, colorPicker);
		tools.setAlignment(Pos.TOP_LEFT);
		root = new Group(tools);
		polyline = new Polyline();
		
		Scene scene = new Scene(root, 1280, 720);
		
		scene.setOnMouseDragged(this::drag);
		scene.setOnMousePressed(this::draw);
		
		clear.setOnMousePressed(this::clearButtonPressed);
		
		primaryStage.setTitle("Paint 2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void clearButtonPressed(MouseEvent event)
	{
		root.getChildren().removeAll(lines);
	}
	
	public void draw(MouseEvent event)
	{
		//root.getChildren().add(new Polyline(event.getX(), event.getY()));
		polyline = new Polyline();
		polyline.setStroke(colorPicker.getValue());
		polyline.getPoints().addAll(event.getX(), event.getY());
		root.getChildren().add(polyline);
		lines.add(polyline);
	}
	
	public void drag(MouseEvent event)
	{
		polyline.getPoints().addAll(event.getX(),event.getY());
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
