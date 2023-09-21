package springmongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmongo.exception.ChannelNotFoundException;
import springmongo.model.Channel;
import springmongo.model.MeetingChannels;
import springmongo.repository.ChannelRepository;
import springmongo.repository.MeetChRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetChServiceImp implements MeetChService {

    private MeetChRepository meetChRepository;

    private ChannelRepository channelRepository;

    @Autowired
    public MeetChServiceImp(MeetChRepository meetChRepository, ChannelRepository channelRepository) {
        this.meetChRepository = meetChRepository;
        this.channelRepository = channelRepository;
    }

    @Override
    public MeetingChannels create(String id, MeetingChannels meetingChannels) {
         Channel channelExists = this.channelRepository.findById(id).orElse(null);

        if(channelExists != null) {
            meetingChannels.setId(channelExists.getId());
            this.meetChRepository.save(meetingChannels);
        }else{
            throw new ChannelNotFoundException("Esse canal não existe");
        }

        return meetingChannels;
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
