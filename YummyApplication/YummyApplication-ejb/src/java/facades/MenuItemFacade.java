package facades;

import entity.MenuItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prakriti
 */
@Stateless
public class MenuItemFacade extends AbstractFacade<MenuItem> implements MenuItemFacadeLocal, MenuItemFacadeRemote {
    @PersistenceContext(unitName = "YummyApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuItemFacade() {
        super(MenuItem.class);
    }
    
}
