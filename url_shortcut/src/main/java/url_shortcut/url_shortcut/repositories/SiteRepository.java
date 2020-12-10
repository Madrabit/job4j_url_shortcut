package url_shortcut.url_shortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import url_shortcut.url_shortcut.domain.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Long> {
    Optional<Site> findByName(String name);
}
