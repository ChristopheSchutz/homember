package com.hypesofts.homember.technical.baseclass;

import com.hypesofts.homember.technical.entityfetcher.EntityFetcherChain;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class FetchableDomainId<T extends DomainEntity> implements DomainId {
    private final UUID id;

    protected FetchableDomainId(UUID id) {
        this.id = id;
    }

    public T fetchEntity() {
        return (T) EntityFetcherChain.fetchEntity(this);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof FetchableDomainId && this.id.equals(((FetchableDomainId) o).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}