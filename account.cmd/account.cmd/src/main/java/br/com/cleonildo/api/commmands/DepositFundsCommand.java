package br.com.cleonildo.api.commmands;

import br.com.cleonildo.command.BaseCommand;
import br.com.cleonildo.dto.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositFundsCommand extends BaseCommand {
        private BigDecimal amount;
}

