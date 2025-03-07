package org.andrey.back2javawebapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreTransferData {
    private Long source = 0L;
    private Long dest = 0L;
    private BigDecimal amount = new BigDecimal(0);
}
