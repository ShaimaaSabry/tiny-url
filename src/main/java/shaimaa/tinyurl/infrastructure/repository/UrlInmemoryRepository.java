package shaimaa.tinyurl.infrastructure.repository;

import shaimaa.tinyurl.domain.Url;
import shaimaa.tinyurl.domain.contracts.UrlRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UrlInmemoryRepository implements UrlRepository {
    Set<Url> urls = new HashSet<>();

    @Override
    public Optional<Url> getByShortUrl(String shortUrl) {
        return urls.stream().filter(u -> u.getShortUrl().equals(shortUrl)).findFirst();
    }

    @Override
    public Url save(Url url) {
        urls.add(url);
        return url;
    }
}
