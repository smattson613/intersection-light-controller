package dev.mattson.intersectionlightcontroller.services;

import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTO;
import dev.mattson.intersectionlightcontroller.entities.Intersection;
import dev.mattson.intersectionlightcontroller.repositories.IntersectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntersectionServiceImpl implements IntersectionService {

    private final IntersectionRepository intersectionRepository;

    public IntersectionServiceImpl(IntersectionRepository intersectionRepository) {
        this.intersectionRepository = intersectionRepository;
    }

    @Override
    public void createIntersection(Intersection intersection) {
        if (intersection.getEWLightRed() < (intersection.getNSLightYellow()+intersection.getNSLightGreen()) ||
                intersection.getNSLightRed() < (intersection.getEWLightYellow())+intersection.getEWLightGreen() ) {
            throw new RuntimeException("Red light timers must be greater than the sum of Yellow and Green timers of the opposite light");
        } else if ((intersection.getEWLightGreen()+intersection.getEWLightYellow()+intersection.getEWLightRed()) !=
                (intersection.getNSLightGreen()+intersection.getNSLightYellow()+intersection.getNSLightRed())) {
            throw new RuntimeException("Make sure the sum of North/South lights equals the sum of East/West lights");
        } else {
            this.intersectionRepository.save(intersection);
        }
    }

    @Override
    public List<Intersection> getAllIntersections() {
        return this.intersectionRepository.findAll();
    }

    @Override
    public Optional<Intersection> getIntersectionById(int id) {
        return Optional.empty();
    }

    @Override
    public void startIntersectionById(int id) {
        Optional<Intersection> intersectionToUpdate = this.intersectionRepository.findById(id);
        intersectionToUpdate.ifPresent(existingIntersection -> {
            existingIntersection.setPower(true);
            this.intersectionRepository.save(existingIntersection);
        });
    }

    @Override
    public void stopIntersectionById(int id) {
        Optional<Intersection> intersectionToUpdate = this.intersectionRepository.findById(id);
        intersectionToUpdate.ifPresent(existingIntersection -> {
            existingIntersection.setPower(false);
            this.intersectionRepository.save(existingIntersection);
        });
    }

    @Override
    public void toggleIntersectionPowerById(int id) {
        Optional<Intersection> intersectionToUpdate = this.intersectionRepository.findById(id);
        if (intersectionToUpdate.isPresent()) {
            Intersection intersection = intersectionToUpdate.get();
            boolean power = intersection.isPower();
            intersection.setPower(!power);
            this.intersectionRepository.save(intersection);
        }
    }

    @Override
    public IntersectionConfigDTO getIntersectionConfigDTOById(int id) {
        Optional<Intersection> intersectionConfig = this.intersectionRepository.findById(id);
        intersectionConfig.ifPresent(existingIntersection -> new IntersectionConfigDTO(
                existingIntersection.getIntersectionId(),
                existingIntersection.getNSLightRed(),
                existingIntersection.getNSLightYellow(),
                existingIntersection.getNSLightGreen(),
                existingIntersection.getEWLightRed(),
                existingIntersection.getEWLightYellow(),
                existingIntersection.getEWLightGreen()
        ));
        return null;
    }

    @Override
    public void setIntersectionConfigById(int id, int[] sixDigits) {
        // validate int array is 6 and throw exception if not
        Optional<Intersection> intersection = this.intersectionRepository.findById(id);
        if (intersection.isPresent()) {
            Intersection intersectionToConfigure = intersection.get();
            intersectionToConfigure.setNSLightRed(sixDigits[0]);
            intersectionToConfigure.setNSLightYellow(sixDigits[1]);
            intersectionToConfigure.setNSLightGreen(sixDigits[2]);
            intersectionToConfigure.setEWLightRed(sixDigits[3]);
            intersectionToConfigure.setEWLightYellow(sixDigits[4]);
            intersectionToConfigure.setEWLightGreen(sixDigits[5]);
        }
    }
}
