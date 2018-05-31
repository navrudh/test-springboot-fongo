package itest.config;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "") // in case your config is not present in package root
@EnableMongoRepositories(basePackages = "")  // in case your config is not present in package root
@Configuration
public abstract class AbstractFongoBaseConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("spring.data.mongodb.database");
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
      return new Fongo(getDatabaseName()).getMongo();
    }
}
