module gui.semesterprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens gui.semesterprojekt to javafx.fxml;
    exports gui.semesterprojekt;

}