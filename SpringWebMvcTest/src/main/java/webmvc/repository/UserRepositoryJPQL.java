//package webmvc.repository;
//
//import org.springframework.stereotype.Repository;
//import webmvc.models.UserJpa;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class UserRepositoryJPQL {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private EntityManagerFactory emf;
//
//    private UserRepositoryJPQL(){
//        this.emf = Persistence.createEntityManagerFactory("ru.itis.darZam.userJpa");
//    }
//
//    private EntityManager getEntityManager() {
//        return this.emf.createEntityManager();
//    }
//
//    public void save(UserJpa user){
//        EntityManager em = getEntityManager();
//        em.createNativeQuery("INSERT INTO user_jpa (email, password, username) VALUES (?,?,?)")
//                .setParameter(1, user.getEmail())
//                .setParameter(2,user.getPassword())
//                .setParameter(3, user.getEmail())
//                .executeUpdate();
//
//    }
//
//    public void update(UserJpa user){
//        EntityManager em = getEntityManager();
//        em.createQuery("UPDATE UserJpa u SET u.email=?1 , u.userName=?2, u.password=?3")
//                .setParameter(1, user.getEmail())
//                .setParameter(2, user.getPassword())
//                .setParameter(3, user.getPassword())
//                .executeUpdate();
//    }
//
//    public UserJpa getUser(Long id){
//        EntityManager em = getEntityManager();
////        UserJpa user = em.find(UserJpa.class, id);
//////        em.detach(user)
//        em.createQuery("SELECT u FROM UserJpa u ORDER BY u.id").setMaxResults(5).setFirstResult(0).getResultList();
//        return null;
//    }
//
//    public List<UserJpa> printUsers(){
//        EntityManager em = getEntityManager();
//
//        Long size =(Long) em.createQuery("select COUNT (u) from UserJpa u ").getSingleResult();
//        int maxResult = 0;
//        System.out.println(size);
//        while (maxResult<size) {
//            maxResult += 5;
//            System.out.println("new page");
//            System.out.println(em.createQuery("SELECT u FROM UserJpa u ORDER BY u.id").setMaxResults(5).setFirstResult(maxResult - 5).getResultList());
//        }
//        return null;
//    }
//
//    public void remove(Long id) {
//        EntityManager em = getEntityManager();
//        em.createQuery("DELETE FROM UserJpa u where u.id=?1").setParameter(1, id);
//        em.getTransaction().begin();
//        UserJpa user = em.find(UserJpa.class, id);
//        em.remove(user);
//        em.getTransaction().commit();
//    }
//
//
//    public static void main(String[] args) {
//        UserJpa userJpa = UserJpa.builder().userName("danis_zam5").email("daniszam").password("qwerty1").build();
//        UserRepositoryJPQL repository = new UserRepositoryJPQL();
//        repository.printUsers();
//        System.out.println(repository.entityManager);
////        repository.save(userJpa);
////        userJpa.setEmail("d");
////        repository.update(userJpa);
//    }
//}
