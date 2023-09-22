package springmongo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmongo.DTO.ChannelResDto;
import springmongo.model.Channel;
import springmongo.service.ChannelService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;
    private final ModelMapper modelMapper;

    @PostMapping("/save")
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Channel> create(@Valid @RequestBody Channel channel) {
        Channel createChannel = channelService.create(channel);
        return ResponseEntity.ok(createChannel);
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<Channel> channels = channelService.findAll();
        return ResponseEntity.ok(channels);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Channel> findById(@PathVariable(value = "id") String id) {
        Channel channel = this.channelService.findById(id);

        if (channel != null) {
            return new ResponseEntity<>(channel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<ChannelResDto> findRespAll(@PathVariable(value = "id") String id) {
        Channel channel = this.channelService.findById(id);

        if (channel != null) {
            ChannelResDto channelResDto = this.modelMapper.map(channel, ChannelResDto.class);
            return new ResponseEntity<>(channelResDto, HttpStatus.OK);
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
