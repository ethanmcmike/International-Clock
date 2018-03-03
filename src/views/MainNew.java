//package views;
//
//import static views.Renderer.importFlags;
//
//import java.io.IOException;
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.image.Image;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//public class MainNew extends Application{
//    
//    private static final int SIZE = 300;
//    private double initX, initY;
//    
//    private SceneController sc;
//    
//    public static Scene scene;
//    public static Scene editScene;
//    
//    private Renderer renderer;
//    private long lastUpdateTime;
//    
//    private final Image[] flagImage = importFlags();
//    
//    @Override
//    public void start(Stage stage) throws IOException{
//        
//        Widget c0 = new Widget("Ethan", 0, flagImage[16*13 + 13]);
//        Widget c1 = new Widget("Ethan", 9, flagImage[16*6 + 12]);
//        Widget c2 = new Widget("Ethan", 14, flagImage[12]);
//        
//        renderer = new Renderer(SIZE);
//        
////        FXMLLoader loader = new FXMLLoader();
////        loader.setLocation(SceneController.class.getResource("/clockNew/MainNew.fxml"));
////        AnchorPane root = loader.load();
//        
//        BorderPane root = new BorderPane();
//        root.setCenter(renderer.getCanvas());
//        scene = new Scene(root);
//        scene.setFill(Color.TRANSPARENT);
//        
//        stage = new Stage();
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.getIcons().add(new Image("res/icon.png"));
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setAlwaysOnTop(false);
//        stage.show();
//        
////        sc = new SceneController(stage);
////        sc.addScene(scene);
////        try{
////            sc.addScene("/clockNew/Edit.fxml");
////        } catch(IOException e){
////            
////        }
////        
////        sc.setScene(0);
//        
//        scene.setOnMousePressed(MouseEvent -> {
//            initX = MouseEvent.getSceneX();
//            initY = MouseEvent.getSceneY();
//        });
//        
//        scene.setOnMouseDragged(MouseEvent -> {
//            double mx = MouseEvent.getScreenX() - initX;
//            double my = MouseEvent.getScreenY() - initY;
//            ((Canvas)MouseEvent.getTarget()).getScene().getWindow().setX(mx);
//            ((Canvas)MouseEvent.getTarget()).getScene().getWindow().setY(my);
//        });
//        
//        scene.setOnContextMenuRequested(MouseEvent -> {
//            ((Stage)scene.getWindow()).setScene(editScene);
//        });
//        
//        long startNanoTime = System.nanoTime();
//        lastUpdateTime = 0;
//        
//        new AnimationTimer(){
//            @Override
//            public void handle(long currentNanoTime)
//            {
//                long t = (currentNanoTime - startNanoTime);
//                
//                if(t - lastUpdateTime > 1000){
//                    lastUpdateTime = t;
//                    renderer.update();
//                }
//            }
//        }.start();
//    }
//    
//    public static void main(String[] args){
//        launch(args);
//    }
//}