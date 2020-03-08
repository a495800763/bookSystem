package com.liumq.booksystem.init;

import com.liumq.booksystem.entity.Config;
import com.liumq.booksystem.service.ConfigService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


@Component
public class InitSystem  implements ServletContextListener, ApplicationContextAware {

    private  ApplicationContext applicationContext;

    @Autowired
    private ConfigService configService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loadData(sce.getServletContext());
    }

    public void loadData(ServletContext application)
    {
        Config config = configService.findById(1);
        application.setAttribute("config", config);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
