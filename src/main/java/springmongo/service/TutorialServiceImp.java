package springmongo.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmongo.DTOs.TutorialResDto;
import springmongo.model.Tutorial;
import springmongo.repository.TutorialRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialServiceImp implements TutorialService {
    private TutorialRepository tutorialRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TutorialServiceImp(TutorialRepository tutorialRepository, ModelMapper modelMapper) {
        this.tutorialRepository = tutorialRepository;
        this.modelMapper = modelMapper;
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
    public TutorialResDto findRespAll(String id) {
        Tutorial tutorial = this.tutorialRepository.findById(id).orElse(null);
        if (tutorial != null) {
            return this.modelMapper.map(tutorial, TutorialResDto.class);
        } else {
            throw new RuntimeException("ID não encontrada " + id);
        }
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
