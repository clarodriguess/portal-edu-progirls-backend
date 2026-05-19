package br.com.progirls.api.portal.model.dto.referencia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReferenciaExternaReponseDTOTest {

	@Test
	@DisplayName("Deve criar o DTO e expor os campos corretamente")
	void deveCriarDtoEExporCampos() {
		ReferenciaExternaReponseDTO dto = new ReferenciaExternaReponseDTO(
				1L,
				"Documentação Spring",
				"Guia oficial do Spring Framework",
				"https://spring.io"
		);

		assertThat(dto.id()).isEqualTo(1L);
		assertThat(dto.titulo()).isEqualTo("Documentação Spring");
		assertThat(dto.descricao()).isEqualTo("Guia oficial do Spring Framework");
		assertThat(dto.link()).isEqualTo("https://spring.io");
	}

	@Test
	@DisplayName("Deve implementar equals e hashCode via semântica de record")
	void deveImplementarEqualsEHashCode() {
		ReferenciaExternaReponseDTO dto1 = new ReferenciaExternaReponseDTO(
				1L,
				"Documentação Spring",
				"Guia oficial do Spring Framework",
				"https://spring.io"
		);
		ReferenciaExternaReponseDTO dto2 = new ReferenciaExternaReponseDTO(
				1L,
				"Documentação Spring",
				"Guia oficial do Spring Framework",
				"https://spring.io"
		);

		assertThat(dto1).isEqualTo(dto2);
		assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
	}

	@Test
	@DisplayName("Deve gerar toString contendo o nome do record e valores")
	void deveGerarToString() {
		ReferenciaExternaReponseDTO dto = new ReferenciaExternaReponseDTO(
				1L,
				"Documentação Spring",
				"Guia oficial do Spring Framework",
				"https://spring.io"
		);

		assertThat(dto.toString())
				.contains("ReferenciaExternaReponseDTO")
				.contains("Documentação Spring")
				.contains("https://spring.io");
	}
}