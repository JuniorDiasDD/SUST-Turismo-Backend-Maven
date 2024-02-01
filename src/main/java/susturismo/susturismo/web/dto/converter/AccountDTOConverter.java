package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.Portfolio;
import susturismo.susturismo.domain.Role;
import susturismo.susturismo.domain.User;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.PortfolioDTO;
import susturismo.susturismo.web.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountDTOConverter {

@Autowired
PortfolioDTOConverter portfolioDTOConverter;

    public AccountDTO convertToDTO(Account account) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setLogin(account.getLogin());
        accountDTO.setId(account.getId());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setDescription(account.getDescription());
        accountDTO.setImage(account.getImage());
        accountDTO.setName(account.getName());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setTel(account.getTel());
        accountDTO.setGoogleId(account.getGoogle_id());
        accountDTO.setPais(account.getPais());
        accountDTO.setUniversidade(account.getUniversidade());

        Set<PortfolioDTO> portfolioDTOS = account.getPortfolios().stream().map(portfolioDTOConverter::convertToDTO).collect(Collectors.toSet());

        accountDTO.setPortfolios(portfolioDTOS);

        return accountDTO;
    }
    public AccountDTO convertToDTOPerfil(Account account,String perfil) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setLogin(account.getLogin());
        accountDTO.setId(account.getId());
        accountDTO.setImage(account.getImage());
        accountDTO.setDescription(account.getDescription());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setName(account.getName());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setTel(account.getTel());
        accountDTO.setGoogleId(account.getGoogle_id());
        accountDTO.setPerfil(perfil);
        accountDTO.setPais(account.getPais());
        accountDTO.setUniversidade(account.getUniversidade());

        Set<PortfolioDTO> portfolioDTOS = account.getPortfolios().stream().map(portfolioDTOConverter::convertToDTO).collect(Collectors.toSet());

        accountDTO.setPortfolios(portfolioDTOS);

        return accountDTO;
    }
    public Account convertToEntity(AccountDTO accountDTO) {

        Account account = new Account();
        account.setLogin(accountDTO.getLogin());
        account.setDescription(accountDTO.getDescription());
        account.setId(accountDTO.getId());
        account.setImage(accountDTO.getImage());
        account.setEmail(accountDTO.getEmail());
        account.setName(accountDTO.getName());
        account.setStatus(accountDTO.getStatus());
        account.setTel(accountDTO.getTel());
        account.setPais(accountDTO.getPais());
        account.setUniversidade(accountDTO.getUniversidade());
        if(accountDTO.getPortfolios()!=null &&!accountDTO.getPortfolios().isEmpty()){
            Set<Portfolio> portfolios = accountDTO.getPortfolios().stream().map(portfolioDTOConverter::convertToEntity).collect(Collectors.toSet());
            account.setPortfolios(portfolios);
        }
        account.setGoogle_id(accountDTO.getGoogleId());
        return account;
    }


}
