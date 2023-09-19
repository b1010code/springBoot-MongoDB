package springmongo.service;

import org.springframework.stereotype.Service;
import springmongo.model.Tutorial;
import springmongo.repository.TutorialRepository;

import java.util.List;

@Service
public class TutorialServiceImp implements TutorialService {
    private TutorialRepository tutorialRepository;

    public TutorialServiceImp(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @Override
    public Tutorial create(Tutorial tutorial) {
        return this.tutorialRepository.save(tutorial);
    }

    @Override
    public List<Tutorial> findAll() {
        return this.tutorialRepository.findAll();
    }

    @Override
    public Tutorial findById(String id) {
        return this.tutorialRepository.findById(id).get();
    }

    @Override
    public Tutorial update(String id, Tutorial tutorial) {

        Tutorial tutorialExists = this.tutorialRepository.findById(id).get();

        if(tutorialExists != null) {
            tutorial.setId(tutorialExists.getId());
            this.tutorialRepository.save(tutorial);
        }

        return tutorial;
    }

    @Override
    public boolean delete(String id) {
        this.tutorialRepository.deleteById(id);
        return false;
    }


}
