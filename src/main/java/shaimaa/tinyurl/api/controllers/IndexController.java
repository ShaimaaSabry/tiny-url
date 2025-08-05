package shaimaa.tinyurl.api.controllers;

import shaimaa.tinyurl.domain.Url;
import shaimaa.tinyurl.domain.UrlService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping()
public class IndexController {
    private final UrlService urlService;

    IndexController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("{shortUrl}")
    RedirectView getOne(@PathVariable String shortUrl) {
        Optional<Url> url = urlService.getByShortUrl(shortUrl);

//        if (url.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url does not exist or has expired.");
//        }

        return new RedirectView(url.get().getOriginalUrl());
    }
}
