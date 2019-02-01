package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Post;

public class DbPost {
	public static void insert(Post post) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(post);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Post post) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.merge(post);
			trans.commit();
		} catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(Post post) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.remove(em.merge(post));
			trans.commit();
		} catch(Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<Post> post(){
		EntityManager em = DbUtil.getEntityManager("WebApp");
		String query = "SELECT p from Post p";
		
		List<Post> posts = null;
		
		try {
			TypedQuery<Post> typedQuery = em.createQuery(query, Post.class);
			posts = typedQuery.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return posts;
	}
	
	public static List<Post> postsOfUser(long userId){
		EntityManager em = DbUtil.getEntityManager("WebApp");
		String query = "select p from Post where p.user.user_id = :user_id";

		List<Post> userPosts = null;
		try {
			TypedQuery<Post> typedQuery = em.createQuery(query, Post.class);
			typedQuery.setParameter("user_id", userId);
			userPosts = typedQuery.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return userPosts;
	}
	
	public static List<Post> searchPosts(String search){
		EntityManager em = DbUtil.getEntityManager("WebApp");
		String query = "SELECT p FROM Post p where p.text like :search";
		
		List<Post> searchPosts = null;
		try {
			TypedQuery<Post> typedQuery = em.createQuery(query, Post.class);
			typedQuery.setParameter("search", "%"+search+"%");
			searchPosts = typedQuery.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return searchPosts;
	}
}






















