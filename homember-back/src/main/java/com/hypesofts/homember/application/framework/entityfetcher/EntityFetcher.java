package com.hypesofts.homember.application.framework.entityfetcher;

import com.hypesofts.homember.application.framework.baseclass.DomainEntity;
import com.hypesofts.homember.application.framework.baseclass.DomainId;

public interface EntityFetcher<I extends DomainId, E extends DomainEntity> {

    E fetchEntity(I domainId);

    Class<? extends DomainId> getDomainIdConcreteClass();

    default <I> boolean canFetch(DomainId id) {
        return id.getClass().isAssignableFrom(getDomainIdConcreteClass());
    }
}
