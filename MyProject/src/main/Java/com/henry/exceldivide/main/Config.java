package com.henry.exceldivide.main;

import com.henry.exceldivide.cron.MyTask;
import com.henry.exceldivide.routes.FontRoutes;
import com.jfinal.config.*;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

public class Config extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入
        constants.setInjectDependency(true);
        constants.setDevMode(true);
        constants.setBaseDownloadPath("upload");

    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new FontRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    @SuppressWarnings("uncheck")
    public void configPlugin(Plugins plugins) {
        Cron4jPlugin cp = new Cron4jPlugin();
        cp.addTask("0 0 * * *", new MyTask());
        plugins.add(cp);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    public static void main(String[] args) {
        UndertowServer.start(Config.class,80,true);
    }
}
