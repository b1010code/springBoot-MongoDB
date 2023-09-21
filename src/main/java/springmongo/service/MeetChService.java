package springmongo.service;

import springmongo.model.MeetingChannels;

import java.util.List;

public interface MeetChService {
    public MeetingChannels create(String id, MeetingChannels meetingChannels);
    public List<MeetingChannels> findAll();
    public MeetingChannels findById(String id);
    public MeetingChannels update (String id, MeetingChannels meetingChannels);
    public boolean delete(String id);
}
