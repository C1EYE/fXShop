package online.c1eye.thanato.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecKeyValueDO {

    @JsonProperty(value = "key_id")
    private Integer keyId;

    @JsonProperty(value = "value_id")
    private Integer valueId;

    private String value;

    private String key;
}
