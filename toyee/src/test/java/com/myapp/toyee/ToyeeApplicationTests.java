package com.myapp.toyee;

import com.myapp.querydsl.entity.Hello;
import com.myapp.querydsl.entity.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Configuration
class ToyeeApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = new QHello("hello~");

		Hello result = query
				.selectFrom(qHello) //query와 관련된건 q타입을 넣어야 함
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(hello);
	}

}
