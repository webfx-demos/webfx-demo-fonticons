// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.demo.fonticons.application {

    // Direct dependencies modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires webfx.extras.fonticons;
    requires webfx.extras.fonticons.feather;
    requires webfx.extras.panes;
    requires webfx.extras.util.control;
    requires webfx.kit.util;
    requires webfx.platform.util;

    // Exported packages
    exports dev.webfx.demo.fonticons;

    // Provided services
    provides javafx.application.Application with dev.webfx.demo.fonticons.FontIconsApplication;

}