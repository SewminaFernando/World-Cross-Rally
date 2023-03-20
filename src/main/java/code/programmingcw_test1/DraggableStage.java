package code.programmingcw_test1;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
*Make Window Draggable
 */
/*
*Reference : https://stackoverflow.com/a/61205707
*Author : Linh
*/

public class DraggableStage extends Stage {

    public void  dragStage(Scene scene, Stage stage) {
        scene.setOnMousePressed(pressEvent -> {
            scene.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });


        /*Make window center of the Screen*/

        // Get the dimensions of the primary screen
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        // Set the position of the stage to the center of the screen
        stage.setX((screenWidth - stage.getWidth()) / 2);
        stage.setY((screenHeight - stage.getHeight()) / 2);
    }
}



