package springmongo.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmongo.DTOs.TutorialResDto;
import springmongo.model.Channel;
import springmongo.service.ChannelService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tutorials")
public class TutorialController {
    private ChannelService channelService;
    private ModelMapper modelMapper;

    public TutorialController(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Channel> create(@Valid @RequestBody Channel tutorial) {
        Channel createChannel = channelService.create(tutorial);
        return ResponseEntity.ok(createChannel);
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<Channel> participants = channelService.findAll();
        return ResponseEntity.ok(participants);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Channel> findById(@PathVariable(value = "id") String id) {
        Channel tutorial = this.channelService.findById(id);

        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<TutorialResDto> findRespAll(@PathVariable(value = "id") String id) {
        Channel channel = this.channelService.findById(id);

        if (channel != null) {
            TutorialResDto tutorialResDto = this.modelMapper.map(channel, TutorialResDto.class);
            return new ResponseEntity<>(tutorialResDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Channel> update(@PathVariable(value = "id") String id, @RequestBody Channel channel) {
        Channel updatedTutorial = this.channelService.update(id, channel);

        if (updatedTutorial != null) {
            return ResponseEntity.ok(updatedTutorial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            this.channelService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
