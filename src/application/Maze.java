package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze {
	public final VBox layout;

	private final int minWidth = 400;
	private final int minHeight = 400;
	private final int spacing = 5;
	private final int minCellSize = 20;
	private final int maxCellSize = 100;
	private final int iniCellSize = 30;
	private final int scrollSpeed = 5;
	private final Pane mazePane;
	private final Slider cellSizeSlider;
	private final Label cellSizeLabel;
	
	private final ArrayList<Rectangle> CellsInUse;
	
	public Maze() {
		mazePane = new Pane();
		cellSizeSlider = new Slider(minCellSize, maxCellSize, iniCellSize);
		cellSizeLabel = new Label(String.valueOf((int)cellSizeSlider.getValue()));
		layout = new VBox(spacing);
		CellsInUse = new ArrayList<Rectangle>();
		
		initSetting();
		System.out.println(cellSizeSlider.getHeight());
	}
	
	public void createGrid() {
		mazePane.getChildren().removeAll(CellsInUse);
		CellsInUse.clear();
		
		int w = (int)mazePane.getPrefWidth();
		int h = (int)mazePane.getPrefHeight();
		int size = (int)cellSizeSlider.getValue();
		int rows = h / size;
		int cols = w / size;
		int leftPadding = w % size / 2;
		int topPadding = h % size / 2;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Rectangle rect = new Rectangle(j * size + leftPadding, i * size + topPadding, size, size);
				rect.setFill(Color.rgb(0, 0, 0, 0));
				rect.setStroke(Color.PALEVIOLETRED);
				CellsInUse.add(rect);
				mazePane.getChildren().add(rect);
			}
		}
	}
	
	private void initSetting() {
		mazePane.setId("maze-pane");

		cellSizeSlider.setPrefHeight(14);

		cellSizeLabel.setPrefWidth(90);
		cellSizeLabel.setPrefHeight(14);
		
		HBox cellSizeHB = new HBox(15);
		cellSizeHB.getChildren().add(cellSizeSlider);
		cellSizeHB.getChildren().add(cellSizeLabel);
		
		layout.setId("maze-layout");
		layout.setMinSize(minWidth, minHeight);
		layout.getChildren().add(mazePane);
		layout.getChildren().add(cellSizeHB);

		mazePane.prefWidthProperty().bind(layout.prefWidthProperty());
		mazePane.prefHeightProperty().bind(layout.prefHeightProperty().subtract(cellSizeSlider.getPrefHeight() + layout.getSpacing()));
		mazePane.setOnScroll(e -> {
			if(e.getDeltaY() > 0) {
				cellSizeSlider.setValue(cellSizeSlider.getValue() + scrollSpeed);
			} else {
				cellSizeSlider.setValue(cellSizeSlider.getValue() - scrollSpeed);
			}
		});
		
		
		cellSizeSlider.prefWidthProperty().bind(layout.prefWidthProperty().subtract(cellSizeLabel.getPrefWidth()));
		cellSizeSlider.valueProperty().addListener(l -> {
			createGrid();
		});
		
		cellSizeLabel.textProperty().bind(cellSizeSlider.valueProperty().asString("格子大小：%.0f"));
	}
}
