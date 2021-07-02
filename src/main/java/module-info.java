open module azterketa {
  requires de.jensd.fx.fontawesomefx.fontawesome;
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires org.apache.commons.codec;
  requires java.sql;
    requires sqlite.jdbc;
    requires com.google.gson;

    exports ehu.isad;
}
