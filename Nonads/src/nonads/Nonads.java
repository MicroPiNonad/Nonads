/* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.nonads;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.util.Callback;


public class Nonads extends Application {
       
    Button about, help, instructions;
    FlowPane aboutInfoPane, helpInfoPane, instructionsInfoPane;
    Scene aboutInfoScene, helpInfoScene, instructionsInfoScene;
    Stage aboutInfoStage, helpInfoStage, instructionsInfoStage;
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane pane = new BorderPane();
        
    ///////////////TITLE////////////////////////////////////////////////////////
        Text topText = new Text();
        topText.setWrappingWidth(500);
        topText.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        topText.setFill(Color.BLACK);   
        topText.setText("Nonads");
        topText.setTextAlignment(TextAlignment.CENTER);
    ////////////////////////////////////////////////////////////////////////////


    /////////////PANE ORGANIZATION//////////////////////////////////////////////
        pane.setTop(getNonadsInfoTxtGrid());
        pane.setPadding(new Insets(10, 10, 10, 10));
        //pane.setCenter(board1);
        pane.setBottom(new Text("Where the tiles or hands will display"));  
        pane.setLeft(getGameInfoTxtGrid());
        pane.setRight(getPlayerTargetPieceGrid());
        
        BorderPane.setAlignment(getNonadsInfoTxtGrid(), Pos.CENTER);
       // BorderPane.setAlignment(board1, Pos.CENTER);
        
       Scene scene = new Scene(pane, 400, 250, Color.WHITE);
       primaryStage.setMaximized(true);
       primaryStage.setTitle("MicroPi Presents: Nonads"); // Set the stage title
       primaryStage.setScene(scene); // Place the scene in the stage
       primaryStage.show(); // Display the stage
    ////////////////////////////////////////////////////////////////////////////
    }
    
    
    /////this is the top portion of the gridpane, for the title plus buttons////
    private HBox getNonadsInfoTxtGrid()
    {
        //GridPane pane1 = new GridPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: BLACK");

        Button instructions = new Button("Instructions");
        instructions.setPrefSize(100, 20);
        instructions.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            if (e.getSource()==instructions)
                instructionsInfoStage.showAndWait();
            else
                instructionsInfoStage.close();
           }
       });
                     
        instructions.setPrefSize(100, 20);
        instructionsInfoPane=new FlowPane();
        instructionsInfoPane.setVgap(10);
        //set background color of each Pane
        instructionsInfoPane.setStyle("-fx-background-color:white;-fx-padding:10px;");
        //add everything to panes (basically, info)
        //aboutInfoPane.getChildren().addAll(lblscene2, btnscene2);
     
         //make 2 scenes from 2 panes
        instructionsInfoScene = new Scene(instructionsInfoPane, 400, 500);
        //make another stage for scene2
        instructionsInfoStage = new Stage();
        instructionsInfoStage.setScene(instructionsInfoScene);
        //tell stage it is meannt to pop-up (Modal)
        instructionsInfoStage.initModality(Modality.APPLICATION_MODAL);
        instructionsInfoStage.setTitle("Pop up window");

        Button newGame = new Button("New Game");
        newGame.setPrefSize(100, 20);
        
        Text topText = new Text();
        topText.setWrappingWidth(500);
        topText.setFont(Font.font("Showcard gothic", FontWeight.BOLD, 46));
        topText.setFill(Color.WHITE);
        
        topText.setText("Nonads");
        topText.setTextAlignment(TextAlignment.CENTER);
        
        Button about = new Button("About");
        about.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            if (e.getSource()==about)
                aboutInfoStage.showAndWait();
            else
                aboutInfoStage.close();
           }
       });
                     
        about.setPrefSize(100, 20);
        aboutInfoPane=new FlowPane();
        aboutInfoPane.setVgap(10);
        //set background color of each Pane
        aboutInfoPane.setStyle("-fx-background-color:white;-fx-padding:10px;");
        //add everything to panes (basically, info)
        //aboutInfoPane.getChildren().addAll(lblscene2, btnscene2);
     
         //make 2 scenes from 2 panes
        aboutInfoScene = new Scene(aboutInfoPane, 400, 500);
        //make another stage for scene2
        aboutInfoStage = new Stage();
        aboutInfoStage.setScene(aboutInfoScene);
        //tell stage it is meannt to pop-up (Modal)
        aboutInfoStage.initModality(Modality.APPLICATION_MODAL);
        aboutInfoStage.setTitle("Pop up window");
        
        Button help = new Button("Need Help?");
        help.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            if (e.getSource()==help)
                aboutInfoStage.showAndWait();
            else
                aboutInfoStage.close();
           }
       });
                     
        help.setPrefSize(100, 20);
        helpInfoPane=new FlowPane();
        helpInfoPane.setVgap(10);
        //set background color of each Pane
        helpInfoPane.setStyle("-fx-background-color:white;-fx-padding:10px;");
        //add everything to panes (basically, info)
        //aboutInfoPane.getChildren().addAll(lblscene2, btnscene2);
     
         //make 2 scenes from 2 panes
        helpInfoScene = new Scene(helpInfoPane, 400, 500);
        //make another stage for scene2
        helpInfoStage = new Stage();
        helpInfoStage.setScene(helpInfoScene);
        //tell stage it is meannt to pop-up (Modal)
        helpInfoStage.initModality(Modality.APPLICATION_MODAL);
        helpInfoStage.setTitle("Pop up window");
        help.setPrefSize(100, 20);
        
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(instructions, newGame, topText, about, help);

        return hbox;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    private GridPane getGameInfoTxtGrid() 
    {
        GridPane pane = new GridPane();
        Label players = new Label("How many players?");
        players.setStyle("-fx-text-fill:black;-fx-font-size: 15px;-fx-font-weight: bold;");
        
        TextField player1name = new TextField("Player 1 Name");
        TextField player2name = new TextField("Player 2 Name");

        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(1);
        flow.setHgap(1);
        flow.setPrefWrapLength(100);
        
        ///////////////////////////////////3333///////////////////////////////
        Text tileStyle = new Text("Customiziziziziiz");
        tileStyle.setStyle("-fx-text-fill:black;-fx-font-size: 15px;-fx-font-weight: bold;");
        
        final ComboBox tileStylesComboBox = new ComboBox();
        tileStylesComboBox.getItems().addAll(
            "red",
            "SailorPluto",
            "pink",
            "SatanicManic",
            "swifty",
            "IrishLuck",
            "blach",
            "other"
        );
        tileStylesComboBox.setPromptText("Tile Style");
        tileStylesComboBox.setValue(null);
        tileStylesComboBox.setCellFactory(
        new Callback<ListView<String>, ListCell<String>>() {
            @Override public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }    
                    @Override public void updateItem(String item, 
                        boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);    
                                if (item.contains("SatanicManic")|| item.contains("red")) {
                                    setTextFill(Color.RED);
                                }
                                else if (item.contains("IrishLuck")){
                                    setTextFill(Color.GREEN);
                                }
                                else if (item.contains("SailorPluto")){
                                    setTextFill(Color.BLUE);
                                }
                                else {
                                    setTextFill(Color.BLACK);
                                }
                            }
                            else {
                                setText(null);
                            }
                        }
            };
            return cell;
            }
        });
        final ComboBox boardStylesComboBox = new ComboBox();
        
        boardStylesComboBox.getItems().addAll(
            "tabletop",
            "other",
            "marble"
        );
        boardStylesComboBox.setPromptText("Board Style");
        boardStylesComboBox.setValue(null);
        
        final ImageView imv = new ImageView();
        imv.setFitHeight(40);
        imv.setFitWidth(40);
        final Image image2 = new Image(Nonads.class.getResourceAsStream("thumb1.png"));
        imv.setImage(image2);
        final HBox pictureRegion6 = new HBox();        
        pictureRegion6.getChildren().add(imv);  
        
        final ImageView imv1 = new ImageView();
        imv1.setFitHeight(40);
        imv1.setFitWidth(40);
        final Image image3 = new Image(Nonads.class.getResourceAsStream("thumb1.png"));
        imv1.setImage(image3);
        final HBox pictureRegion7 = new HBox();        
        pictureRegion7.getChildren().add(imv1);  
        

        /////////////////////////////////////333//////////////////////////
        CheckBox cb4 = new CheckBox();
        cb4.setText("View Hand");
        cb4.setSelected(false);
        cb4.setStyle("-fx-font-size: 18;");
        
        CheckBox cb5 = new CheckBox();
        cb5.setText("View all Tiles");
        cb5.setSelected(false);
        cb5.setStyle("-fx-font-size: 18;");
        
        FlowPane hvh = new FlowPane();
        hvh.setPadding(new Insets(1, 0, 1, 0));
        hvh.setVgap(0);
        hvh.setHgap(1);
        hvh.setPrefWrapLength(100);

        CheckBox cb6 = new CheckBox();
        cb6.setSelected(false);
        cb6.setStyle("-fx-font-size: 18;");
        
        final ImageView humanR = new ImageView();
        humanR.setFitHeight(30);
        humanR.setFitWidth(30);
        final Image RedHuman = new Image(Nonads.class.getResourceAsStream("redHuman.png"));
        humanR.setImage(RedHuman);
        final HBox pictureRegion0 = new HBox();        
        pictureRegion0.getChildren().add(humanR);
        
        Label vs = new Label("vs ");
        vs.setStyle("-fx-font-size: 17;");
                
        final ImageView humanB = new ImageView();
        humanB.setFitHeight(30);
        humanB.setFitWidth(30);
        final Image BlueHuman = new Image(Nonads.class.getResourceAsStream("blueHuman.png"));
        humanB.setImage(BlueHuman);
        final HBox pictureRegionZ = new HBox();        
        pictureRegionZ.getChildren().add(humanB);
        
        hvh.getChildren().addAll(cb6, humanR, vs , humanB);
        
        CheckBox cb7 = new CheckBox();
        cb7.setSelected(false);
        cb7.setStyle("-fx-font-size: 18;");
        
        FlowPane hvc = new FlowPane();
        hvc.setPadding(new Insets(1, 0, 1, 0));
        hvc.setVgap(0);
        hvc.setHgap(1);
        hvc.setPrefWrapLength(80);
        
        final ImageView humanR2 = new ImageView();
        humanR2.setFitHeight(30);
        humanR2.setFitWidth(30);
        final Image RedHuman2 = new Image(Nonads.class.getResourceAsStream("redHuman.png"));
        humanR2.setImage(RedHuman2);
        final HBox pictureRegion3 = new HBox();        
        pictureRegion3.getChildren().add(humanR2);
        
        Label vs2 = new Label("vs ");
        vs2.setStyle("-fx-font-size: 17;");
        
        final ImageView comp = new ImageView();
        comp.setFitHeight(30);
        comp.setFitWidth(30);
        final Image comp1 = new Image(Nonads.class.getResourceAsStream("desktop.png"));
        comp.setImage(comp1);
        final HBox pictureRegion2 = new HBox();        
        pictureRegion2.getChildren().add(comp);
        
        hvc.getChildren().addAll(cb7, humanR2, vs2 , comp);
        
        Button btAdd1 = new Button("Start");
        DropShadow shadow = new DropShadow();
        //Adding the shadow when the mouse cursor is on
        btAdd1.addEventHandler(MouseEvent.MOUSE_ENTERED, 
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
            btAdd1.setEffect(shadow);
        }
    });
        //Removing the shadow when the mouse cursor is off
        btAdd1.addEventHandler(MouseEvent.MOUSE_EXITED, 
        new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            btAdd1.setEffect(null);
        }
    });
        btAdd1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            if (tileStylesComboBox.getValue() != null && 
                    boardStylesComboBox.getValue() != null) {
                System.out.println("it works!");                } 
            else
            {
                if (tileStylesComboBox.getValue() != null && 
                        boardStylesComboBox.getValue() == null) {
                  System.out.println("dude! pick a board style!");
                }
                if (tileStylesComboBox.getValue() == null && 
                        boardStylesComboBox.getValue() != null) {
                  System.out.println("dude! pick a tile style!");
                }
                if (tileStylesComboBox.getValue() == null && 
                        boardStylesComboBox.getValue() == null) {
                  System.out.println("dude! pick a board and tile style!");
                }
            }
            }
        });
        
        pane.add(boardStylesComboBox, 1, 2);
        pane.add(pictureRegion6, 2, 2);
        pane.add(tileStylesComboBox, 1, 3);
        pane.add(pictureRegion7, 2, 3);
        pane.add(cb4, 1, 4);
        pane.add(cb5, 1, 5);
        pane.add(hvh, 1, 6);
        pane.add(hvc, 1, 7);
        pane.add(player1name, 1, 8);
        pane.add(player2name, 1, 9);
        pane.add(btAdd1, 1, 10);

        return pane;
    }
    
    //pane for the right hand side where target pieces are displayed
    private GridPane getPlayerTargetPieceGrid() 
    {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
    
        Label player1 = new Label("Player 1 Target Piece");
        player1.setStyle("-fx-text-fill:black;-fx-font-size: 15px;-fx-font-weight: bold;");
        
        final ImageView imv = new ImageView();
        imv.setFitHeight(100);
        imv.setFitWidth(100);
        final Image image1 = new Image(Nonads.class.getResourceAsStream("thumb1.png"));
        imv.setImage(image1);
        final HBox pictureRegion = new HBox();        
        pictureRegion.getChildren().add(imv);
        
        Label player2 = new Label("Player 2 Target Piece");
        player2.setStyle("-fx-text-fill:black;-fx-font-size: 15px;-fx-font-weight: bold;");
        
        final ImageView imv2 = new ImageView();
        imv2.setFitHeight(100);
        imv2.setFitWidth(100);
        final Image image2 = new Image(Nonads.class.getResourceAsStream("thumb1.png"));
        imv2.setImage(image2);
        final HBox pictureRegion2 = new HBox();        
        pictureRegion2.getChildren().add(imv2);
        
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(player1, 1, 1);
        pane.add(pictureRegion, 1, 2);
        pane.add(player2, 1, 3);
        pane.add(pictureRegion2, 1, 4);

        return pane;
    }
    
        public static void main(String[] args) {
            launch(args);
        }    
        
}

