package gui.semesterprojekt;
import Domain.Item;
import Domain.Room;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import Domain.Game;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Rooms {

    Game game = new Game();
    Pane pane = new Pane();
    private boolean gameIsPaused = false;
    private boolean openInventory = false;
    private boolean winCondition = false;
    int scoreNumber = 0;
    int randomX = 0;
    int randomY = 0;
    Text score = new Text();

    private final Text roomText = new Text();
    private final ArrayList<Bounds> trashBound = new ArrayList<>();
    private final ArrayList<Group> removedTrash = new ArrayList<>();
    private final ListView<Group> listView = new ListView<>();
    private final Button dropButton = new Button();
    private final Button dropTrash = new Button();

    private ArrayList<Group> trash;

    private ImageView waterImage = new ImageView();

    private ImageView earthImage = new ImageView();

    private Image trashImage = null;

    private ImageView trashImageView = new ImageView();

    public Rooms() {
    }

    public boolean isWinCondition() {
        return winCondition;
    }

    public boolean isGameIsPaused() {
        return gameIsPaused;
    }

    public void setGameIsPaused(boolean gameIsPaused) {
        this.gameIsPaused = gameIsPaused;
    }

    public void setOpenInventory(boolean openInventory) {
        this.openInventory = openInventory;
    }


    public Pane helpImage(Text helpText) {
        Pane pane = new Pane();

        Image helpImage = new Image(new File("help.png").toURI().toString());
        ImageView imageView = new ImageView(helpImage);
        imageView.setScaleX(0.1);
        imageView.setScaleY(0.1);
        imageView.setLayoutX(400);
        imageView.setLayoutY(-220);


        pane.getChildren().add(imageView);

        imageView.setOnMouseEntered(e -> {
            imageView.setScaleX(0.13);
            imageView.setScaleY(0.13);
            imageView.setCursor(Cursor.HAND);
        });

        imageView.setOnMouseExited(e -> {
            imageView.setScaleX(0.1);
            imageView.setScaleY(0.1);
        });
        StackPane stackPane = new StackPane();
        stackPane.setVisible(false);
        imageView.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (!stackPane.isVisible()) {
                    stackPane.setVisible(true);
                    Rectangle rectangle = new Rectangle(800, 150);
                    rectangle.setTranslateX(-50);
                    rectangle.setTranslateY(100);
                    rectangle.setFill(Color.BLACK);
                    rectangle.setOpacity(0.8);
                    rectangle.setEffect(new Glow());
                    helpText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    helpText.setTranslateX(rectangle.getTranslateX());
                    helpText.setTranslateY(rectangle.getTranslateY());
                    helpText.setFill(Color.WHITE);
                    helpText.setTextAlignment(TextAlignment.CENTER);

                    stackPane.getChildren().add(0, rectangle);
                    stackPane.getChildren().add(helpText);

                } else {
                    stackPane.getChildren().clear();
                    stackPane.setVisible(false);
                }
            }
        });

        pane.getChildren().addAll(stackPane);

        return pane;
    }



    public Group beachBg() {

        Group root = new Group();

        Image beach = new Image(new File("beach.png").toURI().toString());
        ImageView beachView = new ImageView(beach);
        beachView.setScaleX(2.5);
        beachView.setScaleY(2.5);
        beachView.setTranslateX(500);

        root.getChildren().add(beachView);

        if (game.getCurrentRoom().equals(game.b1) || game.getCurrentRoom().equals(game.b2) || game.getCurrentRoom().equals(game.b3)) {

            Image water = new Image(new File("water.png").toURI().toString());
            waterImage = new ImageView(water);
            waterImage.setScaleX(2);
            waterImage.setScaleY(0.2);
            waterImage.setTranslateY(-350);
            waterImage.setTranslateX(360);
            waterImage.setEffect(new DropShadow());
            root.getChildren().add(waterImage);

        }

        if (game.getCurrentRoom().equals(game.b7) || game.getCurrentRoom().equals(game.b8) || game.getCurrentRoom().equals(game.b9)) {

            Image earth = new Image(new File("earth.png").toURI().toString());
            earthImage = new ImageView(earth);
            earthImage.setScaleX(1);
            earthImage.setScaleY(0.1);
            earthImage.setTranslateY(100);
            earthImage.setEffect(new Glow());
            root.getChildren().add(earthImage);
        }

        return root;


    }

    public Pane menuForRooms() {


        // menu background
        Rectangle backGround = new Rectangle(600, 450);


        // Pos
        backGround.setTranslateX(50);
        backGround.setTranslateY(200);
        backGround.setFill(Color.SANDYBROWN);

        // menus titlen
        Text text = new Text("PAUSED");
        text.setEffect(new DropShadow());
        text.setFont(Font.font(60));
        text.setTranslateY(25);
        text.setFill(Color.WHITE);
        text.setTranslateX(backGround.getTranslateX());

        // menus knapper
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new Button("Resume"));
        buttons.add(new Button("How"));
        buttons.add(new Button("Quit"));

        // back knap
        Button back = new Button("Back");
        back.setFont(Font.font(30));
        back.setTranslateY(backGround.getTranslateY() + 200);
        back.setTranslateX(backGround.getTranslateX());

        // Sætter knappers pos
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setFont(Font.font(30));
            buttons.get(i).setTranslateX(backGround.getTranslateX());
            buttons.get(i).setTranslateY(125 + (i * 100));

        }

        // sætter effekter og handlinger for knapper
        for (Button button : buttons) {
            button.setEffect(new DropShadow());
            button.setDefaultButton(true);


            button.setOnMouseEntered(event -> {
                button.setScaleY(1.1);
                button.setScaleX(1.1);
            });

            button.setOnMouseExited(e -> {
                button.setScaleY(1);
                button.setScaleX(1);
            });
        }

        // Resume knap
        buttons.get(0).setOnMouseClicked(event -> {

            // menuen bliver skjult
            pane.setVisible(false);
            gameIsPaused = false;
        });

        // How knap
        buttons.get(1).setOnMouseClicked(event -> {

            // det bliver lavet en ny background med nye tekster
            StackPane stackPane = new StackPane();
            Text howToPlay = new Text("HOW TO PLAY");
            howToPlay.setEffect(new DropShadow());
            howToPlay.setFont(Font.font(50));
            howToPlay.setFill(Color.WHITE);
            howToPlay.setTranslateX(backGround.getTranslateX());

            Text howTo = new Text("""
                    \t Control \t 'W,A,S,D'

                    \t Pick up \t 'E'

                    \t Drop \t\t 'R'""");

            howTo.setFont(Font.font(25));
            howTo.setTranslateY(backGround.getTranslateY());

            stackPane.getChildren().addAll(backGround, howToPlay, howTo);
            stackPane.getChildren().add(back);

            pane.getChildren().clear();
            pane.getChildren().addAll(stackPane);


        });

        // Quit knap
        buttons.get(2).setOnMouseClicked(event -> {
            System.exit(0);
        });

        // tilføjer alle knapper i en stackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backGround, text);
        stackPane.getChildren().addAll(buttons);

        // hvis man trykker på back, bliver baggrund, tekster og knapper skiftet til det de var før.
        back.setDefaultButton(true);
        back.setOnMouseClicked(e -> {
            pane.getChildren().clear();
            StackPane backPane = new StackPane();
            backPane.getChildren().addAll(backGround, text);
            backPane.getChildren().addAll(buttons);
            pane.getChildren().addAll(backPane);

        });
        back.setOnMouseEntered(event -> {
            back.setScaleY(1.1);
            back.setScaleX(1.1);
        });

        back.setOnMouseExited(e -> {
            back.setScaleY(1);
            back.setScaleX(1);
        });


        pane.getChildren().addAll(stackPane);

        return pane;
    }

    public Pane victoryScreen() {
        Pane pane = new Pane();
        // Victory background
        Rectangle backGround = new Rectangle(600, 450);

        // Pos
        backGround.setTranslateX(50);
        backGround.setTranslateY(200);
        backGround.setFill(Color.SANDYBROWN);

        // victory title
        Text text = new Text("VICTORY");
        text.setEffect(new DropShadow());
        text.setFont(Font.font(120));
        text.setTranslateY(50);
        text.setFill(Color.WHITE);
        text.setTranslateX(backGround.getTranslateX());

        // victory quit knap
        Button quit = new Button ("QUIT");
        quit.setFont(Font.font(30));
        quit.setTranslateY(backGround.getTranslateY() + 200);
        quit.setTranslateX(backGround.getTranslateX());

        quit.setFont(Font.font(30));
        quit.setTranslateX(50);
        quit.setTranslateY(300);

        //Sætter effekter
        quit.setEffect(new DropShadow());
        quit.setDefaultButton(true);

            quit.setOnMouseEntered(event -> {
                quit.setScaleY(1.1);
                quit.setScaleX(1.1);
            });

            quit.setOnMouseExited(e -> {
                quit.setScaleY(1);
                quit.setScaleX(1);
            });

        quit.setOnMouseClicked(event ->{
                    System.exit(0);
                });

        // tilføjer knap til et stackpane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backGround, text);
        stackPane.getChildren().add(quit);

        pane.getChildren().add(stackPane);

        return pane;
    }


    public VBox inventory(){

        VBox vBox = new VBox(10);

        listView.getItems().clear();
        String scoreText = "Score \t";
        score.setEffect(new DropShadow());
        score.setFont(Font.font(30));
        score.setFill(Color.WHITE);
        score.setText(scoreText + this.scoreNumber);
        score.setTranslateX(40);
        score.setTranslateY(40);

        listView.setEffect(new DropShadow());


        vBox.setAlignment(Pos.CENTER);

        vBox.setLayoutY(100);
        vBox.setVisible(false);
        dropButton.setFont(Font.font(15));
        dropButton.setText("Drop");

        dropTrash.setFont(Font.font(15));
        dropTrash.setText("Drop trash");
        dropTrash.setLayoutX(40);
        dropTrash.setLayoutY(650);

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for(Group group : removedTrash) {

            for (Node child : group.getChildren()) {
                child.setVisible(true);
            }

            listView.getItems().add(group);
        }

        dropButton.setOnAction(event ->{


            if(!listView.getItems().isEmpty()){
                for(Group group : listView.getItems()){
                    scoreNumber +=10;
                    score.setText(scoreText + this.scoreNumber);
                    removedTrash.clear();
                }
                listView.getItems().clear();
            }
        });

        dropTrash.setOnMouseClicked(event ->{

            if(!vBox.isVisible()){
                vBox.setVisible(true);
            }

            else{
                vBox.setVisible(false);
            }


        });



        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll(listView,dropButton);

        System.out.println(openInventory);
        winCondition();
        return vBox;


    }
    public ArrayList<Bounds> getTrashBound() {
        return trashBound;
    }

    public Group trashes() {
        PlayerMovement playerMovement = new PlayerMovement();
        Room room = game.getCurrentRoom();
        ImageView imageView;
        trash = new ArrayList<>();
        Image[] imagesArray = new Image[5];
        imagesArray[0] = new Image(new File("can.png").toURI().toString());
        imagesArray[1] = new Image(new File("cig.png").toURI().toString());
        imagesArray[2] = new Image(new File("straw.png").toURI().toString());
        imagesArray[3] = new Image(new File("shoe.png").toURI().toString());
        imagesArray[4] = new Image(new File("bottle.png").toURI().toString());
        for (Item item : room.getItems()) {
            for (Image image : imagesArray) {
                if (image.getUrl().contains(item.getName())) {
                    imageView = new ImageView(image);
                    imageView.setScaleY(0.1);
                    imageView.setScaleX(0.1);
                    randomX = (int) (Math.random() * (playerMovement.getMaxX() - 500) - playerMovement.getMinX()) + playerMovement.getMinX() + 150;
                    randomY = (int) (Math.random() * (playerMovement.getMaxY() - 100) - (playerMovement.getMinY()) + (playerMovement.getMinY() + 150));
                    imageView.setLayoutX(randomX);
                    imageView.setLayoutY(randomY);

                    trash.add(new Group(imageView));

                    //imageView.setLayoutX((int) (Math.random() * (playerMovement.getMaxX()-300)-playerMovement.getMinX())+playerMovement.getMinX()+50);


                    //imageView.setLayoutY((int) (Math.random() * (playerMovement.getMaxY()-400)-(playerMovement.getMinY())+(playerMovement.getMinY()+50)));
                }
            }
        }
        System.out.println(room.getItems());
        System.out.println(trash.size());
        Group mainGroup = new Group();
        mainGroup.getChildren().addAll(trash);
        return  mainGroup;
    }

    public void setTrashBound(Bounds trashBound) {

        this.trashBound.add(trashBound);
    }

    public void pickUpTrash(ImageView collision){

        for(Item item : game.getCurrentRoom().getItems()){
            for(Group group : trash){


                setTrashBound(group.getBoundsInParent());
                for(Bounds bounds : trashBound){

                    if(collision.getBoundsInParent().intersects(bounds) && bounds == group.getBoundsInParent()){

                        for(Node child : group.getChildren()){

                            if(((ImageView) child).getImage().getUrl().contains(item.getName())){
                                removedTrash.add(group);
                                game.getCurrentRoom().removeItem(item);
                                System.out.println(game.getCurrentRoom().getItems());
                                child.setVisible(false);
                            }
                        }
                    }
                }
            }
        }
    }

    public void trashCanColission(ImageView collision){

        if(collision.getBoundsInParent().intersects(trashImageView.getBoundsInParent())){
            dropTrash.setVisible(true);
        }
        else{
           dropTrash.setVisible(false);
        }
    }


    public boolean winCondition(){

        return scoreNumber >= 10;
    }


    public Group switchTob1(){
        Group group = new Group();
        roomText.setText(game.b1.getShortDescription());
        game.setCurrentRoom(game.b1);


        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);



        return group;

    }

    public Group switchTob2(){
        Group group = new Group();

        roomText.setText(game.b2.getShortDescription());
        game.setCurrentRoom(game.b2);

        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);


        return group;
    }

    public Group switchTob3(){
        Group group = new Group();

        roomText.setText(game.b3.getShortDescription());
        game.setCurrentRoom(game.b3);

        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);

        return group;
    }

    public Group switchTob4(){
        Group group = new Group();

        roomText.setText(game.b4.getShortDescription());
        game.setCurrentRoom(game.b4);

        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);

        return group;
    }


    public Group switchTob5(){
        Group group = new Group();

        roomText.setText(game.b5.getShortDescription());
        game.setCurrentRoom(game.b5);

        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);

        return group;
    }

    public Group switchTob6(){
        Group group = new Group();

        roomText.setText(game.b6.getShortDescription());
        game.setCurrentRoom(game.b6);

        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);

        return group;
    }

    public Group switchTob7(){

        Group group = new Group();

        roomText.setText(game.b7.getShortDescription());
        game.setCurrentRoom(game.b7);

        trashImage = new Image(new File("trashcan.png").toURI().toString());
        trashImageView = new ImageView(trashImage);
        trashImageView.setLayoutY(650);
        trashImageView.setLayoutX(40);



        group.getChildren().addAll(beachBg(),helpImage(roomText),menuForRooms());
        group.getChildren().add(trashImageView);
        group.getChildren().add(score);
        group.getChildren().add(dropTrash);
        group.getChildren().add(inventory());


        return group;
    }

    public Group switchTob8(){

        Group group = new Group();

        roomText.setText(game.b8.getShortDescription());
        game.setCurrentRoom(game.b8);

        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);

        return group;
    }

    public Group switchTob9(){
        Group group = new Group();

        roomText.setText(game.b9.getShortDescription());
        game.setCurrentRoom(game.b9);



        group.getChildren().addAll(beachBg(),trashes(),helpImage(roomText),menuForRooms());
        group.getChildren().add(score);

        return group;
    }


}
