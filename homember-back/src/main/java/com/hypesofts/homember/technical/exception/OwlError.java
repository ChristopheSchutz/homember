package com.hypesofts.homember.technical.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum OwlError implements Serializable {

    ENTITY_FETCHER_NOT_FOUND ("No entity fetcher found for this entity");

    private final String value;
}