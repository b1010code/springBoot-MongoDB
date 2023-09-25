package springmongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springmongo.model.MeetingChannels;
import springmongo.repository.ChannelRepository;
import springmongo.repository.MeetChRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetChServiceImp implements MeetChService {

    private final MeetChRepository meetChRepository;
    private  final ChannelRepository channelRepository;


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
        return this.meetChRepository.save(meetingChannels);
    }

    @Override
    public boolean delete(String id) {
        this.meetChRepository.deleteById(id);
        return false;
    }
}
