/*
 *  Copyright (c) 2016, 张威, ivivisoft@gmail.com
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mskl.api.controller;

import com.mskl.common.dto.RestServiceResult;
import com.mskl.dao.model.MsklAccount;
import com.mskl.service.account.AccountService;
import com.mskl.service.verification.VerificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class MsklAccountController {
    private Log logger = LogFactory.getLog(MsklAccountController.class);

    @Resource(name = "account.accountService")
    private AccountService accountService;

    @Resource(name = "verificationService")
    private VerificationService verificationService;
    @RequestMapping("/{token}")
    public RestServiceResult<MsklAccount> getAccountInfo(@PathVariable String token){
        RestServiceResult<MsklAccount> result = new RestServiceResult<MsklAccount>("进入获取用户账户信息controller类", true);
        if (!verificationService.verificationToken(token, result)) {
            if (logger.isInfoEnabled()) {
                logger.info(result.toString());
            }
            return result;
        }

        return accountService.getAccountInfo(token);
    }
}
