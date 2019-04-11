//package webmvc.repository;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import webmvc.models.UserJpa;
//
//import javax.persistence.*;
//import javax.persistence.criteria.*;
//import java.util.List;
//
//@Repository
//public class UserRepositoryCriteria {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private EntityManagerFactory emf;
//
//    private UserRepositoryCriteria(){
//        this.emf = Persistence.createEntityManagerFactory("ru.itis.darZam.userJpa");
//    }
//
//    private EntityManager getEntityManager() {
//        return this.emf.createEntityManager();
//    }
//
//    public void save(UserJpa user){
//        EntityManager em = getEntityManager();
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//    }
//
//    public int update(UserJpa user){
//        EntityManager em = getEntityManager();
//        em.getTransaction().begin();
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaUpdate<UserJpa> update = criteriaBuilder.createCriteriaUpdate(UserJpa.class);
//        Root root = update.from(UserJpa.class);
//
//        update
//                .set(root.get("userName"), user.getUserName())
//                .set(root.get("password"), user.getPassword())
//                .set(root.get("email"), user.getEmail())
//                .where(criteriaBuilder.equal(root.get("id"), user.getId()));
//
//        int i = em.createQuery(update).executeUpdate();
//        em.getTransaction().commit();
//        return i;
//
////        em.getTransaction().begin();
////        em.merge(user);
////        em.getTransaction().commit();
//    }
//
//    public UserJpa getUser(Long id){
//        EntityManager em = getEntityManager();
//
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<UserJpa> query = criteriaBuilder.createQuery(UserJpa.class);
//        Root<UserJpa> root = query.from(UserJpa.class);
//
//        query.select(root).where(criteriaBuilder.equal(root.get("id"), id));
//        TypedQuery<UserJpa> typedQuery = em.createQuery(query);
//
//        return typedQuery.getSingleResult();
//    }
//
//    public void printUsers(){
//        EntityManager em = getEntityManager();
//
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<UserJpa> query = criteriaBuilder.createQuery(UserJpa.class);
//        Root<UserJpa> root = query.from(UserJpa.class);
//
//        query.select(root);
//        TypedQuery<UserJpa> typedQuery = em.createQuery(query);
//
//        int pageNumber = 1;
//        int pageSize = 10;
//
//        int count = typedQuery.getResultList().size();
//        while (pageNumber < count) {
//            typedQuery.setFirstResult(pageNumber - 1);
//            typedQuery.setMaxResults(pageSize);
//            System.out.println("Current page: " + typedQuery.getResultList());
//            pageNumber += pageSize;
//        }
//    }
//
//    @Transactional
//    public void remove(Long id) {
//        EntityManager em = getEntityManager();
////        em.getTransaction().begin();
////        UserJpa user = em.find(UserJpa.class, id);
////        em.remove(user);
////        em.getTransaction().commit();
//        em.getTransaction().begin();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaDelete<UserJpa> delete = cb.
//                createCriteriaDelete(UserJpa.class);
//        Root e = delete.from(UserJpa.class);
//        delete.where(cb.equal(e.get("id"), id));
//        em.createQuery(delete).executeUpdate();
//        em.getTransaction().commit();
//    }
//
//    public static void main(String[] args) {
//        UserJpa userJpa = UserJpa.builder().userName("danis_zam11").email("daniszam2bf1").password("qwefgbrty1").id(2).build();
//        UserRepositoryCriteria repository = new UserRepositoryCriteria();
//        repository.printUsers();
////        repository.save(userJpa);
////        userJpa.setEmail("22");
////        repository.remove(1L);
////        repository.getUser(2L);
////        repository.getUser(2L);
////        userJpa.setEmail("d");
////        repository.update(userJpa);
//    }
//}
