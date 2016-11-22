package jpa_spring.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import jpa_spring.Utils.EntityManagerUtil;
import jpa_spring.entity.Person;
import jpa_spring.form.PersonForm;

@Service
public class PersonService {

	public Person createPerson(PersonForm person) {
		EntityManager em = new EntityManagerUtil().getEntityManager();
		Person createPerson = null;
		try {
			createPerson = new Person();
			createPerson.setName(person.getName());
			createPerson.setAge(person.getAge());
			createPerson.setSex(person.getSex());
			em.persist(createPerson);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
		return createPerson;
	}

	public boolean deletePerson(int id) {
		EntityManager em = new EntityManagerUtil().getEntityManager();
		Person deletePerson = null;
		boolean result = false;
		try {
			em.getTransaction().begin();
			deletePerson = (Person) em.createNamedQuery("Person.findById").setParameter("id", id).getSingleResult();
			em.remove(deletePerson);
			em.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPersonList() {
		EntityManager em = new EntityManagerUtil().getEntityManager();
		List<Person> personList = null;
		try {
			em.getTransaction().begin();
			personList = em.createNamedQuery("Person.findAll").getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
		return personList;
	}

	public Person getPerson(int id) {
		EntityManager em = new EntityManagerUtil().getEntityManager();
		Person person = null;
		try {
			em.getTransaction().begin();
			person = (Person) em.createNamedQuery("Person.findById").setParameter("id", id).getSingleResult();
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
		return person;
	}

	public boolean updatePersonInfo(PersonForm person) {
		EntityManager em = new EntityManagerUtil().getEntityManager();
		boolean result = false;
		Person newPerson = null;
		try {
			em.getTransaction().begin();
			newPerson = (Person) em.createNamedQuery("Person.findById").setParameter("id", person.getId())
					.getSingleResult();
			newPerson.setName(person.getName());
			newPerson.setAge(person.getAge());
			newPerson.setSex(person.getSex());
			em.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.clear();
		}
		System.out.println(result);
		return result;
	}
}