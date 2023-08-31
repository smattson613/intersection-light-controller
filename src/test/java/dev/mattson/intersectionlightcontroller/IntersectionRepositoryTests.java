package dev.mattson.intersectionlightcontroller;

import dev.mattson.intersectionlightcontroller.entities.Intersection;
import dev.mattson.intersectionlightcontroller.entities.LightState;
import dev.mattson.intersectionlightcontroller.repositories.IntersectionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntersectionRepositoryTests {

    @Autowired
    IntersectionRepository repository;
    Intersection intersection;

    @BeforeEach
    void setUp() {
        intersection = new Intersection(1, LightState.RED, LightState.GREEN, false, 60, 5, 40, 60, 5, 40);
        repository.save(intersection);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void saveIntersectionTest() {
        Intersection intersectionTest = new Intersection(0, LightState.RED, LightState.GREEN, true, 50, 5, 30, 50, 7, 28);
        Intersection savedIntersection = repository.save(intersectionTest);

        Assertions.assertThat(savedIntersection.getIntersectionId()).isGreaterThan(0);
        Assertions.assertThat(savedIntersection).isNotNull();
    }

    @Test
    void findIntersectionByIdTest() {
        Intersection foundIntersection = repository.findById(1).get();

        Assertions.assertThat(foundIntersection).isNotNull();
        Assertions.assertThat(foundIntersection.getNSLightRed()).isEqualTo(60);
    }

    @Test
    void getAllIntersectionTest() {
        List<Intersection> intersectionList = repository.findAll();

        Assertions.assertThat(intersectionList.size()).isGreaterThan(0);
    }
}
