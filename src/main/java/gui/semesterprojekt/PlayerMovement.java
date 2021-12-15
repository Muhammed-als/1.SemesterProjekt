package gui.semesterprojekt;

import javafx.animation.*;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;


public class PlayerMovement extends  Application{

    private final int maxX = 650;
    private final int minX = 10;
    private int maxY = 450;
    private int minY = 10;

    private boolean goEast = true;
    private boolean goWest = true;
    private boolean goNorth = true;
    private boolean goSouth = true;
    private boolean playerIsMoving = false;
    private boolean collisionDetected = false;


    Scene scene;
    Stage window;

    Group root = new Group();

    Rooms rooms = new Rooms();


    private final ArrayList<Image> playerImages = new ArrayList<>();
    private final ImageView imageView = new ImageView();

    ImageView[] runForwardAnim = new ImageView[2];

    ImageView[] runBackwardAnim = new ImageView[2];

    ImageView[] runLeftAnim = new ImageView[2];

    ImageView[] runRightAnim = new ImageView[2];

    int imagesIndex = 0;
    Pane pane = new Pane();
    Timeline animation = new Timeline();


    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY(){
        return minY;
    }

    public int getMaxY(){
        return maxY;
    }

    public void runAnimImages( ImageView[] images){

        for (ImageView image : images) {

            image.setFitWidth(imageView.getFitWidth() - 25);
            image.setFitHeight(imageView.getFitHeight());
            image.relocate(imageView.getLayoutX(), imageView.getLayoutY());
        }

        EventHandler<ActionEvent> eventEventHandler = e-> {

            if(imagesIndex < 1){
                pane.getChildren().clear();
                imagesIndex++;
                pane.getChildren().add(images[imagesIndex]);

            }

            else if (imagesIndex ==1){
                imagesIndex = 0;
                pane.getChildren().clear();
                pane.getChildren().add(images[imagesIndex]);


            }

        };

        animation.setDelay(Duration.millis(100));
        animation.setOnFinished(eventEventHandler);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Scene move() {


        playerImages.add(0,new Image(new File("back.png").toURI().toString()));
        playerImages.add(1,new Image(new File("forward.png").toURI().toString()));
        playerImages.add(2,new Image(new File("left.png").toURI().toString()));
        playerImages.add(3,new Image(new File("right.png").toURI().toString()));


        imageView.setImage(playerImages.get(0));
        imageView.relocate(100,400);

        //Setting the position of the image
        imageView.setLayoutX(50);
        imageView.setLayoutY(50);

        //setting the fit height and width of the image view
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);



        root.getChildren().addAll(rooms.switchTob5(),imageView);

        scene = new Scene(root,700,850);

        window = new Stage();

        if(!playerIsMoving ) {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(imageView);
            translate.setDuration(Duration.millis(500));
            translate.setByY(0.3);
            translate.setCycleCount(TranslateTransition.INDEFINITE);
            translate.setAutoReverse(true);
            translate.play();

        }

        // Setting the pause menu unvisible
        rooms.menuForRooms().setVisible(false);


        // Images for run Anim
        Image run1 = new Image(new File("runForward.png").toURI().toString());
        Image run2 = new Image(new File("runForward2.png").toURI().toString());

        Image run3 = new Image(new File("runBack.png").toURI().toString());
        Image run4 = new Image(new File("runBack2.png").toURI().toString());

        Image run5 = new Image(new File("runLeft.png").toURI().toString());
        Image run6 = new Image(new File("runLeft2.png").toURI().toString());

        Image run7 = new Image(new File("runRight.png").toURI().toString());
        Image run8 = new Image(new File("runRight2.png").toURI().toString());


        runForwardAnim[0] = new ImageView(run1);
        runForwardAnim[1] = new ImageView(run2);

        runBackwardAnim[0] = new ImageView(run3);
        runBackwardAnim[1] = new ImageView(run4);

        runLeftAnim[0] = new ImageView(run5);
        runLeftAnim[1] = new ImageView(run6);

        runRightAnim[0] = new ImageView(run7);
        runRightAnim[1] = new ImageView(run8);

        // setting run Anim unvisible in the start
        pane.setVisible(false);



        scene.setOnKeyPressed(keyEvent -> {


            playerIsMoving = true;

            if (rooms.winCondition()) {
                root.getChildren().clear();
                root.getChildren().add(rooms.victoryScreen());
                System.out.println("Hallo");

            }
            if(!rooms.isGameIsPaused()){
                if(keyEvent.getCode().equals(KeyCode.W)){
                    pane.setVisible(true);

                    imageView.setVisible(false);


                    imageView.relocate(imageView.getLayoutX(),imageView.getLayoutY()-20);


                    runAnimImages(runForwardAnim);


                }


                else if(keyEvent.getCode().equals(KeyCode.S)){
                    //imageView.setImage(playerImages.get(0));

                    pane.setVisible(true);

                    imageView.setVisible(false);

                    imageView.relocate(imageView.getLayoutX(),imageView.getLayoutY()+20);


                    runAnimImages(runBackwardAnim);

                }

                else if(keyEvent.getCode().equals(KeyCode.A)){

                    pane.setVisible(true);

                    imageView.setVisible(false);

                    imageView.relocate(imageView.getLayoutX()-20,imageView.getLayoutY());
                    //imageView.setImage(playerImages.get(2));



                    runAnimImages(runLeftAnim);
                }

                else if(keyEvent.getCode().equals(KeyCode.D)){

                    pane.setVisible(true);

                    imageView.setVisible(false);
                    imageView.relocate(imageView.getLayoutX()+20,imageView.getLayoutY());

                   runAnimImages(runRightAnim);



                }

                else if(keyEvent.getCode().equals(KeyCode.E)){
                    rooms.pickUpTrash(imageView);

                }
            }

            if(keyEvent.getCode().equals(KeyCode.P)){
                if(!rooms.menuForRooms().isVisible()){
                    rooms.menuForRooms().setVisible(true);
                    rooms.setGameIsPaused(true);

                }

                else{
                    rooms.menuForRooms().setVisible(false);
                    rooms.setGameIsPaused(false);
                }

            }
            rooms.trashCanColission(imageView);

            changeRooms();

        });


        root.getChildren().addAll(pane);
        scene.setOnKeyReleased(e ->{

            if(!rooms.isGameIsPaused()) {


                if (e.getCode().equals(KeyCode.W)) {
                    imageView.setImage(playerImages.get(1));
                    pane.setVisible(false);
                    imageView.setVisible(true);
                    animation.stop();
                }

                if (e.getCode().equals(KeyCode.S)) {
                    imageView.setImage(playerImages.get(0));
                    pane.setVisible(false);
                    imageView.setVisible(true);
                    animation.stop();
                }


                if (e.getCode().equals(KeyCode.A)) {
                    imageView.setImage(playerImages.get(2));
                    pane.setVisible(false);
                    imageView.setVisible(true);
                    animation.stop();
                }

                if (e.getCode().equals(KeyCode.D)) {
                    imageView.setImage(playerImages.get(3));
                    pane.setVisible(false);
                    imageView.setVisible(true);
                    animation.stop();
                }
            }
        });

        return scene;
    }


