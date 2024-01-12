package br.com.cleonildo.events;

import br.com.cleonildo.event.BaseEvent;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class AccountClosedEvent extends BaseEvent {
}
