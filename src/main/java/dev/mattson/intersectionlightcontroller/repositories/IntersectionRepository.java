package dev.mattson.intersectionlightcontroller.repositories;

import dev.mattson.intersectionlightcontroller.entities.Intersection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntersectionRepository extends JpaRepository<Intersection, Integer> {
}
