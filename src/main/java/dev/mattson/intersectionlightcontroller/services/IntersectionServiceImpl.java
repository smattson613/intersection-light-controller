package dev.mattson.intersectionlightcontroller.services;

import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTO;
import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTOMapper;
import dev.mattson.intersectionlightcontroller.entities.Intersection;
import dev.mattson.intersectionlightcontroller.entities.LightState;
import dev.mattson.intersectionlightcontroller.exceptions.InvalidIntersectionConfigException;
import dev.mattson.intersectionlightcontroller.repositories.IntersectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntersectionServiceImpl implements IntersectionService {

    private final IntersectionRepository intersectionRepository;
    private final IntersectionConfigDTOMapper intersectionConfigDTOMapper;

    public IntersectionServiceImpl(IntersectionRepository intersectionRepository, IntersectionConfigDTOMapper intersectionConfigDTOMapper) {
        this.intersectionRepository = intersectionRepository;
        this.intersectionConfigDTOMapper = intersectionConfigDTOMapper;
    }

    @Override
    public Intersection createIntersection(Intersection intersection) throws InvalidIntersectionConfigException {
            if (intersection.getEWLightRed() < (intersection.getNSLightYellow() + intersection.getNSLightGreen()) ||
                    intersection.getNSLightRed() < (intersection.getEWLightYellow()) + intersection.getEWLightGreen()) {
                throw new InvalidIntersectionConfigException("Red light timers must be greater than the sum of Yellow and Green timers of the opposite light");
            } else if ((intersection.getEWLightGreen() + intersection.getEWLightYellow() + intersection.getEWLightRed()) !=
                    (intersection.getNSLightGreen() + intersection.getNSLightYellow() + intersection.getNSLightRed())) {
                throw new InvalidIntersectionConfigException("Make sure the sum of North/South lights equals the sum of East/West lights");
            } else if ((intersection.getNSLight() == LightState.YELLOW || intersection.getEWLight() == LightState.YELLOW)
                    || (intersection.getNSLight() == LightState.RED & intersection.getEWLight() == LightState.RED)
                    || (intersection.getNSLight() == LightState.GREEN & intersection.getEWLight() == LightState.GREEN)) {
                throw new InvalidIntersectionConfigException("Lights must start with one green and one red light");
            } else {
                return this.intersectionRepository.save(intersection);
            }
    }

    @Override
    public List<Intersection> getAllIntersections() {
        return this.intersectionRepository.findAll();
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
    public Optional<IntersectionConfigDTO> getIntersectionConfigDTOById(int id) {
        return this.intersectionRepository.findById(id).map(intersectionConfigDTOMapper);
    }

    @Override
    public void setIntersectionConfigById(int id, int[] sixDigits) {
        try {
            if (sixDigits.length != 6) {
                throw new InvalidIntersectionConfigException("the array must contain 6 numbers");
            } else if (sixDigits[0] < (sixDigits[4] + sixDigits[5]) || sixDigits[3] < sixDigits[1] + sixDigits[2]) {
                throw new InvalidIntersectionConfigException("Red light timers must be greater than the sum of Yellow and Green timers of the opposite light");
            } else if ((sixDigits[0] + sixDigits[1] + sixDigits[2]) != (sixDigits[3] + sixDigits[4] + sixDigits[5])) {
                throw new InvalidIntersectionConfigException("Make sure the sum of North/South lights equals the sum of East/West lights");
            }
            Optional<Intersection> intersection = this.intersectionRepository.findById(id);
            if (intersection.isPresent()) {
                Intersection intersectionToConfigure = intersection.get();
                intersectionToConfigure.setNSLightRed(sixDigits[0]);
                intersectionToConfigure.setNSLightYellow(sixDigits[1]);
                intersectionToConfigure.setNSLightGreen(sixDigits[2]);
                intersectionToConfigure.setEWLightRed(sixDigits[3]);
                intersectionToConfigure.setEWLightYellow(sixDigits[4]);
                intersectionToConfigure.setEWLightGreen(sixDigits[5]);
                intersectionToConfigure.setNSLight(LightState.RED);
                intersectionToConfigure.setEWLight(LightState.GREEN);
                this.intersectionRepository.save(intersectionToConfigure);
            } else {
                throw new RuntimeException("Intersection not found");
            }
        } catch (InvalidIntersectionConfigException e) {
            System.out.println(e.getMessage());
        }
    }
}
