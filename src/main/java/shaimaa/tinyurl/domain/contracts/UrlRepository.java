package shaimaa.tinyurl.domain.contracts;

import shaimaa.tinyurl.domain.Url;

import java.util.Optional;

public interface UrlRepository {
    Optional<Url> getByShortUrl(String shortUrl);

    Url save(Url url);
}
