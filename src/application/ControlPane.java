package application;

import javafx.scene.layout.VBox;

public class ControlPane extends VBox {
	private final int minWidth = 200;
	private final int minHeight = 400;
	
	public ControlPane() {
		this.setId("control-pane");
		this.setMinSize(minWidth, minHeight);
//		this.setSpacing(20);
	}
}
