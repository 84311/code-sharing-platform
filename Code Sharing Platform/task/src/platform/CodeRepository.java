package platform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, String> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM code c " +
                    "WHERE c.time_restricted = FALSE AND c.view_restricted = FALSE " +
                    "ORDER BY c.date_time DESC LIMIT :limit")
    List<Code> findLastNAdded(@Param("limit") long limit);
}
