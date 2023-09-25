package springmongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import springmongo.DTO.ChannelAgendDto;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("MeetingChannels")
public class MeetingChannels {
    @Id
    private String id;

    @NotBlank(message = "Título obrigatório")
    private String meetName;

    @NotBlank(message = "Selecione o cliente")
    private List<ChannelAgendDto> scheduledChannels;

    @NotBlank(message = "Preencha a data")
    @JsonProperty("meetingDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date meetingDate;

    @Field(targetType = FieldType.STRING)
    private String descriptionMeet;

    @NotBlank(message = "Preencha a data")
    @JsonProperty("dateCreate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateCreate;
}
