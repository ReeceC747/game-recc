package com.gamerecc.backend.desktop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.concurrent.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.WebApplicationType;
import com.gamerecc.backend.BackendApplication;
import com.gamerecc.backend.service.GameCatalogService;
import com.gamerecc.backend.model.SteamApp;

public class GameReccDesktopApplication extends Application
{

    private ConfigurableApplicationContext springContext;

    @Override 
    public void init()
    {
        SpringApplication application = new SpringApplication(BackendApplication.class);

        application.setWebApplicationType(WebApplicationType.NONE); // Disable web environment for desktop application

        springContext = application.run();

    }

    @Override
    public void start(Stage primaryStage)
    {
        GameCatalogService gameCatalogService = springContext.getBean(GameCatalogService.class);

        
        Label title = new Label("GameRecc");
        Label recommendedGameLabel = new Label("No game recommended yet");

        Button recommendationButton = new Button("Get Recommendation");

        recommendationButton.setOnAction(event -> {

            // Defines the Task to fetch a random game in the background
            Task<SteamApp> recommendationTask = new Task<>()
            {
                @Override 
                protected SteamApp call()
                {
                    // Executes on the background thread
                    return gameCatalogService.getRandomGame();
                }
            };

            // Runs on JavaFx thread if successful
            recommendationTask.setOnSucceeded(successEvent -> {
                SteamApp game = recommendationTask.getValue();
                recommendedGameLabel.setText(game.getName());
            });

            // Runs on JavaFx thread if failed
            recommendationTask.setOnFailed(failureEvent -> {
                recommendedGameLabel.setText("Failed to get recommendation");
                recommendationTask.getException().printStackTrace();
            });

            //create a worker thread to run the task
            Thread thread = new Thread(recommendationTask);

            // dont keep the JVM alive if the user closes the window
            thread.setDaemon(true);

            // begin executing the task
            thread.start();
        });
        

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(title);
        root.getChildren().add(recommendationButton);
        root.getChildren().add(recommendedGameLabel);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("GameRecc");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override 
    public void stop()
    {
        if(springContext != null)
        {
            springContext.close();
        }
    }

    public static void main(String[] args)
    {
        // Launch the JavaFX application and invokes start() method
        launch(args);
    }

}