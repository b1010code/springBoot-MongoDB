package springmongo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import springmongo.model.Channel;
import springmongo.model.MeetingChannels;
import springmongo.repository.ChannelRepository;
import springmongo.repository.MeetChRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class MeetChServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private MeetChRepository meetChRepository;

    @Autowired
    private MeetChServiceImp meetChServiceImp;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        meetChServiceImp = new MeetChServiceImp(meetChRepository, channelRepository);
    }

    @Test
    @DisplayName("checks if the id of the other ChannelRepository class exists in the database")
    void createSuccess() {
        Channel channelExists = new Channel();
        String id = "6509d715dcb86e2ebf4e4ba3";
        channelExists.setId(id);

        when(channelRepository.findById(id)).thenReturn(Optional.of(channelExists));

        MeetingChannels meetingChannels = new MeetingChannels();

        MeetingChannels result = meetChServiceImp.create(id, meetingChannels);

        verify(channelRepository).findById(id);

        verify(meetChRepository).save(meetingChannels);

        assertSame(meetingChannels, result);
    }
}