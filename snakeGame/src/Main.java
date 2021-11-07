import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        JFrame jFrame = new JFrame();

        Game game = new Game();

        jFrame.setBounds(10,10,905,700);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.add(game);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
