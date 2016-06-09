package ru.kpfu.itis;

import org.hibernate.Session;
import ru.kpfu.itis.domain.Address;
import ru.kpfu.itis.domain.Customer;
import ru.kpfu.itis.domain.Good;
import ru.kpfu.itis.domain.Order;
import ru.kpfu.itis.service.AddressService;
import ru.kpfu.itis.service.CustomerService;
import ru.kpfu.itis.service.GoodService;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Gataullin Kamil
 *         08.11.2014 23:44
 */
public class TestHibernate {

    public static final GoodService goodService = new GoodService();
    public static final OrderService orderService = new OrderService();
    public static final CustomerService customerService = new CustomerService();
    public static final AddressService addressService = new AddressService();

    public static void main(String[] args) {
        try {
            testConnection();
            testInsert();
            testSelect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!HibernateUtil.getSessionFactory().isClosed())
                HibernateUtil.getSessionFactory().close();
        }
    }

    public static void testConnection() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Exception e) {
            System.err.println("Error in testConnection(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void testInsert() {
        Address address = new Address("Казань", "Университетская", "12", "5");
        addressService.addAddress(address);

        Customer customer = new Customer("Kamil", "kamil23@mail.ru", address);
        customerService.addCustomer(customer);

        goodService.addGood(new Good("автомобиль", 500L));
        goodService.addGood(new Good("барби", 200L));
        goodService.addGood(new Good("LEGO", 1500L));

        Order order = new Order("Новогодние игрушки", new Date(), customer, goodService.getAllGoods());
        orderService.addOrder(order);
    }

    public static void testSelect() {
        List<Order> listOrders = orderService.getAllOrders();
        System.out.println("\n============ All Orders ============");
        for (Order order : listOrders) {
            System.out.println("id=" + order.getId() +
                            ", name=" + order.getName() +
                            ", creationDate=" + order.getCreationDate()
            );
        }

        System.out.println("\n============ All Customer ============");
        for (Customer customer : customerService.getAllCustomers()) {
            soutCustomer(customer);
            for (Order order : customer.getOrders()) {
                System.out.println("id=" + order.getId() +
                                ", name=" + order.getName() +
                                ", creationDate=" + order.getCreationDate() +
                                ", goods=" + order.getGoods().size()
                );
            }
        }

        System.out.println("\n============ Get Customer By Name ============");
        for (Customer customer : customerService.getCustomers("%kamil%", "%mail%"))
            soutCustomer(customer);

        System.out.println("\n============ All Goods ============");
        for (Good good : goodService.getAllGoods())
            System.out.println("id=" + good.getId() +
                            ", name=" + good.getName() +
                            ", price=" + good.getPrice()
            );
    }

    private static void soutCustomer(Customer customer) {
        System.out.println("id=" + customer.getId() +
                        ", name=" + customer.getName() +
                        ", email=" + customer.getEmail() +
                        ", address=" + customer.getAddress().getStreet()
        );
    }
}
