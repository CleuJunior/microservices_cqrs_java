package br.com.cleonildo.event;


import br.com.cleonildo.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@SuperBuilder
public abstract class BaseEvent extends Message {
    private int version;

}
