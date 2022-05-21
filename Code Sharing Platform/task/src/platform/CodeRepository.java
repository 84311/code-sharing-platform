package platform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM code ORDER BY date_time DESC LIMIT :limit")
    List<Code> findLastNAdded(long limit);
}
