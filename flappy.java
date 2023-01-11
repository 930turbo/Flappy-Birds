import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlappyBird extends Application {
    private Circle bird;
    private Timeline timeline;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        bird = new Circle(50, 250, 20, Color.YELLOW);
        pane.getChildren().add(bird);

        Scene scene = new Scene(pane, 700, 500);
        stage.setScene(scene);
        stage.show();

        timeline = new Timeline(new KeyFrame(Duration.millis(30), new AnimationHandler()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(new KeyHandler());
    }

    private class AnimationHandler implements EventHandler<ActionEvent> {
        private double velocity = 0;
        private static final double GRAVITY = 0.5;
        private static final double JUMP_VELOCITY = -10;

        @Override
        public void handle(ActionEvent event) {
            velocity += GRAVITY;
            bird.setCenterY(bird.getCenterY() + velocity);

            if (bird.getCenterY() > 500) {
                bird.setCenterY(500);
                velocity = 0;
            } else if (bird.getCenterY() < 0) {
                bird.setCenterY(0);
                velocity = 0;
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.SPACE) {
                ((AnimationHandler) timeline.getKeyFrames().get(0).getOnFinished()).velocity = JUMP_VELOCITY;
            }
        }
    }
}
