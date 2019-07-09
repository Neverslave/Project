package com.henry.exceldivide.routes;

import com.henry.exceldivide.controller.IndexController;
import com.jfinal.config.Routes;

public class FontRoutes extends Routes {
    @Override
    public void config() {
        add("/", IndexController.class);
    }
}
