package shaimaa.tinyurl.domain;

import shaimaa.tinyurl.domain.contracts.UrlRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UrlServiceTest {
    @MockBean
    private UrlRepository mockUrlRepository;

    @Autowired
    private UrlService urlService;

    @Test
    void when_given_original_url_then_save_url() throws NoSuchAlgorithmException {
        // WHEN
        String longUrl = "test";
        Url url = urlService.createUrl(longUrl);

        // THEN
        Assertions.assertEquals(longUrl, url.getOriginalUrl());
        Mockito.verify(mockUrlRepository).save(url);
    }

    @Test
    void when_given_short_url_then_return_original_url() {
        // GIVEN
        String shortUrl = "HQ4K15";
        String originalUrl = "test";
        Url givenUrl = new Url(shortUrl, originalUrl, 1660723090);
        Mockito.when(mockUrlRepository.getByShortUrl(shortUrl)).thenReturn(Optional.of(givenUrl));

        // WHEN
        Optional<Url> url = urlService.getByShortUrl(shortUrl);

        // THEN
        Assertions.assertEquals(shortUrl, url.get().getShortUrl());
        Assertions.assertEquals(originalUrl, url.get().getOriginalUrl());
        Mockito.verify(mockUrlRepository).getByShortUrl(shortUrl);
    }
}
