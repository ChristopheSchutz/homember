package com.hypesofts.homember.technical.entityfetcher;

import com.hypesofts.homember.technical.baseclass.DomainEntity;
import com.hypesofts.homember.technical.baseclass.DomainId;
import com.hypesofts.homember.technical.exception.OwlError;
import com.hypesofts.homember.technical.exception.OwlException;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

@Configurable
public class EntityFetcherChain {

    public List<EntityFetcher> entityFetchers;

    private static EntityFetcherChain INSTANCE;

    private EntityFetcherChain() {
        entityFetchers = SpringContext.getBeans(EntityFetcher.class);
    }

    private static EntityFetcherChain getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EntityFetcherChain();
        }
        return INSTANCE;
    }

    public static DomainEntity fetchEntity(DomainId domainId) {
        for (EntityFetcher fetcher : getInstance().entityFetchers) {
            if (fetcher.canFetch(domainId)) {
                return fetcher.fetchEntity(domainId);
            }
        }
        throw new OwlException(OwlError.ENTITY_FETCHER_NOT_FOUND);
    }
}
