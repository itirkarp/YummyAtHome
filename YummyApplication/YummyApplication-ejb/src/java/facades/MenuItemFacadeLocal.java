package facades;

import entity.MenuItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Prakriti
 */
@Local
public interface MenuItemFacadeLocal {

    void create(MenuItem menuItem);

    void edit(MenuItem menuItem);

    void remove(MenuItem menuItem);

    MenuItem find(Object id);

    List<MenuItem> findAll();

    List<MenuItem> findRange(int[] range);

    int count();

    List<MenuItem> findByCategory(String category);
    
}
