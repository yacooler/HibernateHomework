import org.hibernate.Session;
import ru.vyazankin.hiberlesson.database.GlobalSessionFactory;
import ru.vyazankin.hiberlesson.entity.Product;
import ru.vyazankin.hiberlesson.repository.ProductDAO;
import ru.vyazankin.hiberlesson.repository.ProductRepository;

public class MainApp {
    public static void main(String[] args) {
        //Инициализируем
        GlobalSessionFactory globalSessionFactory = new GlobalSessionFactory("hibernate.cfg.xml");
        test();
        GlobalSessionFactory.close();

    }

    private static void test(){

        try (Session session = GlobalSessionFactory.get().getCurrentSession()){
            ProductRepository repository = new ProductDAO();

            session.beginTransaction();

            repository.saveOrUpdate(new Product("Мыло", 35));
            repository.saveOrUpdate(new Product("Веревка", 190));
            repository.saveOrUpdate(new Product("Табурет", 700));

            repository.findAll().forEach(System.out::println);

            Product product = repository.findByID(2L);
            System.out.println(product);
            //System.out.println(repository.findByID(4L)); No entity found for query
            repository.deleteByID(1L);

            repository.findAll().forEach(System.out::println);

            product.setTitle("Мочалка");


            //Сохранять не обязательно, он в контексте постоянства
            //repository.saveOrUpdate(product);

            repository.findAll().forEach(System.out::println);
            session.getTransaction().commit();
        }


    }


}
