package springmongo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmongo.DTOs.TutorialResDto;
import springmongo.model.Tutorial;
import springmongo.service.TutorialService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tutorials")
public class TutorialController {
    private TutorialService tutorialService;
    private ModelMapper modelMapper;

    public TutorialController(TutorialService tutorialService, ModelMapper modelMapper) {
        this.tutorialService = tutorialService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Tutorial> create(@RequestBody Tutorial tutorial) {
        Tutorial createTutorial = tutorialService.create(tutorial);
        return ResponseEntity.ok(createTutorial);
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<Tutorial> participants = tutorialService.findAll();
        return ResponseEntity.ok(participants);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Tutorial> findById(@PathVariable(value = "id") String id) {
        Tutorial tutorial = this.tutorialService.findById(id);

        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<TutorialResDto> findRespAll(@PathVariable(value = "id") String id) {
        Tutorial tutorial = this.tutorialService.findById(id);

        if (tutorial != null) {
            TutorialResDto tutorialResDto = this.modelMapper.map(tutorial, TutorialResDto.class);
            return new ResponseEntity<>(tutorialResDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> update(@PathVariable(value = "id") String id, @RequestBody Tutorial tutorial) {
        Tutorial updatedTutorial = this.tutorialService.update(id, tutorial);

        if (updatedTutorial != null) {
            return ResponseEntity.ok(updatedTutorial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            this.tutorialService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
