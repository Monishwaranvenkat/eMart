package com.eMart.main.DTO;

import com.eMart.main.models.AccountDetails;
import com.eMart.main.models.EmployeeDetails;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddEmployeeDTO {
    private AccountDetails accountDetails;
}
