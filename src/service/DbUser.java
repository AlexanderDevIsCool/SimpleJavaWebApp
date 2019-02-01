package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.User;
import service.util.MD5Util;

public class DbUser {

	public static User getUser(long id) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		return em.find(User.class, id);
	}

	public static String getGravatarURL(String email, Integer size) {
		StringBuilder url = new StringBuilder();
		url.append("http://www.gravatar.com/avatar/");
		url.append(MD5Util.md5Hex(email));
		url.append("?s=" + size.toString());
		return url.toString();
	}

	public static void insert(User user) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(User user) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(User user) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static User getUserByEmail(String email) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		String query = " Select u from User u where u.email =:email";
		TypedQuery<User> typedQuery = em.createQuery(query, User.class);
		typedQuery.setParameter("email", email);

		User user = null;

		try {
			user = typedQuery.getSingleResult();
			System.out.println(user.getName());
		} catch (NoResultException e) {
			System.out.println("User dosen't exist");
		} finally {
			em.close();
		}

		return user;
	}

	public static boolean isValidUser(String email, String password) {
		EntityManager em = DbUtil.getEntityManager("WebApp");
		String query = "Select count(u.id) from User u where u.email = :email and u.password = :pass";
		TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
		typedQuery.setParameter("email", email);
		typedQuery.setParameter("pass", password);

		boolean result = false;

		try {
			long id = typedQuery.getSingleResult();
			if (id > 0) {
				result = true;
			}

		} catch (Exception e) {
			result = false;
		} finally {
			em.close();
		}

		return result;
	}

}
