package ua.aleh1s.hotelepam.model.dao.aggregator;

import lombok.extern.java.Log;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.role.UserRole;

import java.time.ZoneId;
import java.util.Locale;

public class UserEntityAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
            throws ArgumentsAggregationException {
        return UserEntity.Builder.newBuilder()
                .id(accessor.getLong(0))
                .login(accessor.getString(1))
                .password(accessor.getString(2))
                .timezone(ZoneId.of(accessor.getString(3)))
                .locale(new Locale(accessor.getString(4)))
                .role(UserRole.valueOf(accessor.getString(5)))
                .build();
    }
}
