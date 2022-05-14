module cz.cvut.fel.pjv{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens cz.cvut.fel.pjv to javafx.fxml;
    exports cz.cvut.fel.pjv.gui;
    opens cz.cvut.fel.pjv.gui to javafx.fxml;
    exports cz.cvut.fel.pjv;
    exports cz.cvut.fel.pjv.game;
    opens cz.cvut.fel.pjv.game to javafx.fxml;
}