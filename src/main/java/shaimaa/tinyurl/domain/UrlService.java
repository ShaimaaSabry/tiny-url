package shaimaa.tinyurl.domain;

import shaimaa.tinyurl.domain.contracts.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url createUrl(String originalUrl) throws NoSuchAlgorithmException {
        Url url = Url.create(originalUrl);
        urlRepository.save(url);
        return url;
    }

    public Optional<Url> getByShortUrl(String shortUrl) {
        return urlRepository.getByShortUrl(shortUrl);
    }
}
