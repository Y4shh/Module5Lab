module net.y4sh.module5lab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens net.y4sh.module5lab to javafx.fxml;
    exports net.y4sh.module5lab;
}