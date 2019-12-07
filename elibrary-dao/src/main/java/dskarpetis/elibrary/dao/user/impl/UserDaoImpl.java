/**
\ * UserDaoImpl.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.dao.user.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dskarpetis.elibrary.dao.user.UserDao;
import dskarpetis.elibrary.domain.Gender;
import dskarpetis.elibrary.domain.Role;
import dskarpetis.elibrary.domain.UserData;
import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.util.HibernateUtil;

/**
 * Implementation for UserDao
 * 
 * @author dskarpetis
 */
public class UserDaoImpl implements UserDao {
	@SuppressWarnings("unused")
	private final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	// builds a session factory from the service registry
	private SessionFactory sessFac;
	// obtains the session
	private Session session;
	private Transaction tx;

	private void openSession() {
		// builds a session factory from the service registry
		sessFac = HibernateUtil.getSessionFactory();
		// obtains the session
		session = sessFac.openSession();
		tx = session.beginTransaction();
	}

	public UserLogin getUserBy(String username) {
		UserLogin userLogin;
		try {
			openSession();
			String hql = "from UserLogin where username=:username";
			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			userLogin = (UserLogin) query.uniqueResult();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.disconnect();
		}
		return userLogin;
	}

	public UserData getUserByEmail(String email) {
		UserData userData;
		try {
			openSession();
			String hql = "from UserData where email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			userData = (UserData) query.uniqueResult();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return userData;
	}

	public void addUser(Role role, UserLogin userLogin, UserData userData) {
		try {
			openSession();
			userLogin.setRoleID(role.getRoleID());
			session.save(userLogin);
			userData.setUserLogin(userLogin);
			session.save(userData);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gender> getGenders() {
		List<Gender> genders = new ArrayList<Gender>();
		try {
			openSession();
			genders = (List<Gender>) session.createQuery("from Gender").list();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return genders;
	}

	@SuppressWarnings("unchecked")
	public List<UserLogin> users() {

		List<UserLogin> users = new ArrayList<UserLogin>();
		// roleID for simple users
		int roleID = 1;
		String hql = null;
		try {
			openSession();
			hql = "from UserLogin where roleID=:roleID";
			Query query = session.createQuery(hql);
			query.setParameter("roleID", roleID);
			// return a list of books
			users = query.list();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	public void removeUser(UserLogin userLogin) {
		try {
			openSession();

			String hql = "delete from UserData where userLogin=:userLogin";
			Query query = session.createQuery(hql);
			query.setParameter("userLogin", userLogin);
			@SuppressWarnings("unused")
			int deleted = query.executeUpdate();
			session.delete(userLogin);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
