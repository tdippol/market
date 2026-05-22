package com.axiante.mui.webapp.business.service;

import com.axiante.mui.webapp.business.SupportoMediaCheckService;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.Dependent;

@Dependent
public class SupportoMediaCheckServiceImpl implements SupportoMediaCheckService {

    @Override
    public List<SupportoMediaCheckEnum> readAll() {
        return Arrays.asList(SupportoMediaCheckEnum.values());
    }
}
