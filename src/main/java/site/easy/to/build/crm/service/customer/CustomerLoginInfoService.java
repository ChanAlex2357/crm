package site.easy.to.build.crm.service.customer;

import jakarta.transaction.Transactional;
import site.easy.to.build.crm.entity.CustomerLoginInfo;


@Transactional
public interface CustomerLoginInfoService {
    public CustomerLoginInfo findById(int id);

    public CustomerLoginInfo findByEmail(String email);

    public CustomerLoginInfo findByToken(String token);

    @Transactional
    public CustomerLoginInfo save(CustomerLoginInfo customerLoginInfo);

    @Transactional
    public void delete(CustomerLoginInfo customerLoginInfo);
}
