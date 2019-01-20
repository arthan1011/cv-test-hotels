package org.arthan.hotels.domain;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.util.stream.Stream;

public class HotelIdentityGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        if (obj instanceof Identifiable) {
            String identifierName = session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName();
            String entityName = obj.getClass().getSimpleName();
            String query = String.format("select %s from %s", identifierName, entityName);

            Stream<String> stream = session.createQuery(query, String.class).stream();

            long max = stream.map(e -> e.substring(2))
                    .mapToLong(Long::parseLong)
                    .max().orElse(0L);
            return String.format("HO%010d", max + 1);
        }
        return super.generate(session, obj);
    }
}
