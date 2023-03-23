package co.kr.minzero.dev.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePartEvent;
import org.springframework.http.codec.multipart.FormPartEvent;
import org.springframework.http.codec.multipart.PartEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@WebFluxTest
public class FrontRestControllerTest {

    @Autowired
    FrontRestController frontRestController;

    @Autowired
    WebTestClient client;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUploadUsingPartEvents() {
        this.client
            .mutateWith(csrf())
            .post()
            .uri("/file-upload-part-events")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(
                Flux.concat(
                    FormPartEvent.create("name", "test"),
                    FormPartEvent.create("name1", "test1"),
                    FormPartEvent.create("name2", "test2"),
                    FilePartEvent.create("file", new ClassPathResource("erd_dcxper.png"))
                ),
                PartEvent.class
            )
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(String.class).hasSize(4);
    }

}
