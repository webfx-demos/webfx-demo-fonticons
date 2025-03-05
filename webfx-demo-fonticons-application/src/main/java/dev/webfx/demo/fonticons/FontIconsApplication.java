package dev.webfx.demo.fonticons;

import dev.webfx.extras.fonticons.FontIcon;
import dev.webfx.extras.fonticons.FontIcons;
import dev.webfx.extras.fonticons.IconFont;
import dev.webfx.extras.fonticons.IconPack;
import dev.webfx.extras.fonticons.feather.FeatherPack;
import dev.webfx.extras.panes.ColumnsPane;
import dev.webfx.extras.util.control.Controls;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.platform.util.collection.Collections;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FontIconsApplication extends Application {

    private static final double ICON_CONTAINER_WIDTH = 115;
    private static final double ICON_CONTAINER_HEIGHT = 135;

    private final ObjectProperty<Pane> selectedIconContainerProperty = new SimpleObjectProperty<>();

    @Override
    public void start(Stage primaryStage) {
        ColumnsPane columnsPane = new ColumnsPane(20, 20);
        columnsPane.setFixedColumnWidth(ICON_CONTAINER_WIDTH);
        columnsPane.setFixedRowHeight(ICON_CONTAINER_HEIGHT);
        columnsPane.setPadding(new Insets(20));
        columnsPane.setBackground(Background.fill(Color.BLACK));

        IconPack iconPack = FeatherPack.getInstance();
        IconFont iconFont = iconPack.getFonts()[0];

        FontIcons.applyFontCssClass(columnsPane, iconFont);

        for (FontIcon fontIcon : iconPack.getIcons()) {
            Text icon = FontIcons.newText(fontIcon);
            icon.getStyleClass().add("icon");
            Text iconName = new Text(fontIcon.getDisplayName());
            iconName.getStyleClass().add("icon-name");
            Pane iconContainer = new Pane(icon, iconName) {
                @Override
                protected void layoutChildren() {
                    icon.relocate(ICON_CONTAINER_WIDTH / 2 - icon.getLayoutBounds().getCenterX(), 20);
                    iconName.relocate(ICON_CONTAINER_WIDTH / 2 - iconName.getLayoutBounds().getCenterX(), ICON_CONTAINER_HEIGHT - 40);
                }
            };
            iconContainer.setMinSize(ICON_CONTAINER_WIDTH, ICON_CONTAINER_HEIGHT);
            iconContainer.setPrefSize(ICON_CONTAINER_WIDTH, ICON_CONTAINER_HEIGHT);
            iconContainer.setMaxSize(ICON_CONTAINER_WIDTH, ICON_CONTAINER_HEIGHT);
            iconContainer.setPadding(new Insets(20, 0, 20, 0));
            iconContainer.getStyleClass().add("icon-container");
            iconContainer.setOnMouseClicked(e -> selectedIconContainerProperty.set(iconContainer));
            columnsPane.getChildren().add(iconContainer);
        }

        FXProperties.runOnPropertyChange((o, oldSelected, newSelected) -> {
            if (oldSelected != null)
                Collections.addIfNotContainsOrRemove(oldSelected.getStyleClass(), false, "selected");
            if (newSelected != null)
                Collections.addIfNotContainsOrRemove(newSelected.getStyleClass(), true, "selected");
        }, selectedIconContainerProperty);

        ScrollPane scrollPane = Controls.createVerticalScrollPane(columnsPane);
        Scene scene = new Scene(scrollPane, 1500, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}