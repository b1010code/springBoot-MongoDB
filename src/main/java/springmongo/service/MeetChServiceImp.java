package springmongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import springmongo.model.MeetingChannels;
import springmongo.repository.MeetChRepository;

import java.util.List;
@RequiredArgsConstructor
public class MeetChServiceImp implements MeetChService {

    private MeetChRepository meetChRepository;

    @Autowired
    public MeetChServiceImp(MeetChRepository meetChRepository) {
        this.meetChRepository = meetChRepository;
    }

    @Override
    public MeetingChannels create(MeetingChannels meetingChannels) {
        return this.meetChRepository.save(meetingChannels);
    }

    @Override
    public List<MeetingChannels> findAll() {
        return this.meetChRepository.findAll();
    }

    @Override
    public MeetingChannels findById(String id) {
        return this.meetChRepository.findById(id).get();
    }

    @Override
    public MeetingChannels update(String id, MeetingChannels meetingChannels) {

        MeetingChannels mtchExists = this.meetChRepository.findById(id).get();

        if(mtchExists != null) {
            meetingChannels.setId(mtchExists.getId());
            this.meetChRepository.save(meetingChannels);
        }

        return meetingChannels;
    }

    @Override
    public boolean delete(String id) {
        this.meetChRepository.deleteById(id);
        return false;
    }
}
