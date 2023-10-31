package dev.matheushbmelo.project.domain.events;

import dev.matheushbmelo.project.domain.models.Conta;

public interface ContaEvent {
    public enum EventType {CREATED, UPDATED, DELETED}

    void dispatch(Conta conta, EventType event) throws Exception;
}
