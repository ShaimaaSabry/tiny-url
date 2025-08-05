package shaimaa.tinyurl.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

class UrlTest {

    @Test
    void when_given_original_url_then_create_short_url() throws NoSuchAlgorithmException {
        // WHEN
        String longUrl = "test";
        Url url = Url.create(longUrl);

        // THEN
        Assertions.assertEquals(longUrl, url.getOriginalUrl());
        Assertions.assertEquals(6, url.getShortUrl().length());
        assertThat(url.getTtl(), greaterThan(0L));
    }

    @Test
    void when_given_two_unique_original_urls_twice_then_create_two_unique_short_urls() throws NoSuchAlgorithmException {
        // WHEN
        Url url1 = Url.create("test1");
        Url url2 = Url.create("test2");

        // THEN
        Assertions.assertNotEquals(url1.getShortUrl(), url2.getShortUrl());
    }

    @Test
    void when_given_one_identical_original_url_twice_then_create_two_unique_short_urls() throws NoSuchAlgorithmException {
        // WHEN
        String originalUrl = "test";
        Url url1 = Url.create(originalUrl);
        Url url2 = Url.create(originalUrl);

        // THEN
        Assertions.assertNotEquals(url1.getShortUrl(), url2.getShortUrl());
    }
}