    public void collisionWater(){




        if(rooms.game.getCurrentRoom().equals(rooms.game.b1)||rooms.game.getCurrentRoom().equals(rooms.game.b2)||rooms.game.getCurrentRoom().equals(rooms.game.b3)){
            minY = 60;


        }

        else{
            minY = 0;

        }


    }

    public void collisionEarth(){




        if(rooms.game.getCurrentRoom().equals(rooms.game.b7)||rooms.game.getCurrentRoom().equals(rooms.game.b8)||rooms.game.getCurrentRoom().equals(rooms.game.b9)){
            maxY = 700;

        }

        else {
            maxY = 830;
        }


    }

    public void changeRooms(){


        collisionWater();
        collisionEarth();




        // hvis man går ned
        if(imageView.getLayoutY() > maxY){
            imageView.setLayoutY(minY);

            // hvis man er i b1
            if(goSouth  && goEast && !goWest && !goNorth){

                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob4(), imageView, pane);

                goNorth = true;

            }

            // hvis man er i b2
            else if(goWest  &&  goEast && goSouth && !goNorth ){

                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob5(),imageView, pane);

                goNorth = true;
            }

            // hvis man er i b3
            else if(goWest  &&  goSouth && !goEast && !goNorth ){

                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob6(),imageView, pane);
                goNorth = true;
            }

            // hvis man er i b4
            else if(goSouth  &&  goNorth && goEast && !goWest ){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob7(),imageView, pane);
                goSouth = false;
            }

            // hvis man er i b5
            else if(goNorth && goSouth && goWest && goEast){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob8(),imageView, pane);
                goSouth = false;
            }

            // hvis man er i b6
            else if(goNorth && goSouth && goWest && !goEast){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob9(),imageView, pane);
                goSouth = false;


            }

            // hvis man er i b7
            else if(goNorth  && goEast && !goWest && !goSouth){
                imageView.setLayoutY(maxY);



            }



            // hvis man er i b8
            else if(goNorth && goWest && goEast && !goSouth){

                imageView.setLayoutY(maxY);



            }

            // hvis man er i b9
            else if(goNorth && goWest && !goEast && !goSouth){
                imageView.setLayoutY(maxY);


            }

        }

        // hvis man går op
        else if(imageView.getLayoutY() < minY){


            imageView.setLayoutY(maxY);

            // hvis man er i b1
            if(goSouth  && goEast && !goWest && !goNorth){
                imageView.setLayoutY(minY);

            }

            // hvis man er i b2
            else if(goWest  &&  goEast && goSouth && !goNorth ){
                imageView.setLayoutY(minY);
            }

            // hvis man er i b3
            else if(goWest  &&  goSouth && !goEast && !goNorth ){
                imageView.setLayoutY(minY);
            }

            // hvis man er i b4
            else if(goSouth  &&  goNorth && goEast && !goWest ){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob1(),imageView, pane);
                goNorth = false;

            }

            // hvis man er i b5
            else if(goNorth && goSouth && goWest && goEast){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob2(),imageView, pane);
                goNorth = false;
            }

            // hvis man er i b6
            else if(goNorth && goSouth && goWest && !goEast){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob3(),imageView, pane);
                goNorth = false;



            }

            // hvis man er i b7
            else if(goNorth  && goEast && !goWest && !goSouth){

                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob4(),imageView, pane);
                goWest = false;
                goSouth = true;

            }



            // hvis man er i b8
            else if(goNorth && goWest && goEast && !goSouth){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob5(),imageView, pane);
                goSouth = true;

            }

            // hvis man er i b9
            else if(goNorth && goWest && !goEast && !goSouth){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob6(),imageView, pane);
                goSouth = true;

            }




        }

        // hvis man går til venstre
        else if (imageView.getLayoutX() < minX){
            imageView.setLayoutX(maxX);


            // hvis man er i b1
            if(goSouth  && goEast && !goWest && !goNorth){
                imageView.setLayoutX(minX);

            }

            // hvis man er i b2
            else if(goWest  &&  goEast && goSouth && !goNorth ){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob1(),imageView, pane);
                goWest = false;
            }

            // hvis man er i b3
            else if(goWest  &&  goSouth && !goEast && !goNorth ){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob2(),imageView, pane);
                goEast = true;
            }

            // hvis man er i b4
            else if(goSouth  &&  goNorth && goEast && !goWest ){
                imageView.setLayoutX(minX);

            }

            // hvis man er i b5
            else if(goNorth && goSouth && goWest && goEast){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob4(),imageView, pane);
                goWest = false;
            }

            // hvis man er i b6
            else if(goNorth && goSouth && goWest && !goEast){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob5(),imageView, pane);
                goEast = true;



            }

            // hvis man er i b7
            else if(goNorth  && goEast && !goWest && !goSouth){

                imageView.setLayoutX(minX);

            }



            // hvis man er i b8
            else if(goNorth && goWest && goEast && !goSouth){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob7(),imageView, pane);
                goWest = false;

            }

            // hvis man er i b9
            else if(goNorth && goWest && !goEast && !goSouth){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob8(),imageView, pane);
                goEast = true;

            }

        }

        // hvis man går til højre

        else if(imageView.getLayoutX() > maxX){

            imageView.setLayoutX(minX);

            // hvis man er i b1
            if(goSouth  && goEast && !goWest && !goNorth){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob2(),imageView, pane);
                goWest = true;

            }

            // hvis man er i b2
            else if(goWest  &&  goEast && goSouth && !goNorth ){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob3(),imageView, pane);
                goEast = false;
            }

            // hvis man er i b3
            else if(goWest  &&  goSouth && !goEast && !goNorth ){
                imageView.setLayoutX(maxX);
            }

            // hvis man er i b4
            else if(goSouth  &&  goNorth && goEast && !goWest ){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob5(),imageView, pane);
                goWest = true;

            }

            // hvis man er i b5
            else if(goNorth && goSouth && goWest && goEast){
                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob6(),imageView, pane);
                goEast = false;
            }

            // hvis man er i b6
            else if(goNorth && goSouth && goWest && !goEast){
                imageView.setLayoutX(maxX);



            }

            // hvis man er i b7
            else if(goNorth  && goEast && !goWest && !goSouth){

                root.getChildren().clear();
                root.getChildren().addAll(rooms.switchTob8(),imageView, pane);
                goWest = true;

            }



            // hvis man er i b8
            else if(goNorth && goWest && goEast && !goSouth){
                root.getChildren().clear();

                root.getChildren().addAll(rooms.switchTob9(),imageView, pane);
                goEast = false;

            }

            // hvis man er i b9
            else if(goNorth && goWest && !goEast && !goSouth){
                imageView.setLayoutX(maxX);

            }
        }



    }



    @Override
    public void start(Stage stage) throws Exception {
        move();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
