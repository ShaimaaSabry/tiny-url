package shaimaa.tinyurl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Random;


@AllArgsConstructor
@Getter
@ToString
public class Url {
    private static final int SHORT_URL_LENGTH = 6;
    private static final int DEFAULT_TTL_IN_SECONDS = 60;

    private final String shortUrl;
    private final String originalUrl;
    private final long ttl;
    private final Random random = new Random();

   private Url(String originalUrl) throws NoSuchAlgorithmException {
        this.originalUrl = originalUrl;
        this.shortUrl = shorten();
        this.ttl = Instant.now().getEpochSecond() + DEFAULT_TTL_IN_SECONDS;
    }

    static Url create(String originalUrl) throws NoSuchAlgorithmException {
        return new Url(originalUrl);
    }

    private String shorten() throws NoSuchAlgorithmException {
        // md5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((this.originalUrl + random.nextLong()).getBytes());
        byte[] hash = md.digest();

        // base64
        String encoded = new String(Base64.getEncoder().encode(hash));

        return encoded.substring(0, SHORT_URL_LENGTH);
    }
}
