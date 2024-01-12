package br.com.cleonildo.api.commmands;

import br.com.cleonildo.command.BaseCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawtFundsCommand extends BaseCommand {
        private BigDecimal amount;
}

