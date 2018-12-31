package com.example.bootifulazure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
class SqlServerDemo {

		private final JdbcTemplate jdbcTemplate;

		SqlServerDemo(JdbcTemplate jdbcTemplate) {
				this.jdbcTemplate = jdbcTemplate;
		}

		@EventListener(ApplicationReadyEvent.class)
		public void demo() {
				String query = "select TOP 3  * from customer ";
				RowMapper<Customer> rowMapper =
					(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
				List<Customer> customerList = this.jdbcTemplate.query(query, rowMapper);
				customerList.forEach(log::info);
		}

		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		private static class Customer {
				private Long id;
				private String firstName, lastName;
		}
}
