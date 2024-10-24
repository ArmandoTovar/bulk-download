package tovar.infrastructure.persistent.repositories;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.reactive.mutiny.Mutiny.SessionFactory;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Tuple;
import tovar.domain.repository.SqlReportSourceRepository;

@ApplicationScoped
public class SqlReportSourceRepositoryImpl extends SqlReportSourceRepository {

  @Inject
  SessionFactory sessionFactory;

  @Override
  public Uni<List<Map<String, Object>>> execute(String query) {
    return sessionFactory.withSession(session -> session.createNativeQuery(query, Tuple.class).getResultList()
        .onItem().transform(tuples -> tuples.stream()
            .map(this::tupleToMap)
            .collect(Collectors.toList())));
  }

  private Map<String, Object> tupleToMap(Tuple tuple) {
    return tuple.getElements().stream()
        .collect(Collectors.toMap(
            element -> element.getAlias(),
            element -> tuple.get(element.getAlias())));
  }

}
