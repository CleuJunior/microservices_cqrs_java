package br.com.cleonildo.api.commmands;

import br.com.cleonildo.command.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CloseAccountCommand extends BaseCommand {

        public CloseAccountCommand(String id) {
                super(id);
        }
}

