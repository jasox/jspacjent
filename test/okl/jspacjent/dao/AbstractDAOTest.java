package okl.jspacjent.dao;

import org.springframework.test.*;

/**
 * A base class for all DAO for tests.
 * 
 * Allows subclasses to specify context locations.
 *
 * <p>This class extends 
 *  AbstractTransactionalDataSourceSpringContextTests,
 *  one of the valuable test superclasses provided in the 
 *  org.springframework.test package. 
 *  This represents best practice for integration tests with Spring. 
 *  The AbstractTransactionalDataSourceSpringContextTests 
 *  superclass provides the following services:
 * <li>Injects test dependencies, meaning that we don't need to 
 *  perform application context lookups. See the set...() method.
 *  Injection uses autowiring by type.
 * <li>Executes each test method in its own transaction, which is 
 *  automatically rolled back by default. This means that even if 
 *  tests insert or otherwise change database state, there is 
 *  no need for a teardown or cleanup script.
 * <li>Provides useful inherited protected fields, such as a 
 *  JdbcTemplate that can be used to verify database state after 
 *  test operations, or verify the results of queries performed by 
 *  application code. An ApplicationContext is also inherited, 
 *  and can be used for explicit lookup if necessary.
 *
 * <p>The AbstractTransactionalDataSourceSpringContextTests 
 *  and related classes are shipped in the spring-mock.jar.
 * 
 * @see org.springframework.test
 *         .AbstractTransactionalDataSourceSpringContextTests
 * 
 * 
 * @author Janusz Swó³
 */
public abstract class AbstractDAOTest 
extends AbstractTransactionalDataSourceSpringContextTests {

  // the daoFactory to be tested.
  protected DAO dao;  
  
  /**
   * This method is provided to set the DAO instance 
   *   being tested by the Dependency Injection. 
   * Injection behaviour of the superclass from the 
   *    <code>org.springframework.test</code> package.
   * @param dao to test
   */
  public void setDao(DAO dao) {
    this.dao = dao;
  }

  /**
   * Setting up the current test
   * 
   * @throws Exception
   */
  protected void onSetUpInTransaction() throws Exception {
    dao = createDao();    
  }

  /**
   * Returns the dao to be tested.
   * 
   * @return The dao to be tested.
   */
  protected abstract DAO createDao();

  /**
   * Called sometimes just before the assertions in the test. This
   * callback method can be used for example to flush memory state to
   * the database (in Hibernate or JDO for example).
   */
  protected abstract void beforeAssertions();

  /**
   * @return the dao
   */
  public DAO getDao() {
    return dao;
  }  
  
  
}
