package facades;

import entity.MenuItem;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Prakriti
 */
@Remote
public interface MenuItemFacadeRemote {

    void create(entity.MenuItem menuItem);

    void edit(entity.MenuItem menuItem);

    void remove(entity.MenuItem menuItem);

    entity.MenuItem find(Object id);

    List<MenuItem> findAll();

    List<MenuItem> findRange(int[] range);

    int count();

    List<MenuItem> findByCategory(String category);
    
}
