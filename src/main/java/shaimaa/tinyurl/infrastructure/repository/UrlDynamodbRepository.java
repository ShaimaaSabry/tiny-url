package shaimaa.tinyurl.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@EnableScan
//@Repository
@Component
public interface UrlDynamodbRepository extends CrudRepository<UrlEntity, String> {
    Optional<UrlEntity> findByTinyUrl(String TinyUrl);
}
