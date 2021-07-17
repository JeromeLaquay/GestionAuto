package com.example.demo;

import com.example.demo.models.Car;
import com.example.demo.models.Model;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.ModelRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ModelRepository modelRepository, CarRepository carRepository, DatabaseClient client) {
        return args -> {
            client.execute("create table IF NOT EXISTS MODEL" +
                    "(id SERIAL PRIMARY KEY, name varchar (255), brand_id INTEGER);").fetch().first().subscribe();
            client.execute("DELETE FROM MODEL;").fetch().first().subscribe();
            client.execute("create table IF NOT EXISTS CAR" +
                    "(id SERIAL PRIMARY KEY, year INTEGER, color varchar (255), model_id INTEGER, image_path varchar (255) not null);").fetch().first().subscribe();
            client.execute("DELETE FROM CAR;").fetch().first().subscribe();

            Stream<Model> streamModel = Stream.of(new Model( "ds3", 1L),
                    new Model( "serie 1", 2L),
                    new Model( "m2", 2L));

            Stream<Car> streamCar = Stream.of(new Car(1990, "bleu", 1L, "https://voiture.kidioui.fr/image/img-auto/ds-ds3.jpg"),
                    new Car(2000, "rouge", 1L, "https://www.loueruneauto.fr/storage/jpg-0vnbytqOzgqocmNTh3jpMhTpLuONmzucdvtvGD3G.jpg"),
                    new Car(2021, "blanc", 2L, "https://www.bmw.fr/content/dam/bmw/marketFR/bmw_fr/all-models/1-series/5-door/2019/1series-sport-avant.png"),
                    new Car(1990, "bleu", 1L, "https://voiture.kidioui.fr/image/img-auto/ds-ds3.jpg"),
                    new Car(2000, "rouge", 1L, "https://www.loueruneauto.fr/storage/jpg-0vnbytqOzgqocmNTh3jpMhTpLuONmzucdvtvGD3G.jpg"),
                    new Car(2021, "blanc", 2L, "https://www.bmw.fr/content/dam/bmw/marketFR/bmw_fr/all-models/1-series/5-door/2019/1series-sport-avant.png"),
                    new Car(1990, "bleu", 1L, "https://voiture.kidioui.fr/image/img-auto/ds-ds3.jpg"),
                    new Car(2000, "rouge", 1L, "https://www.loueruneauto.fr/storage/jpg-0vnbytqOzgqocmNTh3jpMhTpLuONmzucdvtvGD3G.jpg"),
                    new Car(2021, "blanc", 2L, "https://www.bmw.fr/content/dam/bmw/marketFR/bmw_fr/all-models/1-series/5-door/2019/1series-sport-avant.png"),
                    new Car(1990, "bleu", 1L, "https://voiture.kidioui.fr/image/img-auto/ds-ds3.jpg"),
                    new Car(2000, "rouge", 1L, "https://www.loueruneauto.fr/storage/jpg-0vnbytqOzgqocmNTh3jpMhTpLuONmzucdvtvGD3G.jpg"),
                    new Car(2021, "blanc", 2L, "https://www.bmw.fr/content/dam/bmw/marketFR/bmw_fr/all-models/1-series/5-door/2019/1series-sport-avant.png"));

           modelRepository.saveAll(Flux.fromStream(streamModel)).then().subscribe();
            carRepository.saveAll(Flux.fromStream(streamCar)).then().subscribe();

        };
    }

}

