package springmongo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmongo.exception.ChannelNotFoundException;
import springmongo.model.MeetingChannels;
import springmongo.service.MeetChService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/meets")
public class MeetChController {
    private MeetChService meetChService;

    public MeetChController(MeetChService meetChService) {
        this.meetChService = meetChService;
    }

    @PostMapping("/save")
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<MeetingChannels> createMeetingChannel(@PathVariable String id, @RequestBody MeetingChannels meetingChannels) {
        try {
            MeetingChannels createdChannel = meetChService.create(id, meetingChannels);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChannel);
        } catch (ChannelNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<MeetingChannels> meets = meetChService.findAll();
        return ResponseEntity.ok(meets);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MeetingChannels> findById(@PathVariable(value = "id") String id) {
        MeetingChannels meets = this.meetChService.findById(id);

        if (meets != null) {
            return new ResponseEntity<>(meets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingChannels> update(@PathVariable(value = "id") String id,
                                                  @RequestBody MeetingChannels meetingChannels) {
        MeetingChannels updatedMeet = this.meetChService.update(id, meetingChannels);

        if (updatedMeet != null) {
            return ResponseEntity.ok(updatedMeet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            this.meetChService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
