package com.ezyxip.runiwweb;

import com.ezyxip.runiwweb.prestart.SourcePrestarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootApplication
@Controller
public class RuniwWebApplication {

    public static Logger logger = Logger.getLogger(RuniwWebApplication.class.getName());

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RuniwWebApplication.class, args);
        SourcePrestarter prestarter = (SourcePrestarter) applicationContext.getBean("H2DBPrestarter");
        logger.info("Начальная конфигурация базы данных: " + prestarter.create());
    }

}
