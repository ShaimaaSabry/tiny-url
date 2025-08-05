package shaimaa.tinyurl.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UrlDTO {
    private String shortUrl;
    private String originalUrl;
}
