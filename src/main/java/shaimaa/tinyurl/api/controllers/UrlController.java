package shaimaa.tinyurl.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import shaimaa.tinyurl.api.dto.UrlCreateDTO;
import shaimaa.tinyurl.api.dto.UrlDTO;
import shaimaa.tinyurl.domain.Url;
import shaimaa.tinyurl.domain.UrlService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("urls")
public class UrlController {
    private final UrlService urlService;

    UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UrlDTO createOne(@Validated @RequestBody UrlCreateDTO urlCreateDTO) throws NoSuchAlgorithmException {
        Url url = urlService.createUrl(urlCreateDTO.getOriginalUrl());

        return new UrlDTO(url.getShortUrl(), url.getOriginalUrl());
    }
}
