//package com.example.movierev;
//
//import jakarta.ejb.Stateless;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//@Stateless
//public class DatabaseTestBean {
//    @PersistenceContext(unitName = "PUnit")
//    private EntityManager entityManager;
//
//    public void testConnection() {
//        entityManager.createNativeQuery("SELECT 1").getResultList();
//    }
//}
