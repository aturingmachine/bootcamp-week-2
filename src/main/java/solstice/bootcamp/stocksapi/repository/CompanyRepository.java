package solstice.bootcamp.stocksapi.repository;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import solstice.bootcamp.stocksapi.model.Company;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;

@Repository
public class CompanyRepository {

  private final JdbcTemplate template;

  private final RowMapper<Company> companyRowMapper = (ResultSet rs, int row) -> new Company(
      rs.getInt(1),
      rs.getString(2)
  );

  public CompanyRepository(JdbcTemplate template) {
    this.template = template;
  }

  private final String INSERT = "INSERT INTO companies (symbol) values(?)";
  private final String GET_ALL = "SELECT * FROM companies";
  private final String GET_BY_SYMBOL = "SELECT * from companies WHERE symbol = '[SYMBOL]'";
  private final String GET_BY_ID = "SELECT * FROM companies where id = '[ID]'";

  public void save(HashSet<Company> companies) {

    companies.forEach(company -> {
        template.update(INSERT, company.getSymbol());
    });
  }

  public List<Company> getAll() {
    return template.query(GET_ALL, companyRowMapper);
  }

  public Company getCompanyBySymbol(String symbol) {
    String queryString = GET_BY_SYMBOL.replace("[SYMBOL]", symbol);
    try {
      return template.queryForObject(queryString, companyRowMapper);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public Company getCompanyById(int id) {
    String queryString = GET_BY_ID.replace("[ID]", Integer.toString(id));
    try {
      return template.queryForObject(queryString, companyRowMapper);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}
