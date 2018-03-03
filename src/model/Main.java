//package clock;
//
//import java.awt.AWTException;
//import java.awt.MenuItem;
//import java.awt.PopupMenu;
//import java.awt.SystemTray;
//import java.awt.TrayIcon;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.net.URL;
//
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import static javafx.application.Application.launch;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//import javafx.scene.image.PixelWriter;
//import javafx.scene.image.WritableImage;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javax.imageio.ImageIO;
//
//public class Main extends Application{
//
//    public static int SIZE = 200;   
//    
//    //List
//    Scene list;
//    
//    //Clock
//    public Scene scene;
//    public BorderPane borderPane;
//    public Group root;
//    public Canvas canvas;
//    public static GraphicsContext gc;
//    public WritableImage img;
//    public PixelWriter pw;
//    
//    public long lastUpdateTime = 0;
//    public long lastRenderTime = 0;
//    
//    public int toggle = 0;
//    public Menu menu;
//    
//    double initX, initY;
//    
//    public void start(Stage stage1){
//        
//        //List
////        list = new Scene(root);
//        
//        //Clock
//        img = new WritableImage(SIZE, SIZE);
//        pw = img.getPixelWriter();
//        
//        canvas = new Canvas(SIZE, SIZE);
//        gc = canvas.getGraphicsContext2D();
//
//        root = new Group();
//        root.getChildren().add(canvas);
//        
//        borderPane = new BorderPane();
//        borderPane.setCenter(root);
//        
//        scene = new Scene(borderPane);
//        scene.setFill(Color.TRANSPARENT);
//        
//        final Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.getIcons().add(new Image("res/icon.png"));
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setAlwaysOnTop(true);
//        stage.centerOnScreen();
//        stage.show();        
//
//        scene.setOnMousePressed(MouseEvent -> {
//            initX = MouseEvent.getSceneX();
//            initY = MouseEvent.getSceneY();
//        });
//        
//        scene.setOnMouseDragged(MouseEvent -> {
//            double mx = MouseEvent.getScreenX() - initX;
//            double my = MouseEvent.getScreenY() - initY;
//            stage.setX(mx);
//            stage.setY(my);
//        });
//        
//        scene.setOnContextMenuRequested(event -> {
//            toggle++;
//            if(toggle > 2) toggle = 0;
//        });
//        
//        if(toggle == 0){
//            scene.setOnScroll(scroll -> {
//                double dy = scroll.getDeltaY();
//                resize(dy);
//                stage.setWidth(SIZE);
//                stage.setHeight(SIZE);
//                clock.resize(SIZE);
//            });
//        }
//
//        if(toggle == 1){
//            scene.setOnScroll(scroll -> {
//                double dy = scroll.getDeltaY();
//                menu.scroll(dy);
//            });
//        }
//        
//        
//        clock = new Clock(SIZE);
//        menu = new Menu(SIZE);
//
//        final long startNanoTime = System.nanoTime();
//        
//        new AnimationTimer(){
//            public void handle(long currentNanoTime)
//            {
//                long t = (currentNanoTime - startNanoTime) / 1000000;
//                
//                if(t - lastUpdateTime > 1){
//                    lastUpdateTime = t;
//                    update();
//                }
//                
//                if(t - lastRenderTime > 1000/60){
//                    lastRenderTime = t;
//                    render();
//                }
//            }
//        }.start();
//    }
//    
//    public void init(Stage stage){
//        stage.setX(0);
//    }
//    
//    public void update(){ 
//        switch(toggle){
//            case 0: clock.update(); break;
//            case 1: menu.draw(); break;
//            case 2: menu.draw(); break;
//        }
//    }
//    
//    public void render(){
//        switch(toggle){
//            case 0: clock.draw(); break;
//            case 1: menu.draw(); break;
//            case 2: menu.draw(); break;
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    public void resize(double dy){
//        SIZE += dy;
//        
//        if(SIZE < 100) SIZE = 100;
//        if(SIZE > 500) SIZE = 500;
//    }
//    
//    public void createTrayIcon(final Stage stage) {
//        if (SystemTray.isSupported()) {
//            // get the SystemTray instance
//            SystemTray tray = SystemTray.getSystemTray();
//            // load an image
//            java.awt.Image timg = null;
//            try{
//                URL url = new URL("http://www.digitalphotoartistry.com/rose1.jpg");
//                timg = ImageIO.read(url);
//            } catch(IOException ex){
//                System.out.println(ex);
//            }
//
//
//            stage.setOnCloseRequest(event -> {
//                    stage.hide();
//            });
//            
//            // create a action listener to listen for default action executed on the tray icon
//            final ActionListener closeListener = new ActionListener() {
//                @Override
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    System.exit(0);
//                }
//            };
//            
//            final ActionListener showListener = new ActionListener() {
//                @Override
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    stage.show();
//                }
//            };
//
//            // create a popup menu
//            PopupMenu popup = new PopupMenu();
//
//            MenuItem showItem = new MenuItem("Show");
//            showItem.addActionListener(showListener);
//            popup.add(showItem);
//
//            MenuItem closeItem = new MenuItem("Close");
//            closeItem.addActionListener(closeListener);
//            popup.add(closeItem);
//            /// ... add other items
//            // construct a TrayIcon
//            
//            TrayIcon trayIcon = new TrayIcon(timg, "Clock", popup);
//            // set the TrayIcon properties
//            trayIcon.addActionListener(showListener);
//            // ...
//            // add the tray image
//            try {
//                tray.add(trayIcon);
//            } catch (AWTException e) {
//                System.err.println(e);
//            }
//            // ...
//        }
//    }
//}