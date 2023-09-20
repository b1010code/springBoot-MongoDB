package springmongo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmongo.DTOs.TutorialResDto;
import springmongo.model.Channel;
import springmongo.repository.ChannelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelServiceImp implements ChannelService {
    private ChannelRepository channelRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ChannelServiceImp(ChannelRepository channelRepository, ModelMapper modelMapper) {
        this.channelRepository = channelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Channel create(Channel channel) {
        return this.channelRepository.save(channel);
    }

    @Override
    public List<Channel> findAll() {
        return this.channelRepository.findAll();
    }

    @Override
    public TutorialResDto findRespAll(String id) {
        Channel channel = this.channelRepository.findById(id).orElse(null);
        if (channel != null) {
            return this.modelMapper.map(channel, TutorialResDto.class);
        } else {
            throw new RuntimeException("ID não encontrada " + id);
        }
    }


    @Override
    public Channel findById(String id) {
        return this.channelRepository.findById(id).get();
    }

    @Override
    public Channel update(String id, Channel channel) {

        Channel tutorialExists = this.channelRepository.findById(id).get();

        if(tutorialExists != null) {
            channel.setId(tutorialExists.getId());
            this.channelRepository.save(channel);
        }

        return channel;
    }

    @Override
    public boolean delete(String id) {
        this.channelRepository.deleteById(id);
        return false;
    }


}
