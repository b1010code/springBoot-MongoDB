package springmongo.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmongo.model.Tutorial;
import springmongo.service.TutorialService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tutorials")
public class TutorialController {
    private TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
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

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> update(@PathVariable(value = "id") String id, @RequestBody Tutorial tutorial) {
        Tutorial updatedTutorial = this.tutorialService.update(id, tutorial);

        if (updatedTutorial != null) {
            return ResponseEntity.ok(updatedTutorial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        boolean deleted = this.tutorialService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
