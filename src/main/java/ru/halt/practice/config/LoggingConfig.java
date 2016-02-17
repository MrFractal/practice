package ru.halt.practice.config;


import org.apache.log4j.*;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.varia.LevelRangeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Petr Rudenko on 09.02.2016.
 */
@Configuration
@PropertySource(value = {"classpath:log4j.properties"})
public class LoggingConfig {
    @Autowired
    private Environment env;

    @Bean
    public ConsoleAppender consoleAppender() {
        System.out.println("In LoggingConfig!!!!!!!!");
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern(env.getRequiredProperty("log4j.appender.Stdout.layout.conversionPattern"));
        consoleAppender.setLayout(patternLayout);
        return consoleAppender;
    }

    @Bean
    public FileAppender fileAppender() {
        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setThreshold(Level.ALL);
        fileAppender.setFile("c:\\my.log"); //env.getRequiredProperty("log4j.appender.file.File")
        fileAppender.setMaxFileSize("100KB");
        fileAppender.setMaxBackupIndex(1);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern(env.getRequiredProperty("log4j.appender.Stdout.layout.conversionPattern"));
        fileAppender.setLayout(patternLayout);
        return fileAppender;
    }


    /*
    @Bean
    public SMTPAppender mailAppender() {
        SMTPAppender mailAppender = new SMTPAppender();
        mailAppender.setThreshold(Level.ERROR);
        mailAppender.setSMTPDebug(true);
        mailAppender.setSMTPProtocol("smtps");
        mailAppender.setSMTPHost("smtp.gmail.com");
        mailAppender.setSMTPPort(465);
        mailAppender.setSMTPUsername("learnintouch@gmail.com");
        mailAppender.setSMTPPassword("xxxxxx");
        mailAppender.setFrom("stephane@learnintouch.com");
        mailAppender.setTo("stephane@learnintouch.com");
        mailAppender.setSubject("[LOG] Java - learnintouch");
        mailAppender.setBufferSize(1);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d{ABSOLUTE} %5p %c{1}:%L - %m%n");
        mailAppender.setLayout(patternLayout);
        LevelRangeFilter levelRangeFilter = new LevelRangeFilter();
        levelRangeFilter.setLevelMin(Level.DEBUG);
        levelRangeFilter.setLevelMax(Level.FATAL);
        mailAppender.addFilter(levelRangeFilter);
        return mailAppender;
    }
    */

    @Bean
    public Logger registerSpringLogger() {
        Logger logger = Logger.getLogger("org.springframework");
        logger.addAppender(consoleAppender());
        return logger;
    }

    @Bean
    public Logger registerThalasoftLogger() {
        Logger logger = Logger.getLogger("com.thalasoft");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(consoleAppender());
        logger.addAppender(fileAppender());
        return logger;
    }

    @Bean
    public Logger registerHibernateTypeLogger() {
        Logger logger = Logger.getLogger("org.hibernate.type");
        logger.setLevel(Level.TRACE);
        logger.addAppender(consoleAppender());
        return logger;
    }

    @Bean
    public Logger registerHibernateSqlLogger() {
        Logger logger = Logger.getLogger("org.hibernate.sql");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(consoleAppender());
        return logger;
    }

    @Bean
    public Logger registerJdbcSqlOnlyLogger() {
        Logger logger = Logger.getLogger("jdbc.sqlonly");
        logger.setLevel(Level.TRACE);
        logger.setAdditivity(false);
        logger.addAppender(consoleAppender());
        logger.addAppender(fileAppender());
        return logger;
    }
}
