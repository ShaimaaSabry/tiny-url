package shaimaa.tinyurl.infrastructure.repository;

import shaimaa.tinyurl.domain.Url;
import shaimaa.tinyurl.domain.contracts.UrlRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UrlRepositoryImpl implements UrlRepository {
    private final UrlDynamodbRepository urlDynamodbRepository;
//    private final RedisRepository redisRepository;

    public UrlRepositoryImpl(
            final UrlDynamodbRepository urlDynamodbRepository
//            final RedisRepository redisRepository
    ) {
        this.urlDynamodbRepository = urlDynamodbRepository;
//        this.redisRepository = redisRepository;
    }

    @Override
    public Optional<Url> getByShortUrl(String shortUrl) {
//        String cached = this.redisRepository.getKey(shortUrl);
//        if (!cached.isEmpty()) {
//            Url url = new Url(shortUrl, cached, 0);
//            return Optional.of(url);
//        }

        Optional<UrlEntity> urlItem = urlDynamodbRepository.findByTinyUrl(shortUrl);

        if (urlItem.isEmpty()) {
            return Optional.empty();
        }

//        this.redisRepository.setKey(urlItem.get().getTinyUrl(), urlItem.get().getOriginalUrl());

        Url url = new Url(urlItem.get().getTinyUrl(), urlItem.get().getOriginalUrl(), urlItem.get().getTtl());
        return Optional.of(url);
    }

    @Override
    public Url save(Url url) {
        UrlEntity urlEntity = new UrlEntity(url.getShortUrl(), url.getOriginalUrl(), url.getTtl());
        urlEntity = urlDynamodbRepository.save(urlEntity);
        return new Url(urlEntity.getTinyUrl(), urlEntity.getOriginalUrl(), urlEntity.getTtl());
    }
}
