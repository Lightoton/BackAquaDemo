package com.aquaOlsberg.demo.config;

import com.aquaOlsberg.demo.entity.Price;
import com.aquaOlsberg.demo.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(PriceRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Price p1 = new Price();
                p1.setCategory("Erwachsene");
                p1.setPrice2h(4.00);
                p1.setPrice3h(5.00);
                p1.setPriceDay(6.00);

                Price p2 = new Price();
                p2.setCategory("Kinder / Jugendliche");
                p2.setPrice2h(2.50);
                p2.setPrice3h(3.50);
                p2.setPriceDay(4.50);

                repository.save(p1);
                repository.save(p2);
                System.out.println("✅ База данных инициализирована базовыми ценами!");
            }
        };
    }
}