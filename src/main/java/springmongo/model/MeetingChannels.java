package springmongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MeetingChannels")
public class MeetingChannels {
    @Id
    private String id;

    private String meetName;

    private List<Channel> scheduledChannels;

    private Date meetingDate;

    private String descriptionMeet;

    private Date dateCreate;
}
